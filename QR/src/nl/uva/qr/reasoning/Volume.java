package nl.uva.qr.reasoning;

class Volume extends Quantity
{
    public Volume(Magnitude magnitude, Derivative derivative)
    {
        super(magnitude, derivative);
    }

    public String toString()
    {
        return ("Volume:\t \t" + this.getMagnitude().getSymbol() + "\t" + this.getDerivative().getSymbol() + "\n");
    }
}
