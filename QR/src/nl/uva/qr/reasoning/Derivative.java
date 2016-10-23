package nl.uva.qr.reasoning;

enum Derivative
{
    DECREASING("-"),
    STABLE("0"),
    INCREASING("+");

    private final String symbol;
    private Derivative next;
    private Derivative previous;

    static
    {
        DECREASING.next = STABLE;
        DECREASING.previous = DECREASING;

        STABLE.next = INCREASING;
        STABLE.previous = DECREASING;

        INCREASING.next = INCREASING;
        INCREASING.previous = STABLE;
    }



    Derivative(String symbol)
    {
        this.symbol = symbol;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public Derivative getNext()
    {
        return next;
    }

    public Derivative getPrevious()
    {
        return previous;
    }

    public boolean isDecreasing()
    {
        return this == DECREASING;
    }

    public boolean isStable()
    {
        return this == STABLE;
    }

    public boolean isIncreasing()
    {
        return this == INCREASING;
    }


}

