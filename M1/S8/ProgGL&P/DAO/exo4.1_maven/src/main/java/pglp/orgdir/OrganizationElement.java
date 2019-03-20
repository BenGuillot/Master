package pglp.orgdir;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class OrganizationElement implements Iterable<OrganizationElement> {
    public static enum TraversalStrategy {DFS, BFS};

    private TraversalStrategy traversalStrategy = TraversalStrategy.DFS;

    public void setStrategy(TraversalStrategy traversalStrategy) {
        this.traversalStrategy = traversalStrategy;
    }

    public Iterator<OrganizationElement> iterator() {
        List<OrganizationElement> list = new ArrayList();
        list.add(this);
        switch (traversalStrategy) {
            case DFS: addSubElementsDFS(list); break;
            case BFS: addSubElementsBFS(list); break;
        }
        return list.iterator();
    }

    protected abstract void addSubElementsDFS(List<OrganizationElement> list);
    protected abstract void addSubElementsBFS(List<OrganizationElement> list);

    public abstract String getDescription();
}
