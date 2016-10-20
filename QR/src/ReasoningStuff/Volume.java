package ReasoningStuff;

class Volume extends Quantity
{
    public Volume(String magnitude, int quantitySpaceLength)
    {
        this.setQuantitySpace(quantitySpaceLength);
        this.setDerivativeSpace();
        this.setMagnitude(magnitude);
        this.setDerivative("0");
    }

    public Volume(String magnitude)
    {
        int quantitySpaceLength = 3;
        this.setQuantitySpace(quantitySpaceLength);
        this.setDerivativeSpace();
        this.setMagnitude(magnitude);
        this.setDerivative("0");
    }

    public String toString()
    {
        return ("Volume:\t \t" + this.getMagnitude() + "\t" + this.getDerivative() + "\n");
    }
}
