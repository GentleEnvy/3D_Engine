package task_4;

import task_4.graphics.geometry.points.Point;
import task_4.graphics.graphic_objects.primitives.Line;
import task_4.graphics.lighting.ColorLight;

import java.util.Random;
import java.util.Set;


public class Utils {
    public static Set<Line> getAxes() {
        Point origin = new Point(0, 0, 0);
        Line x = new Line(origin, new Point(100, 0, 0));
        x.setColor(new ColorLight(255, 0, 0));
        Line y = new Line(origin, new Point(0, 100, 0));
        y.setColor(new ColorLight(0, 255, 0));
        Line z = new Line(origin, new Point(0, 0, 100));
        z.setColor(new ColorLight(0, 0, 255));
        return Set.of(x, y, z);
    }

    public static ColorLight getRandomColor() {
        Random random = new Random();
        return new ColorLight(
            random.nextInt(255),
            random.nextInt(255),
            random.nextInt(255)
        );
    }
}
