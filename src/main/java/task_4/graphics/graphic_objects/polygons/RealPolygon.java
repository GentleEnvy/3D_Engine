package task_4.graphics.graphic_objects.polygons;

import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.geometry.points.Point;
import task_4.graphics.lighting.ColorLight;
import task_4.graphics.scene.PointConverter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class RealPolygon {
    private final List<Point> points;

    private boolean isFill = true;
    private ColorLight color = new ColorLight(0, 0, 0);

    public RealPolygon(Collection<Point> points) {
        this.points = List.copyOf(points);
    }

    public RealPolygon(Point... points) {
        this(List.of(points));
    }

    public List<Point> getPoints() {
        return points;
    }

    public boolean isFill() {
        return isFill;
    }

    public void setFill(boolean fill) {
        isFill = fill;
    }

    public ColorLight getColor() {
        return color;
    }

    public void setColor(ColorLight color) {
        this.color = color;
    }

    public GraphicPolygon toGraphic(PointConverter converter) {
        List<Point> points = getPoints();
        List<Pixel> pixels = new LinkedList<>();
        for (Point point : points) {
            pixels.add(converter.convert(point));
        }
        GraphicPolygon graphicPolygon = new GraphicPolygon(pixels);
        graphicPolygon.setFill(isFill());
        graphicPolygon.setColor(getColor());
        return graphicPolygon;
    }
}
