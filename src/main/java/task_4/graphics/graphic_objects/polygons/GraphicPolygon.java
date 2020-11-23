package task_4.graphics.graphic_objects.polygons;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import task_4.Utils;
import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.lighting.ColorLight;
import task_4.graphics.scene.Screen;

import java.util.Collection;
import java.util.List;


public class GraphicPolygon
    implements Comparable<GraphicPolygon>
{
    private final List<Pixel> pixels;

    public List<Pixel> getPixels() {
        return pixels;
    }

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

    public void render(Screen screen) {
        screen.fromFxNode(toFx());
    }

    protected Node toFx() {
        Polygon fxPolygon = new Polygon();
        Color fxColor = getColor().toFxColor();
        if (pixels.size() == 2) {
            Polyline polyline = new Polyline(
                pixels.get(0).getX(), pixels.get(0).getY(),
                pixels.get(1).getX(), pixels.get(1).getY()
            );
            polyline.setStroke(fxColor);
            return polyline;
        }

        for (Pixel pixel : pixels) {
            fxPolygon.getPoints().addAll(
                pixel.getX(),
                pixel.getY()
            );
        }
        if (isFill) {
            fxPolygon.setFill(fxColor);
        } else {
            fxPolygon.setFill(Color.TRANSPARENT);
        }
        fxPolygon.setStroke(fxColor);
        return fxPolygon;
    }

    protected double getAverageDepth() {
        double sumDepth = 0;
        for (Pixel pixel : pixels) {
            sumDepth += pixel.getDepth();
        }
        return sumDepth / pixels.size();
    }

    /**
     @return 1, если данный полигон ближе; -1, если дальше; 0 если на одинаково расстоянии
     от камеры
     */
    @Override
    public int compareTo(GraphicPolygon graphicPolygon) {
        return Double.compare(graphicPolygon.getAverageDepth(), getAverageDepth());
    }
}
