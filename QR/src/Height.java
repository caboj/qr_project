/**
 * Created by Elvira on 19-10-16.
 */
public class Height extends Quantity
{
    public Height(String magnitude, int quantatySpaceLength)
    {
        this.setQuantitySpace(quantatySpaceLength);
        this.setDerivatySpace();
        this.setMagnitude(magnitude);
        this.setDerivaty("0");
    }

    public String toString()
    {
        return ("Height:\t \t" + this.getMagnitude() + "\t" + this.getDerivaty() + "\n");
    }
}
