package task_4.graphics.graphic_objects.primitives;

import task_4.graphics.graphic_objects.models.Model;
import task_4.graphics.graphic_objects.polygons.GraphicPolygon;
import task_4.graphics.graphic_objects.polygons.RealPolygon;
import task_4.graphics.scene.PointConverter;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;


abstract public class GraphicPrimitive
    extends Model
{
    @Override
    public final List<GraphicPolygon> getPolygons(PointConverter converter) {
        List<GraphicPolygon> graphicPolygons = new LinkedList<>();
        for (RealPolygon realPolygon : getPolygons()) {
            graphicPolygons.add(realPolygon.toGraphic(converter));
        }
        return graphicPolygons;
    }

    @Override
    protected final Set<Model> getChildren() {
        return Set.of();
    }

    abstract public Set<RealPolygon> getPolygons();
}
