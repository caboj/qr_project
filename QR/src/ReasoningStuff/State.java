package ReasoningStuff;

public class State
{
    final Inflow inflow;
    final Outflow outflow;
    final Volume volume;
    final Height height;
    final Pressure pressure;


    private State previousState;
    private State nextState;

    public State getPreviousState()
    {
        return previousState;
    }
    public void setPreviousState(State previousState)
    {
        this.previousState = previousState;
    }
    public State getNextState()
    {
        return nextState;
    }
    public void setNextState(State nextState)
    {
        this.nextState = nextState;
    }


    /***
     *
     * @param parameters magnitude and derivative for ReasoningStuff.Inflow, ReasoningStuff.Outflow, ReasoningStuff.Volume, ReasoningStuff.Height, ReasoningStuff.Pressure
     * Previous and next state are set to null
     */
    public State(String[][] parameters)
    {
        this.inflow = new Inflow(parameters[0][0]);
        this.outflow = new Outflow(parameters[1][0]);
        this.volume = new Volume(parameters[2][0]);
        this.height = new Height(parameters[3][0]);
        this.pressure = new Pressure(parameters[4][0]);

        this.previousState = null;
        this.nextState = null;
    }

    private String[][] getParameters()
    {
        return new String [][] {
                { inflow.getMagnitude(), inflow.getDerivative()},
                { outflow.getMagnitude(), outflow.getDerivative()},
                { volume.getMagnitude(), volume.getDerivative()},
                { height.getMagnitude(), height.getDerivative()},
                { pressure.getMagnitude(), pressure.getDerivative()} };
    }
    public State copy()
    {
        return new State(this.getParameters());
    }
    public String toString()
    {
        return inflow.toString() +
                outflow.toString() +
                volume.toString() +
                height.toString() +
                pressure.toString();
    }
}
