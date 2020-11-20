package task_4.graphics.scene;

import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.geometry.points.Point;
import task_4.graphics.scene.camera.Transform;


public interface PointConverter {
    Pixel convert(Point point);

    Transform modify(Transform transform);

    void comeBack(Transform transform);
}
