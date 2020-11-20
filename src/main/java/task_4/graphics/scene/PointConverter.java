package task_4.graphics.scene;

import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.geometry.points.Point;


public interface PointConverter {
    Pixel convert(Point point);
}
