package nl.uva.qr.reasoning;

import nl.uva.qr.tree.Node;
import nl.uva.qr.tree.Tree;

import java.util.Optional;

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
            System.out.println("Derivatives toegepast op de onderstaande node: ");
            System.out.println(node.toString());
            System.out.println("Child:");
            System.out.println(node.getChildren().get(node.getChildren().size() - 1).toString());
            runDependencies(tree, node.getChildren().get(node.getChildren().size()-1));
        });

        dp.influencePos(tree, node).ifPresent(state ->
        {
            System.out.println("I+ op de onderstaande node: ");
            System.out.println(node.toString());
            System.out.println("Child:");
            System.out.println(node.getChildren().get(node.getChildren().size() - 1).toString());
            //runDependencies(tree,node.getChildren().get(node.getChildren().size() - 1));
        });

        dp.influenceNeg(tree, node).ifPresent(state ->
        {
            System.out.println("I- op start node: ");
            System.out.println(node.toString());
            System.out.println("Child:");
            System.out.println(node.getChildren().get(node.getChildren().size() - 1).toString());
            //runDependencies(tree, node.getChildren().get(node.getChildren().size() - 1));
        });

        dp.VC(tree, node).ifPresent(state ->
        {
            System.out.println("VC op start node: ");
            System.out.println(node.toString());
            System.out.println("Child:");
            System.out.println(node.getChildren().get(node.getChildren().size() - 1).toString());
            //runDependencies(node.getChildren().get(node.getChildren().size() - 1));
        });

        dp.proportionalityPos(tree, node).ifPresent(state ->
        {
            System.out.println("P+ op start node: ");
            System.out.println(node.toString());
            System.out.println("Child:");
            System.out.println(node.getChildren().get(node.getChildren().size() - 1).toString());
            //runDependencies(node.getChildren().get(node.getChildren().size() - 1));
        });
    }
}