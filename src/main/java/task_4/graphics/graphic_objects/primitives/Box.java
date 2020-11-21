package task_4.graphics.graphic_objects.primitives;

import task_4.graphics.geometry.points.Point;
import task_4.graphics.graphic_objects.polygons.RealPolygon;
import task_4.graphics.lighting.ColorLight;

import java.util.*;


public class Box extends GraphicPrimitive {
    private final double width;
    private final double height;
    private final double depth;

    private final List<ColorLight> colors = new ArrayList<>();

    public Box(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;

        for (int i = 0; i < 6; ++i) {
            colors.add(getRandomColor());
        }
    }

    private ColorLight getRandomColor() {
        Random random = new Random();
        return new ColorLight(
            random.nextInt(255),
            random.nextInt(255),
            random.nextInt(255)
        );
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

        RealPolygon front = new RealPolygon(ftl, ftr, fbr, fbl);
        RealPolygon top = new RealPolygon(ftl, ftr, btr, btl);
        RealPolygon bottom = new RealPolygon(fbl, fbr, bbr, bbl);
        RealPolygon left = new RealPolygon(ftl, btl, bbl, fbl);
        RealPolygon right = new RealPolygon(ftr, btr, bbr, fbr);
        RealPolygon behind = new RealPolygon(btl, btr, bbr, bbl);

        front.setColor(colors.get(0));
        top.setColor(colors.get(1));
        bottom.setColor(colors.get(2));
        left.setColor(colors.get(3));
        right.setColor(colors.get(4));
        behind.setColor(colors.get(5));

        return Set.of(front, top, bottom, left, right, behind);
    }
}
