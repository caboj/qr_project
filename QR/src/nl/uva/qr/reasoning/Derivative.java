package nl.uva.qr.reasoning;

enum Derivative
{
    DECREASING,
    STABLE,
    INCREASING;

    private String symbol;
    private Derivative next;
    private Derivative previous;

    static
    {
        DECREASING.symbol = "-";
        DECREASING.next = STABLE;
        DECREASING.previous = DECREASING;

        STABLE.symbol = "0";
        STABLE.next = INCREASING;
        STABLE.previous = DECREASING;

        INCREASING.symbol = "+";
        INCREASING.next = INCREASING;
        INCREASING.previous = STABLE;
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

