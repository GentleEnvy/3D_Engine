package task_4.graphics.graphic_objects.models;

import java.util.HashSet;
import java.util.Set;


public class Group
    extends Model
{
    private final Set<Model> children = new HashSet<>();

    public void addChild(Model child) {
        children.add(child);
    }

    public void removeChild(Model child) {
        children.remove(child);
    }

    @Override
    protected Set<Model> getChildren() {
        return children;
    }
}
