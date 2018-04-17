package de.ykstr.jneuralnet.deep.components;

import de.ykstr.jneuralnet.deep.components.layers.InputLayer;
import de.ykstr.jneuralnet.deep.components.layers.OutputLayer;
import de.ykstr.jneuralnet.deep.components.neurons.InputNeuron;
import de.ykstr.jneuralnet.deep.components.neurons.Neuron;
import de.ykstr.jneuralnet.deep.components.neurons.WeightedNeuron;
import de.ykstr.jneuralnet.deep.functions.IActivationFunction;

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

    public void addInput(InputNeuron n, double inputWeight){
        input.addNeuron(n);
        WeightedNeuron outputNeuron = new WeightedNeuron(f);
        outputNeuron.addMultipleInputs(hidden.getNeurons(), 1.0/hidden.getNeurons().size());
        for(WeightedNeuron hiddenNeuron : hidden.getNeurons()){
            hiddenNeuron.addInput(n, inputWeight);
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
