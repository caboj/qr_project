package nl.uva.qr.reasoning;

import java.util.Optional;

@SuppressWarnings("DanglingJavadoc")
public class Dependencies
{
    /**
     * Twee soorten invloeden:
     * 1 - Aan de hand van de derivatives eigen magnitudes veranderen (state "rechttrekken")
     * 2 - aan de hand van de dependencies de derivatives of magnitudes veranderen
     * <p>
     * We beginnen met eigen states netjes "rechttrekken". Dat is een state die we kunnen returnen
     */
    Optional<State> processDerivatives(State state)
    {
        State.Builder builder = state.builder().copyOf(state);

        switch (state.inflow.getDerivative())
        {
            case INCREASING:
                builder.withInflow(state.inflow.increaseMagnitude());
                break;
            case DECREASING:
                builder.withInflow(state.inflow.decreaseMagnitude());
                break;
        }
        switch ((state.outflow.getDerivative()))
        {
            case INCREASING:
                builder.withOutflow(state.outflow.increaseMagnitude());
                break;
            case DECREASING:
                builder.withOutflow(state.outflow.decreaseMagnitude());
                break;
        }
        switch ((state.volume.getDerivative()))
        {
            case INCREASING:
                builder.withVolume(state.volume.increaseMagnitude());
                break;
            case DECREASING:
                builder.withVolume(state.volume.decreaseMagnitude());
                break;
        }
        switch ((state.height.getDerivative()))
        {
            case INCREASING:
                builder.withHeight(state.height.increaseMagnitude());
                break;
            case DECREASING:
                builder.withHeight(state.height.decreaseMagnitude());
                break;
        }
        switch ((state.pressure.getDerivative()))
        {
            case INCREASING:
                builder.withPressure(state.pressure.increaseMagnitude());
                break;
            case DECREASING:
                builder.withPressure(state.pressure.decreaseMagnitude());
                break;
        }
        State nextState = builder.build();

        if (state.equals(nextState))
        {
            return Optional.empty();
        }

        System.out.println("Derivatives toegepast op de onderstaande node: ");
        System.out.println(state.toString());
        System.out.println("Child:");
        System.out.println(nextState.toString());

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
    Optional<State> influencePos(State state)
    {
        if (state.inflow.getMagnitude() == Magnitude.POSITIVE)
        {
            State.Builder builder = state.builder().copyOf(state);
            builder.withVolume(state.volume.increaseDerivative());

            State nextState = builder.build();

            System.out.println("I+ op de onderstaande node: ");
            System.out.println(state.toString());
            System.out.println("Child:");
            System.out.println(nextState.toString());

            if (!state.equals(nextState))
            {
                return Optional.of(nextState);
            }
        }
        return Optional.empty();
    }

    /**
     * I-(Outflow, Volume)
     * The amount of outflow decreases the volume of water in the tub
     *
     * @param state Node with start state as node.getData()
     * @return Returns an Optional<State>, means if there are changes,
     * it returns an optional with a new derived state in it.
     * If there are no changes, it returns an empty optional. This way, nullPointerExceptions can't appear anymore.
     */
    Optional<State> influenceNeg(State state)
    {
        if (state.outflow.getMagnitude() == Magnitude.POSITIVE)
        {
            State.Builder builder = state.builder().copyOf(state);
            builder.withVolume(state.volume.decreaseDerivative());

            State nextState = builder.build();

            System.out.println("I- op start node: ");
            System.out.println(state.toString());
            System.out.println("Child:");
            System.out.println(nextState.toString());

            if (!state.equals(nextState))
            {
                return Optional.of(nextState);
            }
        }
        return Optional.empty();
    }

    /**
     * P+(Volume, Outflow)  - outflow changes are proportional to volume changes (volume derivative -> outflow derivative)
     * P+(Volume, Height)   - height changes are proportional to volume changes (volume derivative -> height derivative)
     * P+(Height, Pressure) - pressure changes are proportional to height changes (height derivative -> pressure derivative)
     *
     * @param state Node with start state as node.getData()
     * @return Returns an Optional<State>, means if there are changes,
     * it returns an optional with a new derived state in it.
     */
    Optional<State> proportionalityPos(State state)
    {
        State.Builder builder = state.builder().copyOf(state);
        switch (state.volume.getDerivative())
        {
            case INCREASING:
                builder.withOutflow(state.outflow.increaseDerivative());
                builder.withHeight(state.height.increaseDerivative());
                break;
            case DECREASING:
                builder.withOutflow(state.outflow.decreaseDerivative());
                builder.withHeight(state.height.decreaseDerivative());
                break;
        }
        switch (state.height.getDerivative())
        {
            case INCREASING:
                builder.withPressure(state.pressure.increaseDerivative());
                break;
            case DECREASING:
                builder.withPressure(state.pressure.decreaseDerivative());
                break;
        }

        State nextState = builder.build();

        if (state.equals(nextState))
        {
            return Optional.empty();
        }
        
        System.out.println("P+ op start node: ");
        System.out.println(state.toString());
        System.out.println("Child:");
        System.out.println(nextState.toString());

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
     * @param state Node with start state as node.getData()
     * @return Returns an Optional<State>, means if there are changes,
     * it returns an optional with a new derived state in it.
     * If there are no changes, it returns an empty optional. This way, nullPointerExceptions can't appear anymore.
     */
    private Optional<State> maxVC(State state)
    {
        State.Builder builder = state.builder().copyOf(state);
        builder.withOutflow(state.outflow.increaseMagnitude());

        State nextState = builder.build();

        if (state.equals(nextState))
        {
            return Optional.empty();
        }

        System.out.println("VC(max) op start node: ");
        System.out.println(state.toString());
        System.out.println("Child:");
        System.out.println(nextState.toString());

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
    private Optional<State> zeroVC(State state)
    {
        State.Builder builder = state.builder().copyOf(state);
        builder.withOutflow(state.outflow.decreaseMagnitude());

        State nextState = builder.build();

        if (state.equals(nextState))
        {
            return Optional.empty();
        }

        System.out.println("VC(zero) op start node: ");
        System.out.println(state.toString());
        System.out.println("Child:");
        System.out.println(nextState.toString());

        return Optional.of(nextState);
    }

    /**************************** Help methods ********************************************/

    /**
     * Additional method to decide which of the two VC methods to call.
     *
     * @param node Node with start state as node.getData()
     */

    Optional<State> VC(State state)
    {
        if (state.volume.getMagnitude() == Magnitude.MAX)
        {
            return maxVC(state);
        } else if (state.volume.getMagnitude() == Magnitude.OFF)
        {
            return zeroVC(state);
        }
        return Optional.empty();
    }
}