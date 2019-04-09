package data;
import java.util.ArrayList;
/**
 * Class that contains the index rows that belongs a sample
 * @author WilddogOo
 *
 */

public class Data {
	private ArrayList<Integer> index;
	/**
	 * Constructor
	 */
	public Data() {
		this.index=new ArrayList<Integer>();
	}
/**
 * Getter method
 * @return rows of the sample 
 */
	public ArrayList<Integer> getIndex() {
		return index;
	}
/**
 * Add a new index for the sample
 * @param index
 */
	public void addIndex(int index) {
		this.index.add(index);
	}
	
}
