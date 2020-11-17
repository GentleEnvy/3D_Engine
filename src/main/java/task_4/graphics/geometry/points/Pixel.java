package task_4.graphics.geometry.points;

import task_4.graphics.geometry.matrixes.NumberVector;
import task_4.graphics.lighting.ColorLight;


public class Pixel {
    public static final ColorLight DEFAULT_COLOR = new ColorLight(
        0, 0, 0
    );

    private final int x;
    private final int y;
    private final int z;
    private final ColorLight color;

    public Pixel(int x, int y, int z, ColorLight color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }

    public Pixel(int x, int y, int z) {
        this(x, y, z, DEFAULT_COLOR);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public ColorLight getColor() {
        return color;
    }
}
