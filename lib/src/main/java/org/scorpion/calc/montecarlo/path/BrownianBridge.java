package org.scorpion.calc.montecarlo.path;

import com.google.common.base.Preconditions;
import org.scorpion.calc.montecarlo.PathGenerator;
import org.scorpion.calc.montecarlo.RandomNumberGenerator;
import org.scorpion.calc.montecarlo.rng.ParkMillerRandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class BrownianBridge implements PathGenerator {

    private long numberOfTimeSteps;
    private double expiry;
    private RandomNumberGenerator rng;

    /* ---- this is the brownian bridge expectation and variance parameters ---- */
    private List<Double> time = new ArrayList<>();
    private List<Double> a1 = new ArrayList<>();
    private List<Double> a2 = new ArrayList<>();
    private List<Double> b = new ArrayList<>();
    /* ---- this is the brownian bridge expectation and variance parameters ---- */

    public BrownianBridge(long numberOfTimeSteps, double expiry) {
        this.numberOfTimeSteps = numberOfTimeSteps;
        this.expiry = expiry;

        this.rng = new ParkMillerRandomNumberGenerator(1L, 1L);
        rng.skip(1L);

        calcPara();
    }

    private void calcPara() {
	    for (int i = 0; i < numberOfTimeSteps; ++i) {
	        time.add((1d + i) * expiry / numberOfTimeSteps);
        }

        a1.add(0d);
        a2.add(time.get(0) / expiry);
        b.add(Math.sqrt((expiry - time.get(0)) * time.get(0) / expiry));

        for (int i = 1; i < numberOfTimeSteps; ++i) {
            a1.add((expiry - time.get(i)) / (expiry - time.get(i - 1)));
            a2.add((expiry / numberOfTimeSteps) / (expiry - time.get((i - 1))));
            b.add(Math.sqrt((expiry - time.get(i)) * (expiry / numberOfTimeSteps) / (expiry - time.get(i - 1))));
        }
    }

    @Override
    public void generateOneBrownianPath(List<Double> path) {
        int length = path.size();
        Preconditions.checkArgument(length == numberOfTimeSteps);
        Double terminal = path.get(length - 1);
        Double random = rng.getGaussianNumbers().get(0);
        Double tempRes = a2.get(0) * terminal + b.get(0) * random;
        path.set(0, tempRes);
        for (int i = 1; i < numberOfTimeSteps; ++i) {
            random = rng.getGaussianNumbers().get(0);
            tempRes = path.get(i - 1) * a1.get(i) + terminal * a2.get(i) + b.get(i) * random;
            path.set(i, tempRes);
        }
        for (int i = (int) numberOfTimeSteps - 1; i > 0; --i) {
            tempRes = (path.get(i) - path.get(i - 1)) / Math.sqrt(1d / numberOfTimeSteps);
            path.set(i, tempRes);
        }
        tempRes = path.get(0) / Math.sqrt(1d / numberOfTimeSteps);
        path.set(0, tempRes);
    }
}
