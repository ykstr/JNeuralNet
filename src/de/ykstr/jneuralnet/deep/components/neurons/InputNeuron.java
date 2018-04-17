package de.ykstr.jneuralnet.deep.components.neurons;

import de.ykstr.jneuralnet.deep.functions.IActivationFunction;

public class InputNeuron extends Neuron{
    public InputNeuron(IActivationFunction f, double value) {
        super(f);
        setValue(value);
    }
}
