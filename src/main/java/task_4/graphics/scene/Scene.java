package task_4.graphics.scene;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import task_4.graphics.graphic_objects.models.Model;
import task_4.graphics.graphic_objects.polygons.GraphicPolygon;
import task_4.graphics.scene.camera.Camera;
import task_4.graphics.scene.camera.Transform;
import task_4.graphics.scene.camera.camera_handlers.MouseEventHandler;
import task_4.graphics.scene.camera.camera_handlers.ScrollEventHandler;

import java.util.ArrayList;
import java.util.List;


public class Scene
    extends Pane
{
    private static final int DEFAULT_SCREEN_WIDTH = 1536;
    private static final int DEFAULT_SCREEN_HEIGHT = 810;

    private final List<Model> models = new ArrayList<>();
    private final Camera camera = new Camera();

    {
        camera.addEventHandler(new MouseEventHandler(camera));
        camera.addEventHandler(new ScrollEventHandler(camera));
    }

    {
        setOnMousePressed(camera::handleEvent);
        setOnMouseDragged(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });
        setOnMouseReleased(camera::handleEvent);

        setOnScroll(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });
    }

    public Scene() {
        super();
    }

    public void addModel(Model model) {
        models.add(model);
    }

    public void render() {
        getChildren().clear();

        Screen screen = new Screen(
            getWidth() <= 0 ? DEFAULT_SCREEN_WIDTH : (int) getWidth(),
            getHeight() <= 0 ? DEFAULT_SCREEN_HEIGHT : (int) getHeight()
        );

        Transform defaultTransform = new Transform();
        defaultTransform.modifyTranslateX(screen.getScreenWidth() / 2.0);
        defaultTransform.modifyTranslateY(-screen.getScreenHeight() / 2.0);
        Transform oldTransform = camera.modify(defaultTransform);

        List<GraphicPolygon> polygons = new ArrayList<>();
        for (Model model : models) {
            polygons.addAll(model.getPolygons(camera));
        }
        polygons.sort(GraphicPolygon::compareTo);
        for (GraphicPolygon polygon : polygons) {
            screen.fromFxNode(polygon.toFxPolygon());
        }

        camera.comeBack(oldTransform);

        getChildren().add(screen);
    }
}
