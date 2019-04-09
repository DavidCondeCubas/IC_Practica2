package data;

import java.util.ArrayList;
import java.util.HashMap;

 
public class Attribute{
	private String header;
	private ArrayList<String> subset;
	private HashMap<String,Data> values;
 
	public Attribute(String header,int index) {
		this.header=header;
		this.values=new HashMap<String,Data>();
		this.subset=new ArrayList<String>();
	}
	 
	public void insertData(String name,	int index) {
		if(!this.values.containsKey(name)) {
			values.put(name, new Data());
			this.subset.add(name);
		}
		
		values.get(name).addIndex(index);
	}
 
	public String getHeader() {
		return header;
	}
	 

	public ArrayList<String> getSubset(){
		return this.subset;
	}
	 
	public HashMap<String, Data> getValues() {
		return values;
	}

}
