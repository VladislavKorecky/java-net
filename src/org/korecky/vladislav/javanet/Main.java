package org.korecky.vladislav.javanet;


import org.korecky.vladislav.javanet.activations.ActivationFunction;
import org.korecky.vladislav.javanet.activations.LeakyRelu;
import org.korecky.vladislav.javanet.activations.Linear;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        NeuralNetwork net = new NeuralNetwork(
                new int[]{2, 2, 1}, new ActivationFunction[]{null, new LeakyRelu(), new Linear()}
        );
        float[] output = net.forward(new float[]{1, 0});
        System.out.println(Arrays.toString(output));
    }
}