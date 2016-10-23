package nl.uva.qr.reasoning;

public class State
{
    public static int lastId = 0;
    private int id;

    final Quantity inflow;
    final Quantity outflow;
    final Quantity volume;
    final Quantity height;
    final Quantity pressure;


    private State(Quantity inflow, Quantity outflow, Quantity volume, Quantity height, Quantity pressure)
    {
        lastId++;
        this.id = lastId;
        this.inflow = inflow;
        this.outflow = outflow;
        this.volume = volume;
        this.height = height;
        this.pressure = pressure;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder
    {
        private Quantity inflow;
        private Quantity outflow;
        private Quantity volume;
        private Quantity height;
        private Quantity pressure;

        public Builder withInflow(Quantity inflow)
        {
            this.inflow = inflow;
            return this;
        }
        public Builder withOutflow(Quantity outflow)
        {
            this.outflow = outflow;
            return this;
        }
        public Builder withVolume(Quantity volume)
        {
            this.volume = volume;
            return this;
        }
        public Builder withHeight(Quantity height)
        {
            this.height = height;
            return this;
        }
        public Builder withPressure(Quantity pressure)
        {
            this.pressure = pressure;
            return this;
        }

        @SuppressWarnings("UnnecessaryLocalVariable")
        public Builder copyOf(State state)
        {
            Builder builder = new Builder()
                    .withInflow(state.inflow)
                    .withOutflow(state.outflow)
                    .withVolume(state.volume)
                    .withHeight(state.height)
                    .withPressure(state.pressure);

            return builder;
        }

        public State build()
        {
            return new State(inflow, outflow,volume,height,pressure);
        }
    }

    public int getId()
    {
        return id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (!inflow.equals(state.inflow)) return false;
        if (!outflow.equals(state.outflow)) return false;
        if (!volume.equals(state.volume)) return false;
        if (!height.equals(state.height)) return false;
        return pressure.equals(state.pressure);

    }

    @Override
    public int hashCode()
    {
        int result = inflow.hashCode();
        result = 31 * result + outflow.hashCode();
        result = 31 * result + volume.hashCode();
        result = 31 * result + height.hashCode();
        result = 31 * result + pressure.hashCode();
        return result;
    }

    public String toString(String prefix)
    {
        return prefix + inflow.toString() +
                prefix + outflow.toString() +
                prefix + volume.toString() +
                prefix + height.toString() +
                prefix + pressure.toString();
    }
}
