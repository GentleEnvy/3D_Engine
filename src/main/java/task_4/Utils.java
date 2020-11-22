package task_4;

import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.geometry.points.Point;
import task_4.graphics.graphic_objects.polygons.GraphicPolygon;
import task_4.graphics.graphic_objects.polygons.Triangle;
import task_4.graphics.graphic_objects.primitives.Line;
import task_4.graphics.lighting.ColorLight;

import java.util.*;


public class Utils {
    public static Set<Line> getAxes() {
        Point origin = new Point(0, 0, 0);
        Line x = new Line(origin, new Point(100, 0, 0));
        x.setColor(new ColorLight(255, 0, 0));
        Line y = new Line(origin, new Point(0, 100, 0));
        y.setColor(new ColorLight(0, 255, 0));
        Line z = new Line(origin, new Point(0, 0, 100));
        z.setColor(new ColorLight(0, 0, 255));
        return Set.of(x, y, z);
    }

    public static ColorLight getRandomColor() {
        Random random = new Random();
        return new ColorLight(
            random.nextInt(255),
            random.nextInt(255),
            random.nextInt(255)
        );
    }

    public static Set<Triangle> triangulation(
        GraphicPolygon polygon,
        double minTriangleArea
    ) {
        if (polygon.getPixels().size() < 3) {
            return Set.of(Triangle.fromPolygon(polygon));
        }
        Map<Triangle, Double> areaCache = new HashMap<>();
        Set<Triangle> smallTriangles = new HashSet<>();
        Set<Triangle> bigTriangles = new HashSet<>();
        if (polygon.getPixels().size() > 3) {
            Set<Triangle> smallerTriangles = splitPolygon(polygon);
            for (Triangle smallerTriangle : smallerTriangles) {
                double area = smallerTriangle.getArea();
                if (area > minTriangleArea) {
                    bigTriangles.add(smallerTriangle);
                    areaCache.put(smallerTriangle, area);
                } else {  // triangle is small
                    smallTriangles.add(smallerTriangle);
                }
            }
        } else {  // polygon is triangle
            Triangle polygonsTriangle = Triangle.fromPolygon(polygon);
            double area = polygonsTriangle.getArea();
            if (area > minTriangleArea) {
                bigTriangles.add(polygonsTriangle);
                areaCache.put(polygonsTriangle, area);
            } else {  // triangle is small
                smallTriangles.add(polygonsTriangle);
            }
        }
        while (!bigTriangles.isEmpty()) {
            Triangle bigTriangle = bigTriangles.iterator().next();
            Set<Triangle> smallerTriangles = splitTriangle(bigTriangle);
            for (Triangle smallerTriangle : smallerTriangles) {
                double area = areaCache.get(bigTriangle) / 4;
                if (area > minTriangleArea) {
                    bigTriangles.add(smallerTriangle);
                    areaCache.put(smallerTriangle, area);
                } else {  // triangle is small
                    smallTriangles.add(smallerTriangle);
                }
            }
            bigTriangles.remove(bigTriangle);
            areaCache.remove(bigTriangle);
        }
        return smallTriangles;
    }

    private static Set<Triangle> splitTriangle(Triangle triangle) {
        Pixel middle1 = getMiddle(triangle.getVertex1(), triangle.getVertex2());
        Pixel middle2 = getMiddle(triangle.getVertex2(), triangle.getVertex3());
        Pixel middle3 = getMiddle(triangle.getVertex1(), triangle.getVertex3());

        Set<Triangle> smallerTriangles = new HashSet<>(List.of(
            new Triangle(triangle.getVertex1(), middle1, middle3),
            new Triangle(triangle.getVertex2(), middle1, middle2),
            new Triangle(triangle.getVertex3(), middle2, middle3),
            new Triangle(middle1, middle2, middle3)
        ));
        for (Triangle smallerTriangle : smallerTriangles) {
            smallerTriangle.setFill(triangle.isFill());
            smallerTriangle.setColor(triangle.getColor());
            //smallerTriangle.setColor(getRandomColor()); FIXME
        }
        return smallerTriangles;
    }

    private static Pixel getMiddle(Pixel pixel1, Pixel pixel2) {
        return new Pixel(
            (pixel1.getX() + pixel2.getX()) / 2,
            (pixel1.getY() + pixel2.getY()) / 2,
            (pixel1.getDepth() + pixel2.getDepth()) / 2
        );
    }

    private static Set<Triangle> splitPolygon(GraphicPolygon polygon) {
        ColorLight color = polygon.getColor();
        //ColorLight color = getRandomColor(); FIXME
        boolean isFill = polygon.isFill();
        List<Pixel> pixels = polygon.getPixels();
        if (pixels.size() == 4) {
            Triangle triangle1 = Triangle.fromPolygon(polygon);
//            Triangle triangle1 = new Triangle(
//                pixels.get(0), pixels.get(1), pixels.get(2)
//            );
//            triangle1.setColor(getRandomColor());
//            triangle1.setFill(isFill); FIXME

            Triangle triangle2 = new Triangle(
                pixels.get(0), pixels.get(3), pixels.get(2)
            );
            triangle2.setColor(color);
            triangle2.setFill(isFill);
            return Set.of(triangle1, triangle2);
        } else {
            return Set.of(Triangle.fromPolygon(polygon));
        }
    }
}
