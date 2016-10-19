/**
 * Created by Elvira on 18-10-16.
 */
public class Inflow extends Quantity
{
    public Inflow(String magnitude, int quantatySpaceLength)
    {
        this.setQuantitySpace(quantatySpaceLength);
        this.setDerivatySpace();
        this.setMagnitude(magnitude);
        this.setDerivaty("0");
    }

    public String toString()
    {
        return ("Inflow:\t \t" + this.getMagnitude() + "\t" + this.getDerivaty() + "\n");
    }
}
