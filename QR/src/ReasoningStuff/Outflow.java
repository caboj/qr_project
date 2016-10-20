package ReasoningStuff;

class Outflow extends Quantity
{
    public Outflow(String magnitude, int quantitySpaceLength)
    {
        this.setQuantitySpace(quantitySpaceLength);
        this.setDerivativeSpace();
        this.setMagnitude(magnitude);
        this.setDerivative("0");
    }
    public Outflow(String magnitude)
    {
        int quantitySpaceLength = 3;
        this.setQuantitySpace(quantitySpaceLength);
        this.setDerivativeSpace();
        this.setMagnitude(magnitude);
        this.setDerivative("0");
    }

    public String toString()
    {
        return ("Outflow:\t" + this.getMagnitude() + "\t" + this.getDerivative() + "\n");
    }
}
