package ReasoningStuff;

import TreeStuff.Node;

import java.util.Optional;
import java.util.ArrayList;

@SuppressWarnings("DanglingJavadoc")
class Dependencies
{

    //daarnaast missen interne dependencies. Zo als als de derivative increasing is, dan moet the magnitude ook increasen. Zou je die ook kunnen implementeren aub?
		/**
		 * If derivative is not 0 then do something
		 */
		ArrayList<Optional<State>> processDerivatives(Node node)
    {
				ArrayList<Optional<State>> nextStates = new ArrayList<Optional<State>>();
				if (node.getData().inflow.getDerivative().equalsIgnoreCase("+"))
        {
            nextStates.add(this.increaseMagnitude(node, "inflow"));
        }
				if (node.getData().volume.getDerivative().equalsIgnoreCase("+"))
        {
            nextStates.add(this.increaseMagnitude(node, "volume"));
        }
				if (node.getData().height.getDerivative().equalsIgnoreCase("+"))
        {
            nextStates.add(this.increaseMagnitude(node, "height"));
        }
				if (node.getData().pressure.getDerivative().equalsIgnoreCase("+"))
        {
            nextStates.add(this.increaseMagnitude(node, "pressure"));
        }
				if (node.getData().outflow.getDerivative().equalsIgnoreCase("+"))
        {
            nextStates.add(this.increaseMagnitude(node, "outflow"));
        }
				if (node.getData().inflow.getDerivative().equalsIgnoreCase("-"))
        {
            nextStates.add(this.decreaseMagnitude(node, "inflow"));
        }
				if (node.getData().volume.getDerivative().equalsIgnoreCase("-"))
        {
            nextStates.add(this.decreaseMagnitude(node, "volume"));
        }
				if (node.getData().height.getDerivative().equalsIgnoreCase("-"))
        {
            nextStates.add(this.decreaseMagnitude(node, "height"));
        }
				if (node.getData().pressure.getDerivative().equalsIgnoreCase("-"))
        {
            nextStates.add(this.decreaseMagnitude(node, "pressure"));
        }
				if (node.getData().outflow.getDerivative().equalsIgnoreCase("-"))
        {
            nextStates.add(this.decreaseMagnitude(node, "outflow"));
        }

        return nextStates;
    }

		
    /**
     * The amount of inflow increases the volume of water in the tub
     * @param node Node with start state as node.getData()
     * @return Returns an Optional<State>, means if there are changes,
     * it returns an optional with a new derived state in it.
     * If there are no changes, it returns an empty optional. This way, nullPointerExceptions can't appear anymore.
     */
    // Jacob, ik weet niet zeker of de methode implementatie correct is. Wil je naar dee methode kijken aub?
    Optional<State> influencePos(Node node)
    {
        if (node.getData().inflow.getMagnitude().equalsIgnoreCase("+"))
        {
            return this.increaseDerivative(node, "volume");
        }
        return Optional.empty();
    }

    /**
     *  The amount of outflow decreases the volume of water in the tub
     * @param node Node with start state as node.getData()
     * @return Returns an Optional<State>, means if there are changes,
     * it returns an optional with a new derived state in it.
     * If there are no changes, it returns an empty optional. This way, nullPointerExceptions can't appear anymore.
     */
    // Jacob, ik weet niet zeker of de methode implementatie correct is. Wil je naar dee methode kijken aub?
    Optional<State> influenceNeg(Node node)
    {
        if (node.getData().outflow.getMagnitude().equalsIgnoreCase("+"))
        {
            return this.decreaseDerivative(node, "volume");
        }
        return Optional.empty();
    }

    /**
     *
     * P+(Volume, Outflow)  - outflow changes are proportional to volume changes
     * P+(Volume, ReasoningStuff.Height)   - height changes are proportional to volume changes
     * P+(ReasoningStuff.Height, Pressure) - pressure changes are proportional to height changes
     */
    // Jacob, ik weet niet zeker of de methode implementatie correct is. Wil je naar dee methode kijken aub?
    // Dit moet echt wel gesplits worden, aangezien het proportional invloed heeft en dus stap voor stap moet gebeuren...
    Optional<State> proportionalityPos(Node node)
    {
        if(node.getData().volume.getDerivative().equalsIgnoreCase("-"))
        {
            this.decreaseDerivative(node, "outflow");
            this.decreaseDerivative(node, "height");
        }
        else if(node.getData().volume.getDerivative().equalsIgnoreCase("+"))
        {
            this.increaseDerivative(node, "outflow");
            this.increaseDerivative(node, "height");
        }
        else
        {
            System.out.println("Volume isn't changing");
        }

        if(node.getData().height.getDerivative().equalsIgnoreCase("-"))
        {
            this.decreaseDerivative(node, "pressure");
        }
        else if(node.getData().height.getDerivative().equalsIgnoreCase("+"))
        {
            this.increaseDerivative(node, "pressure");
        }
        else
        {
            System.out.println("ReasoningStuff.Height isn't changing");
        }
        return Optional.empty();
    }

