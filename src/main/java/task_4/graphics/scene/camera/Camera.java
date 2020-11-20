package task_4.graphics.scene.camera;

import javafx.event.Event;
import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.geometry.points.Point;
import task_4.graphics.scene.PointConverter;
import task_4.transforms.Transform;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class Camera
    implements PointConverter
{
    private final Set<CameraEventHandler> eventHandlers = new HashSet<>();
    private final Transform transform = new Transform();

    public Camera(Collection<CameraEventHandler> eventHandlers) {
        this.eventHandlers.addAll(eventHandlers);
    }

    public Camera(CameraEventHandler... eventHandlers) {
        this.eventHandlers.addAll(Arrays.asList(eventHandlers));
    }

    public void addEventHandler(CameraEventHandler eventHandler) {
        this.eventHandlers.add(eventHandler);
    }

    public void handleEvent(Event event) {
        for (CameraEventHandler eventHandler : eventHandlers) {
            eventHandler.handle(event);
        }
    }

    public Pixel convert(Point point) {
        return transform.convert(point);
    }

    public Transform modifyTransform(Transform transform) {
        return this.transform.modify(transform);
    }

    public void comeBack(Transform transform) {
        this.transform.copyOf(transform);
    }

    abstract public static class CameraEventHandler {
        private final Transform cameraTransform;

        public CameraEventHandler(Camera camera) {
            this.cameraTransform = camera.transform;
        }

        protected Transform getCameraTransform() {
            return cameraTransform;
        }

        protected abstract void handle(Event event);
    }
}
