import de.ykstr.jneuralnet.convolutional.components.ConvolutionalLayer;
import de.ykstr.jneuralnet.convolutional.components.Kernel;
import de.ykstr.jneuralnet.convolutional.components.poolingfunctions.AverageFunction;
import de.ykstr.jneuralnet.convolutional.components.poolingfunctions.MaxFunction;
import de.ykstr.jneuralnet.convolutional.components.PoolingLayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class CNNTest {
    public static void main(String[] args) {
        try{
            BufferedImage input = ImageIO.read(new File("resources/flugzeug.png"));
            ConvolutionalLayer con = new ConvolutionalLayer(input, true);
            con = con.apply(Kernel.PrewittX(), ConvolutionalLayer.KernelMode.SAME);
            con = con.apply(Kernel.PrewittY(), ConvolutionalLayer.KernelMode.SAME);

            BufferedImage output = con.toImage();
            ImageIO.write(output, "png", new File("resources/edges.png"));

            PoolingLayer pool = new PoolingLayer(con);
            pool.pool(con.getWidth()/(con.getWidth()/10), con.getHeight()/(con.getHeight()/10), con.getDepth(), new MaxFunction());

            output = pool.toImage();
            ImageIO.write(output, "png", new File("resources/pooling.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void printDimensions(ConvolutionalLayer layer){
        System.out.printf("width: %d, height: %d, depth: %d%n",layer.getWidth(), layer.getHeight(), layer.getDepth());
    }
}
