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

    @Test
    public void testCompare(){
        int caseNum = 1;
        File leftFile = new File("C:\\Users\\Jisu\\Desktop\\samples\\s" + caseNum + " left.txt");
        File rightFile = new File("C:\\Users\\Jisu\\Desktop\\samples\\s" + caseNum + " right.txt");
        TestCase test = new TestCase();

        test.initTestCompare(caseNum);

        //initialize model with testing input
        model = new Model(leftFile, rightFile);

        //get item
        Item item = model.compare();
        String[][] ListData = new String[2][];
        ListData[0] = item.getListViewItem(0);
        ListData[1] = item.getListViewItem(1);

        //create expected result
        int test_listActiveOrder = test.getListActiveOrder();    //true - 홀수  false - 짝수
        String[][] test_ListData = test.getComparedList();

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
