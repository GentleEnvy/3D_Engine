package task_4.graphics.graphic_objects.primitives;

import task_4.graphics.graphic_objects.BaseGraphic;
import task_4.graphics.graphic_objects.models.Model;
import task_4.graphics.graphic_objects.polygons.GraphicPolygon;
import task_4.graphics.graphic_objects.polygons.RealPolygon;
import task_4.graphics.lighting.ColorLight;
import task_4.graphics.scene.PointConverter;
import task_4.graphics.scene.camera.Transform;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;


abstract public class GraphicPrimitive
    extends BaseGraphic
{
    private ColorLight color = null;
    private Boolean isFill = null;

    @Override
    protected final List<GraphicPolygon> _getPolygons(PointConverter converter) {
        List<GraphicPolygon> graphicPolygons = new LinkedList<>();
        for (RealPolygon realPolygon : getPolygons()) {
            if (color != null) {
                realPolygon.setColor(color);
            }
            if (isFill != null) {
                realPolygon.setFill(isFill);
            }
            graphicPolygons.add(realPolygon.toGraphic(converter));
        }
        return graphicPolygons;
    }

    public void setColor(ColorLight color) {
        this.color = color;
    }

    public void setFill(boolean isFill) {
        this.isFill = isFill;
    }

    abstract public Set<RealPolygon> getPolygons();
}
