package de.ykstr.jneuralnet.functions;

public class LinearActivation implements IActivationFunction{

    private static LinearActivation instance;

    private LinearActivation(){}

    public static LinearActivation getInstance(){
        if(instance == null){
            instance = new LinearActivation();
        }
        return instance;
    }

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
