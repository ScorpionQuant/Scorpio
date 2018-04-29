package org.scorpion.calc.montecarlo.variancereduction;

import java.util.List;

public interface StratifiedSampling {

    List<Double> getTerminalValue(long numberOfStrata);

}
