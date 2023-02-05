package org.korecky.vladislav.javanet;


import org.korecky.vladislav.javanet.activations.ActivationFunction;
import org.korecky.vladislav.javanet.activations.LeakyRelu;
import org.korecky.vladislav.javanet.activations.Linear;


public class Main {
    public static void main(String[] args) {
        NeuralNetwork net = new NeuralNetwork(
                new int[]{2, 2, 1}, new ActivationFunction[]{null, new LeakyRelu(), new Linear()}
        );
        NeuralNetwork net2 = net.clone();
        System.out.println(net != net2);
    }
}