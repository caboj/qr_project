/**
 * Created by Elvira on 19-10-16.
 */
public class DerivatySpace
{
    private String[] derivatySpace;

    public DerivatySpace()
    {
        String[] space = {"-","0","+"};

        this.derivatySpace = new String[3];

        for(int i = 0; i < 3; i++)
        {
            derivatySpace[i] = space[i];
        }
    }
    public String[] getDerivatySpace()
    {
        return this.derivatySpace;
    }
}
