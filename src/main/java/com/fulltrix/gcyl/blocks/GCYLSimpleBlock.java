package com.fulltrix.gcyl.blocks;

import gregtech.api.block.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class GCYLSimpleBlock extends VariantBlock<GCYLSimpleBlock.BlockType> {


    public GCYLSimpleBlock() {
        super(Material.IRON);
        setTranslationKey("gcyl_simple_block");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(BlockType.SUPERHEAVY_BLOCK));
    }

    public enum BlockType implements IStringSerializable {

        SUPERHEAVY_BLOCK("superheavy_block");


        private final String name;

        BlockType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

    }
}