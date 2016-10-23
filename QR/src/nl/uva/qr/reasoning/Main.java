package nl.uva.qr.reasoning;

import nl.uva.qr.tree.Node;
import nl.uva.qr.tree.Tree;

class Main
{
    static Dependencies dp;
    static Tree myTree;

    public static void main(String[] args)
    {
        dp = new Dependencies();
        myTree = new Tree(State.builder()
                .withInflow(new Quantity(QuantityType.INFLOW, Magnitude.OFF, Derivative.INCREASING))
                .withOutflow(new Quantity(QuantityType.OUTFLOW, Magnitude.OFF, Derivative.STABLE))
                .withVolume(new Quantity(QuantityType.VOLUME, Magnitude.OFF, Derivative.STABLE))
                .withHeight(new Quantity(QuantityType.HEIGHT, Magnitude.OFF, Derivative.STABLE))
                .withPressure(new Quantity(QuantityType.PRESSURE,Magnitude.OFF, Derivative.STABLE))
                .build());


        System.out.println("Start tree met alleen onderstaande root:");
        myTree.printTree(myTree.getRoot());
        System.out.println("---------------------");

        runDependencies(myTree, myTree.getRoot());
        System.out.println("---------------------");
        System.out.println("Tree na het uitvoeren van alle dependencies op de root node:");
        myTree.printTree(myTree.getRoot());
        //myTree.printTreeByName(myTree.getRoot()," ");


    }

    /***
     * The dependencies are runned depth first.
     * Every new state is added as a child and all the dependencies are called again.
     * @param node Start node to run dependencies on.
     */
    private static void runDependencies(Tree tree, final Node node)
    {
        dp.processDerivatives(tree, node).ifPresent(state ->
        {
           /* dp.influencePos(tree, tree.getLastAddedNode());
            dp.influenceNeg(tree, tree.getLastAddedNode());
            dp.proportionalityPos(tree, tree.getLastAddedNode());
            dp.VC(tree, tree.getLastAddedNode());*/

            dp.influencePos(tree, node.getChildren().get(node.getChildren().size()-1));
            dp.influenceNeg(tree, node.getChildren().get(node.getChildren().size()-1));
            dp.proportionalityPos(tree, tree.getLastAddedNode());
            dp.VC(tree, tree.getLastAddedNode());

            runDependencies(tree, tree.getLastAddedNode());
        });

        dp.influencePos(tree, tree.getLastAddedNode()).ifPresent(state -> System.out.println("I+ done"));

        dp.influenceNeg(tree, tree.getLastAddedNode()).ifPresent(state -> System.out.println("I- done"));

        dp.VC(tree, tree.getLastAddedNode()).ifPresent(state -> System.out.println("VC(MAX) of VC(0) done"));

        dp.proportionalityPos(tree, tree.getLastAddedNode()).ifPresent(state -> System.out.println("P+ done"));
    }
}