package execute;

import java.util.ArrayList;
import data.Attribute;
import data.Data;
import data.Entropy;
import data.tree.Node;
import parser.DataParser;
/**
 * Class that implements the ID3 algorithm
 * @author WilddogOo
 *
 */


public class ID3Algorithm {

	private DataParser parser;
	
	/**
	 * constructor method
	 * @param index
	 * @param data
	 * @throws Exception
	 */
	public ID3Algorithm(String index,String data) throws Exception {
		this.parser=new DataParser(index,data);
	}
	/**
	 * Method that initiate the Decision build construction
	 * @return Decision tree
	 */
	public  Node buildTree()
	{
		ArrayList< Integer > dataList = new ArrayList< Integer >(parser.getNRows());
		int positive=0,negative=0;
		for ( int i = 0; i < parser.getNRows(); i++ )
		{
			dataList.add( i );
			if(this.parser.getClassAt().getData().get(i))
				++positive;
			else
				++negative;
		}
		

		return treeBuilder( new Entropy( positive,negative ), 0, new ArrayList<Integer>(), dataList );
	}
	/**
	 * Recursive method to build the decision tree
	 * @param entropy
	 * @param level
	 * @param indexList
	 * @param dataList
	 * @return decision tree
	 */
	private 	Node treeBuilder( Entropy entropy, int level, ArrayList< Integer > indexList, ArrayList< Integer > dataList )
	{
		ArrayList<Entropy> selectedEntropy = null;;
		ArrayList<Entropy> entTemp;
		double gain = 0;
		double temp;
		Attribute attribute;
		Attribute attributeSelected = null;
		int index = 0;

		for ( int i = 0; i < this.parser.getHeader().size(); i++ )
		{
			//Avoid use attributes used already
			if ( !indexList.contains( i ) )
			{
				attribute = this.parser.getHeader().get( i );
				entTemp = calculateEntropies( attribute, dataList );
				temp = calculateGain( entTemp, entropy );

				if ( temp >= gain )
				{
					gain = temp;
					selectedEntropy = entTemp;
					index = i;
					attributeSelected = attribute;
				}
			}
		}

		Node treeNode = new Node( attributeSelected, level );

		for (int i=0;i<attributeSelected.getSubset().size();++i )
		{
			
			if ( selectedEntropy.get(i).getEntropy() == 0.0 )
			{
				treeNode.getChild().put( attributeSelected.getSubset().get(i), new Node((selectedEntropy.get(i).getPositive() == 0.0) ? false : true,level+1) );
			}
			else if ( indexList.size() == parser.getHeader().size() - 1 )
			{
				treeNode.getChild().put(attributeSelected.getSubset().get(i) , new Node( (selectedEntropy.get(i).getPositive() > selectedEntropy.get(i).getNegative()) ? true : false ,level+1) );
			}
			else
			{
				indexList.add(index);
				ArrayList<Integer> tmp=generateSelectedRow(dataList,attributeSelected.getValues().get(attributeSelected.getSubset().get(i)).getIndex());
				Node tmpNode = treeBuilder(selectedEntropy.get(i),level + 1,indexList,tmp);
				treeNode.getChild().put(attributeSelected.getSubset().get(i),tmpNode);
				indexList.remove((Integer)index);
			}
		}
		return treeNode;
	}

	
	/**
	 * Support method that compare two array list of index to filter the rows
	 * @param dataList
	 * @param index
	 * @return Filter rows
	 */
	private ArrayList<Integer> generateSelectedRow(ArrayList<Integer> dataList, ArrayList<Integer> index) {
		ArrayList<Integer>data=new ArrayList<Integer>();
		for(int i=0;i<dataList.size();++i) {
			if(index.contains(dataList.get(i))) {
				data.add(dataList.get(i));
			}
		}
		return data;
	}
/**
 * Method to calculate the gain of an Attribute
 * @param tmp
 * @param entropy
 * @return Gain
 */
	private double calculateGain(ArrayList<Entropy> tmp, Entropy entropy) {
		double gain=entropy.getEntropy();
		double x;
		for(int i=0;i<tmp.size();++i) {
			x=(double)tmp.get(i).getTotal()/entropy.getTotal();
			gain=gain-(double)(tmp.get(i).getEntropy()*x);
		}
		
		return gain;
	}
/**
 * Method to calculate entropies of an attribute
 * @param attr
 * @param row
 * @return calculated entropys.
 */
	private ArrayList<Entropy> calculateEntropies(Attribute attr, ArrayList<Integer> row) {
		ArrayList<Entropy> result=new ArrayList<Entropy>();
		Data tmp;
		int positive,negative;
		for(int i=0;i<attr.getSubset().size();++i) {
			positive=0;
			negative=0;
			tmp=attr.getValues().get(attr.getSubset().get(i));
			for(int j=0;j<tmp.getIndex().size();++j) {
				if(row.contains(tmp.getIndex().get(j))) {
					if(this.parser.getClassAt().getData().get(tmp.getIndex().get(j)))
						++positive;
					else
						++negative;
				}
			}
			result.add(new Entropy(positive,negative));
		}
		
		return result;
	}

}

