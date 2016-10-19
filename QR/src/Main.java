/**
 * Created by Elvira on 18-10-16.
 */
public class Main
{
    public static void main(String[] args)
    {
        /*
        Depencies depencies = new Depencies();
        Inflow inflow = new Inflow();
        Outflow outflow = new Outflow();
        Volume volume = new Volume();
        Height heigh = new Height();
        Pressure pressure = new Pressure();

        inflow.setCurrentState(1);
        outflow.setCurrentState(1);
        volume.setCurrentState(0);

        depencies.influensePos(inflow, volume);
        depencies.influenceNeg(outflow, volume);
        */

        /*
        Inflow inflow = new Inflow("0", 2);
        Outflow outflow = new Outflow("0", 3);
        Volume volume = new Volume("+", 3);
        Height height = new Height("+", 3);
        Pressure pressure = new Pressure("+", 3);

        System.out.print(inflow.toString());
        System.out.print(outflow.toString());
        System.out.print(volume.toString());
        System.out.print(height.toString());
        System.out.print(pressure.toString());
        */
        String[][] myStringArray = new String [][] {
                { "X0", "Y0"},
                { "X1", "Y1"},
                { "X2", "Y2"},
                { "X3", "Y3"},
                { "X4", "Y4"} };

        for (int i = 0; i < 5; i ++)
        {
            for(int j = 0; j < 2; j++)
            {
                System.out.print(myStringArray[i][j]);
            }
        }

    }
}
