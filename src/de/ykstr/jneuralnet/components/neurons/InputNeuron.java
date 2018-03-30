package de.ykstr.jneuralnet.components.neurons;

import de.ykstr.jneuralnet.functions.IActivationFunction;

public class InputNeuron extends Neuron{
    public InputNeuron(IActivationFunction f, double value) {
        super(f);
        setValue(value);
    }
}
