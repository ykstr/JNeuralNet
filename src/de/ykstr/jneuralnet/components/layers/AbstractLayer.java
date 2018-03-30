package de.ykstr.jneuralnet.components.layers;

import de.ykstr.jneuralnet.components.neurons.Neuron;
import de.ykstr.jneuralnet.components.neurons.WeightedNeuron;

import java.util.ArrayList;

public abstract class AbstractLayer<T extends Neuron> {

    protected ArrayList<T> neurons;

    public AbstractLayer(){
        neurons = new ArrayList<>();
    }

    public ArrayList<T> getNeurons(){
        return neurons;
    }

    public void addNeuron(T neuron){
        getNeurons().add(neuron);
    }

    public void removeNeuron(T neuron){
        getNeurons().remove(neuron);
    }

    public T getNeuron(int index){
        return getNeurons().get(index);
    }
}
