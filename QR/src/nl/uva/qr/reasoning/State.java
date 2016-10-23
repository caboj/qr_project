package nl.uva.qr.reasoning;

public class State
{
    final Quantity inflow;
    final Quantity outflow;
    final Quantity volume;
    final Quantity height;
    final Quantity pressure;


    private State(Quantity inflow, Quantity outflow, Quantity volume, Quantity height, Quantity pressure)
    {
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


    public boolean equals(State state)
    {
        if(!state.inflow.getMagnitude().equals(this.inflow.getMagnitude()))
        {
            return false;
        }
        if(!state.inflow.getDerivative().equals(this.inflow.getDerivative()))
        {
            return false;
        }

        if(!state.outflow.getMagnitude().equals(this.outflow.getMagnitude()))
        {
            return false;
        }
        if(!state.outflow.getDerivative().equals(this.outflow.getDerivative()))
        {
            return false;
        }

        if(!state.volume.getMagnitude().equals(this.volume.getMagnitude()))
        {
            return false;
        }
        if(!state.volume.getDerivative().equals(this.volume.getDerivative()))
        {
            return false;
        }

        if(!state.height.getMagnitude().equals(this.height.getMagnitude()))
        {
            return false;
        }
        if(!state.height.getDerivative().equals(this.height.getDerivative()))
        {
            return false;
        }

        if(!state.pressure.getMagnitude().equals(this.pressure.getMagnitude()))
        {
            return false;
        }
        if(!state.pressure.getDerivative().equals(this.pressure.getDerivative()))
        {
            return false;
        }
        return true;
    }

    public String toString()
    {
        return inflow.toString() +
                outflow.toString() +
                volume.toString() +
                height.toString() +
                pressure.toString();
    }
}
