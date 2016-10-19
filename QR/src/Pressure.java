/**
 * Created by Elvira on 19-10-16.
 */
public class Pressure extends Quantity
{
    public Pressure(String magnitude, int quantatySpaceLength)
    {
        this.setQuantitySpace(quantatySpaceLength);
        this.setDerivatySpace();
        this.setMagnitude(magnitude);
        this.setDerivaty("0");
    }

    public String toString()
    {
        return ("Pressure: \t" + this.getMagnitude() + "\t" + this.getDerivaty() + "\n");
    }
}
