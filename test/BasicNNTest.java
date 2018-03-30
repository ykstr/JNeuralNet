import de.ykstr.jneuralnet.components.BasicNetwork;
import de.ykstr.jneuralnet.components.layers.InputLayer;
import de.ykstr.jneuralnet.components.neurons.InputNeuron;
import de.ykstr.jneuralnet.components.neurons.WeightedNeuron;
import de.ykstr.jneuralnet.functions.LinearActivation;

import java.util.ArrayList;

public class BasicNNTest {
    public static void main(String[] args) {
        BasicNetwork network = new BasicNetwork(15, LinearActivation.getInstance());
        network.addInput(new InputNeuron(LinearActivation.getInstance(),Math.random()),
                Math.random());
        network.addInput(new InputNeuron(LinearActivation.getInstance(),Math.random()),
                Math.random());
        network.addInput(new InputNeuron(LinearActivation.getInstance(),Math.random()),
                Math.random());
        System.out.println(network.calculateOutputs());
    }
}
