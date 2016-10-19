/**
 * Created by Elvira on 18-10-16.
 */
public class Main
{
    public static void main(String[] args)
    {
        Depencies depencies = new Depencies();

        String[][] parameters = new String [][] {
                { "+", "0"},
                { "+", "0"},
                { "0", "0"},
                { "0", "0"},
                { "0", "0"} };

        State startState = new State(parameters);
        System.out.println("Start state:\n" + startState.toString());

        depencies.influensePos(startState);
        System.out.println("Next state:\n" + startState.getNextState().toString());

        depencies.influenceNeg(startState.getNextState());
        System.out.println("Third state: \n" + startState.getNextState().getNextState().toString());


    }
}
