package nl.uva.qr.tree;

import nl.uva.qr.reasoning.State;

public class Tree<T>
{
    private final Node root;
    private Node lastAddedNode;

    public Tree(State rootData)
    {
        root = new Node(null, rootData);
        root.setId("0");
        lastAddedNode = root;
    }

    public Node getRoot()
    {
        return root;
    }

    public Node addChild(Node parent, State data)
    {
        String sb = String.valueOf(parent.getId()) +
                (parent.getChildren().size() + 1);

        Node node = new Node(parent, data);
        node.setId(sb);
        parent.getChildren().add(node);
        this.lastAddedNode = node;

        return node;
    }

    public Node getLastAddedNode()
    {
        return lastAddedNode;
    }

    public void printTreeByName(Node node, String appender)
    {
        if (node.getParent() == null)
        {
            System.out.println(node.getName());
        }
        else
        {
            System.out.println(appender + node.getName());
        }
        for (Node each : node.getChildren())
        {
            this.printTreeByName(each, appender + appender);
        }
    }

    public void printTree(Node node)
    {
        printTree(node, " ");
    }
    private void printTree(Node node, String appender)
    {
        for (String curr : node.toString().split("\n"))
        {
            if (node.getParent() == null)
            {
                System.out.println(curr);
            }
            else
            {
                System.out.println(appender + curr);
            }
        }
        System.out.println("");

        for (Node each : node.getChildren())
        {
            this.printTree(each, appender + appender);
        }
    }
}
