package de.ykstr.jneuralnet.convolutional.components;

public class Kernel {
    int[][][] matrix;

    public Kernel(int width, int height, int depth){
        matrix = new int[width][height][depth];
    }

    public Kernel(int[][] filter2d){
        matrix = new int[filter2d.length][filter2d[0].length][1];
        for(int x = 0; x < filter2d.length; x++){
            for(int y = 0; y < filter2d[x].length; y++){
                set(x,y,0, filter2d[x][y]);
            }
        }
    }

    public void set(int x, int y, int z, int value){
        matrix[x][y][z] = value;
    }

    public int get(int x, int y, int z){
        return matrix[x][y][z];
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

    public static Kernel PrewittX(){
        Kernel result = new Kernel(new int[][]
                {{-1, -1, -1},
                 {0, 0, 0},
                 {1, 1, 1}}
        );
        return result;
    }

    public static Kernel PrewittY(){
        Kernel result = new Kernel(new int[][]
                {{-1, 0, 1},
                 {-1, 0, 1},
                 {-1, 0, 1}}
        );
        return result;
    }

}
