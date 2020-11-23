package task_4.graphics.lighting;

import javafx.scene.paint.Color;


public class ColorLight {
    private int red;
    private int green;
    private int blue;
    private double opacity;

    public ColorLight(int red, int green, int blue, double opacity) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    public ColorLight(int red, int green, int blue) {
        this(red, green, blue, 1);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    public Color toFxColor() {
        return Color.rgb(getRed(), getGreen(), getBlue(), getOpacity());
    }
}
