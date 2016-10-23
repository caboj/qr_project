package nl.uva.qr.reasoning;

import nl.uva.qr.tree.Node;

import java.util.ArrayList;
import java.util.List;

class Main
{
    private static Dependencies dp;

    public static void main(String[] args)
    {
        dp = new Dependencies();

        Node node = new Node(null, null);

        node.getChildren().add(execute(State.builder()
                .withInflow(new Quantity(QuantityType.INFLOW, Magnitude.OFF, Derivative.INCREASING))
                .withOutflow(new Quantity(QuantityType.OUTFLOW, Magnitude.OFF, Derivative.STABLE))
                .withVolume(new Quantity(QuantityType.VOLUME, Magnitude.OFF, Derivative.STABLE))
                .withHeight(new Quantity(QuantityType.HEIGHT, Magnitude.OFF, Derivative.STABLE))
                .withPressure(new Quantity(QuantityType.PRESSURE, Magnitude.OFF, Derivative.STABLE))
                .build()));

        node.getChildren().add(execute(State.builder()
                .withInflow(new Quantity(QuantityType.INFLOW, Magnitude.POSITIVE, Derivative.INCREASING))
                .withOutflow(new Quantity(QuantityType.OUTFLOW, Magnitude.OFF, Derivative.STABLE))
                .withVolume(new Quantity(QuantityType.VOLUME, Magnitude.OFF, Derivative.STABLE))
                .withHeight(new Quantity(QuantityType.HEIGHT, Magnitude.OFF, Derivative.STABLE))
                .withPressure(new Quantity(QuantityType.PRESSURE, Magnitude.OFF, Derivative.STABLE))
                .build()));

        node.getChildren().add(execute(State.builder()
                .withInflow(new Quantity(QuantityType.INFLOW, Magnitude.POSITIVE, Derivative.STABLE))
                .withOutflow(new Quantity(QuantityType.OUTFLOW, Magnitude.OFF, Derivative.STABLE))
                .withVolume(new Quantity(QuantityType.VOLUME, Magnitude.OFF, Derivative.STABLE))
                .withHeight(new Quantity(QuantityType.HEIGHT, Magnitude.OFF, Derivative.STABLE))
                .withPressure(new Quantity(QuantityType.PRESSURE, Magnitude.OFF, Derivative.STABLE))
                .build()));

        node.getChildren().add(execute(State.builder()
                .withInflow(new Quantity(QuantityType.INFLOW, Magnitude.POSITIVE, Derivative.DECREASING))
                .withOutflow(new Quantity(QuantityType.OUTFLOW, Magnitude.OFF, Derivative.STABLE))
                .withVolume(new Quantity(QuantityType.VOLUME, Magnitude.OFF, Derivative.STABLE))
                .withHeight(new Quantity(QuantityType.HEIGHT, Magnitude.OFF, Derivative.STABLE))
                .withPressure(new Quantity(QuantityType.PRESSURE, Magnitude.OFF, Derivative.STABLE))
                .build()));

        node.getChildren().add(execute(State.builder()
                .withInflow(new Quantity(QuantityType.INFLOW, Magnitude.OFF, Derivative.DECREASING))
                .withOutflow(new Quantity(QuantityType.OUTFLOW, Magnitude.OFF, Derivative.STABLE))
                .withVolume(new Quantity(QuantityType.VOLUME, Magnitude.OFF, Derivative.STABLE))
                .withHeight(new Quantity(QuantityType.HEIGHT, Magnitude.OFF, Derivative.STABLE))
                .withPressure(new Quantity(QuantityType.PRESSURE, Magnitude.OFF, Derivative.STABLE))
                .build()));

        node.getChildren().add(execute(State.builder()
                .withInflow(new Quantity(QuantityType.INFLOW, Magnitude.OFF, Derivative.STABLE))
                .withOutflow(new Quantity(QuantityType.OUTFLOW, Magnitude.OFF, Derivative.STABLE))
                .withVolume(new Quantity(QuantityType.VOLUME, Magnitude.OFF, Derivative.STABLE))
                .withHeight(new Quantity(QuantityType.HEIGHT, Magnitude.OFF, Derivative.STABLE))
                .withPressure(new Quantity(QuantityType.PRESSURE, Magnitude.OFF, Derivative.STABLE))
                .build()));

        printAll(node, 0, "");

    }

    private static Node execute(State state)
    {
        Node rootNode = new Node(state, Main::cleanUpDerivatives);
        System.out.println("Totaal aantal verschillende states: " + (countNodes(rootNode) + 1));
        System.out.println("---------------------");
        System.out.println("Tree na het uitvoeren van alle dependencies op de root node:");
        System.out.println(rootNode.getData().toString(""));
        return rootNode;

    }

    private static void printAll(Node node, int depth, String append)
    {
        String tabs = "";
        for (int a = 0; a < depth; a++)
        {
            tabs += "\t";
        }
        if (node.getData() != null)
        {
            System.out.println(node.toString(tabs));
        }
        node.getChildren().forEach(child -> printAll(child, depth + 1, append));
    }

    private static int countNodes(Node node)
    {
        int treeSize = node.getChildren().size();
        int childSizes = node.getChildren()
                .stream()
                .mapToInt(Main::countNodes)
                .sum();

        return treeSize + childSizes;
    }

    private static List<Node> cleanUpDerivatives(State state)
    {
        final List<Node> list = new ArrayList<>();
        dp.processDerivatives(state)
                .map(newState -> new Node(newState, Main::execureDependencies))
                .ifPresent(list::add);
        return list;
    }

    private static List<Node> execureDependencies(State state)
    {
        final List<Node> list = new ArrayList<>();
        if (state.getId() > 200)
        {
            System.out.println("Geforceerd stop, te veel children");
            return list;
        }
        dp.influencePos(state)
                .map(newState -> new Node(newState, Main::cleanUpDerivatives))
                .ifPresent(list::add);
        dp.influenceNeg(state)
                .map(newState -> new Node(newState, Main::cleanUpDerivatives))
                .ifPresent(list::add);
        dp.proportionalityPos(state)
                .map(newState -> new Node(newState, Main::cleanUpDerivatives))
                .ifPresent(list::add);
        dp.VC(state)
                .map(newState -> new Node(newState, Main::cleanUpDerivatives))
                .ifPresent(list::add);


        return list;
    }

}