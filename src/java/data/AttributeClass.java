package data;

import java.util.ArrayList;
/**
 * Class that contains the class attribute , contais the index of negative and positive rows
 * @author WilddogOo
 *
 */
public class AttributeClass {
 private ArrayList<Boolean> data;
 /**
  * constructor Method
  */
 public AttributeClass() {
	 this.data =new ArrayList<Boolean>();
 }
/**
 * add a positive (true) or negative(false) in the row (Pos) 
 * @param pos
 * @param data
 */
 public void addData(int pos,boolean data){
		 this.data.add(pos,data);
 }
/**
 * Getter method
 * @return the positve and negative rows
 */
public ArrayList<Boolean> getData() {
	return data;
}
 
}
