package model;

import view.UI.DataSet.Item;//`ll be changed
import view.UI.Position;//`ll be changed // can be enum class
import java.util.List;
import java.io.File;

public interface ModelInterface {
	public Item load(File f, int lr);
//{	
	public Item loadLeft(File f);
	public Item loadRight(File f);
//}
//{
	public Item load(String path, int lr);
	public Item loadLeft(String path);
	public Item loadRight(String path);
//}
	public Item save(List<String> data, int lr);
//{	
	// public Item saveLeft(List<String> date);
	// public Item saveRight(List<String> date);
//}

//{	
	public Item edit(String line, int idx, int lr);
	public Item editLeft(String line, int idx);
	public Item editRight(String line, int idx);
//}
	
	public Item compare(List<String> chData/*, int lr*/);
	public Item compare(/*int lr*/);

	public Item getCompareResult(int lr);
//{
	public Item getCompareResultLeft();
	public Item getCompareResultRight();
//}
	public Item merge(List<Integer> idxList, int lr);
	public Item merge(int idx, int lr);
	public Item merge(int lr);
}

