package org.korecky.vladislav.javanet;

import org.korecky.vladislav.javanet.activations.ActivationFunction;

public class Layer implements Cloneable {
    private Neuron[] neurons;
    private ActivationFunction activationFunction;

    public Layer(int layerSize, int previousLayerSize, ActivationFunction activationFunction) {
        neurons = new Neuron[layerSize];
        this.activationFunction = activationFunction;

        // create the neurons
        for (int i = 0; i < layerSize; i++) {
            neurons[i] = new Neuron(previousLayerSize);
        }
    }

    public void pass(float[] input) {
        for (Neuron neuron : neurons) {
            neuron.calculateValue(input, activationFunction);
        }
    }

    public float[] getValues() {
        float[] output = new float[neurons.length];

        for (int i = 0; i < neurons.length; i++) {
            output[i] = neurons[i].getValue();
        }

        return output;
    }

    public void setValues(float[] values) {
        for (int i = 0; i < neurons.length; i++) {
            neurons[i].setValue(values[i]);
        }
    }

    @Override
    public Layer clone() {
        Layer clone;

        try {
            // make a shallow copy of the object (super.clone() -> Object.clone())
            clone = (Layer) super.clone();
        } catch (CloneNotSupportedException e) {
            // this will happen if the class doesn't implement Cloneable (a.k.a. never)
            throw new AssertionError();
        }

        // make a deep copy of the neurons
        clone.neurons = new Neuron[neurons.length];

        for (int i = 0; i < neurons.length; i++) {
            clone.neurons[i] = neurons[i].clone();
        }

        return clone;
    }

    public Neuron[] getNeurons() {
        return neurons;
    }

    public void setNeurons(Neuron[] neurons) {
        this.neurons = neurons;
    }

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    public void setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }
}
