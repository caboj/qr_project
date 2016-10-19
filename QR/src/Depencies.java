/**
 * Created by Elvira on 19-10-16.
 */
public class Depencies
{
    /***
     *
     * The amount of inflow increases the volume of water in the tub
     */
    public void influensePos(State state)
    {
        if (state.inflow.getMagnitude().equalsIgnoreCase("+"))
        {
            State nextState = state.copy();
            nextState.volume.increaseMagnitude();

            state.setNextState(nextState);
            nextState.setPreviousState(state);
        }
    }

    /***
     *
     *  The amount of outflow decreases the volume of water in the tub
     */
    public void influenceNeg(State state)
    {
        if (state.outflow.getMagnitude().equalsIgnoreCase("+"))
        {
            State nextState = state.copy();
            nextState.volume.decreaseMagnitude();
            state.setNextState(nextState);
            nextState.setPreviousState(state);
        }
    }

    /***
     *
     * Outflow changes are proportional to volume changes
     */
    public void proposionalityPos(State state)
    {

    }

}
