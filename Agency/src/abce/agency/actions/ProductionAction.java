package abce.agency.actions;


import abce.agency.firm.Firm;
import abce.agency.goods.Good;



public class ProductionAction extends SimulationAction {

	private static final long	serialVersionUID	= 1L;

	public final Firm			firm;
	public final Good			good;
	public final double			qty;



	public ProductionAction(Firm f, Good good, double qty) {
		this.firm = f;
		this.good = good;
		this.qty = qty;
	}



	@Override
	protected String describe() {
		return "Firm " + firm + " producing " + qty + " of good " + good;
	}



	@Override
	public boolean isAllowed() {
		return firm.verify(this);
	}



	@Override
	public void reject() {
		// do nothing; the firm will just not produce this period.
	}



	@Override
	protected void actualize() {
		firm.actualize(this);
	}

}
