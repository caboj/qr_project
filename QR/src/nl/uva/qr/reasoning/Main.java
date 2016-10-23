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
                .withHeight(new Quantity(QuantityType.HEIGHT, Magnitude.OFF, Derivative.INCREASING))
                .withPressure(new Quantity(QuantityType.PRESSURE,Magnitude.OFF, Derivative.STABLE))
                .build());


        System.out.println("Start tree met alleen onderstaande root:");
        myTree.printTree(myTree.getRoot());
        System.out.println("---------------------");

        runDependencies(myTree.getRoot());
        System.out.println("---------------------");
        System.out.println("Tree na het uitvoeren van alle dependencies op de root node:");
        myTree.printTree(myTree.getRoot());
    }

    private static void runDependencies(final Node node)
    {
        dp.processDerivatives(node).ifPresent(state->
        {
            System.out.println("Derivatives toegepast op de onderstaande node: ");
            System.out.println(node.toString());
            myTree.addChild(node, state);
            System.out.println("Child:");
            System.out.println(node.getChildren().get(node.getChildren().size() - 1).toString());
            //runDependencies(node.getChildren().get(node.getChildren().size()-1));
        });

        dp.influencePos(node).ifPresent(state ->
        {
            System.out.println("I+ op de onderstaande node: ");
            System.out.println(node.toString());
            myTree.addChild(node, state);
            System.out.println("Child:");
            System.out.println(node.getChildren().get(node.getChildren().size() - 1).toString());
            //runDependencies(node.getChildren().get(node.getChildren().size() - 1));
        });

    }
    /***
     * The dependencies are runned depth first.
     * Every new state is added as a child and all the dependencies are called again.
     * @param node Start node to run dependencies on.
     */
    /*private static void runDependencies(Node node)
    {
        dp.processDerivatives(node).forEach(state ->
        {
            System.out.println("derivative op de onderstaande node: ");
            System.out.println(node.toString());
            myTree.addChild(node, state);
            System.out.println("Child:");
            System.out.println(node.getChildren().get(node.getChildren().size() - 1).toString());
            //runDependencies(node.getChildren().get(node.getChildren().size() - 1));
        });


        dp.influencePos(node)
                .ifPresent(state ->
                {
                    System.out.println("I+ op de onderstaande node: ");
                    System.out.println(node.toString());
                    myTree.addChild(node, state);
                    System.out.println("Child:");
                    System.out.println(node.getChildren().get(node.getChildren().size() - 1).toString());
                    //runDependencies(node.getChildren().get(node.getChildren().size() - 1));
                });
        dp.influenceNeg(node)
                .ifPresent(state ->
                {
                    System.out.println("I- op start node: ");
                    System.out.println(node.toString());
                    myTree.addChild(node, state);
                    System.out.println("Child:");
                    System.out.println(node.getChildren().get(node.getChildren().size() - 1).toString());
                    //runDependencies(node.getChildren().get(node.getChildren().size() - 1));
                });
        dp.VC(node)
                .ifPresent(state ->
                {
                    System.out.println("VC op start node: ");
                    System.out.println(node.toString());
                    myTree.addChild(node, state);
                    System.out.println("Child:");
                    System.out.println(node.getChildren().get(node.getChildren().size() - 1).toString());
                    //runDependencies(node.getChildren().get(node.getChildren().size() - 1));
                });
    }*/

    /**
     * Test case for the tree class
     */
    private static void testTree()
    {
        State startState = State.builder()
                .withInflow(new Quantity(QuantityType.INFLOW,Magnitude.POSITIVE, Derivative.STABLE))
                .withOutflow(new Quantity(QuantityType.OUTFLOW, Magnitude.POSITIVE, Derivative.STABLE))
                .withVolume(new Quantity(QuantityType.VOLUME, Magnitude.OFF, Derivative.STABLE))
                .withHeight(new Quantity(QuantityType.HEIGHT, Magnitude.OFF, Derivative.STABLE))
                .withPressure(new Quantity(QuantityType.PRESSURE, Magnitude.OFF, Derivative.STABLE))
                .build();

        State childState = State.builder()
                .withInflow(new Quantity(QuantityType.INFLOW, Magnitude.POSITIVE, Derivative.INCREASING))
                .withOutflow(new Quantity(QuantityType.OUTFLOW, Magnitude.POSITIVE, Derivative.INCREASING))
                .withVolume(new Quantity(QuantityType.VOLUME, Magnitude.OFF, Derivative.INCREASING))
                .withHeight(new Quantity(QuantityType.HEIGHT, Magnitude.OFF, Derivative.INCREASING))
                .withPressure(new Quantity(QuantityType.PRESSURE, Magnitude.OFF, Derivative.INCREASING))
                .build();

        State childState2 = State.builder()
                .withInflow(new Quantity(QuantityType.INFLOW, Magnitude.POSITIVE, Derivative.INCREASING))
                .withOutflow(new Quantity(QuantityType.OUTFLOW, Magnitude.POSITIVE, Derivative.INCREASING))
                .withVolume(new Quantity(QuantityType.VOLUME, Magnitude.OFF, Derivative.INCREASING))
                .withHeight(new Quantity(QuantityType.HEIGHT, Magnitude.OFF, Derivative.INCREASING))
                .withPressure(new Quantity(QuantityType.PRESSURE, Magnitude.OFF, Derivative.INCREASING))
                .build();

        State childState11 = State.builder()
                .withInflow(new Quantity(QuantityType.INFLOW,Magnitude.POSITIVE, Derivative.STABLE))
                .withOutflow(new Quantity(QuantityType.OUTFLOW, Magnitude.POSITIVE, Derivative.STABLE))
                .withVolume(new Quantity(QuantityType.VOLUME, Magnitude.OFF, Derivative.STABLE))
                .withHeight(new Quantity(QuantityType.HEIGHT, Magnitude.OFF, Derivative.STABLE))
                .withPressure(new Quantity(QuantityType.PRESSURE, Magnitude.OFF, Derivative.STABLE))
                .build();

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
        myTree.printTree(myTree.getRoot());
    }
}
