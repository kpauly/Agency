package abce.agency;


import java.util.LinkedHashSet;
import java.util.Set;

import sim.engine.Schedule;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import abce.agency.async.AsyncUpdate;



/**
 * This addition to the MASON scheduler tests whether Steppables also implement
 * the AsyncUpdate interface. If they do, this schedule will call their update
 * method after each step of the schedule has been completed.
 * 
 * The MASON scheduler calls a protected function each time a public
 * scheduleOnce...() function is called. Therefore, since we cannot override the
 * generic function, we override each of these scheduleOnce...() functions.
 * However, each of the public scheduleRepeating() functions also calls the
 * public scheduleRepeating function which is overridden below. Therefore, the
 * hook is only required once for that entire set of functions.
 * 
 * @author kkoning
 * 
 */
public class AsyncDataSchedule extends Schedule {

	private static final long	serialVersionUID	= 1L;

	/**
	 * The set of agents which need to be updated asyncronously. This must be a
	 * set rather than a list/vector/array because updating an agent more than
	 * once would cause undesirable (inaccurate) behavior.
	 */
	protected Set<AsyncUpdate>	asyncUpdaters		= new LinkedHashSet<AsyncUpdate>();



	@Override
	public synchronized boolean step(SimState state) {
		// Process this step's updates.
		for (AsyncUpdate updater : asyncUpdaters) {
			updater.update();
		}
		
		// run the schedule as normal.
		boolean toReturn = super.step(state);

		

		return toReturn;
	}



	@Override
	public boolean scheduleOnce(Steppable event) {
		if (event instanceof AsyncUpdate) {
			asyncUpdaters.add((AsyncUpdate) event);
		}
		return super.scheduleOnce(event);
	}



	@Override
	public boolean scheduleOnceIn(double delta, Steppable event) {
		if (event instanceof AsyncUpdate) {
			asyncUpdaters.add((AsyncUpdate) event);
		}
		return super.scheduleOnceIn(delta, event);
	}



	@Override
	public boolean scheduleOnce(Steppable event, int ordering) {
		if (event instanceof AsyncUpdate) {
			asyncUpdaters.add((AsyncUpdate) event);
		}
		return super.scheduleOnce(event, ordering);
	}



	@Override
	public boolean scheduleOnceIn(double delta, Steppable event, int ordering) {
		if (event instanceof AsyncUpdate) {
			asyncUpdaters.add((AsyncUpdate) event);
		}
		return super.scheduleOnceIn(delta, event, ordering);
	}



	@Override
	public boolean scheduleOnce(double time, Steppable event) {
		if (event instanceof AsyncUpdate) {
			asyncUpdaters.add((AsyncUpdate) event);
		}
		return super.scheduleOnce(time, event);
	}



	@Override
	public boolean scheduleOnce(double time, int ordering, Steppable event) {
		if (event instanceof AsyncUpdate) {
			asyncUpdaters.add((AsyncUpdate) event);
		}
		return super.scheduleOnce(time, ordering, event);
	}



	@Override
	public boolean scheduleOnce(Key key, Steppable event) {
		if (event instanceof AsyncUpdate) {
			asyncUpdaters.add((AsyncUpdate) event);
		}
		return super.scheduleOnce(key, event);
	}



	@Override
	public Stoppable scheduleRepeating(double time, int ordering, Steppable event, double interval) {
		if (event instanceof AsyncUpdate) {
			asyncUpdaters.add((AsyncUpdate) event);
		}
		return super.scheduleRepeating(time, ordering, event, interval);
	}

}
