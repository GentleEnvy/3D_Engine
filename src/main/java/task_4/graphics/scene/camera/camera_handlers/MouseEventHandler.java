package task_4.graphics.scene.camera.camera_handlers;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.geometry.points.Point;
import task_4.graphics.scene.camera.Camera;


public class MouseEventHandler
    extends Camera.CameraEventHandler
{
    private static final double SPEED_DRAG = 1;

    private Pixel prevDrag = null;

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
        prevDrag = new Pixel(
            (int) mouseEvent.getX(),
            (int) mouseEvent.getY(),
            0
        );
    }

    private void handleReleased(MouseEvent __) {
        prevDrag = null;
    }

    private void handleDragged(MouseEvent mouseEvent) {
        Pixel delta;
        Pixel current = new Pixel(
            (int) mouseEvent.getX(),
            (int) mouseEvent.getY(),
            0
        );
        if (prevDrag != null) {
            delta = new Pixel(
                (int) ((current.getX() - prevDrag.getX()) * SPEED_DRAG),
                (int) ((current.getY() - prevDrag.getY()) * SPEED_DRAG),
                0
            );
            getCameraTransform().modifyTranslateX(delta.getX());
            getCameraTransform().modifyTranslateY(delta.getY());
            prevDrag = current;
        }
    }
}
