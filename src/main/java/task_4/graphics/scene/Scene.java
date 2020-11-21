package task_4.graphics.scene;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import task_4.graphics.graphic_objects.BaseGraphic;
import task_4.graphics.graphic_objects.models.Model;
import task_4.graphics.graphic_objects.polygons.GraphicPolygon;
import task_4.graphics.graphic_objects.primitives.Line;
import task_4.graphics.lighting.ColorLight;
import task_4.graphics.scene.camera.Camera;
import task_4.graphics.scene.camera.Transform;
import task_4.graphics.scene.camera.camera_handlers.MouseEventHandler;
import task_4.graphics.scene.camera.camera_handlers.ScrollEventHandler;

import java.util.ArrayList;
import java.util.List;

import static task_4.Utils.getAxes;


public class Scene
    extends Pane
{
    private static final int DEFAULT_SCREEN_WIDTH = 1536;
    private static final int DEFAULT_SCREEN_HEIGHT = 810;

    private final List<BaseGraphic> graphics = new ArrayList<>();
    private final Camera camera = new Camera();

    {
        camera.addEventHandler(new MouseEventHandler(camera));
        camera.addEventHandler(new ScrollEventHandler(camera));
    }

    private boolean visibleAxes = false;
    private ColorLight backgroundColor = new ColorLight(70, 70, 70);

    {
        setOnMousePressed(camera::handleEvent);
        setOnMouseDragged(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });
        setOnMouseReleased(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });

        setOnScroll(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });
    }

    {
//        Timeline timeline = new Timeline(
//            new KeyFrame(Duration.millis(10),
//                e -> {
//                    Transform transform = new Transform();
//
//                    transform.modifyRotateXZ(1);
//
//                    camera.modify(transform);
//                    render();
//                }
//            )
//        );
//        timeline.setCycleCount(-1);
//        timeline.play();
    }

    public void setVisibleAxes(boolean visibleAxes) {
        this.visibleAxes = visibleAxes;
    }

    public ColorLight getBackgroundColor() {
        return backgroundColor;
    }

    public void addGraphic(BaseGraphic model) {
        graphics.add(model);
    }

    public void render() {
        getChildren().clear();

        Screen screen = new Screen(
            getWidth() <= 0 ? DEFAULT_SCREEN_WIDTH : (int) getWidth(),
            getHeight() <= 0 ? DEFAULT_SCREEN_HEIGHT : (int) getHeight()
        );
        screen.setBackground(new Background(new BackgroundFill(
            backgroundColor.toFxColor(), CornerRadii.EMPTY, Insets.EMPTY
        )));

        Transform defaultTransform = new Transform();
        defaultTransform.modifyTranslateX(screen.getScreenWidth() / 2.0);
        defaultTransform.modifyTranslateY(-screen.getScreenHeight() / 2.0);
        Transform oldTransform = camera.modify(defaultTransform);

        List<GraphicPolygon> polygons = new ArrayList<>();
        for (BaseGraphic model : graphics) {
            polygons.addAll(model.getPolygons(camera));
        }
        polygons.sort(GraphicPolygon::compareTo);
        for (GraphicPolygon polygon : polygons) {
            screen.fromFxNode(polygon.toFx());
        }

        if (visibleAxes) {
            for (Line axis : getAxes()) {
                for (GraphicPolygon polygon : axis.getPolygons(camera)) {
                    screen.fromFxNode(polygon.toFx());
                }
            }
        }

        camera.comeBack(oldTransform);

        getChildren().add(screen);
    }

    private List<GraphicPolygon> createBackground() {
        return null;
    }
}
