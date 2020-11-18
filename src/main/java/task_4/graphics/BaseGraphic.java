package task_4.graphics;

import task_4.graphics.geometry.camera.Camera;
import task_4.graphics.scane.Screen;
import task_4.transforms.Transform;


abstract public class BaseGraphic {
    private final Transform transform = new Transform();

    public final Transform getTransform() {
        return transform;
    }

    public final void render(Screen screen, Camera camera) {
        Transform transform = camera.modifyTransform(this.transform);
        _render(screen, camera);
        camera.comeBack(transform);
    }

    abstract protected void _render(Screen screen, Camera camera);
}
