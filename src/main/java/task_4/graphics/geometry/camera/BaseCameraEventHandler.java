package task_4.graphics.geometry.camera;

import javafx.event.Event;
import task_4.transforms.Transform;


abstract public class BaseCameraEventHandler {
    private final Camera camera;

    public BaseCameraEventHandler(Camera camera) {
        this.camera = camera;
    }

    protected Transform getCameraTransform() {
        return camera.getTransform();
    }

    protected abstract void handle(Event event);
}
