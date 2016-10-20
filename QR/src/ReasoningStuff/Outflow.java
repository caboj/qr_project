package ReasoningStuff;

class Outflow extends Quantity
{
    public Outflow(String magnitude, String derivative, int quantitySpaceLength)
    {
        if(quantitySpaceLength == 0)
        {
            quantitySpaceLength = 3;
        }
        this.setQuantitySpace(quantitySpaceLength);
        this.setDerivativeSpace();
        this.setMagnitude(magnitude);
        this.setDerivative(derivative);
    }

    public String toString()
    {
        return ("Outflow:\t" + this.getMagnitude() + "\t" + this.getDerivative() + "\n");
    }
}
