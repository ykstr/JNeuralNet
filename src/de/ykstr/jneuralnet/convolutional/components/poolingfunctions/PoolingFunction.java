package de.ykstr.jneuralnet.convolutional.components.poolingfunctions;

@FunctionalInterface
public interface PoolingFunction {
    public double pool(double[][][] input);
}
