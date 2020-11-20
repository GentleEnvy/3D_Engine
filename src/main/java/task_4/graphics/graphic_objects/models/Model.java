package task_4.graphics.graphic_objects.models;

import task_4.graphics.graphic_objects.polygons.GraphicPolygon;
import task_4.graphics.graphic_objects.polygons.RealPolygon;
import task_4.graphics.graphic_objects.primitives.GraphicPrimitive;
import task_4.transforms.Transform;

import java.util.Set;


abstract public class Model {
    private final Transform transform = new Transform();

    public final Transform getTransform() {
        return transform;
    }

    abstract public Set<GraphicPrimitive> getPrimitives();
}
