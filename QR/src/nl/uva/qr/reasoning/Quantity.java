package nl.uva.qr.reasoning;

public class Quantity
{
    private final QuantityType type;
    private final Magnitude magnitude;
    private final Derivative derivative;

    public Quantity(QuantityType type, Magnitude magnitude, Derivative derivative)
    {
        this.type = type;
        this.derivative = derivative;
        this.magnitude = magnitude;
    }

    Quantity increaseMagnitude()
    {
        return new Quantity(this.type, this.magnitude.getNextFor(this.type), this.derivative);
    }

    Quantity decreaseMagnitude()
    {
        return new Quantity(this.type, this.magnitude.getPrevious(), this.derivative);    }

    Quantity increaseDerivative()
    {
        return new Quantity(this.type, this.magnitude, this.derivative.getNext());
    }

    Quantity decreaseDerivative()
    {
        return new Quantity(this.type, this.magnitude, this.derivative.getPrevious());
    }

    Magnitude getMagnitude()
    {
        return magnitude;
    }

    Derivative getDerivative()
    {
        return derivative;
    }

    public String toString()
    {
        return (type + ": \t" + this.getMagnitude().getSymbol() + "\t" + this.getDerivative().getSymbol() + "\n");
    }
}
