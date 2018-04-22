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

    public static double[] softMax(double[] input){
        double[] result = new double[input.length];
        double inputExpSum = 0;

        for(double i : input) inputExpSum += Math.exp(i);
        for(int i = 0; i < input.length; i++)  result[i] = Math.exp(input[i])/inputExpSum;

        return result;
    }

}
