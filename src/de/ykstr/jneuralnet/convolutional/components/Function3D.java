package de.ykstr.jneuralnet.convolutional.components;
@FunctionalInterface
public interface Function3D {
    public void apply(int x, int y, int z, double value);
}
