package com.fulltrix.gcyl.machines.multi.advance;

import appeng.core.AEConfig;
import appeng.core.features.AEFeature;
import com.fulltrix.gcyl.api.multi.GCYLCleanroomType;
import com.fulltrix.gcyl.blocks.GCYLMetaBlocks;
import com.fulltrix.gcyl.blocks.metal.GCYLCleanroomCasing;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.CleanroomLogic;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.metatileentity.multiblock.ICleanroomProvider;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.*;
import gregtech.api.util.BlockInfo;
import gregtech.api.util.GTUtility;
import gregtech.api.util.Mods;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockCleanroomCasing;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityCleanroom;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.fulltrix.gcyl.api.pattern.TraceabilityPredicates.filterCasings;

//TODO: Decrease tps lag when they try to cheat it
public class MetaTileEntityMegaCleanroom extends MetaTileEntityCleanroom  implements ICleanroomProvider {
    public static final int MIN_RADIUS = 10;
    public static final int MIN_DEPTH = 9;
    private int lDist = 0;
    private int rDist = 0;
    private int bDist = 0;
    private int fDist = 0;
    private int hDist = 0;
    private final CleanroomLogic cleanroomLogic;
    private CleanroomType cleanroomType = null;

    private IEnergyContainer energyContainer;
    private boolean initialForm = true;
    public MetaTileEntityMegaCleanroom(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.cleanroomLogic = new CleanroomLogic(this, GTValues.LV);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityMegaCleanroom(metaTileEntityId);
    }

    @Override
    public boolean updateStructureDimensions() {
        World world = getWorld();
        EnumFacing front = getFrontFacing();
        EnumFacing back = front.getOpposite();
        EnumFacing left = front.rotateYCCW();
        EnumFacing right = left.getOpposite();

        BlockPos.MutableBlockPos lPos = new BlockPos.MutableBlockPos(getPos());
        BlockPos.MutableBlockPos rPos = new BlockPos.MutableBlockPos(getPos());
        BlockPos.MutableBlockPos fPos = new BlockPos.MutableBlockPos(getPos());
        BlockPos.MutableBlockPos bPos = new BlockPos.MutableBlockPos(getPos());
        BlockPos.MutableBlockPos hPos = new BlockPos.MutableBlockPos(getPos());

        // find the distances from the controller to the plascrete blocks on one horizontal axis and the Y axis
        // repeatable aisles take care of the second horizontal axis
        int lDist = 0;
        int rDist = 0;
        int bDist = 0;
        int fDist = 0;
        int hDist = 0;

        // find the left, right, back, and front distances for the structure pattern
        // maximum size is 15x15x15 including walls, so check 7 block radius around the controller for blocks
        for (int i = 1; i < 51; i++) {
            if (lDist == 0 && isBlockEdge(world, lPos, left)) lDist = i;
            if (rDist == 0 && isBlockEdge(world, rPos, right)) rDist = i;
            if (bDist == 0 && isBlockEdge(world, bPos, back)) bDist = i;
            if (fDist == 0 && isBlockEdge(world, fPos, front)) fDist = i;
            if (lDist != 0 && rDist != 0 && bDist != 0 && fDist != 0) break;
        }

        // height is diameter instead of radius, so it needs to be done separately
        for (int i = 1; i < 101; i++) {
            if (isBlockFloor(world, hPos, EnumFacing.DOWN)) hDist = i;
            if (hDist != 0) break;
        }

        if (lDist < MIN_RADIUS || rDist < MIN_RADIUS || bDist < MIN_RADIUS || fDist < MIN_RADIUS || hDist < MIN_DEPTH) {
            invalidateStructure();
            return false;
        }

        this.lDist = lDist;
        this.rDist = rDist;
        this.bDist = bDist;
        this.fDist = fDist;
        this.hDist = hDist;

        writeCustomData(GregtechDataCodes.UPDATE_STRUCTURE_SIZE, buf -> {
            buf.writeInt(this.lDist);
            buf.writeInt(this.rDist);
            buf.writeInt(this.bDist);
            buf.writeInt(this.fDist);
            buf.writeInt(this.hDist);
        });
        return true;
    }

