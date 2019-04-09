package data.tree;

import java.util.HashMap;

import data.Attribute;  
public class Node
{
	private Attribute attribute;
	private int level;
	private Boolean result;
	private HashMap< String, Node > child = new HashMap< String, Node >();
/**
 * Default Node
 * @param attribute
 * @param level
 */
	public Node ( Attribute attribute, int level )
	{
		this.attribute = attribute;
		this.level = level;
		this.result=null;
	}
/**
 * Constructor used by leaf nodes
 * @param b
 * @param level
 */
	public Node(boolean b,int level) {
		this.result=b;
		this.level=level;
	}
/**
 * get the result of a leaf
 * @return result of the leaf
 */
	public Boolean getResult() {
		return result;
	}
/**
 * Set the result of a leaf
 * @param result
 */
	public void setResult(Boolean result) {
		this.result = result;
	}
/**
 * Getter method
 * @return Attribute data
 */
	public Attribute getAttribute() {
		return attribute;
	}
/**
 * Getter method
 * @return the tree level
 */public static String Line =System.getProperty("line.separator");
	public int getLevel() {
		return level;
	}
/**
 * getter method
 * @return subset nodes
 */
	public HashMap<String, Node> getChild() {
		return child;
	}
		
	
	/**
	 * toString method that outputs all tree nodes recursive.
	 */
	public String toString() {
		String separator="";
		String output = "";
		for (int i=0;i<this.level;++i)
			separator+="	";
 		if(this.attribute!=null) {
		output+=separator+"/"+this.attribute.getHeader()+"/"+Line;
		for (int i=0;i<this.attribute.getSubset().size();++i) {	
			output+= "	"+separator+this.attribute.getSubset().get(i)+Line;
			if(this.result==null) {
				output+=this.child.get(this.attribute.getSubset().get(i)).toString();
			}

			}	
		}
 		if(this.result!=null) {
			if(this.result)
				output+=separator+"//////"+"Result : +"+Line;
			else
				output+=separator+"//////"+"Result : -"+Line;	
			}
 		return output;
	

}
}
