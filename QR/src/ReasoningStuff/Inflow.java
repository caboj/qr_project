package ReasoningStuff;

class Inflow extends Quantity
{
    public Inflow(String magnitude, String derivative, int quantitySpaceLength)
    {
        if(quantitySpaceLength==0)
        {
            quantitySpaceLength = 2;
        }
        this.setQuantitySpace(quantitySpaceLength);
        this.setDerivativeSpace();
        this.setMagnitude(magnitude);
        this.setDerivative(derivative);
    }

    public String toString()
    {
        return ("Inflow:\t \t" + this.getMagnitude() + "\t" + this.getDerivative() + "\n");
    }
}