    /*
    @Override
    public void update() {
        if (getOffsetTimer() % 100 == 0) {
            super.update();
        }
    }
     */
    @SubscribeEvent
    public void checkStructurePatternSave(WorldEvent.Save event) {
        this.initialForm = true;
        super.checkStructurePattern();
    }

    @Override
    public void checkStructurePattern() {
        if((initialForm) || !isStructureFormed()) {
            this.initialForm = false;
            super.checkStructurePattern();
        }
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
        Object type = context.get("FilterType");
        if (type instanceof BlockCleanroomCasing.CasingType) {
            BlockCleanroomCasing.CasingType casingType = (BlockCleanroomCasing.CasingType) type;

            if (casingType.equals(BlockCleanroomCasing.CasingType.FILTER_CASING)) {
                this.cleanroomType = CleanroomType.CLEANROOM;
            } else if (casingType.equals(BlockCleanroomCasing.CasingType.FILTER_CASING_STERILE)) {
                this.cleanroomType = CleanroomType.STERILE_CLEANROOM;
            }
        }

        if (type instanceof GCYLCleanroomCasing.CasingType) {
            GCYLCleanroomCasing.CasingType casingType = (GCYLCleanroomCasing.CasingType) type;

            if (casingType.equals(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO3)) {
                this.cleanroomType = GCYLCleanroomType.ISO3;
            } else if (casingType.equals(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO2)) {
                this.cleanroomType = GCYLCleanroomType.ISO2;
            } else if (casingType.equals(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO1)) {
                this.cleanroomType = GCYLCleanroomType.ISO1;
            } else if (casingType.equals(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO0)) {
                this.cleanroomType = GCYLCleanroomType.ISO0;
            }

        }
        // max progress is based on the dimensions of the structure: (x^3)-(x^2)
        // taller cleanrooms take longer than wider ones
        // minimum of 100 is a 5x5x5 cleanroom: 125-25=100 ticks
        this.cleanroomLogic.setMaxProgress(Math.max(100,
                ((lDist + rDist + 1) * (bDist + fDist + 1) * hDist) - ((lDist + rDist + 1) * (bDist + fDist + 1))));
    }

