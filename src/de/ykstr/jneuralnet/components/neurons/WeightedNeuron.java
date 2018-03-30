package de.ykstr.jneuralnet.components.neurons;

import de.ykstr.jneuralnet.functions.IActivationFunction;

import java.util.HashMap;
import java.util.Map;

public class WeightedNeuron extends Neuron{
    private HashMap<Neuron, Double> weightedInputs;

    public WeightedNeuron(IActivationFunction f) {
        super(f);
    }



    @Override
    public double calculateValue() {
        double result = 0;
        for(Map.Entry<Neuron, Double> entry : weightedInputs.entrySet()){
            result+= entry.getKey().calculate()*entry.getValue();
        }
        return result;
    }
}
