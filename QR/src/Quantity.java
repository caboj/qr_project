abstract class Quantity
{
    private QuantitySpace quantitySpace;
    private DerivativeSpace derivativeSpace;

    private int  magnitude;
    private int derivative;

    void setQuantitySpace(int length)
    {
        this.quantitySpace = new QuantitySpace(length);
    }
    void setDerivativeSpace()
    {
        this.derivativeSpace = new DerivativeSpace();
    }


    void increaseMagnitude()
    {
        if (this.magnitude < (quantitySpace.getLength()-1))
        {
            this.magnitude++;
        }
        else
        {
            System.out.println("Sorry, magnitude can't be increased anymore");
        }
    }

    void decreaseMagnitude()
    {
        if(this.magnitude > 0)
        {
            this.magnitude--;
        }
        else
        {
            System.out.println("Sorry, magnitude can't be decreased anymore");
        }
    }

    void increaseDerivative()
    {
        if(this.derivative<2)
        {
            this.derivative++;
        }
        else
        {
            System.out.println("Quantity is already increasing");
        }
    }
    void decreaseDerivative()
    {
        if(this.derivative < 0)
        {
            this.derivative--;
        }
        else
        {
            System.out.println("Quantity is already decreasing");
        }
    }

    String getMagnitude()
    {
        return quantitySpace.getQuantitySpace()[magnitude];
    }

    void setMagnitude(String magnitude)
    {
        switch (magnitude)
        {
            case "0":
                this.magnitude = 0;
                break;
            case "+":
                this.magnitude = 1;
                break;
            case "MAX":
                this.magnitude = 2;
                break;
        }
    }

    String getDerivative()
    {
        return derivativeSpace.getDerivativeSpace()[derivative];
    }

    void setDerivative(String derivative)
    {
        switch (derivative)
        {
            case "-":
                this.derivative = 0;
                break;
            case "0":
                this.derivative = 1;
                break;
            case "+":
                this.derivative = 2;
                break;
        }
    }
}
