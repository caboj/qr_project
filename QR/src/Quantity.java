/**
 * Created by Elvira on 19-10-16.
 */
public abstract class Quantity
{
    private QuantitySpace quantitySpace;
    private DerivatySpace derivatySpace;

    private int  magnitude;
    private int derivaty;

    public void setQuantitySpace(int length)
    {
        this.quantitySpace = new QuantitySpace(length);
    }
    public void setDerivatySpace()
    {
        this.derivatySpace = new DerivatySpace();
    }


    public void increaseMagnitude()
    {
        if (this.magnitude < (quantitySpace.getLength()-1))
        {
            magnitude++;
        }
        else
        {
            System.out.println("Sorry, magnitude can't be increased anymore");
        }
    }

    public void decreaseMagnitude()
    {
        if(this.magnitude > 0)
        {
            magnitude--;
        }
        else
        {
            System.out.println("Sorry, magnitude can't be decreased anymore");
        }
    }

    public String getMagnitude()
    {
        return quantitySpace.getQuantitySpace()[magnitude];
    }

    public void setMagnitude(String magnitude)
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

    public String getDerivaty()
    {
        return derivatySpace.getDerivatySpace()[derivaty];
    }

    public void setDerivaty(String derivaty)
    {
        switch (derivaty)
        {
            case "-":
                this.derivaty = 0;
                break;
            case "0":
                this.derivaty = 1;
                break;
            case "+":
                this.derivaty = 2;
                break;
        }
    }
}
