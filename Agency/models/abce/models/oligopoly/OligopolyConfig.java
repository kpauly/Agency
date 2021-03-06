package abce.models.oligopoly;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class OligopolyConfig extends Properties {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public double				firm_initial_price;
	public double				firm_initial_production;
	public double				firm_endowment;
	public int					number_of_customers;
	public int					persons_per_consumer_agent;
	public double				demand_intercept;
	public double				demand_slope;
	public double				cost_constant;
	public int					steps_to_run;
	public String				simulation_root;
	public double				good_spoilage_frac;



	public OligopolyConfig(String file_path) throws FileNotFoundException, IOException {
		FileInputStream fin = null;
		fin = new FileInputStream(file_path);
		load(fin);
		fin.close();
		register();
	}



	protected void register() {
		firm_initial_price = D("firm_initial_price");
		firm_initial_production = D("firm_initial_production");
		firm_endowment = D("firm_endowment");
		number_of_customers = I("number_of_customers");
		persons_per_consumer_agent = I("persons_per_consumer_agent");
		demand_intercept = D("demand_intercept");
		demand_slope = D("demand_slope");
		cost_constant = D("cost_constant");
		steps_to_run = I("steps_to_run");
		simulation_root = S("simulation_root");
		good_spoilage_frac = D("good_spoilage_frac");
	}



	protected Double D(String key) {
		return Double.valueOf(getProperty(key));
	}



	protected Integer I(String key) {
		return Integer.valueOf(getProperty(key));
	}



	protected String S(String key) {
		return getProperty(key);
	}
}
