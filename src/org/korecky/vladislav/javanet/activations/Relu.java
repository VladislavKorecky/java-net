package org.korecky.vladislav.javanet.activations;

public class Relu implements ActivationFunction {
    @Override
    public float run(float x) {
        return Math.max(0, x);
    }
}
