package abce.models.oligopoly;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import abce.agency.Market;
import abce.agency.MarketSimulation;
import abce.agency.consumer.Consumer;
import abce.agency.consumer.PerfectlyRationalConsumer;
import abce.agency.firm.Firm;
import abce.agency.goods.DurableGood;
import abce.agency.goods.Good;
import abce.agency.goods.PerishableGood;
import abce.agency.production.ConstantCostProductionFunction;
import abce.agency.production.ProductionFunction;
import abce.util.events.EventProcedureDescription;
import abce.util.io.FileManager;



/**
 * The Oligopoly simulation is the main entry part for the Oligopoly domain
 * model. The ECJ calls the constructor with the random number seed to
 * initialize the simulation and load the default configuration settings from
 * file. A subsequent all to initialize() finishes the configuration process.
 * Changes to the configuration can be made between these times by modifying
 * settings in the config object. After initialize() is finished, many changes
 * to configuration settings may cause undefined behaviors.
 * 
 * setupEvents and setupFirms setup those respective objects.
 * 
 * The main entry point for the model is through the call() method.
 * 
 * @author ruppmatt
 * 
 */
public class OligopolySimulation extends MarketSimulation {

	private static final long		serialVersionUID	= 1L;

	public final Good				good;
	public final Market				m;
	public ProductionFunction		pf;

	protected OligopolyConfig		_config;
	public transient FileManager	file_manager;



	public OligopolySimulation(long seed, String config_path, int gen) {
		super(seed);
		super.generation = gen;
		loadConfiguration(config_path);
		
		good = new PerishableGood("testgood", _config.good_spoilage_frac);
		m = new Market(good);

		
	}



	/**
	 * Initialize should be called after all changes to the configuration
	 * settings are made. Once this method is called, the settings in the
	 * configuration object are used to build other objects in the model. Any
	 * subsequent changes to configuration after initialize() returns results in
	 * undefined behavior in many cases.
	 */
	public void initialize() {
		_config.register();
		super.setStepsToRun(_config.steps_to_run);
		super.setSimulationRoot(new File(_config.simulation_root));

		pf = new ConstantCostProductionFunction(_config.cost_constant);
		addMarket(m);
		// Add consumers
		for (int i = 0; i < _config.number_of_customers; i++) {
			Consumer c = new PerfectlyRationalConsumer(_config.persons_per_consumer_agent);
			// Consumer c = new
			// ReluctantSwitcher(_config.persons_per_consumer_agent);
			c.enterMarket(m);
			
			// changed willingness to pay to be a function of the # of consumer agents,
			// representing a demand curve.  --kk
			c.setWTP(good, _config.demand_intercept + (_config.demand_slope * i) );
			addConsumer(c);
		}
	}



	/**
	 * Setup events for the domain model
	 * 
	 * @param events
	 */
	public void setupEvents(EventProcedureDescription[] events) {
		for (EventProcedureDescription desc : events) {
			addEventProcedure(desc);
		}
	}



	/**
	 * Setup firms in the domain model.
	 * 
	 * @param f
	 */
	public void setupFirm(Firm f) {
		f.grantEndowment(_config.firm_endowment);
		f.startProducing(good, pf);
		f.setInitialPrice(good, _config.firm_initial_price);
		f.setInitialProduction(good, _config.firm_initial_production);
		forceMarketEntry(f, m);
		addFirm(f);
	}



	/**
	 * Load the configuration file
	 * 
	 * @param config_path
	 */
	protected void loadConfiguration(String config_path) {
		_config = null;
		try {
			_config = new OligopolyConfig(config_path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}



	/**
	 * return the configuration object
	 */
	public OligopolyConfig getConfig() {
		return _config;
	}



	@Override
	public Integer call() throws Exception {
		return super.call();
	}
}
