package nl.uva.qr.reasoning;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Created by Elvira on 22-10-16.
 */
enum Magnitude
{
    OFF,
    POSITIVE,
    MAX;

    private String symbol;
    private Magnitude next;
    private Magnitude previous;

    static
    {
        OFF.symbol = "0";
        OFF.next = POSITIVE;
        OFF.previous = OFF;

        POSITIVE.symbol = "+";
        POSITIVE.next = MAX;
        POSITIVE.previous = OFF;

        MAX.symbol = "MAX";
        MAX.next = MAX;
        MAX.previous = POSITIVE;

    }

    public String getSymbol()
    {
        return symbol;
    }

    public Magnitude getNext()
    {
        return next;
    }

    public Magnitude getPrevious()
    {
        return previous;
    }

    public Magnitude getNextFor(Quantity quantity)
    {
        if (quantity instanceof Inflow && this == POSITIVE)
        {
            return POSITIVE;
        }
        return next;

    }
}
