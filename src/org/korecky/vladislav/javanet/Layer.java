package org.korecky.vladislav.javanet;

import org.korecky.vladislav.javanet.activations.ActivationFunction;

public class Layer {
    Neuron[] neurons;
    ActivationFunction activationFunction;

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
}
