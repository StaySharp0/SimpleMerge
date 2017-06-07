package model;
import dataSet.*;//`ll be changed
import view.UI.FileSaveUI;
import view.UI.Position;//`ll be changed
import java.io.File;
import java.util.List;
import java.util.ArrayList;

import dataSet.MergeCompare;
import org.junit.Test;
import org.junit.Assert;
import org.easymock.EasyMock;
/**
 * Created by Jisu on 6/7/2017.
 */
public class ModelTester {

    //public Item load(File f, int lr);
    //public Item load(String path, int lr);
    //public Item save(int lr);
    public boolean testLoad(){

        return true;
    }
    //public Item save(String data, int lr);
    //public Item save(List<String> data, int lr);
    public boolean testSave(){

        return true;
    }

    //public Item edit(String data, int lr);
    //public Item edit(List<String> data, int lr);
    public boolean testEdit(){

        return true;
    }

    //public Item compare();
    /*
		MergeCompare rtn = new Item();
		if(!this.isCompared()){
			this.algo = new Algorithm(this.left.getLines(), this.right.getLines());
		}

		String[][] ListData = {
				(String[]) this.getResultLeft().toArray(),
				(String[]) this.getResultRight().toArray()
		};

		rtn.setListViewItem(ListData);
		rtn.setListActiveOrder(this.algo.isFirstAreSame());

		return (Item) rtn;
     */
    public boolean testCompare(){
    //create needed variables

        return true;
    }

    //public Item merge(int idx, int lr);
    //public Item merge(List<Integer> idxList, int lr);
    public boolean testMerge(){

        return true;
    }
}
