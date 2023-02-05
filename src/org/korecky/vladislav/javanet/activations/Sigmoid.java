package org.korecky.vladislav.javanet.activations;

public class Sigmoid implements ActivationFunction {
    @Override
    public float run(float x) {
        return (float) (1 / (1 + Math.exp(-x)));
    }
}
