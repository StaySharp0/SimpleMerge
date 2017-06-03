package model;

import view.UI.DataSet.Item;//`ll be changed
import view.UI.Position;//`ll be changed // can be enum class
import java.util.List;
import java.io.File;

public interface ModelInterface {
	public Item load(File f, int lr);
	public Item load(String path, int lr);

	public Item save(int lr);
	public Item save(String data, int lr);
	public Item save(List<String> data, int lr);
//{	
	// public Item saveLeft(List<String> date);
	// public Item saveRight(List<String> date);
//}

	public Item edit(String data, int lr);
	public Item edit(List<String> data, int lr);	
	
	public Item compare();

	public Item getCompareResult(int lr);

	
	public Item merge(int idx, int lr);
	public Item merge(int lr);
	public Item merge(List<Integer> idxList, int lr);
}