    @Override
    public boolean checkCleanroomType(@NotNull CleanroomType type) {
        return type == this.cleanroomType;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern() {
        // return the default structure, even if there is no valid size found
        // this means auto-build will still work, and prevents terminal crashes.
        if (getWorld() != null) updateStructureDimensions();

        // these can sometimes get set to 0 when loading the game, breaking JEI
        if (lDist < MIN_RADIUS) lDist = MIN_RADIUS;
        if (rDist < MIN_RADIUS) rDist = MIN_RADIUS;
        if (bDist < MIN_RADIUS) bDist = MIN_RADIUS;
        if (fDist < MIN_RADIUS) fDist = MIN_RADIUS;
        if (hDist < MIN_DEPTH) hDist = MIN_DEPTH;

        if (this.frontFacing == EnumFacing.EAST || this.frontFacing == EnumFacing.WEST) {
            int tmp = lDist;
            lDist = rDist;
            rDist = tmp;
        }

        // build each row of the structure
        StringBuilder borderBuilder = new StringBuilder();     // BBBBB
        StringBuilder wallBuilder = new StringBuilder();       // BXXXB
        StringBuilder insideBuilder = new StringBuilder();     // X X
        StringBuilder roofBuilder = new StringBuilder();       // BFFFB
        StringBuilder controllerBuilder = new StringBuilder(); // BFSFB
        StringBuilder centerBuilder = new StringBuilder();     // BXKXB

        // everything to the left of the controller
        for (int i = 0; i < lDist; i++) {
            borderBuilder.append("B");
            if (i == 0) {
                wallBuilder.append("B");
                insideBuilder.append("X");
                roofBuilder.append("B");
                controllerBuilder.append("B");
                centerBuilder.append("B");
            } else {
                insideBuilder.append(" ");
                wallBuilder.append("X");
                roofBuilder.append("F");
                controllerBuilder.append("F");
                centerBuilder.append("X");
            }
        }

        // everything in-line with the controller
        borderBuilder.append("B");
        wallBuilder.append("X");
        insideBuilder.append(" ");
        roofBuilder.append("F");
        controllerBuilder.append("S");
        centerBuilder.append("K");

        // everything to the right of the controller
        for (int i = 0; i < rDist; i++) {
            borderBuilder.append("B");
            if (i == rDist - 1) {
                wallBuilder.append("B");
                insideBuilder.append("X");
                roofBuilder.append("B");
                controllerBuilder.append("B");
                centerBuilder.append("B");
            } else {
                insideBuilder.append(" ");
                wallBuilder.append("X");
                roofBuilder.append("F");
                controllerBuilder.append("F");
                centerBuilder.append("X");
            }
        }

        // build each slice of the structure
        String[] wall = new String[hDist + 1]; // "BBBBB", "BXXXB", "BXXXB", "BXXXB", "BBBBB"
        Arrays.fill(wall, wallBuilder.toString());
        wall[0] = borderBuilder.toString();
        wall[wall.length - 1] = borderBuilder.toString();

        String[] slice = new String[hDist + 1]; // "BXXXB", "X X", "X X", "X X", "BFFFB"
        Arrays.fill(slice, insideBuilder.toString());
        slice[0] = wallBuilder.toString();
        slice[slice.length - 1] = roofBuilder.toString();

        String[] center = Arrays.copyOf(slice, slice.length); // "BXKXB", "X X", "X X", "X X", "BFSFB"
        if (this.frontFacing == EnumFacing.NORTH || this.frontFacing == EnumFacing.SOUTH) {
            center[0] = centerBuilder.reverse().toString();
            center[center.length - 1] = controllerBuilder.reverse().toString();
        } else {
            center[0] = centerBuilder.toString();
            center[center.length - 1] = controllerBuilder.toString();
        }

        TraceabilityPredicate wallPredicate = states(getCasingState(), getGlassState());
        TraceabilityPredicate basePredicate = autoAbilities().or(abilities(MultiblockAbility.INPUT_ENERGY)
                .setMinGlobalLimited(1).setMaxGlobalLimited(3));

        // layer the slices one behind the next
        return FactoryBlockPattern.start()
                .aisle(wall)
                .aisle(slice).setRepeatable(bDist - 1)
                .aisle(center)
                .aisle(slice).setRepeatable(fDist - 1)
                .aisle(wall)
                .where('S', selfPredicate())
                .where('B', states(getCasingState()).or(basePredicate))
                .where('X', wallPredicate.or(basePredicate)
                        .or(doorPredicate().setMaxGlobalLimited(8))
                        .or(abilities(MultiblockAbility.PASSTHROUGH_HATCH).setMaxGlobalLimited(30)))
                .where('K', wallPredicate) // the block beneath the controller must only be a casing for structure
                // dimension checks
                .where('F', filterCasings())
                .where(' ', innerPredicate())
                .build();
    }

    @Override
    public int getEnergyTier() {
        return this.energyContainer == null ? 1 : Math.max(1, GTUtility.getFloorTierByVoltage(this.energyContainer.getInputVoltage()));
    }

    @Override
    public long getEnergyInputPerSecond() {
        return this.energyContainer.getInputPerSec();
    }

    @Override
    public boolean drainEnergy(boolean simulate) {
        if(isStructureFormed()) {
            long energyToDrain = this.isClean() ? (long) Math.max(4.0, Math.pow(4.0, this.getEnergyTier())) : (long) GTValues.VA[this.getEnergyTier()];
            long resultEnergy = this.energyContainer.getEnergyStored() - energyToDrain;
            if (resultEnergy >= 0L && resultEnergy <= this.energyContainer.getEnergyCapacity()) {
                if (!simulate) {
                    this.energyContainer.changeEnergy(-energyToDrain);
                }

                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    protected void initializeAbilities() {
        this.energyContainer = new EnergyContainerList(this.getAbilities(MultiblockAbility.INPUT_ENERGY));
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXIHLXXXXXXXXX", "XXXXXXXXXXDXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFSFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXXXXXXXXXXXXX", "X                   X", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "G                   G", "X                   X", "XFFFFFFFFFFFFFFFFFFFX")
                .aisle("XXXXXXXXXMXEXXXXXXXXX", "XXXXXXXXXXOXXXXXXXXXX", "XXXXXXXXXXRXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXX")
                .where('X', MetaBlocks.CLEANROOM_CASING.getState(BlockCleanroomCasing.CasingType.PLASCRETE))
                .where('G', MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.CLEANROOM_GLASS))
                .where('S', MetaTileEntities.CLEANROOM, EnumFacing.SOUTH)
                .where(' ', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.ZPM], EnumFacing.SOUTH)
                .where('I', MetaTileEntities.PASSTHROUGH_HATCH_ITEM, EnumFacing.NORTH)
                .where('L', MetaTileEntities.PASSTHROUGH_HATCH_FLUID, EnumFacing.NORTH)
                .where('H', MetaTileEntities.HULL[GTValues.HV], EnumFacing.NORTH)
                .where('D', MetaTileEntities.DIODES[GTValues.HV], EnumFacing.NORTH)
                .where('M',
                        () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH :
                                MetaBlocks.CLEANROOM_CASING.getState(BlockCleanroomCasing.CasingType.PLASCRETE),
                        EnumFacing.SOUTH)
                .where('O',
                        Blocks.IRON_DOOR.getDefaultState().withProperty(BlockDoor.FACING, EnumFacing.NORTH)
                                .withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER))
                .where('R', Blocks.IRON_DOOR.getDefaultState().withProperty(BlockDoor.FACING, EnumFacing.NORTH)
                        .withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER));

        Arrays.stream(BlockCleanroomCasing.CasingType.values())
                .filter(casingType -> !casingType.equals(BlockCleanroomCasing.CasingType.PLASCRETE))
                .forEach(casingType -> shapeInfo
                        .add(builder.where('F', MetaBlocks.CLEANROOM_CASING.getState(casingType)).build()));

        Arrays.stream(GCYLCleanroomCasing.CasingType.values())
                .forEach(casingType -> shapeInfo
                        .add(builder.where('F', GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getState(casingType)).build()));

        return shapeInfo;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.machine.cleanroom.tooltip.1"));
        tooltip.add(I18n.format("gregtech.machine.cleanroom.tooltip.2"));
        tooltip.add(I18n.format("gregtech.machine.cleanroom.tooltip.3"));
        tooltip.add(I18n.format("gcyl.machine.cleanroom.tooltip.1"));
        tooltip.add(I18n.format("gcyl.machine.cleanroom.tooltip.2"));

        if (TooltipHelper.isCtrlDown()) {
            tooltip.add("");
            tooltip.add(I18n.format("gcyl.machine.cleanroom.tooltip.3"));
            tooltip.add(I18n.format("gcyl.machine.cleanroom.tooltip.4"));
            tooltip.add(I18n.format("gcyl.machine.cleanroom.tooltip.5"));
            tooltip.add(I18n.format("gcyl.machine.cleanroom.tooltip.6"));
            tooltip.add(I18n.format("gregtech.machine.cleanroom.tooltip.5"));
            tooltip.add(I18n.format("gregtech.machine.cleanroom.tooltip.6"));
            tooltip.add(I18n.format("gregtech.machine.cleanroom.tooltip.7"));
            tooltip.add(I18n.format("gregtech.machine.cleanroom.tooltip.8"));
            tooltip.add(I18n.format("gregtech.machine.cleanroom.tooltip.9"));
            if (Mods.AppliedEnergistics2.isModLoaded()) {
                tooltip.add(I18n.format(AEConfig.instance().isFeatureEnabled(AEFeature.CHANNELS) ?
                        "gregtech.machine.cleanroom.tooltip.ae2.channels" :
                        "gregtech.machine.cleanroom.tooltip.ae2.no_channels"));
            }
            tooltip.add("");
        } else {
            tooltip.add(I18n.format("gregtech.machine.cleanroom.tooltip.hold_ctrl"));
        }
    }


    /*
    @Override
    public NBTTagCompound writeToNBT(@NotNull NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("initialForm", this.initialForm);
        return this.cleanroomLogic.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.initialForm = data.hasKey("initialForm") ? data.getBoolean("initialForm") : this.initialForm;
        this.cleanroomLogic.readFromNBT(data);
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(this.initialForm);
        this.cleanroomLogic.writeInitialSyncData(buf);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.initialForm = buf.readBoolean();
        this.cleanroomLogic.receiveInitialSyncData(buf);
    }

     */



}
