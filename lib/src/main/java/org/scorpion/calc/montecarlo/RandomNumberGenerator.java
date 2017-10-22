package org.scorpion.calc.montecarlo;

import org.scorpion.calc.math.NormalDistribution;

import java.util.List;
import java.util.stream.Collectors;

public interface RandomNumberGenerator {

    /* When reset the dimensionality, basically I will reset the seed of inner engine */
    void resetDimensionality(long dimensionality);

    /* Reset the initial seed of the random number generator and the inner generator */
    void resetInitialSeed(long initialSeed);

    /* Reset the initial seed of the inner generator */
    void reset();

    List<Double> getUniforms();

    /* Let's skip some certain paths */
    void skip(long numberOfPaths);

    default List<Double> getGaussianNumbers() {
        List<Double> uniforms = getUniforms();
        return uniforms.stream().map(NormalDistribution::inverseCumulativeNormal).collect(Collectors.toList());
    }
}
