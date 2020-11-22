package task_4.graphics.graphic_objects.polygons;

import task_4.graphics.geometry.points.Pixel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Triangle
    extends GraphicPolygon
{
    private final Pixel vertex1;
    private final Pixel vertex2;
    private final Pixel vertex3;

    public Triangle(Pixel vertex1, Pixel vertex2, Pixel vertex3) {
        super(List.of(vertex1, vertex2, vertex3));
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    public Pixel getVertex1() {
        return vertex1;
    }

    public Pixel getVertex2() {
        return vertex2;
    }

    public Pixel getVertex3() {
        return vertex3;
    }

    public double getArea() {
        double side1 = getSide(vertex1, vertex2);
        double side2 = getSide(vertex1, vertex3);
        double side3 = getSide(vertex2, vertex3);
        double p = (side1 + side2 + side3) / 3;
        double a = p - side1;
        double b = p - side2;
        double c = p - side3;
        return Math.sqrt(p * (a * a + b * b + c * c));
    }

    private static double getSide(Pixel vertex1, Pixel vertex2) {
        double dx = vertex1.getX() - vertex2.getX();
        double dy = vertex1.getY() - vertex2.getY();
        double dz = vertex1.getDepth() - vertex2.getDepth();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static Triangle fromPolygon(GraphicPolygon polygon) {
        Triangle triangle = new Triangle(
            polygon.getPixels().get(0),
            polygon.getPixels().get(1),
            polygon.getPixels().get(2)
        );
        triangle.setColor(polygon.getColor());
        triangle.setFill(polygon.isFill());
        return triangle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Triangle)) {
            return false;
        }

        Triangle triangle = (Triangle) o;

        if (!vertex1.equals(triangle.vertex1)) {
            return false;
        }
        if (!vertex2.equals(triangle.vertex2)) {
            return false;
        }
        return vertex3.equals(triangle.vertex3);
    }

    @Override
    public int hashCode() {
        int result = vertex1.hashCode();
        result = 31 * result + vertex2.hashCode();
        result = 31 * result + vertex3.hashCode();
        return result;
    }
}
