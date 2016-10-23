package nl.uva.qr.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import nl.uva.qr.reasoning.State;

public class Node
{
    private final State data;
    private String name;
    private String id;

    private final List<Node> children = new ArrayList<>();
    private final Function<State, List<Node>> action;

    public Node(State data, Function<State, List<Node>> action)
    {
        this.data = data;
        this.action = action;
        this.name = "q";
        if(action!=null)
        {
            apply();
        }
    }

    public void apply()
    {
        children.addAll(action.apply(data));
    }

    public List<Node> getChildren()
    {
        return children;
    }

    public State getData()
    {
        return data;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
        this.name =name + id;
    }

    public String getName()
    {
        return name;
    }

    public String toString(String prefix)
    {
        return this.data.toString(prefix);
    }

    private static void printChildren(List<Node> list)
    {
        list.forEach(System.out::println);
    }
}
