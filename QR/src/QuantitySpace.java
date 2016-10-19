class QuantitySpace
{
    final private String[] quantitySpace;

    public QuantitySpace(int length)
    {
        String[] space = {"0","+","MAX"};

        this.quantitySpace = new String[length];
        System.arraycopy(space, 0, quantitySpace, 0, length);
    }
    public int getLength()
    {
        return quantitySpace.length;
    }

    public String[] getQuantitySpace()
    {
        return this.quantitySpace;
    }
}
