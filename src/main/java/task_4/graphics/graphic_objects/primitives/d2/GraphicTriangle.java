package task_4.graphics.graphic_objects.primitives.d2;

import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.geometry.points.Point;
import task_4.graphics.graphic_objects.polygons.RealPolygon;
import task_4.graphics.graphic_objects.polygons.Triangle;
import task_4.graphics.graphic_objects.primitives.GraphicPrimitive;

import java.util.List;
import java.util.Set;


public class GraphicTriangle
    extends GraphicPrimitive
{
    private final Point vertex1;
    private final Point vertex2;
    private final Point vertex3;

    public GraphicTriangle(Point vertex1, Point vertex2, Point vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    public Point getVertex1() {
        return vertex1;
    }

    public Point getVertex2() {
        return vertex2;
    }

    public Point getVertex3() {
        return vertex3;
    }

    @Override
    public Set<RealPolygon> getPolygons() {
        return Set.of(new RealPolygon(vertex1, vertex2, vertex3));
    }
}
