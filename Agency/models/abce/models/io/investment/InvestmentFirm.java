package abce.models.io.investment;

import abce.agency.finance.Accounts;

public interface InvestmentFirm {
	
	public double purchaseCapital(InvestmentModel sim, int marketNum);
	public double setProduction(InvestmentModel sim, int marketNum);
	
}
