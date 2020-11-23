package task_4.graphics.scene;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.lighting.ColorLight;


public class Screen
    extends Pane
{
    private final WritableImage writableImage;
    private final PixelWriter pixelWriter;
    private final Double[][] depths;

    public Screen(int screenWidth, int screenHeight) {
        super();
        writableImage = new WritableImage(screenWidth, screenHeight);
        pixelWriter = writableImage.getPixelWriter();
        depths = new Double[screenHeight][screenWidth];

        ImageView imageView = new ImageView(writableImage);
        setMinSize(screenWidth, screenHeight);
        setMaxSize(screenWidth, screenHeight);
        getChildren().add(imageView);
    }

    public int getScreenWidth() {
        return (int) writableImage.getWidth();
    }

    public int getScreenHeight() {
        return (int) writableImage.getHeight();
    }

    public void setPixel(Pixel pixel) {
        int pixelX = (int) pixel.getX();
        int pixelY = (int) pixel.getY();
        if (depths[pixelY][pixelX] == null || depths[pixelY][pixelX] > pixel.getDepth()) {
            try {
                ColorLight colorLight = pixel.getColor();
                pixelWriter.setColor(
                    pixelX, pixelY,
                    colorLight.toFxColor()
                );
                depths[pixelY][pixelX] = pixel.getDepth();
            } catch (IndexOutOfBoundsException e) {
                //
            }
        }
    }

    public void fromFxNode(Node node) {
        getChildren().add(node);
    }
}
