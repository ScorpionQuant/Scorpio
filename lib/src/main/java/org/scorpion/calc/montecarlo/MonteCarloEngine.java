package org.scorpion.calc.montecarlo;

public interface MonteCarloEngine {

    MCSinglePathResult doOnePath () throws Exception;

    MCStatistics doSimulate() throws Exception;
    
}
