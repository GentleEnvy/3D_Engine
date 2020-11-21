package task_4.graphics.graphic_objects.primitives;

import task_4.graphics.geometry.points.Point;
import task_4.graphics.graphic_objects.polygons.RealPolygon;

import java.util.Arrays;
import java.util.Set;


public class Line
    extends GraphicPrimitive
{
    private final Point start;
    private final Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Set<RealPolygon> getPolygons() {
        return Set.of(new RealPolygon(Arrays.asList(
            new Point(
                start.getX(),
                start.getY(),
                start.getZ()
            ),
            new Point(
                end.getX(),
                end.getY(),
                end.getZ()
            )
        )));
    }
}
