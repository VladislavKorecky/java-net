package org.korecky.vladislav.javanet;

import org.korecky.vladislav.javanet.activations.ActivationFunction;

public class Neuron {
    private float value;
    private float[] weights;
    private float bias;

    public Neuron(int inputSize) {
        this.value = bias;

        this.weights = new float[inputSize];
    }

    public void calculateValue(float[] input, ActivationFunction activationFunction) {
        // check for invalid input
        if (input.length != weights.length) {
            throw new IllegalArgumentException("Neuron input has to be the same length as it's weights");
        }

        // reset the value and add the bias
        value = bias;

        // multiply the weights
        for (int i = 0; i < input.length; i++) {
            value += input[i] * weights[i];
        }

        // apply the activation function
        value = activationFunction.run(value);
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float[] getWeights() {
        return weights;
    }

    public void setWeights(float[] weights) {
        this.weights = weights;
    }

    public float getBias() {
        return bias;
    }

    public void setBias(float bias) {
        this.bias = bias;
    }
}
