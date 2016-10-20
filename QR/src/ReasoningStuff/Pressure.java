package ReasoningStuff;

class Pressure extends Quantity
{
    public Pressure(String magnitude, int quantitySpaceLength)
    {
        this.setQuantitySpace(quantitySpaceLength);
        this.setDerivativeSpace();
        this.setMagnitude(magnitude);
        this.setDerivative("0");
    }

    public Pressure(String magnitude)
    {
        int quantitySpaceLength = 3;
        this.setQuantitySpace(quantitySpaceLength);
        this.setDerivativeSpace();
        this.setMagnitude(magnitude);
        this.setDerivative("0");
    }

    public String toString()
    {
        return ("Pressure: \t" + this.getMagnitude() + "\t" + this.getDerivative() + "\n");
    }
}
