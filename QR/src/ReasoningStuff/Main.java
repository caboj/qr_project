package ReasoningStuff;

import TreeStuff.Node;
import TreeStuff.Tree;

class Main
{
    static Dependencies dp;
    static Tree myTree;
    public static void main(String[] args)
    {
        dp = new Dependencies();
        myTree = new Tree(
                    new State(
                            new String [][]{
                                { "0", "+"},
                                { "0", "0"},
                                { "0", "0"},
                                { "0", "0"},
                                { "0", "0"} }));

        //testTree();

        System.out.println("Start tree met alleen onderstaande root:");
        myTree.printTree(myTree.getRoot(), "\t");
        System.out.println("---------------------");
        runDependencies(myTree.getRoot());
        System.out.println("---------------------");
        System.out.println("Tree na het uitvoeren van alle dependencies op de root node:");
        myTree.printTree(myTree.getRoot(),"\t");
    }

    /***
     * The dependencies are runned depth first.
     * Every new state is added as a child and all the dependencies are called again.
     * @param node Start node to run dependencies on.
     */
    private static void runDependencies(Node node)
    {
				dp.processDerivatives(node)
                .ifPresent(state->
                {
                    System.out.println("derivative processed on state: ");
                    System.out.println(node.toString());
                    myTree.addChild(node, state);
                    System.out.println("Child:");
                    System.out.println(node.getChildren().get(node.getChildren().size()-1).toString());
                    runDependencies(node.getChildren().get(node.getChildren().size()-1));
                });
				dp.influencePos(node)
                .ifPresent(state->
                {
                    System.out.println("I+ op de onderstaande node: ");
                    System.out.println(node.toString());
                    myTree.addChild(node, state);
                    System.out.println("Child:");
                    System.out.println(node.getChildren().get(node.getChildren().size()-1).toString());
                    runDependencies(node.getChildren().get(node.getChildren().size()-1));
                });
        dp.influenceNeg(node)
                .ifPresent(state ->
                {
                    System.out.println("I- op start node: ");
                    System.out.println(node.toString());
                    myTree.addChild(node, state);
                    System.out.println("Child:");
                    System.out.println(node.getChildren().get(node.getChildren().size()-1).toString());
                    runDependencies(node.getChildren().get(node.getChildren().size()-1));
                });
        dp.VC(node)
                .ifPresent(state->
                {
                    System.out.println("VC op start node: ");
                    System.out.println(node.toString());
                    myTree.addChild(node, state);
                    System.out.println("Child:");
                    System.out.println(node.getChildren().get(node.getChildren().size()-1).toString());
                    runDependencies(node.getChildren().get(node.getChildren().size()-1));
                }
                );
    }

    /**
     * Test case for the tree class
     */
    private static void testTree()
    {
        String[][] parameters = new String [][] {
                { "+", "0"},
                { "+", "0"},
                { "0", "0"},
                { "0", "0"},
                { "0", "0"} };

        String[][] parametersChild = new String [][] {
                { "+", "1"},
                { "+", "1"},
                { "+", "1"},
                { "+", "1"},
                { "+", "1"} };

        State startState = new State(parameters);
        State childState = new State(parametersChild);
        State childState2 = new State(parametersChild);

        State childState11 = new State(parameters);

        Node childNode = myTree.addChild(myTree.getRoot(), childState);
        Node childNode2 = myTree.addChild(myTree.getRoot(), childState2);

        Node childChildNode = myTree.addChild(childNode, childState11);
        myTree.addChild(childNode2, childState11);
        myTree.addChild(childNode2, childState);

        myTree.addChild(childNode, childState);
        myTree.addChild(childNode, startState);
        myTree.addChild(childNode, childState);

        myTree.printTreeByName(myTree.getRoot(), " ");
        System.out.println();
        myTree.printTree(myTree.getRoot(),"\t");

    }
}
