package de.ykstr.jneuralnet.convolutional.components.poolingfunctions;
public class AverageFunction implements PoolingFunction{
    @Override
    public double pool(double[][][] input) {
        double result = 0;
        int counter = 0;
        for(int x = 0; x < input.length; x++){
            for(int y = 0; y < input[x].length; y++){
                for(int z = 0; z < input[x][y].length; z++){
                    result += input[x][y][z];
                    counter++;
                }
            }
        }
        System.out.printf("result = %f, counter = %d%n",result,counter);
        return result/counter;
    }
}
