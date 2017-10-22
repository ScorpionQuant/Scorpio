package org.scorpion.calc.montecarlo;

import java.util.List;

public abstract class MCStatistics {

    private List<MCSinglePathResult> singlePathResults;

    public MCStatistics(List<MCSinglePathResult> singlePathResults) {
        this.singlePathResults = singlePathResults;
    }

    public List<MCSinglePathResult> getSinglePathResults() {
        return singlePathResults;
    }

    public abstract Double value();

}
