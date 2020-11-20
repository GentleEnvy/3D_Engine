package task_4.graphics.graphic_objects.polygons;

import task_4.graphics.geometry.points.Point;

import java.util.Collection;
import java.util.List;


public class RealPolygon {
    private final List<Point> points;

    public RealPolygon(Collection<Point> points) {
        this.points = List.copyOf(points);
    }

    public List<Point> getPoints() {
        return points;
    }
}
