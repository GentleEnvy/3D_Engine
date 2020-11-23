package task_4.graphics.graphic_objects.polygons;

import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.scene.Screen;

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
    public void render(Screen screen) {
        class Render {
            // cache
            final double v1x = vertex1.getX();
            final double v1y = vertex1.getY();
            final double v2x = vertex2.getX();
            final double v2y = vertex2.getY();
            final double v3x = vertex3.getX();
            final double v3y = vertex3.getY();

            final double v23x = v2x - v3x;
            final double v23y = v2y - v3y;
            final double v31x = v3x - v1x;
            final double v31y = v3y - v1y;
            final double v12x = v1x - v2x;
            final double v12y = v1y - v2y;

            final double triangleArea = v23y * v31x - v31y * v23x;

            final int minX = (int) Math.max(
                0, Math.ceil(Math.min(v1x, Math.min(v2x, v3x)))
            );
            final int maxX = (int) Math.min(
                screen.getScreenWidth() - 1, Math.floor(
                    Math.max(v1x, Math.max(v2x, v3x))
                )
            );
            final int minY = (int) Math.max(
                0, Math.ceil(Math.min(v1y, Math.min(v2y, v3y)))
            );
            final int maxY = (int) Math.min(
                screen.getScreenHeight() - 1, Math.floor(
                    Math.max(v1y, Math.max(v2y, v3y))
                )
            );

            void render() {
                for (int y = minY; y <= maxY; y++) {
                    for (int x = minX; x <= maxX; x++) {
                        Double depth = calcPixelDepth(x, y);

                        if (depth != null) {
                            screen.setPixel(new Pixel(
                                x, y, depth, getColor()
                            ));
                        }
                    }
                }
            }

            Double calcPixelDepth(double x, double y) {
                double b1 = (v23x * (y - v3y) + v23y * (v3x - x)) / triangleArea;
                double b2 = (v31x * (y - v1y) + v31y * (v1x - x)) / triangleArea;
                double b3 = (v12x * (y - v2y) + v12y * (v2x - x)) / triangleArea;

                if  (
                    b1 >= 0 && b1 <= 1 && b2 >= 0 &&
                        b2 <= 1 && b3 >= 0 && b3 <= 1
                ) {
                    return b1 * vertex1.getDepth()
                        + b2 * vertex2.getDepth()
                        + b3 * vertex3.getDepth();
                }
                return null;
            }
        }
        new Render().render();
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
