package task_4.graphics.geometry.points;

import task_4.graphics.geometry.matrixes.NumberVector;
import task_4.graphics.lighting.ColorLight;


public class Pixel {
    public static final ColorLight DEFAULT_COLOR = new ColorLight(
        0, 0, 0
    );

    private final int x;
    private final int y;
    private final double depth;
    private final ColorLight color;

    public Pixel(int x, int y, double depth, ColorLight color) {
        this.x = x;
        this.y = y;
        this.depth = depth;
        this.color = color;
    }

    public Pixel(int x, int y, double depth) {
        this(x, y, depth, DEFAULT_COLOR);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDepth() {
        return depth;
    }

    public ColorLight getColor() {
        return color;
    }
}
