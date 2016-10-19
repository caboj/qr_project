class Main
{
    public static void main(String[] args)
    {
        Dependencies dependencies = new Dependencies();

        String[][] parameters = new String [][] {
                { "+", "0"},
                { "+", "0"},
                { "0", "0"},
                { "0", "0"},
                { "0", "0"} };

        State startState = new State(parameters);
        System.out.println("Start state:\n" + startState.toString());

        dependencies.influencePos(startState);
        System.out.println("Next state:\n" + startState.getNextState().toString());

        dependencies.influenceNeg(startState.getNextState());
        System.out.println("Third state: \n" + startState.getNextState().getNextState().toString());


    }
}
