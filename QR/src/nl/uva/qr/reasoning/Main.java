package nl.uva.qr.reasoning;

import nl.uva.qr.tree.Node;

import java.util.ArrayList;
import java.util.List;

class Main
{
    static Dependencies dp;

    public static void main(String[] args)
    {
        dp = new Dependencies();

        State rootState = State.builder()
                .withInflow(new Quantity(QuantityType.INFLOW, Magnitude.OFF, Derivative.INCREASING))
                .withOutflow(new Quantity(QuantityType.OUTFLOW, Magnitude.OFF, Derivative.STABLE))
                .withVolume(new Quantity(QuantityType.VOLUME, Magnitude.OFF, Derivative.STABLE))
                .withHeight(new Quantity(QuantityType.HEIGHT, Magnitude.OFF, Derivative.STABLE))
                .withPressure(new Quantity(QuantityType.PRESSURE, Magnitude.OFF, Derivative.STABLE))
                .build();
        System.out.println("Start tree met alleen onderstaande root:");
        System.out.println("---------------------");
        System.out.println(rootState.toString());

        Node rootNode = new Node(rootState, Main::a);

        System.out.println("---------------------");
        System.out.println("Tree na het uitvoeren van alle dependencies op de root node:");
        printAll(rootNode, 0, "");

        System.out.println("Totaal verschillende states: " + (countNodes(rootNode)+1) );

    }


    private static void printAll(Node node, int depth, String append)
    {
        String tabs = "";
        for(int a = 0; a < depth; a++)
        {
            tabs+="\t";
        }
        System.out.println(node.toString(tabs));
        node.getChildren().forEach(child->printAll(child, depth+1, append));
    }

    private static int countNodes(Node node)
    {
        int treeSize = node.getChildren().size();
        int childSizes = node.getChildren()
                .stream()
                .mapToInt(child->countNodes(child))
                .sum();

        return treeSize + childSizes;
    }

    public static List<Node> a(State state)
    {
        final List<Node> list = new ArrayList<>();
        dp.processDerivatives(state)
                .map(newState-> new Node(newState, Main::b))
                .ifPresent(list::add);
        return list;
    }

    public static List<Node> b(State state)
    {
        final List<Node> list = new ArrayList<>();
        if(state.getId()>200)
        {
            System.out.println("Geforceerd stop, te veel children");
            return list;
        }
        dp.influencePos(state)
                .map(newState-> new Node(newState, Main::b))
                .ifPresent(list::add);
        dp.influenceNeg(state)
                .map(newState-> new Node(newState, Main::a))
                .ifPresent(list::add);
        dp.proportionalityPos(state)
                .map(newState-> new Node(newState, Main::a))
                .ifPresent(list::add);
        dp.VC(state)
                .map(newState-> new Node(newState, Main::a))
                .ifPresent(list::add);


        return list;
    }

}