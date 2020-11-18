package task_4.graphics.scane;

import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.lighting.ColorLight;

import java.util.Arrays;


public class Screen
    extends ImageView
{
    private final WritableImage writableImage;
    private final double[][] pixelDepths;

    public Screen(int screenWidth, int screenHeight) {
        super();
        writableImage = new WritableImage(screenWidth, screenHeight);
        setImage(writableImage);
        pixelDepths = new double[screenHeight][screenWidth];
        for (double[] pixelDepthsRow : pixelDepths) {
            Arrays.fill(pixelDepthsRow, Double.NEGATIVE_INFINITY);
        }
    }

    public int getWidth() {
        return (int) writableImage.getWidth();
    }

    public int getHeight() {
        return (int) writableImage.getHeight();
    }

    public void setPixel(Pixel pixel) {
        try {
            ColorLight colorLight = pixel.getColor();
            writableImage.getPixelWriter().setColor(
                pixel.getX(), pixel.getY(),
                Color.rgb(
                    colorLight.getRed(),
                    colorLight.getGreen(),
                    colorLight.getBlue()
                )
            );
        } catch (IndexOutOfBoundsException e) {
            //
        }
    }

    public boolean isOut(Pixel pixel) {
        return pixel.getX() < 0 || pixel.getY() < 0 ||
            pixel.getX() > writableImage.getWidth() ||
            pixel.getY() > writableImage.getHeight();
    }
}
