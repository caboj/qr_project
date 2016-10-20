package ReasoningStuff;

class DerivativeSpace
{
    final private String[] derivativeSpace;

    DerivativeSpace()
    {
        String[] space = {"-","0","+"};

        this.derivativeSpace = new String[3];

        System.arraycopy(space, 0, derivativeSpace, 0, 3);
    }
    String[] getDerivativeSpace()
    {
        return this.derivativeSpace;
    }
}
