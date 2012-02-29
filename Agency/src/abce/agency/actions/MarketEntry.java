package abce.agency.actions;

import abce.agency.Market;
import abce.agency.consumer.Consumer;
import abce.agency.firm.Firm;

public class MarketEntry extends SimulationAction {

	public final Firm firm;
	public final Market market;
	public final Consumer consumer;
	
	public MarketEntry(Firm f, Market m) {
		firm = f;
		market = m;
		consumer = null;
	}
	
	public MarketEntry(Consumer c, Market m) {
		market = m;
		consumer = c;
		firm = null;
	}

	@Override
	protected void execute() {
		market.execute(this);
		if (firm != null)
			firm.execute(this);
		if (consumer != null)
			consumer.execute(this);
	}

	@Override
	protected boolean verify() {
		return true; // simulation always allows market entry
	}

	@Override
	protected String describe() {
		String description;
		if (consumer != null)
			description = "Entry of consumer " + consumer + " into market " + market;
		else
			description = "Entry of firm " + firm + " into market " + market;
		
		return description;
	}

	
}
