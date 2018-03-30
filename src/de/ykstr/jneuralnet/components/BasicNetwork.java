package de.ykstr.jneuralnet.components;

import de.ykstr.jneuralnet.components.layers.InputLayer;
import de.ykstr.jneuralnet.components.layers.OutputLayer;
import de.ykstr.jneuralnet.components.neurons.InputNeuron;
import de.ykstr.jneuralnet.components.neurons.Neuron;
import de.ykstr.jneuralnet.components.neurons.WeightedNeuron;
import de.ykstr.jneuralnet.functions.IActivationFunction;
import de.ykstr.jneuralnet.functions.LinearActivation;

import java.util.ArrayList;

public class BasicNetwork {
    private InputLayer input;
    private OutputLayer hidden;
    private OutputLayer output;
    private IActivationFunction f;

    public BasicNetwork(int hiddenSize, IActivationFunction f){
        input = new InputLayer();
        hidden = new OutputLayer(hiddenSize, f);
        output = new OutputLayer();
        this.f = f;
    }

    public void addInput(InputNeuron n){
        input.addNeuron(n);
        WeightedNeuron outputNeuron = new WeightedNeuron(f);
        outputNeuron.addMultipleInputs(hidden.getNeurons(), 1.0/hidden.getNeurons().size());
        for(WeightedNeuron hiddenNeuron : hidden.getNeurons()){
            hiddenNeuron.addInput(n, 1);
        }
        output.addNeuron(outputNeuron);

    }

    public ArrayList<Double> calculateOutputs(){
        ArrayList<Double> result = new ArrayList<>();
        for(Neuron n : output.getNeurons()){
            result.add(n.calculate());
        }
        return result;
    }


}
