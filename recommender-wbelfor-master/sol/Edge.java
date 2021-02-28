package sol;

import src.INode;

public class Edge {

    public Object value;
    public INode node;

    public Edge(Object value, INode node) {
        this.value = value;
        this.node = node;
    }

}
