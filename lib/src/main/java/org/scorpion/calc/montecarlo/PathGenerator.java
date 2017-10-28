package org.scorpion.calc.montecarlo;

import java.util.List;

public interface PathGenerator {

    void generateOneBrownianPath(List<Double> path);

}
