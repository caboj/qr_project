package ReasoningStuff;

import TreeStuff.Node;
import TreeStuff.Tree;

class Main
{
    public static void main(String[] args)
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


        Tree myTree = new Tree(startState);

        Node childNode = myTree.addChild(myTree.getRoot(), childState);
        Node childNode2 = myTree.addChild(myTree.getRoot(), childState2);

        Node childChildNode = myTree.addChild(childNode, childState11);
        myTree.addChild(childNode2, childState11);
        myTree.addChild(childNode2, childState);

        myTree.addChild(childNode, childState);
        myTree.addChild(childNode, childState);
        myTree.addChild(childNode, childState);

        /*
        myTree.addChild(childNode, childState);
        */

        myTree.printTreeByName(myTree.getRoot(), " ");
        System.out.println();
        myTree.printTree(myTree.getRoot(),"\t");

        /*System.out.println(myTree.getRootData().toString());
        for (Node curr:myTree.getRoot().getChildren())
        {
            System.out.println(curr.toString());
        }*/

        /*
        ReasoningStuff.Dependencies dependencies = new ReasoningStuff.Dependencies();


        System.out.println("Start state:\n" + startState.toString());

        dependencies.influencePos(startState);
        System.out.println("Next state:\n" + startState.getNextState().toString());

        dependencies.influenceNeg(startState.getNextState());
        System.out.println("Third state: \n" + startState.getNextState().getNextState().toString());
        */
    }
}
