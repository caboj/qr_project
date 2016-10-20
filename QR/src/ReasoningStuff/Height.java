package ReasoningStuff;

class Height extends Quantity
{
    public Height(String magnitude, int quantitySpaceLength)
    {
        this.setQuantitySpace(quantitySpaceLength);
        this.setDerivativeSpace();
        this.setMagnitude(magnitude);
        this.setDerivative("0");
    }

    public Height(String magnitude)
    {
        int quantitySpaceLength = 3;
        this.setQuantitySpace(quantitySpaceLength);
        this.setDerivativeSpace();
        this.setMagnitude(magnitude);
        this.setDerivative("0");
    }

    public String toString()
    {
        return ("Height:\t \t" + this.getMagnitude() + "\t" + this.getDerivative() + "\n");
    }
}
