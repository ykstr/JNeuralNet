package de.ykstr.jneuralnet.components.layers;

import de.ykstr.jneuralnet.components.neurons.Neuron;
import de.ykstr.jneuralnet.components.neurons.WeightedNeuron;
import de.ykstr.jneuralnet.functions.IActivationFunction;

public class OutputLayer extends AbstractLayer<WeightedNeuron>{

    public OutputLayer(){}

    public OutputLayer(int size, IActivationFunction f){
        super();
        for(int i = 0; i<size; i++){
            addNeuron(new WeightedNeuron(f));
        }
    }

    public void connectAll(AbstractLayer former){
        for(WeightedNeuron n : neurons){
            n.addMultipleInputs(former.getNeurons(),1.0/former.getNeurons().size());
        }
    }

    public void connectAll(Neuron input, double weight){
        for(WeightedNeuron n : neurons){
            n.addInput(input, weight);
        }
    }

    public void connectAll(Neuron input){
        connectAll(input,0);
    }
}
