/**
 * Created by Elvira on 19-10-16.
 */
public class QuantitySpace
{
    private String[] quantatySpace;

    public QuantitySpace(int length)
    {
        String[] space = {"0","+","MAX"};

        this.quantatySpace = new String[length];
        for(int i = 0; i < length; i++)
        {
            quantatySpace[i] = space[i];
        }
    }
    public QuantitySpace()
    {
        int length = 3;
        String[] space = {"0","+","MAX"};

        this.quantatySpace = new String[length];
        for(int i = 0; i < length; i++)
        {
            quantatySpace[i] = space[i];
        }
    }
    public int getLength()
    {
        return quantatySpace.length;
    }

    public String[] getQuantitySpace()
    {
        return this.quantatySpace;
    }
}
