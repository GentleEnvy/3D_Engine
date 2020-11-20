package task_4.graphics.scene;

import javafx.scene.layout.Pane;
import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.graphic_objects.models.Model;
import task_4.graphics.graphic_objects.polygons.GraphicPolygon;
import task_4.graphics.lighting.ColorLight;
import task_4.graphics.scene.camera.Camera;
import task_4.graphics.scene.camera.Transform;

import java.util.ArrayList;
import java.util.List;


public class Scene
    extends javafx.scene.Scene
{
    private static final int DEFAULT_SCREEN_WIDTH = 1536;
    private static final int DEFAULT_SCREEN_HEIGHT = 810;

    private final Pane mainPane;
    private final List<Model> models = new ArrayList<>();
    private final Camera camera = new Camera(
        // TODO: add camera event handlers
    );

    {
        setOnMousePressed(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });
        setOnMouseReleased(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });
        setOnMouseDragged(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });
        setOnScroll(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });
    }

    public Scene(Pane pane) {
        super(pane);
        mainPane = pane;
    }

    public void addModel(Model model) {
        models.add(model);
    }

    public void render() {
        mainPane.getChildren().clear();

        Screen screen = new Screen(
            getWidth() <= 0 ? DEFAULT_SCREEN_WIDTH : (int) getWidth(),
            getHeight() <= 0 ? DEFAULT_SCREEN_HEIGHT : (int) getHeight()
        );

        Transform defaultTransform = new Transform();
        defaultTransform.modifyTranslateX(screen.getScreenWidth() / 2.0);
        defaultTransform.modifyTranslateY(screen.getScreenHeight() / 2.0);
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

        mainPane.getChildren().add(screen);
    }
}
