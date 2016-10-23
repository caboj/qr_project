package nl.uva.qr.reasoning;

class Outflow extends Quantity
{
    public Outflow(Magnitude magnitude, Derivative derivative)
    {
        super(magnitude, derivative);
    }

    public String toString()
    {
        return ("Outflow:\t" + this.getMagnitude().getSymbol() + "\t" + this.getDerivative().getSymbol() + "\n");
    }
}
