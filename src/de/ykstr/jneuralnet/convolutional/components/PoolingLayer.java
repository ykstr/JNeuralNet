package de.ykstr.jneuralnet.convolutional.components;

import de.ykstr.jneuralnet.convolutional.components.poolingfunctions.PoolingFunction;
import de.ykstr.jneuralnet.util.NNMath;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PoolingLayer {
    double[][][] matrix;
    ConvolutionalLayer previous;

    public PoolingLayer(ConvolutionalLayer previous){
        this.previous = previous;
    }

    public void pool(int width, int height, int depth, PoolingFunction f){
        matrix = new double[previous.getWidth()/width][previous.getHeight()/height][previous.getDepth()/depth]; //Result of the pooling layer
        for(int x = 0; x < previous.getWidth()/width; x++){
            for(int y = 0; y < previous.getHeight()/height; y++){
                for(int z = 0; z < previous.getDepth()/depth; z++){

                    double[][][] poolCube = new double[width][height][depth];
                    for(int cx = 0; cx < width; cx++){
                        for(int cy = 0; cy < height; cy++){
                            for(int cz = 0; cz < depth; cz++){
                                poolCube[cx][cy][cz] = previous.get(x*width+cx, y*height+cy, z*depth+cz);
                            }
                        }
                    }
                    matrix[x][y][z] = f.pool(poolCube);

                }
            }
        }
    }

    public BufferedImage toImage(){
        BufferedImage result = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < getWidth(); x++){
            for(int y = 0; y < getHeight(); y++){
                Color c = null;
                if(getDepth() == 1){
                    int l = (int) NNMath.range(get(x,y,0),0,255);
                    c = new Color(l,l,l);
                }else{
                    int r = (int) NNMath.range(get(x,y,0),0,255);
                    int g = (int) NNMath.range(get(x,y,1),0,255);
                    int b = (int) NNMath.range(get(x,y,2),0,255);
                    c = new Color(r, g, b);
                }
                result.setRGB(x,y,c.getRGB());
            }
        }
        return result;
    }

    public int getWidth(){
        return matrix.length;
    }

    public int getHeight(){
        return matrix[0].length;
    }

    public int getDepth(){
        return matrix[0][0].length;
    }

    public double get(int x, int y, int z){
        return matrix[x][y][z];
    }
}
