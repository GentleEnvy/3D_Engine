package task_4.graphics.scene.camera.camera_handlers;

import javafx.event.Event;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.geometry.points.Point;
import task_4.graphics.scene.camera.Camera;


public class MouseEventHandler
    extends Camera.CameraEventHandler
{
    private static final double SPEED_DRAG = 1;
    private static final double SPEED_ROTATE_X = 1;
    private static final double SPEED_ROTATE_Y = 1;

    private final TranslateHandler translateHandler = new TranslateHandler();
    private final RotateHandler rotateHandler = new RotateHandler();

    public MouseEventHandler(Camera camera) {
        super(camera);
    }

    @Override
    protected void handle(Event event) {
        if (event instanceof MouseEvent) {
            MouseEvent mouseEvent = (MouseEvent) event;
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    translateHandler.handlePressed(mouseEvent);
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
                    translateHandler.handleReleased();
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    translateHandler.handleDragged(mouseEvent);
                }
            } else if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    rotateHandler.handlePressed(mouseEvent);
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
                    rotateHandler.handleReleased();
                } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    rotateHandler.handleDragged(mouseEvent);
                }
            }
        }
    }

    private class RotateHandler {
        private Point prevDrag = null;

        void handlePressed(MouseEvent mouseEvent) {
            prevDrag = new Point(
                mouseEvent.getX(),
                mouseEvent.getY(),
                0
            );
        }

        void handleReleased() {
            prevDrag = null;
        }

        void handleDragged(MouseEvent mouseEvent) {
            if (prevDrag != null) {
                double dx = mouseEvent.getX() - prevDrag.getX();
                double dy = mouseEvent.getY() - prevDrag.getY();
                getCameraTransform().modifyRotateXZ(-dx * SPEED_ROTATE_X);
                getCameraTransform().modifyRotateYZ(-dy * SPEED_ROTATE_Y);
            }
            prevDrag = new Point(
                mouseEvent.getX(),
                mouseEvent.getY(),
                0
            );
        }
    }

    private class TranslateHandler {
        private Point prevDrag = null;

        void handlePressed(MouseEvent mouseEvent) {
            prevDrag = new Point(
                mouseEvent.getX(),
                mouseEvent.getY(),
                0
            );
        }

        void handleReleased() {
            prevDrag = null;
        }

        void handleDragged(MouseEvent mouseEvent) {
            Point delta;
            Point current = new Point(
                mouseEvent.getX(),
                mouseEvent.getY(),
                0
            );
            if (prevDrag != null) {
                delta = new Point(
                    (current.getX() - prevDrag.getX()) * SPEED_DRAG,
                    (current.getY() - prevDrag.getY()) * SPEED_DRAG,
                    0
                );
                getCameraTransform().modifyTranslateX(delta.getX());
                getCameraTransform().modifyTranslateY(-delta.getY());
            }
            prevDrag = current;
        }
    }
}
