package task_4.graphics.graphic_objects.primitives;

import task_4.graphics.geometry.points.Point;
import task_4.graphics.graphic_objects.polygons.RealPolygon;

import java.util.Set;


public class Box extends GraphicPrimitive {
    private final double width;
    private final double height;
    private final double depth;

    public Box(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    @Override
    public Set<RealPolygon> getPolygons() {
        double w = width / 2;
        double h = height / 2;
        double d = depth / 2;

        Point ftl = new Point(-w, h, -d);  // front top left
        Point ftr = new Point(w, h, -d);  // front top right
        Point fbl = new Point(-w, -h, -d);  // front bottom left
        Point fbr = new Point(w, -h, -d);  // front bottom right
        Point btl = new Point(-w, h, d);  // back top left
        Point btr = new Point(w, h, d);  // back top right
        Point bbl = new Point(-w, -h, d);  // back bottom left
        Point bbr = new Point(w, -h, d);  // back bottom right

        return Set.of(
            new RealPolygon(ftl, ftr, fbr, fbl),  // front
            new RealPolygon(ftl, ftr, btr, btl),  // top
            new RealPolygon(fbl, fbr, bbr, bbl),  // bottom
            new RealPolygon(ftl, btl, bbl, fbl),  // left
            new RealPolygon(ftr, btr, bbr, fbr),  // right
            new RealPolygon(btl, btr, bbr, bbl)  // behind
        );
    }
}
