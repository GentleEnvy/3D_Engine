package task_4.graphics.scene;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.lighting.ColorLight;


public class Screen
    extends Pane
{
    private final WritableImage writableImage;

    public Screen(int screenWidth, int screenHeight) {
        super();
        writableImage = new WritableImage(screenWidth, screenHeight);
        ImageView imageView = new ImageView(writableImage);
        setMinSize(screenWidth, screenHeight);
        setMaxSize(screenWidth, screenHeight);
        setStyle("-fx-border-color: red");
        getChildren().add(imageView);
    }

    public int getScreenWidth() {
        return (int) writableImage.getWidth();
    }

    public int getScreenHeight() {
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

    public void fromFxNode(Node node) {
        getChildren().add(node);
    }

    public boolean isOut(Pixel pixel) {
        return pixel.getX() < 0 || pixel.getY() < 0 ||
            pixel.getX() > writableImage.getWidth() ||
            pixel.getY() > writableImage.getHeight();
    }
}
