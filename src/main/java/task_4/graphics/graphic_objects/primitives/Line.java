package task_4.graphics.graphic_objects.primitives;

import task_4.graphics.geometry.points.Point;
import task_4.graphics.graphic_objects.polygons.RealPolygon;

import java.util.Arrays;
import java.util.Set;


public class Line
    extends GraphicPrimitive
{
    public static final double DEFAULT_STROKE = 1;

    private final Point start;
    private final Point end;
    private final double stroke;

    public Line(Point start, Point end, double stroke) {
        this.start = start;
        this.end = end;
        this.stroke = stroke;
    }

    public Line(Point start, Point end) {
        this(start, end, DEFAULT_STROKE);
    }

    @Override
    public Set<RealPolygon> getPolygons() {
        double halfStroke = stroke / 2;
        return Set.of(new RealPolygon(Arrays.asList(
            new Point(
                start.getX() - halfStroke,
                start.getY() - halfStroke,
                start.getZ()
            ),
            new Point(
                start.getX() + halfStroke,
                start.getY() + halfStroke,
                start.getZ()),
            new Point(
                end.getX() + halfStroke,
                end.getY() + halfStroke,
                end.getZ()),
            new Point(
                end.getX() - halfStroke,
                end.getY() - halfStroke,
                end.getZ())
        )));
    }
}
