/**
 * Created by Elvira on 18-10-16.
 */
public class Outflow extends Quantity
{
    public Outflow(String magnitude, int quantatySpaceLength)
    {
        this.setQuantitySpace(quantatySpaceLength);
        this.setDerivatySpace();
        this.setMagnitude(magnitude);
        this.setDerivaty("0");
    }

    public String toString()
    {
        return ("Outflow:\t" + this.getMagnitude() + "\t" + this.getDerivaty() + "\n");
    }
}
