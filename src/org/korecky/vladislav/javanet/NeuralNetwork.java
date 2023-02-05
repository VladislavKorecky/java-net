package org.korecky.vladislav.javanet;

import org.korecky.vladislav.javanet.activations.ActivationFunction;

import java.util.Arrays;

public class NeuralNetwork implements Cloneable {
    private Layer[] layers;
    private final int[] topology;

    /**
     * Create an empty neural network.
     *
     * @param topology Architecture of the network. Each item represents a layer and the value is the amount of neurons.
     */
    public NeuralNetwork(int[] topology, ActivationFunction[] activations) {
        if (topology.length != activations.length) {
            throw new IllegalArgumentException("The length of topology and activations must be the same.");
        }

        this.topology = topology;

        layers = new Layer[topology.length];

        // init/create the layers
        int previousLayerSize = 0;
        for (int i = 0; i < layers.length; i++) {
            layers[i] = new Layer(topology[i], previousLayerSize, activations[i]);
            previousLayerSize = topology[i];
        }
    }

    public float[] forward(float[] input) {
        // set the first layer
        Layer inputLayer = layers[0];
        inputLayer.setValues(input);

        // iterate through layers and pass the previous values to it
        for (int i = 1; i < layers.length; i++) {
            Layer layer = layers[i];
            float[] previousValues = layers[i - 1].getValues();

            layer.pass(previousValues);
        }

        // return the values of the last layer (a.k.a. the output layer)
        return layers[layers.length - 1].getValues();
    }

    @Override
    public NeuralNetwork clone() {
        NeuralNetwork clone;

        try {
            // make a shallow copy of the object (super.clone() -> Object.clone())
            clone = (NeuralNetwork) super.clone();
        } catch (CloneNotSupportedException e) {
            // this will happen if the class doesn't implement Cloneable (a.k.a. never)
            throw new AssertionError();
        }

        // make a deep copy of the neurons
        clone.layers = new Layer[layers.length];

        for (int i = 0; i < layers.length; i++) {
            clone.layers[i] = layers[i].clone();
        }

        return clone;
    }

    public void adjust(float maxChange) {
        float maxChangePerLayer = maxChange / layers.length;

        for (Layer layer : layers) {
            layer.adjust(maxChangePerLayer);
        }
    }
}
