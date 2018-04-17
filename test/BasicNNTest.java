import de.ykstr.jneuralnet.deep.components.BasicNetwork;
import de.ykstr.jneuralnet.deep.components.neurons.InputNeuron;
import de.ykstr.jneuralnet.deep.functions.LinearActivation;

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
