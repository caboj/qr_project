package nl.uva.qr.reasoning;

import nl.uva.qr.tree.Node;
import nl.uva.qr.tree.Tree;

import java.util.Optional;

public class Dependencies
{
    /**
     * Twee soorten invloeden:
     * 1 - Aan de hand van de derivatives eigen magnitudes veranderen (state "rechttrekken")
     * 2 - aan de hand van de dependencies de derivatives of magnitudes veranderen
     * <p>
     * We beginnen met eigen states netjes "rechttrekken". Dat is een state die we kunnen returnen
     */
    Optional<State> processDerivatives(Tree tree, Node node)
    {
        State.Builder builder = node.getData().builder().copyOf(node.getData());

        switch (node.getData().inflow.getDerivative())
        {
            case INCREASING:
                builder.withInflow(node.getData().inflow.increaseMagnitude());
                break;
            case DECREASING:
                builder.withInflow(node.getData().inflow.decreaseMagnitude());
                break;
        }
        switch ((node.getData().outflow.getDerivative()))
        {
            case INCREASING:
                builder.withOutflow(node.getData().outflow.increaseMagnitude());
                break;
            case DECREASING:
                builder.withOutflow(node.getData().outflow.decreaseMagnitude());
                break;
        }
        switch ((node.getData().volume.getDerivative()))
        {
            case INCREASING:
                builder.withVolume(node.getData().volume.increaseMagnitude());
                break;
            case DECREASING:
                builder.withVolume(node.getData().volume.decreaseMagnitude());
                break;
        }
        switch ((node.getData().height.getDerivative()))
        {
            case INCREASING:
                builder.withHeight(node.getData().height.increaseMagnitude());
                break;
            case DECREASING:
                builder.withHeight(node.getData().height.decreaseMagnitude());
                break;
        }
        switch ((node.getData().pressure.getDerivative()))
        {
            case INCREASING:
                builder.withPressure(node.getData().pressure.increaseMagnitude());
                break;
            case DECREASING:
                builder.withPressure(node.getData().pressure.decreaseMagnitude());
                break;
        }
        State nextState = builder.build();

        if (node.getData().equals(nextState))
        {
            return Optional.empty();
        }
        tree.addChild(node, nextState);
        return Optional.of(nextState);
    }

    /**
     * I+(Inflow, Volume)
     * The amount of inflow increases the volume of water in the tub (magnitude van inflow -> derivative Volume)
     *
     * @param node Node with start state as node.getData()
     * @return Returns an Optional<State>, means if there are changes,
     * it returns an optional with a new derived state in it.
     * If there are no changes, it returns an empty optional. This way, nullPointerExceptions can't appear anymore.
     */
    Optional<State> influencePos(Tree tree, Node node)
    {
        if (node.getData().inflow.getMagnitude() == Magnitude.POSITIVE)
        {
            State.Builder builder = node.getData().builder().copyOf(node.getData());
            builder.withVolume(node.getData().volume.increaseDerivative());

            State nextState = builder.build();
            tree.addChild(node, nextState);
            processDerivatives(tree, node.getChildren().get(node.getChildren().size() - 1));
            return Optional.of(nextState);
        }
        return Optional.empty();
    }

    /**
     * I-(Outflow, Volume)
     * The amount of outflow decreases the volume of water in the tub
     *
     * @param node Node with start state as node.getData()
     * @return Returns an Optional<State>, means if there are changes,
     * it returns an optional with a new derived state in it.
     * If there are no changes, it returns an empty optional. This way, nullPointerExceptions can't appear anymore.
     */
    Optional<State> influenceNeg(Tree tree,Node node)
    {
        if (node.getData().outflow.getMagnitude() == Magnitude.POSITIVE)
        {
            State.Builder builder = node.getData().builder().copyOf(node.getData());
            builder.withVolume(node.getData().volume.decreaseDerivative());

            State nextState = builder.build();
            tree.addChild(node, nextState);
            proportionalityPos(tree, node.getChildren().get(node.getChildren().size() - 1));
            processDerivatives(tree, node.getChildren().get(node.getChildren().size() - 1));
            return Optional.of(nextState);
        }
        return Optional.empty();
    }

