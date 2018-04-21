package de.ykstr.jneuralnet.convolutional.components;

import de.ykstr.jneuralnet.util.NNMath;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.BitSet;

public class ConvolutionalLayer {

    public enum KernelMode {VALID, SAME, FULL}

    private double[][][] matrix;

    public ConvolutionalLayer(int width, int height, int depth){
        System.out.printf("Creating layer with width = %d, height = %d, depth = %d%n",width,height,depth);
        matrix = new double[width][height][depth];
    }

    public ConvolutionalLayer(BufferedImage image, boolean greyscale){
        this(image.getWidth(), image.getHeight(), greyscale?1:3);
        for(int x = 0; x < image.getWidth(); x++){
            for(int y = 0; y < image.getHeight(); y++){
                Color c = new Color(image.getRGB(x,y));
                if(greyscale){
                    double l = (c.getRed()*0.3+c.getGreen()*0.59+c.getBlue()*0.11);
                    set(x,y,0,l);
                }else{
                    set(x,y,0,c.getRed());
                    set(x,y,1,c.getGreen());
                    set(x,y,2,c.getBlue());
                }
            }
        }
    }

    public ConvolutionalLayer apply(Kernel k, KernelMode mode){
        return apply(k,1,1,1,mode);
    }

    public double apply(Kernel k, int x, int y, int z){
        double result = 0;
        for(int xk = 0; xk < k.getWidth(); xk++){
            for(int yk = 0; yk < k.getHeight(); yk++){
                for(int zk = 0; zk < k.getDepth(); zk++){
                    result += k.get(xk, yk, zk)*get(x+xk-k.getWidth()/2, y+yk-k.getHeight()/2, z+zk-k.getDepth()/2);
                }
            }
        }
        return result;
    }

    public ConvolutionalLayer apply(Kernel k, int xStride, int yStride, int zStride, KernelMode mode){
        int width = 0;
        int height = 0;
        int depth = 0;
        ConvolutionalLayer result = null;
        switch (mode){
            //no zero padding
            case VALID:
                width = getWidth()-k.getWidth()+1;
                height = getHeight()-k.getHeight()+1;
                depth = getDepth()-k.getDepth()+1;
                break;
            //result is of equal size to the input
            case SAME:
                width = getWidth();
                height = getHeight();
                depth = getDepth();
                System.out.printf("new size: width = %d, height = %d, depth = %d%n",width,height, depth);
                break;
            //every pixel of the input is visited the same amount of times
            case FULL:
                width = getWidth()+k.getWidth()-1;
                height = getHeight()+k.getHeight()-1;
                depth = getDepth()+k.getDepth()-1;
                break;
        }
        result = new ConvolutionalLayer(width,height,depth);

        //currently without stride, implementation with modulo
        switch (mode){
            case VALID:
                for(int x = k.getWidth()/2; x < result.getWidth()-k.getWidth()/2; x++){
                    for(int y = k.getHeight()/2; y < result.getHeight()-k.getHeight()/2; y++){
                        for(int z = k.getDepth()/2; z <  result.getDepth()-k.getDepth()/2; z++){
                            result.set(x,y,z, apply(k,x,y,z));
                        }
                    }
                }
                break;
            case SAME:
                for(int x = 0; x < result.getWidth(); x++){
                    for(int y = 0; y < result.getHeight(); y++){
                        for(int z = 0; z <  result.getDepth(); z++){
                            result.set(x,y,z, apply(k,x,y,z));
                        }
                    }
                }
                break;
            case FULL:
                for(int x = 0; x < result.getWidth(); x++){
                    for(int y = 0; y < result.getHeight(); y++){
                        for(int z = 0; z <  result.getDepth(); z++){
                            result.set(x,y,z, apply(k,x-k.getWidth()/2,y-k.getHeight()/2,z-k.getDepth()/2));
                        }
                    }
                }
                break;
        }

        return result;
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

    /**
     * Getter including zero padding
     * @param x
     * @param y
     * @param z
     * @return the appropriate value or 0 if the coordinates are outside of the boundaries
     */
    public double get(int x, int y, int z){
        if(!checkBounds(x,y,z))return 0;
        return matrix[x][y][z];
    }

    public void set(int x, int y, int z, double value){
        matrix[x][y][z] = value;
    }

    public boolean checkBounds(int x, int y, int z){
        return !(x < 0 || x >= getWidth() || y < 0 || y >= getHeight() || z < 0 || z >= getDepth());
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
}
