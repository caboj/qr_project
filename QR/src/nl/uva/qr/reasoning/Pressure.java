package nl.uva.qr.reasoning;

class Pressure extends Quantity
{
    public Pressure(Magnitude magnitude, Derivative derivative)
    {
        super(magnitude, derivative);
    }

    public String toString()
    {
        return ("Pressure: \t" + this.getMagnitude().getSymbol() + "\t" + this.getDerivative().getSymbol() + "\n");
    }
}
