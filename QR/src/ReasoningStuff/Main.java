package ReasoningStuff;

import TreeStuff.Node;
import TreeStuff.Tree;

import java.util.Optional;
import java.util.ArrayList;

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
				State state = dp.processDerivatives(node.getData());
				state = setValueCorrespondenceAndDependencies(state);
				myTree.addChild(node,state);
				
		}

		private static State setValueCorrespondenceAndDependencies(State state){
				state = dp.setVCs(state);
				state = dp.setProportionals(state);
				return state;
		}
				
}
