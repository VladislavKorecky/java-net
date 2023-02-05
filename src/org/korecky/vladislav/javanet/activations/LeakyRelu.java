package org.korecky.vladislav.javanet.activations;

public class LeakyRelu implements ActivationFunction {
    @Override
    public float run(float x) {
        return x > 0 ? x : (float) (0.01 * x);
    }
}
