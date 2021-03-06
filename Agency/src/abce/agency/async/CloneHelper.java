package abce.agency.async;

import java.io.Serializable;

/**
 * Static factory methods to clone objects which we cannot extend to implement
 * AsyncUpdate.
 * 
 * <p>
 * OPTIMIZE: Consider abandoning in favor of a different approach.
 * 
 * @see abce.agency.async.Temporal
 * @author kkoning
 * 
 */
public class CloneHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Double cloneDouble(Double obj) {
		return new Double(obj);
	}

	public static Integer cloneInteger(Integer obj) {
		return new Integer(obj);
	}

}
