package nl.uva.qr.tree;

import java.util.ArrayList;
import java.util.List;
import nl.uva.qr.reasoning.State;

public class Node
{
    private final State data;
    private String name;
    private int id;
    private List<Node> children;
    private final Node parent;

    public Node(Node parent, State data)
    {
        this.parent = parent;
        this.data = data;
        this.name = "q";
        this.children = new ArrayList<>();

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
        this.name =name + id;
    }

    public String getName()
    {
        return name;
    }

    public State getData()
    {
        return data;
    }


    public List<Node> getChildren()
    {
        return children;
    }

    Node getParent()
    {
        return parent;
    }

    public String toString()
    {
        return this.data.toString();
    }
}
