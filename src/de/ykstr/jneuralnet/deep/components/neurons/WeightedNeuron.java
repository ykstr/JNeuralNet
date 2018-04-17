package de.ykstr.jneuralnet.deep.components.neurons;

import de.ykstr.jneuralnet.deep.functions.IActivationFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeightedNeuron extends Neuron{
    private HashMap<Neuron, Double> weightedInputs = new HashMap<>();

    public WeightedNeuron(IActivationFunction f) {
        super(f);
    }

    @Override
    public double calculateValue() {
        double result = 0;
        double weightSum = 0;
        for(Map.Entry<Neuron, Double> entry : weightedInputs.entrySet()){
            double neuronValue = entry.getKey().calculate();
            result+= neuronValue*entry.getValue();
            weightSum+= entry.getValue();
        }
        return result/weightSum;
    }

    public void addInput(Neuron input, double weight){
        weightedInputs.put(input,weight);
    }

    public void addInput(Neuron input){
        addInput(input,0);
    }

    public void addMultipleInputs(ArrayList<? extends Neuron> inputs, double averageWeight){
        for(Neuron input : inputs){
            addInput(input, averageWeight);
        }
    }

    public void addMultipleInputs(ArrayList<? extends Neuron> inputs){
        addMultipleInputs(inputs,0);
    }

    public void addMultipleInputs(ArrayList<? extends Neuron> inputs, ArrayList<Double> weights){
        for(int i = 0;i<inputs.size(); i++){
            addInput(inputs.get(i), weights.get(i));
        }
    }
}
