package task_4.graphics.graphic_objects.model;

import task_4.graphics.graphic_objects.polygons.GraphicPolygon;
import task_4.graphics.scene.PointConverter;
import task_4.graphics.scene.camera.Transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


abstract public class Model {
    private final Transform transform = new Transform();

    public List<GraphicPolygon> getPolygons(PointConverter converter) {
        List<GraphicPolygon> polygons = new ArrayList<>();
        Transform transform = converter.modify(getTransform());
        for (Model child : getChildren()) {
            polygons.addAll(child.getPolygons(converter));
        }
        converter.comeBack(transform);
        return polygons;
    }

    protected final Transform getTransform() {
        return transform;
    }

    abstract protected Set<Model> getChildren();
}
