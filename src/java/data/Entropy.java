package data;
/**
 * Class that generate objects to give entropy to the attributes
 * @author WilddogOo
 *
 */
public class Entropy {
	private int positive;
	private int negative;
	private double entropy;
	/**
	 * constructor that recives the number of positive and negative case for the attribute
	 * @param positive
	 * @param negative
	 */
	public Entropy(int positive,int negative) {
		this.positive=positive;
		this.negative=negative;
		calculateEntropy();
	}
	/**
	 * Private method that generates the entropy using the number of negative and positives samples
	 */
	private void calculateEntropy() {
		int total=this.positive+this.negative;

		if(this.positive==0 || this.negative==0)
			this.entropy=0;
		else {
		double pChance=(double)this.positive/(double)total;
		double nChance=(double)this.negative/(double)total;
		this.entropy=(-pChance * Math.log( pChance ) / Math.log( 2 )) - (nChance * Math.log( nChance ) / Math.log( 2 ));
		}	
	}
	/***
	 * Getter method
	 * @return Entropy
	 */
	public double getEntropy() {
		return this.entropy;
	}
	/***
	 * Getter method
	 * @return The number of samples used
	 */
	public int getTotal() {
		return this.positive+this.negative;
	}
	/***
	 * Getter method
	 * @return Positive data
	 */
	
	public int getPositive() {
		return this.positive;
	}
	/***
	 * Getter method
	 * @return Negative data
	 */
	public int getNegative() {
		return this.negative;
	}
	
}
	