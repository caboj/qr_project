package ReasoningStuff;

import TreeStuff.Node;

import java.util.Optional;
import java.util.ArrayList;

@SuppressWarnings("DanglingJavadoc")
class Dependencies
{

    //daarnaast missen interne dependencies. Zo als als de derivative increasing is, dan moet the magnitude ook increasen. Zou je die ook kunnen implementeren aub?
		/**
		 * If derivative is not 0 then do something
		 */
		State processDerivatives(State state)
    {
				State nextState = state.copy();
				String[] qualities = {"inflow","outflow","volume","height","pressure"};
        for(int i = 0; i < state.getParameters().length; i++)
						{
								if(state.getParameters()[i][1].equalsIgnoreCase("+"))
										{
												increaseMagnitude(nextState,qualities[i]);
												decreaseDerivative(nextState,qualities[i]);
										}
								if(state.getParameters()[i][1].equalsIgnoreCase("-"))
										{
												decreaseMagnitude(nextState,qualities[i]);
												increaseDerivative(nextState,qualities[i]);
										}

						}
				return nextState;
		}

		State setVCs(State state){
				if(state.volume.getMagnitude().equals("MAX"))
					 state.height.setMagnitude("MAX");
				if(state.height.getMagnitude().equals("MAX"))
					 state.pressure.setMagnitude("MAX");
				if(state.pressure.getMagnitude().equals("MAX"))
					 state.outflow.setMagnitude("MAX");

				if(state.volume.getMagnitude().equals("0"))
					 state.height.setMagnitude("0");
				if(state.height.getMagnitude().equals("0"))
					 state.pressure.setMagnitude("0");
				if(state.pressure.getMagnitude().equals("0"))
					 state.outflow.setMagnitude("0");

				return state;
		}

		State setProportionals(State state){
				String[] derivs = {"-","0","+"};
				for(String s : derivs){
						if(state.volume.getDerivative().equals(s))
								state.height.setDerivative(s);
						if(state.height.getDerivative().equals(s))
								state.pressure.setDerivative(s);
						if(state.pressure.getDerivative().equals(s))
								state.outflow.setDerivative(s);
				}

				return state;
		}

		State posInfluence(State state){
				if(state.inflow.getMagnitude().equals("+"))
					 state.volume.increaseDerivative();
				if(state.inflow.getMagnitude().equals("0"))
					 state.volume.setDerivative("0");
				return state;
		}

		State negInfluence(State state){
				if(state.outflow.getMagnitude().equals("+") || state.outflow.getMagnitude().equals("MAX"))
					 state.volume.increaseDerivative();
				if(state.outflow.getMagnitude().equals("0"))
					 state.volume.setDerivative("0");
				return state;
		}

					 
										
    /**
     * Four methods to increase and decrease Magnitude and Derivative of each quantity in general.
     *
     * @param node Node with start state as node.getData()
     * @param quantity Quantity with has to be increased or decreased
     * @return Returns an Optional<State>, means if there are changes,
     * it returns an optional with a new derived state in it.
     * If there are no changes, it returns an empty optional. This way, nullPointerExceptions can't appear anymore.
     */
    private State increaseMagnitude(State state, String quantity)
    {
        switch (quantity)
        {
            case "inflow":
                state.inflow.increaseMagnitude();
                break;
            case "outflow":
                state.outflow.increaseMagnitude();
                break;
            case "volume":
                state.volume.increaseMagnitude();
                break;
            case "height":
                state.height.increaseMagnitude();
                break;
        }
				return state;
    }


    private State decreaseMagnitude(State state, String quantity)
    {
				switch (quantity)
        {
            case "inflow":
                state.inflow.decreaseMagnitude();
                break;
            case "outflow":
                state.outflow.decreaseMagnitude();
                break;
            case "volume":
                state.volume.decreaseMagnitude();
                break;
            case "height":
                state.height.decreaseMagnitude();
                break;
        }
				return state;
    }
    private State increaseDerivative(State state, String quantity)
    {
        switch (quantity)
        {
            case "inflow":
                state.inflow.increaseDerivative();
                break;
            case "outflow":
                state.outflow.increaseDerivative();
                break;
            case "volume":
                state.volume.increaseDerivative();
                break;
            case "height":
                state.height.increaseDerivative();
                break;
        }
				return state;
    }

    private State decreaseDerivative(State state, String quantity)
    {
        switch (quantity)
        {
            case "inflow":
                state.inflow.decreaseDerivative();
                break;
            case "outflow":
                state.outflow.decreaseDerivative();
                break;
            case "volume":
                state.volume.decreaseDerivative();
                break;
            case "height":
                state.height.decreaseDerivative();
                break;
        }
				return state;
    }
}
