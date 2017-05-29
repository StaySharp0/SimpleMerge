package model;

import view.UI.DataSet.Item;
import java.util.List;

public interface ModelInterface {
	public Item load(String path, boolean lr); // false : left, true : right
	public Item loadLeft(String path);
	public Item loadRight(String path);

	public Item save(List<String> data, boolean lr);
	public Item saveLeft(List<String> date);
	public Item saveRight(List<String> date);

	public Item compare();
	public Item compareResultLeft();
	public Item compareResultRight();
}

