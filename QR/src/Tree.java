import java.util.ArrayList;

class Tree<T>
{
    Node<T> root;

    Tree(T rootData)
    {
        root = new Node<T>(null);
        root.setData(rootData);
        root.setChildren(new ArrayList<Node<T>>());
    }

    Node<T> addChild(Node<T> parent, T data)
    {
        Node node = new Node(parent);
        node.setData(data);
        parent.getChildren().add(node);
        return node;
    }

    void printTree(Node<T> node, String appender)
    {
        System.out.println(appender + node.toString());
        for (Node<T> each: node.getChildren())
        {
            this.printTree(each, appender + appender);
        }
    }
}
