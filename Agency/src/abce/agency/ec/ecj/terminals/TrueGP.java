package abce.agency.ec.ecj.terminals;


import abce.agency.ec.ecj.operators.GPNodeDebug;
import abce.agency.ec.ecj.types.BooleanGP;
import abce.ecj.Debugger;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;



public class TrueGP extends GPNode {

	private static final long	serialVersionUID	= 1L;



	@Override
	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual,
			Problem problem) {
		BooleanGP b = (BooleanGP) input;
		b.value = true;

		// This is debug code and should not be enabled in most production-style
		// experiments
		if (Debugger.DEBUG_NODE_VALUES)
			GPNodeDebug.debug(state, thread, input, this, "bool");
	}



	@Override
	public String toString() {
		return "true";
	}

}
