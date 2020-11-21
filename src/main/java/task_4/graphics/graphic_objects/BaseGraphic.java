package task_4.graphics.graphic_objects;

import task_4.graphics.graphic_objects.models.Model;
import task_4.graphics.graphic_objects.polygons.GraphicPolygon;
import task_4.graphics.graphic_objects.primitives.Line;
import task_4.graphics.scene.PointConverter;
import task_4.graphics.scene.camera.Transform;

import java.util.ArrayList;
import java.util.List;

import static task_4.Utils.getAxes;


abstract public class BaseGraphic {
    private final Transform transform = new Transform();

    private boolean visibleAxes = false;

    public void setVisibleAxes(boolean visibleAxes) {
        this.visibleAxes = visibleAxes;
    }

    public final Transform getTransform() {
        return transform;
    }

    public final List<GraphicPolygon> getPolygons(PointConverter converter) {
        Transform transform = converter.modify(getTransform());
        List<GraphicPolygon> polygons = new ArrayList<>(_getPolygons(converter));
        if (visibleAxes) {
            for (Line axis : getAxes()) {
                polygons.addAll(axis.getPolygons(converter));
            }
        }
        converter.comeBack(transform);
        return polygons;
    }

    abstract protected List<GraphicPolygon> _getPolygons(PointConverter converter);
}
