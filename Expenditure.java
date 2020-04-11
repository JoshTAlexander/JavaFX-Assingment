
/**
 * @author jta787
 *
 */
public class Expenditure {
	private String description;
	private int value;
	/**
	 * @param description
	 * @param value
	 */
	public Expenditure(String description, int value) {
		super();
		this.description = description;
		this.value = value;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
}
