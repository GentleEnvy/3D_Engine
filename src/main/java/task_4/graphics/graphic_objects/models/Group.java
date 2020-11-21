package task_4.graphics.graphic_objects.models;

import task_4.graphics.graphic_objects.BaseGraphic;

import java.util.HashSet;
import java.util.Set;


public class Group
    extends Model
{
    private final Set<BaseGraphic> children = new HashSet<>();

    public void addChild(BaseGraphic child) {
        children.add(child);
    }

    public void removeChild(BaseGraphic child) {
        children.remove(child);
    }

    @Override
    protected Set<BaseGraphic> getChildren() {
        return children;
    }
}
