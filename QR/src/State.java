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

    public State(String[][] parameters)
    {
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
                System.out.print(parameters[i][j]);

            }
        }

        Inflow inflow = new Inflow("0", 2);
        Outflow outflow = new Outflow("0", 3);
        Volume volume = new Volume("+", 3);
        Height height = new Height("+", 3);
        Pressure pressure = new Pressure("+", 3);
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
