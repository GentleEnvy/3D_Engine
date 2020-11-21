package task_4.graphics.scene;

import javafx.scene.layout.Pane;
import task_4.graphics.geometry.points.Pixel;
import task_4.graphics.graphic_objects.models.Model;
import task_4.graphics.graphic_objects.polygons.GraphicPolygon;
import task_4.graphics.lighting.ColorLight;
import task_4.graphics.scene.camera.Camera;
import task_4.graphics.scene.camera.Transform;
import task_4.graphics.scene.camera.camera_handlers.MouseEventHandler;

import java.util.ArrayList;
import java.util.List;


public class Scene {
    private static final int DEFAULT_SCREEN_WIDTH = 1536;
    private static final int DEFAULT_SCREEN_HEIGHT = 810;

    private final Pane pane = new Pane();
    private final List<Model> models = new ArrayList<>();
    private final Camera camera = new Camera();

    {
        camera.addEventHandler(new MouseEventHandler(camera));
    }

    {
//        setOnMousePressed(mouseEvent -> {
//            camera.handleEvent(mouseEvent);
//            render();
//        });
//        setOnMouseReleased(mouseEvent -> {
//            camera.handleEvent(mouseEvent);
//            render();
//        });
//        setOnMouseDragged(mouseEvent -> {
//            System.out.println(mouseEvent);
//            camera.handleEvent(mouseEvent);
//            render();
//        });
//
//        setOnScroll(mouseEvent -> {
//            camera.handleEvent(mouseEvent);
//            render();
//        });
    }

    public Scene() {
        pane.setOnMousePressed(mouseEvent -> {
            camera.handleEvent(mouseEvent);
        });
        pane.setOnMouseDragged(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });
        pane.setOnMouseReleased(mouseEvent -> {
            camera.handleEvent(mouseEvent);
        });

        pane.setOnScroll(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });
    }

    public void addModel(Model model) {
        models.add(model);
    }

    public void render() {
        pane.getChildren().clear();

        Screen screen = new Screen(
            pane.getWidth() <= 0 ? DEFAULT_SCREEN_WIDTH : (int) pane.getWidth(),
            pane.getHeight() <= 0 ? DEFAULT_SCREEN_HEIGHT : (int) pane.getHeight()
        );

        Transform defaultTransform = new Transform();
        defaultTransform.modifyTranslateX(screen.getScreenWidth() / 2.0);
        defaultTransform.modifyTranslateY(screen.getScreenHeight() / 2.0);
        defaultTransform.modifyRotateX(30);
        defaultTransform.modifyRotateY(-10);
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

        pane.getChildren().add(screen);
    }

    public Pane getPane() {
        return pane;
    }
}
