package org.korecky.vladislav.javanet;

import org.korecky.vladislav.javanet.activations.ActivationFunction;

public class NeuralNetwork {
    private final Layer[] layers;

    /**
     * Create an empty neural network.
     *
     * @param topology Architecture of the network. Each item represents a layer and the value is the amount of neurons.
     */
    public NeuralNetwork(int[] topology, ActivationFunction[] activations) {
        if (topology.length != activations.length) {
            throw new IllegalArgumentException("The length of topology and activations must be the same.");
        }

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
}
