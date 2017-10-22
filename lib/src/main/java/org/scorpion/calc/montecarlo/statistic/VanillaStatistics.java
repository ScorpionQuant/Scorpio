package org.scorpion.calc.montecarlo.statistic;

import org.scorpion.calc.montecarlo.MCSinglePathResult;
import org.scorpion.calc.montecarlo.MCStatistics;

import java.util.List;

public class VanillaStatistics extends MCStatistics {

    public VanillaStatistics(List<MCSinglePathResult> singlePathResults) {
        super(singlePathResults);
    }

    @Override
    public Double value() {
        List<MCSinglePathResult> resList = getSinglePathResults();
        return resList.stream()
                .map(MCSinglePathResult::getSinglePathResult)
                .reduce(0d, (x, y) -> (x + y)) / resList.size();
    }
}
