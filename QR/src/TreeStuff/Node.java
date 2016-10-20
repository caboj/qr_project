package TreeStuff;

import java.util.ArrayList;
import java.util.List;
import ReasoningStuff.State;

public class Node
{
    private State data;
    private String name;
    private int id;
    private List<Node> children;
    private final Node parent;

    public Node(Node parent)
    {
        this.parent = parent;
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

    void setData(State data)
    {
        this.data = data;
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
