package de.ykstr.jneuralnet.util;

public class NNMath {
    public static double range(double input, double min, double max){
        if(input < min){
            return min;
        }else if(input > max){
            return max;
        }else{
            return input;
        }
    }
}
