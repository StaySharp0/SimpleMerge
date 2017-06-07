package model;

import dataSet.Item;//`ll be changed

import java.util.List;
import java.io.File;

public interface ModelInterface {
	public Item load(File f, int lr);
	public Item load(String path, int lr);

	public Item save(int lr);
	public Item save(String data, int lr);
	public Item save(List<String> data, int lr);


	public Item edit(String data, int lr);
	public Item edit(List<String> data, int lr);	
	
	public Item compare();

//	public Item getCompareResult(int lr);

	public Item merge(int idx, int lr);
	public Item merge(List<Integer> idxList, int lr);
	//public Item merge(int lr);
	
}

