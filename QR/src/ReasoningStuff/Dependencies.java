package ReasoningStuff;

class Dependencies
{
    /***
     *
     * The amount of inflow increases the volume of water in the tub
     */
    void influencePos(State state)
    {
        if (state.inflow.getMagnitude().equalsIgnoreCase("+"))
        {
            this.increaseMagnitude(state, "volume");
        }
    }

    /***
     *
     *  The amount of outflow decreases the volume of water in the tub
     */
    void influenceNeg(State state)
    {
        if (state.outflow.getMagnitude().equalsIgnoreCase("+"))
        {
            this.decreaseMagnitude(state, "volume");
        }
    }

    /***
     *
     * P+(Volume, Outflow)  - outflow changes are proportional to volume changes
     * P+(Volume, ReasoningStuff.Height)   - height changes are proportional to volume changes
     * P+(ReasoningStuff.Height, Pressure) - pressure changes are proportional to height changes
     */
    void proportionalityPos(State state)
    {
        if(state.volume.getDerivative().equalsIgnoreCase("-"))
        {
            this.decreaseDerivative(state, "outflow");
            this.decreaseDerivative(state, "height");
        }
        else if(state.volume.getDerivative().equalsIgnoreCase("+"))
        {
            this.increaseDerivative(state, "outflow");
            this.increaseDerivative(state, "height");
        }
        else
        {
            System.out.println("Volume isn't changing");
        }

        if(state.height.getDerivative().equalsIgnoreCase("-"))
        {
            this.decreaseDerivative(state, "pressure");
        }
        else if(state.height.getDerivative().equalsIgnoreCase("+"))
        {
            this.increaseDerivative(state, "pressure");
        }
        else
        {
            System.out.println("ReasoningStuff.Height isn't changing");
        }
    }

    /***
     *
     * The outflow is at its highest value (max), when the volume is at it highest value.
     *
     */
    private void maxVC(State state)
    {
        switch (state.outflow.getMagnitude())
        {
            case "0":
                this.increaseMagnitude(state, "outflow");
                this.maxVC(state.getNextState());
                break;
            case "+":
                this.increaseMagnitude(state, "outflow");
                break;
            case "MAX":
                System.out.println("Outflow is already at the highest value.");
                break;
        }
    }

    /***
     *
     * There is no outflow, when there is no volume
     */
    private void zeroVC(State state)
    {
        switch (state.outflow.getMagnitude())
        {
            case "0":
                System.out.println("There is already no outflow");
                break;
            case "+":
                this.decreaseMagnitude(state, "outflow");
                break;
            case "MAX":
                this.decreaseMagnitude(state, "outflow");
                this.zeroVC(state.getNextState());
                break;
        }
    }

    void VC(State state)
    {
        if(state.volume.getMagnitude().equalsIgnoreCase("MAX"))
        {
            maxVC(state);
        }
        else if (state.volume.getMagnitude().equalsIgnoreCase("0"))
        {
            zeroVC(state);
        }
    }

    /***
     * Some handy general methods
     */
    private void increaseMagnitude(State state, String quantity)
    {
        State nextState = state.copy();
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
        state.setNextState(nextState);
        nextState.setPreviousState(state);
    }

    private void decreaseMagnitude(State state, String quantity)
    {
        State nextState = state.copy();
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
        state.setNextState(nextState);
        nextState.setPreviousState(state);
    }

    private void increaseDerivative(State state, String quantity)
    {
        State nextState = state.copy();
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
        state.setNextState(nextState);
        nextState.setPreviousState(state);
    }

    private void decreaseDerivative(State state, String quantity)
    {
        State nextState = state.copy();
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
        state.setNextState(nextState);
        nextState.setPreviousState(state);
    }
}
