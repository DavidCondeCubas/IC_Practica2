package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import data.Attribute;
import data.AttributeClass;
/**
 * Class to parse the CSV data
 * @author WilddogOo
 *
 */
public class DataParser {
	private ArrayList<Attribute> header;
	private AttributeClass classAt;
	private int nRows;
	/**
	 * constructor method that generates the attributes and the attribute class
	 * @param header
	 * @param data
	 * @throws Exception
	 */
	public DataParser(String header,String data) throws Exception {
		
		this.classAt =new AttributeClass();
		this.header=new ArrayList<Attribute>();
		BufferedReader sc=new BufferedReader(new FileReader(header));
		String dataInput[]=sc.readLine().split(",");
		for(int i=0;i<dataInput.length-1;++i) {
			this.header.add(new Attribute(dataInput[i],i));		
		}
		sc.close();
		sc=new BufferedReader(new FileReader(data));
		int k=0;
		boolean positive;
		while(sc.ready()) {
			dataInput=sc.readLine().split(",");
			positive=dataInput[dataInput.length-1].equals("si");	
			this.classAt.addData(k,positive);
			for(int i=0;i<dataInput.length-1;++i) {
				this.header.get(i).insertData(dataInput[i], k);
			}
			++k;
		}
		this.nRows=k;
		sc.close();
	}
/**
 * getter method
 * @return
 */
	public ArrayList<Attribute> getHeader() {
		return header;
	}
/**
 * getter method
 * @return
 */
	public AttributeClass getClassAt() {
		return this.classAt;
	}
/**
 * getter method
 * @return
 */
	public int getNRows() {
		return this.nRows;
	}
	
}
