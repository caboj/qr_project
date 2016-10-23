package nl.uva.qr.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import nl.uva.qr.reasoning.State;

public class Node
{
    private final State data;

    private final List<Node> children = new ArrayList<>();
    private final Function<State, List<Node>> action;

    public Node(State data, Function<State, List<Node>> action)
    {
        this.data = data;
        this.action = action;
        if(action!=null)
        {
            apply();
        }
    }

    private void apply()
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

    public String toString(String prefix)
    {
        return this.data.toString(prefix);
    }

}
