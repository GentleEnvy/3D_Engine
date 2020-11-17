package task_4.graphics.geometry.camera.camera_handlers;

import javafx.event.Event;
import javafx.scene.input.ScrollEvent;
import task_4.graphics.geometry.camera.BaseCameraEventHandler;
import task_4.graphics.geometry.camera.Camera;
import task_4.transforms.Transform;


public class ScrollEventHandler extends BaseCameraEventHandler {
    private static final double DEFAULT_SPEED_SCALE = 0.03;

    private final double speedScale;

    public ScrollEventHandler(Camera camera, double speedScale) {
        super(camera);
        this.speedScale = speedScale;
    }

    public ScrollEventHandler(Camera camera) {
        this(camera, DEFAULT_SPEED_SCALE);
    }

    @Override
    protected void handle(Event event) {
        if (event instanceof ScrollEvent) {
            ScrollEvent scrollEvent = (ScrollEvent) event;
            int ticks = (int) -scrollEvent.getDeltaY() / 32;
            double k = ticks <= 0 ? 1 - speedScale : 1 + speedScale;
            Transform transform = getCameraTransform();
            transform.modifyScale(k * Math.pow(k, Math.abs(ticks)));
        }
    }
}
