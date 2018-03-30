package de.ykstr.jneuralnet.functions;

public class LinearActivation implements IActivationFunction{
    @Override
    public double calculate(double value) {
        if(value<0){
            return 0;
        }else if(value > 1){
            return 1;
        }else{
            return value;
        }
    }
}