    /**
     * The outflow is at its highest value (max), when the volume is at it highest value.
     *
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
    // Jacob, ik weet niet zeker of de methode implementatie correct is. Wil je naar dee methode kijken aub?
    private Optional<State> maxVC(Node node)
    {
        return this.increaseMagnitude(node, "outflow");
    }

    /**
     * There is no outflow, when there is no volume.
     *
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
    // Jacob, ik weet niet zeker of de methode implementatie correct is. Wil je naar dee methode kijken aub?
    private Optional<State> zeroVC(Node node)
    {
       return this.decreaseMagnitude(node,"outflow");
    }


    /**************************** Help methods ********************************************/

    /**
     * Additional method to decide which of the two VC methods to call.
     * @param node Node with start state as node.getData()
     */
    Optional<State> VC(Node node)
    {
        if(node.getData().volume.getMagnitude().equalsIgnoreCase("MAX"))
        {
            return maxVC(node);
        }
        else if (node.getData().volume.getMagnitude().equalsIgnoreCase("0"))
        {
            return zeroVC(node);
        }
        return Optional.empty();
    }
    /**
     * Four methods to increase and decrease Magnitude and Derivative of each quantity in general.
     *
     * @param node Node with start state as node.getData()
     * @param quantity Quantity with has to be increased or decreased
     * @return Returns an Optional<State>, means if there are changes,
     * it returns an optional with a new derived state in it.
     * If there are no changes, it returns an empty optional. This way, nullPointerExceptions can't appear anymore.
     */
    private Optional<State> increaseMagnitude(Node node, String quantity)
    {
        State nextState = node.getData().copy();
        switch (quantity)
        {
            case "inflow":
                nextState.inflow.increaseMagnitude();
                break;
            case "outflow":
                nextState.outflow.increaseMagnitude();
                break;
            case "volume":
                nextState.volume.increaseMagnitude();
                break;
            case "height":
                nextState.height.increaseMagnitude();
                break;
        }
        if (!nextState.equals(node.getData()))
        {
            return Optional.of(nextState);
        }
        return Optional.empty();
    }
    private Optional<State> decreaseMagnitude(Node node, String quantity)
    {
        State nextState = node.getData().copy();
        switch (quantity)
        {
            case "inflow":
                nextState.inflow.decreaseMagnitude();
                break;
            case "outflow":
                nextState.outflow.decreaseMagnitude();
                break;
            case "volume":
                nextState.volume.decreaseMagnitude();
                break;
            case "height":
                nextState.height.decreaseMagnitude();
                break;
        }
        if (!nextState.equals(node.getData()))
        {
            return Optional.of(nextState);
        }
        return Optional.empty();
    }
    private Optional<State> increaseDerivative(Node node, String quantity)
    {
        State nextState = node.getData().copy();
        switch (quantity)
        {
            case "inflow":
                nextState.inflow.increaseDerivative();
                break;
            case "outflow":
                nextState.outflow.increaseDerivative();
                break;
            case "volume":
                nextState.volume.increaseDerivative();
                break;
            case "height":
                nextState.height.increaseDerivative();
                break;
        }
        if (!nextState.equals(node.getData()))
        {
            return Optional.of(nextState);
        }
        return Optional.empty();
    }
    private Optional<State> decreaseDerivative(Node node, String quantity)
    {
        State nextState = node.getData().copy();
        switch (quantity)
        {
            case "inflow":
                nextState.inflow.decreaseDerivative();
                break;
            case "outflow":
                nextState.outflow.decreaseDerivative();
                break;
            case "volume":
                nextState.volume.decreaseDerivative();
                break;
            case "height":
                nextState.height.decreaseDerivative();
                break;
        }
        if (!nextState.equals(node.getData()))
        {
            return Optional.of(nextState);
        }
        return Optional.empty();
    }
}
