/**
 * Created by Elvira on 19-10-16.
 */
public class State
{
    Inflow inflow;
    Outflow outflow;
    Volume volume;
    Height height;
    Pressure pressure;

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
     * @param parameters magnitude and derivaty for Inflow, Outflow, Volume, Height, Pressure
     * Previous and next state are set to null
     */
    public State(String[][] parameters)
    {
        this.inflow = new Inflow(parameters[0][0], 2);
        this.outflow = new Outflow(parameters[1][0], 3);
        this.volume = new Volume(parameters[2][0], 3);
        this.height = new Height(parameters[3][0], 3);
        this.pressure = new Pressure(parameters[4][0], 3);
        this.previousState = null;
        this.nextState = null;
    }

    private String[][] getParameters()
    {
        return new String [][] {
                { inflow.getMagnitude(), inflow.getDerivaty()},
                { outflow.getMagnitude(), outflow.getDerivaty()},
                { volume.getMagnitude(), volume.getDerivaty()},
                { height.getMagnitude(), height.getDerivaty()},
                { pressure.getMagnitude(), pressure.getDerivaty()} };
    }
    public State copy()
    {
        return new State(this.getParameters());
    }
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(inflow.toString());
        sb.append(outflow.toString());
        sb.append(volume.toString());
        sb.append(height.toString());
        sb.append(pressure.toString());

        return sb.toString();
    }
}
