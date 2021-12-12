package day12;

import java.util.ArrayList;
import java.util.List;

public class Node {
    List<Node> neighborNodes = new ArrayList<>();
    private String name;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Node> getNeighborNodes() {
        return neighborNodes;
    }

    public void add(Node node) {
        neighborNodes.add(node);
    }

    public boolean isNameSame(String name) {
        return this.name.equals(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
