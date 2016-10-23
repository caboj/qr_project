package nl.uva.qr.reasoning;

import nl.uva.qr.tree.Node;

import java.util.Optional;
import java.util.ArrayList;

public class Dependencies
{
    /**
     * Twee soorten invloeden:
     * 1 - Aan de hand van de derivatives eigen magnitudes veranderen (state "rechttrekken")
     * 2 - aan de hand van de dependencies de derivatives of magnitudes veranderen
     *
     * We beginnen met eigen states netjes "rechttrekken" en die in de tree opslaan. Dat is een state!
     */



    /**
     * I+(Inflow, Volume)
     * The amount of inflow increases the volume of water in the tub (magnitude van inflow -> derivative Volume)
     *
     * @param node Node with start state as node.getData()
     * @return Returns an Optional<State>, means if there are changes,
     * it returns an optional with a new derived state in it.
     * If there are no changes, it returns an empty optional. This way, nullPointerExceptions can't appear anymore.
     */
    Optional<State> influencePos(Node node)
    {
        if (node.getData().inflow.getMagnitude() == Magnitude.POSITIVE)
        {
            State.Builder builder = node.getData().builder().copyOf(node.getData());
            builder.withVolume(node.getData().volume.increaseDerivative());

            State nextState = builder.build();
            return Optional.of(nextState);
        }
        return Optional.empty();
    }

    Optional<State>  processDerivatives(Node node)
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

        if(node.getData().equals(nextState))
        {
            return Optional.empty();
        }
        return Optional.of(nextState);
    }
}
