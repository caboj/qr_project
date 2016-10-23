package nl.uva.qr.reasoning;

class Height extends Quantity
{
    public Height(Magnitude magnitude, Derivative derivative)
    {
        super(magnitude, derivative);
    }

    public String toString()
    {
        return ("Height:\t \t" + this.getMagnitude().getSymbol() + "\t" + this.getDerivative().getSymbol() + "\n");
    }
}
