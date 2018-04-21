package de.ykstr.jneuralnet.convolutional.components.poolingfunctions;

public class MaxFunction implements PoolingFunction{
    @Override
    public double pool(double[][][] input) {
        double result = input[0][0][0];
        for(int x = 0; x < input.length; x++){
            for(int y = 0; y < input[x].length; y++){
                for(int z = 0; z < input[x][y].length; z++){
                    result = Math.max(result, input[x][y][z]);
                }
            }
        }
        return result;
    }
}
