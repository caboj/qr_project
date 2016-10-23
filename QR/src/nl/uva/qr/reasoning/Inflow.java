package nl.uva.qr.reasoning;

class Inflow extends Quantity
{
    public Inflow(Magnitude magnitude, Derivative derivative)
    {
        super(magnitude, derivative);
    }

    public String toString()
    {
        return ("Inflow:\t \t" + this.getMagnitude().getSymbol() + "\t" + this.getDerivative().getSymbol() + "\n");
    }
}
