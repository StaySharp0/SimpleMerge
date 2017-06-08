package model;
import dataSet.*;//`ll be changed
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import view.UI.FileSaveUI;
import view.UI.Position;//`ll be changed
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import model.*;

import javax.print.Doc;

/**
 * Created by Jisu on 6/7/2017.
 */
public class ModelTester {
    private Model model;
    /* mock이 필요한것만 냄기자
    private FileManager fm;
    private Document left;
    private Document right;
    private Document oleft;
    private Document oright;
    private Algorithm algo;
    private ArrayList<String> compResultLeft;
    private ArrayList<String> compResultRight;
    */
    public void setUp(){

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
    @Test
    public void testCompare(){
        File leftFile = new File("C:\\Users\\Jisu\\Desktop\\samples\\s2 left.txt");
        File rightFile = new File("C:\\Users\\Jisu\\Desktop\\samples\\s2 right.txt");

        //initialize model with testing input
        model = new Model(leftFile, rightFile);

        //get item
        Item item = model.compare();
        String[][] ListData = new String[2][];
        ListData[0] = item.getListViewItem(0);
        ListData[1] = item.getListViewItem(1);

        //create expected result
        int test_listActiveOrder = 1;    //true - 홀수  false - 짝수
        String[][] test_ListData = new String[2][3];
        test_ListData[0][0] = "1\n";
        test_ListData[0][1] = "\n";
        test_ListData[0][2] = "3\n4\n5\n6\n7\n8\n9\n10";
        test_ListData[1][0] = "1\n";
        test_ListData[1][1] = "2\n";
        test_ListData[1][2] = "3\n4\n5\n6\n7\n8\n9\n10";

        Assert.assertEquals(test_listActiveOrder, item.getListActiveOrder());
        Assert.assertEquals(test_ListData, ListData);
    }

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



    //public Item merge(int idx, int lr);
    //public Item merge(List<Integer> idxList, int lr);
    public boolean testMerge(){

        return true;
    }
}
