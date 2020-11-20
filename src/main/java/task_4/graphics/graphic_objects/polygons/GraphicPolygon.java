package task_4.graphics.graphic_objects.polygons;

import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.geometry.points.Point;
import task_4.graphics.scene.PointConverter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class GraphicPolygon
    implements Comparable<GraphicPolygon>
{
    private final List<Pixel> pixels;

    public GraphicPolygon(Collection<Pixel> pixels) {
        this.pixels = List.copyOf(pixels);
    }

    public static GraphicPolygon fromReal(
        RealPolygon realPolygon,
        PointConverter converter
    ) {
        List<Point> points = realPolygon.getPoints();
        List<Pixel> pixels = new LinkedList<>();
        for (Point point : points) {
            pixels.add(converter.convert(point));
        }
        return new GraphicPolygon(pixels);
    }

    private double getAverageDepth() {
        double sumDepth = 0;
        for (Pixel pixel : pixels) {
            sumDepth += pixel.getDepth();
        }
        return sumDepth / pixels.size();
    }

    @Override
    public int compareTo(GraphicPolygon graphicPolygon) {
        double selfAvg = this.getAverageDepth();
        double otherAvg = graphicPolygon.getAverageDepth();
        if (selfAvg == otherAvg) {
            return 0;
        }
        return selfAvg < otherAvg ? 1 : -1;
    }
}
