package task_4.graphics.scene;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import task_4.graphics.graphic_objects.BaseGraphic;
import task_4.graphics.graphic_objects.polygons.GraphicPolygon;
import task_4.graphics.graphic_objects.polygons.Triangle;
import task_4.graphics.graphic_objects.primitives.Line;
import task_4.graphics.lighting.ColorLight;
import task_4.graphics.scene.camera.Camera;
import task_4.graphics.scene.camera.Transform;
import task_4.graphics.scene.camera.camera_handlers.MouseEventHandler;
import task_4.graphics.scene.camera.camera_handlers.ScrollEventHandler;

import java.util.*;

import static task_4.Utils.getAxes;
import static task_4.Utils.triangulation;


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
//                    renderPolygons();
//                }
//            )
//        );
//        timeline.setCycleCount(-1);
//        timeline.play();
    }

    public void setVisibleAxes(boolean visibleAxes) {
        this.visibleAxes = visibleAxes;
    }

    public void setBackgroundColor(ColorLight backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void addGraphic(BaseGraphic graphic) {
        graphics.add(graphic);
    }

    public void render() {
        getChildren().clear();
        Screen screen = createScreen();
        Transform oldTransform = toScreenCenter(screen);
        //renderPolygons(screen);
        renderTriangle(screen);
        camera.comeBack(oldTransform);
        getChildren().add(screen);
    }

    private Screen createScreen() {
        Screen screen = new Screen(
            getWidth() <= 0 ? DEFAULT_SCREEN_WIDTH : (int) getWidth(),
            getHeight() <= 0 ? DEFAULT_SCREEN_HEIGHT : (int) getHeight()
        );
        screen.setBackground(new Background(new BackgroundFill(
            backgroundColor.toFxColor(), CornerRadii.EMPTY, Insets.EMPTY
        )));
        return screen;
    }

    private Transform toScreenCenter(Screen screen) {
        Transform defaultTransform = new Transform();
        defaultTransform.modifyTranslateX(screen.getScreenWidth() / 2.0);
        defaultTransform.modifyTranslateY(-screen.getScreenHeight() / 2.0);
        return camera.modify(defaultTransform);
    }

    private void drawAxes(Screen screen) {
        for (Line axis : getAxes()) {
            for (GraphicPolygon polygon : axis.getPolygons(camera)) {
                polygon.render(screen);
            }
        }
    }

    private void renderPolygons(Screen screen) {
        List<GraphicPolygon> polygons = new ArrayList<>();
        for (BaseGraphic graphic : graphics) {
            polygons.addAll(graphic.getPolygons(camera));
        }

        polygons.sort(GraphicPolygon::compareTo);
        for (GraphicPolygon polygon : polygons) {
            polygon.render(screen);
        }

        if (visibleAxes) {
            drawAxes(screen);
        }
    }

    private void renderTriangle(Screen screen) {
        Set<GraphicPolygon> polygons = new HashSet<>();
        for (BaseGraphic graphic : graphics) {
            polygons.addAll(graphic.getPolygons(camera));
        }

        List<Triangle> triangles = new ArrayList<>();
        for (GraphicPolygon polygon : polygons) {
            triangles.addAll(triangulation(polygon, 70));
        }
        triangles.sort(Comparator.reverseOrder());

        for (Triangle triangle : triangles) {
            triangle.render(screen);
        }

        if (visibleAxes) {
            drawAxes(screen);
        }
    }
}