    /**
     * P+(Volume, Outflow)  - outflow changes are proportional to volume changes (volume derivative -> outflow derivative)
     * P+(Volume, Height)   - height changes are proportional to volume changes (volume derivative -> hieght derivative)
     * P+(Height, Pressure) - pressure changes are proportional to height changes (heght derivative -> pressure derivative)
     *
     * @param node Node with start state as node.getData()
     * @return Returns an Optional<State>, means if there are changes,
     * it returns an optional with a new derived state in it.
     */
    Optional<State> proportionalityPos(Tree tree, Node node)
    {
        State.Builder builder = node.getData().builder().copyOf(node.getData());
        switch (node.getData().volume.getDerivative())
        {
            case INCREASING:
                builder.withInflow(node.getData().outflow.increaseDerivative());
                builder.withInflow(node.getData().height.increaseDerivative());
                break;
            case DECREASING:
                builder.withInflow(node.getData().outflow.decreaseDerivative());
                builder.withInflow(node.getData().height.decreaseDerivative());
                break;
        }
        switch (node.getData().height.getDerivative())
        {
            case INCREASING:
                builder.withInflow(node.getData().pressure.increaseDerivative());
                break;
            case DECREASING:
                builder.withInflow(node.getData().pressure.decreaseDerivative());
                break;
        }

        State nextState = builder.build();

        if (node.getData().equals(nextState))
        {
            return Optional.empty();
        }
        tree.addChild(node, nextState);
        return Optional.of(nextState);
    }

    /**
     * The outflow is at its highest value (max), when the volume is at it highest value.
     * <p>
     * But, outflow can't increase to the "MAX" at once if it was "0", it has to become "+" first,
     * because of the domain {0,+,MAX}. And that means a (very short) new state.
     * After every new state all the dependencies have to be applied to the new state.
     * That is why this method is not recursive.
     *
     * @param node Node with start state as node.getData()
     * @return Returns an Optional<State>, means if there are changes,
     * it returns an optional with a new derived state in it.
     * If there are no changes, it returns an empty optional. This way, nullPointerExceptions can't appear anymore.
     */
    private Optional<State> maxVC(Tree tree, Node node)
    {
        State.Builder builder = node.getData().builder().copyOf(node.getData());
        builder.withVolume(node.getData().outflow.increaseMagnitude());

        State nextState = builder.build();

        if (node.getData().equals(nextState))
        {
            return Optional.empty();
        }
        tree.addChild(node, nextState);
        return Optional.of(nextState);
    }

    /**
     * There is no outflow, when there is no volume.
     * <p>
     * But, outflow can't decrease to the "0" at once if it was "MAX", it has to become "+" first,
     * because of the domain {0,+,MAX}. And that means a (very short) new state.
     * After every new state all the dependencies have to be applied to the new state.
     * That is why this method is not recursive.
     *
     * @param node Node with start state as node.getData()
     * @return Returns an Optional<State>, means if there are changes,
     * it returns an optional with a new derived state in it.
     * If there are no changes, it returns an empty optional. This way, nullPointerExceptions can't appear anymore.
     */
    private Optional<State> zeroVC(Tree tree, Node node)
    {
        State.Builder builder = node.getData().builder().copyOf(node.getData());
        builder.withVolume(node.getData().outflow.decreaseMagnitude());

        State nextState = builder.build();

        if (node.getData().equals(nextState))
        {
            return Optional.empty();
        }
        tree.addChild(node, nextState);
        return Optional.of(nextState);
    }

    /**************************** Help methods ********************************************/

    /**
     * Additional method to decide which of the two VC methods to call.
     *
     * @param node Node with start state as node.getData()
     */

    Optional<State> VC(Tree tree, Node node)
    {
        if (node.getData().volume.getMagnitude() == Magnitude.MAX)
        {
            return maxVC(tree, node);
        } else if (node.getData().volume.getMagnitude() == Magnitude.OFF)
        {
            return zeroVC(tree, node);
        }
        return Optional.empty();
    }
}