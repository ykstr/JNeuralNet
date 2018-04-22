package de.ykstr.jneuralnet.convolutional.components;

public class ReLU {

    double[][][] matrix;

    public ReLU(ConvolutionalLayer previous){
        matrix = new double[previous.getWidth()][previous.getHeight()][previous.getDepth()];
        previous.iterate((x, y, z, value) -> matrix[x][y][z] = value<0?0:value);
    }
}
