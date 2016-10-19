class Inflow extends Quantity
{
    public Inflow(String magnitude, int quantitySpaceLength)
    {
        this.setQuantitySpace(quantitySpaceLength);
        this.setDerivativeSpace();
        this.setMagnitude(magnitude);
        this.setDerivative("0");
    }

    public Inflow(String magnitude)
    {
        int quantitySpaceLength = 2;
        this.setQuantitySpace(quantitySpaceLength);
        this.setDerivativeSpace();
        this.setMagnitude(magnitude);
        this.setDerivative("0");
    }

    public String toString()
    {
        return ("Inflow:\t \t" + this.getMagnitude() + "\t" + this.getDerivative() + "\n");
    }
}
