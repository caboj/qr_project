package ReasoningStuff;

class Pressure extends Quantity
{
    public Pressure(String magnitude, String derivative, int quantitySpaceLength)
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
        return ("Pressure: \t" + this.getMagnitude() + "\t" + this.getDerivative() + "\n");
    }
}
