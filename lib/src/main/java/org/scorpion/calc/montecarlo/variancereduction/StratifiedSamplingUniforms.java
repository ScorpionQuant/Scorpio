package org.scorpion.calc.montecarlo.variancereduction;

import com.google.common.base.Preconditions;
import org.scorpion.calc.math.NormalDistribution;
import org.scorpion.calc.montecarlo.RandomNumberGenerator;
import org.scorpion.calc.montecarlo.rng.ParkMillerRandomNumberGenerator;

import java.util.List;

public class StratifiedSamplingUniforms implements StratifiedSampling {

    private long numberOfPaths;
    private RandomNumberGenerator rng;

    public StratifiedSamplingUniforms(long numberOfPaths, long seed) {
        this.numberOfPaths = numberOfPaths;
        this.rng = new ParkMillerRandomNumberGenerator(numberOfPaths, seed);
    }

    @Override
    public List<Double> getTerminalValue(long numberOfStrata) {
        /* This precondition is decided by me, not math or method */
        Preconditions.checkArgument(
                numberOfPaths % numberOfStrata == 0d,
                "In uniform method, the strata number should be dividend of path numbers "
        );
        List<Double> res = rng.getUniforms();
        long strataStep = numberOfPaths / numberOfStrata;
        int iterator = 0;
        double temp;
        for (int i = 0; i < numberOfStrata; ++i) {
            for (int j = 0; j < strataStep; ++j) {
                /* Note, here is easy to wrong */
                temp = (res.get(iterator) + i * 1.0) / numberOfStrata;
                res.set(iterator, NormalDistribution.inverseCumulativeNormal(temp));
                ++iterator;
            }
        }
        return res;
    }
}
