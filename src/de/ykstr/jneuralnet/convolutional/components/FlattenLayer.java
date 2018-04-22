package de.ykstr.jneuralnet.convolutional.components;

import java.util.ArrayList;

public class FlattenLayer {

    double[] vector;

    public FlattenLayer(ConvolutionalLayer layer){
        vector = new double[layer.getWidth()*layer.getHeight()*layer.getDepth()];
        //layer.iterate((x, y, z, value) -> vector[x*layer.getWidth()+y*layer.getHeight()+z*layer.getDepth()] = value);
        ArrayList<Double> temp = new ArrayList<>();
        for(int z = 0; z < layer.getDepth(); z++){
            for(int y = 0; y < layer.getHeight(); y++){
                for(int x = 0; x < layer.getWidth(); x++){
                    temp.add(layer.get(x,y,z));
                }
            }
        }
        for(int i = 0; i < temp.size(); i++) vector[i] = temp.get(i);
    }
}
