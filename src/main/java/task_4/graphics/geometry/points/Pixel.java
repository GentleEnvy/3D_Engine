package task_4.graphics.geometry.points;

import task_4.graphics.lighting.ColorLight;


public class Pixel {
    public static final ColorLight DEFAULT_COLOR = new ColorLight(
        0, 0, 0
    );

    private final double x;
    private final double y;
    private final double depth;
    private final ColorLight color;

    public Pixel(double x, double y, double depth, ColorLight color) {
        this.x = x;
        this.y = y;
        this.depth = depth;
        this.color = color;
    }

    public Pixel(double x, double y, double depth) {
        this(x, y, depth, DEFAULT_COLOR);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDepth() {
        return depth;
    }

    public ColorLight getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pixel)) {
            return false;
        }

        Pixel pixel = (Pixel) o;

        if (getX() != pixel.getX()) {
            return false;
        }
        if (getY() != pixel.getY()) {
            return false;
        }
        if (Double.compare(pixel.getDepth(), getDepth()) != 0) {
            return false;
        }
        return getColor() != null
            ? getColor().equals(pixel.getColor())
            : pixel.getColor() == null;
    }

    @Override
    public int hashCode() {
        double result;
        long temp;
        result = getX();
        result = 31 * result + getY();
        temp = Double.doubleToLongBits(getDepth());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        return (int) result;
    }
}
