package nl.uva.qr.reasoning;

enum Magnitude
{
    OFF("0"),
    POSITIVE("+"),
    MAX("MAX");


    private final String symbol;
    private Magnitude next;
    private Magnitude previous;

    static
    {
        OFF.next = POSITIVE;
        OFF.previous = OFF;

        POSITIVE.next = MAX;
        POSITIVE.previous = OFF;

        MAX.next = MAX;
        MAX.previous = POSITIVE;

    }


    Magnitude(String symbol)
    {
        this.symbol = symbol;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public Magnitude getPrevious()
    {
        return previous;
    }

    public Magnitude getNextFor(QuantityType type)
    {
        if(type==QuantityType.INFLOW && this==POSITIVE)
        {
            return POSITIVE;
        }
        return next;

    }
}
