package task_4.graphics.geometry.camera.camera_handlers;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import task_4.graphics.geometry.camera.Camera;


public class MouseEventHandler extends Camera.CameraEventHandler {
    public MouseEventHandler(Camera camera) {
        super(camera);
    }

    @Override
    protected void handle(Event event) {
        if (event instanceof MouseEvent) {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                handlePressed((MouseEvent) event);
            } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                handleReleased((MouseEvent) event);
            } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                handleDragged((MouseEvent) event);
            }
        }
    }

    private void handlePressed(MouseEvent mouseEvent) {

    }

    private void handleReleased(MouseEvent mouseEvent) {

    }

    private void handleDragged(MouseEvent mouseEvent) {

    }
}
