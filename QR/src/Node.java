import java.util.ArrayList;
import java.util.List;

class Node<T>
{
    private T data;
    private List<Node<T>> children;
    private final Node parent;

    Node(Node parent)
    {
        this.parent = parent;
    }

    T getData()
    {
        return data;
    }

    void setData(T data)
    {
        this.data = data;
    }

    void setChildren(ArrayList <Node<T>> children)
    {
        this.children = children;
    }

    List<Node<T>> getChildren()
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
    void printNode()
    {
        System.out.println(data.toString());
    }
}
