package task_4.graphics.graphic_objects.polygons;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.geometry.points.Point;
import task_4.graphics.lighting.ColorLight;
import task_4.graphics.scene.PointConverter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class GraphicPolygon
    implements Comparable<GraphicPolygon>
{
    private final List<Pixel> pixels;

    private boolean isFill = true;
    private ColorLight color = new ColorLight(0, 0, 0);

    public GraphicPolygon(Collection<Pixel> pixels) {
        this.pixels = List.copyOf(pixels);
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

    private double getAverageDepth() {
        double sumDepth = 0;
        for (Pixel pixel : pixels) {
            sumDepth += pixel.getDepth();
        }
        return sumDepth / pixels.size();
    }

    public Polygon toFxPolygon() {
        Polygon fxPolygon = new Polygon();
        for (Pixel pixel : pixels) {
            fxPolygon.getPoints().addAll(
                (double) pixel.getX(),
                (double) pixel.getY()
            );
        }
        Color fxColor = getColor().toFxColor();
        if (isFill) {
            fxPolygon.setFill(fxColor);
        } else {
            fxPolygon.setFill(Color.TRANSPARENT);
            fxPolygon.setStroke(fxColor);
        }
        return fxPolygon;
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
