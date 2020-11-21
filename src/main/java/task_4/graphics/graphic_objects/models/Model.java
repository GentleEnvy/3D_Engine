package task_4.graphics.graphic_objects.models;

import task_4.graphics.geometry.points.Point;
import task_4.graphics.graphic_objects.BaseGraphic;
import task_4.graphics.graphic_objects.polygons.GraphicPolygon;
import task_4.graphics.graphic_objects.primitives.Line;
import task_4.graphics.lighting.ColorLight;
import task_4.graphics.scene.PointConverter;
import task_4.graphics.scene.camera.Transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static task_4.Utils.getAxes;


abstract public class Model
    extends BaseGraphic
{
    @Override
    protected List<GraphicPolygon> _getPolygons(PointConverter converter) {
        List<GraphicPolygon> polygons = new ArrayList<>();
        Transform transform = converter.modify(getTransform());
        for (BaseGraphic child : getChildren()) {
            polygons.addAll(child.getPolygons(converter));
        }
        converter.comeBack(transform);
        return polygons;
    }

    abstract protected Set<BaseGraphic> getChildren();
}
