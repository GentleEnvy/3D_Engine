package task_4.graphics.geometry.camera;

import javafx.event.Event;
import task_4.transforms.Transform;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Camera {
    private final Set<BaseCameraEventHandler> eventHandlers = new HashSet<>();
    private final Transform transform = new Transform();

    public Camera(BaseCameraEventHandler ...eventHandlers) {
        this.eventHandlers.addAll(Arrays.asList(eventHandlers));
    }

    public Transform getTransform() {
        return transform;
    }

    public void addEventHandler(BaseCameraEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public void handleEvent(Event event) {
        for (BaseCameraEventHandler eventHandler : eventHandlers) {
            eventHandler.handle(event);
        }
    }
}
