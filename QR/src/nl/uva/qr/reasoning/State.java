package nl.uva.qr.reasoning;

import com.sun.tools.javadoc.Start;

import java.time.OffsetDateTime;

public class State
{
    final Inflow inflow;
    final Outflow outflow;
    final Volume volume;
    final Height height;
    final Pressure pressure;

    /**
     *
     * @param inflow
     * @param outflow
     * @param volume
     * @param height
     * @param pressure
     *
     *
     */
    private State(Inflow inflow, Outflow outflow, Volume volume, Height height, Pressure pressure)
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
        private Inflow inflow;
        private Outflow outflow;
        private Volume volume;
        private Height height;
        private Pressure pressure;

        public Builder withInflow(Inflow inflow)
        {
            this.inflow = inflow;
            return this;
        }
        public Builder withOutflow(Outflow outflow)
        {
            this.outflow = outflow;
            return this;
        }
        public Builder withVolume(Volume volume)
        {
            this.volume = volume;
            return this;
        }
        public Builder withHeight(Height height)
        {
            this.height = height;
            return this;
        }
        public Builder withPressure(Pressure pressure)
        {
            this.pressure = pressure;
            return this;
        }

        public State copyOf(State state)
        {
            return new State(state.inflow, state.outflow, state.volume, state.height, state.pressure);
            //this.inflowMagnitude = state.getInflow().getMagnitude();
        }

        public State build()
        {
            return new State(inflow, outflow,volume,height,pressure);
        }
    }

    /*
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
    */

    public String toString()
    {
        return inflow.toString() +
                outflow.toString() +
                volume.toString() +
                height.toString() +
                pressure.toString();
    }
}
