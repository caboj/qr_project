package nl.uva.qr.tree;

import nl.uva.qr.reasoning.State;

public class Tree<T>
{
    private final Node root;

    public Tree(State rootData)
    {
        root = new Node(null);
        root.setId(0);
        root.setData(rootData);
    }

    public Node getRoot()
    {
        return root;
    }

    public State getRootData()
    {
        return root.getData();
    }

    public Node addChild(Node parent, State data)
    {
        String sb = String.valueOf(parent.getId()) +
                (parent.getChildren().size() + 1);

        Node node = new Node(parent);
        node.setData(data);
        node.setId(Integer.parseInt(sb));
        parent.getChildren().add(node);

        return node;
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

    public void printTree(Node node, String appender)
    {

        /*System.out.println(myTree.getRootData().toString());
        for (Node curr:myTree.getRoot().getChildren())
        {
            System.out.println(curr.toString());
        }
        */

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
            //System.out.println("Ik heb een kind :D");
            this.printTree(each, appender + appender);
        }
    }
}
