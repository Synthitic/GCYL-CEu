package com.fulltrix.gcyl.api.util;

import java.util.List;

public interface IResearchContainer {
    public void setResearchId(List<String> inputResearchIds);

    public List<String> getResearchIds();

    public void clearResearch();
}
