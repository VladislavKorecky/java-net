package org.korecky.vladislav.javanet;

import org.korecky.vladislav.javanet.activations.ActivationFunction;
import org.korecky.vladislav.javanet.activations.LeakyRelu;
import org.korecky.vladislav.javanet.activations.Linear;

public class XORTraining {
    public static float getError(NeuralNetwork net, float[][] dataset, float[] labelset) {
        float error = 0;

        for (int i = 0; i < dataset.length; i++) {
            float[] data = dataset[i];
            float label = labelset[i];

            float[] output = net.forward(data);
            float prediction = output[0];

            error += Math.abs(label - prediction);
        }

        return error;
    }

    public static void main(String[] args) {
        // create the training set
        float[][] dataset = new float[][]{
                {0, 0},
                {1, 1},
                {0, 1},
                {1, 0}
        };
        float[] labelset = new float[] {
                0,
                0,
                1,
                1
        };

        // create the network
        NeuralNetwork net = new NeuralNetwork(
                new int[]{2, 2, 1}, new ActivationFunction[]{null, new LeakyRelu(), new Linear()}
        );
        float error = XORTraining.getError(net, dataset, labelset);



        while (true) {
            NeuralNetwork netCopy = net.clone();
            netCopy.adjust(10f);

            float newError = XORTraining.getError(netCopy, dataset, labelset);

            if (newError < error) {
                net = netCopy;
                error = newError;

                System.out.println(error);
            }
        }
    }
}
