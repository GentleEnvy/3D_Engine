package task_4.graphics.lighting;

import javafx.scene.paint.Color;


public class ColorLight {
    private int red;
    private int green;
    private int blue;

    public ColorLight(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
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

    public Color toFxColor() {
        return Color.rgb(getRed(), getGreen(), getBlue());
    }
}
