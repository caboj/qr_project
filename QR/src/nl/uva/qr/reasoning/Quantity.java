package nl.uva.qr.reasoning;

abstract class Quantity
{
    private Magnitude magnitude;
    private Derivative derivative;

    public Quantity(Magnitude magnitude, Derivative derivative)
    {
        this.derivative = derivative;
        this.magnitude = magnitude;
    }

    void increaseMagnitude()
    {
        magnitude = magnitude.getNextFor(this);
    }

    void decreaseMagnitude()
    {
        magnitude = magnitude.getPrevious();
    }

    void increaseDerivative()
    {
        this.derivative = this.derivative.getNext();
    }

    void decreaseDerivative()
    {
        this.derivative = this.derivative.getPrevious();
    }

    Magnitude getMagnitude()
    {
        return magnitude;
    }

    void setMagnitude(Magnitude magnitude)
    {
        this.magnitude = magnitude;
    }

    Derivative getDerivative()
    {
        return derivative;
    }

    void setDerivative(Derivative derivative)
    {
        this.derivative = derivative;
    }
}
