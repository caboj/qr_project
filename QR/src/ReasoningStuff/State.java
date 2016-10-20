package ReasoningStuff;

public class State
{
    final Inflow inflow;
    final Outflow outflow;
    final Volume volume;
    final Height height;
    final Pressure pressure;

    /***
     *
     * @param parameters magnitude and derivative for Inflow, Outflow, Volume, Height, Pressure
     * Previous and next state are set to null
     */
    public State(String[][] parameters)
    {
        this.inflow = new Inflow(parameters[0][0], parameters[0][1], 0);
        this.outflow = new Outflow(parameters[1][0], parameters[1][1],0);
        this.volume = new Volume(parameters[2][0],parameters[2][1],0);
        this.height = new Height(parameters[3][0],parameters[3][1],0);
        this.pressure = new Pressure(parameters[4][0],parameters[4][1],0);
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
    public boolean equals(State state)
    {
        for(int i = 0; i < state.getParameters().length; i++)
        {
            for(int j = 0; j < state.getParameters()[i].length; j++)
            {
                if(!state.getParameters()[i][j].equalsIgnoreCase(this.getParameters()[i][j]))
                {
                    return false;
                }
            }
        }
        return true;
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
