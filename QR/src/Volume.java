/**
 * Created by Elvira on 19-10-16.
 */
public class Volume extends Quantity
{
    public Volume(String magnitude, int quantatySpaceLength)
    {
        this.setQuantitySpace(quantatySpaceLength);
        this.setDerivatySpace();
        this.setMagnitude(magnitude);
        this.setDerivaty("0");
    }

    public String toString()
    {
        return ("Volume:\t \t" + this.getMagnitude() + "\t" + this.getDerivaty() + "\n");
    }
}
