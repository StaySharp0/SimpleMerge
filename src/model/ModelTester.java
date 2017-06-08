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
    //이슈1 : 문서 맨 끝의 개행문자가 생략되는 문제
    @Test
    public void testCompare(){
        File leftFile = new File("C:\\Users\\Jisu\\Desktop\\samples\\s7 left.txt");
        File rightFile = new File("C:\\Users\\Jisu\\Desktop\\samples\\s7 right.txt");

        //initialize model with testing input
        model = new Model(leftFile, rightFile);

        //get item
        Item item = model.compare();
        String[][] ListData = new String[2][];
        ListData[0] = item.getListViewItem(0);
        ListData[1] = item.getListViewItem(1);

        //create expected result
        int test_listActiveOrder = 1;    //true - 홀수  false - 짝수
        String[][] test_ListData = new String[2][9];

        test_ListData[0][0] = "mainprog illegal;\n" +
                "\n";
        test_ListData[0][1] = "procedure proc1 (a : integer; b,c : integer);\n";
        test_ListData[0][2] = "var d,e:string;\n" +
                "begin\n" +
                "\ta := 10;\n" +
                "\tb := 20;\n" +
                "\tc := 30;\n" +
                "end\n" +
                "\n" +
                "function max (a: integer; b: integer) : integer; \n" +
                "var r,y:float;\n";
        test_ListData[0][3] = "\u23CE\n";
        test_ListData[0][4] = "begin\n" +
                "\tif a >= b then return a; \n" +
                "\telse return b;\n" +
                "end\n" +
                "\n" +
                "function func1(a,b : integer) : float;\n" +
                "var fval : float;\n" +
                "begin\n" +
                "\treturn a;\n" +
                "end\n" +
                "\n" +
                "\n" +
                "begin\n" +
                "\n" +
                "   proc1(10,20.0,30.0);\n" +
                "   proc1(10,20);\n";
        test_ListData[0][5] = "\u23CE\n";
        test_ListData[0][6] = "\n" +
                "   a := 33;\n" +
                "   print a;\n" +
                "   print max(10,2.4);\n" +
                "   print func1(3,4);\n" +
                "\n";
        test_ListData[0][7] = "\u23CE\n\u23CE\n";
        test_ListData[0][8] = "end. ";
//\u23CE\n
        test_ListData[1][0] = "mainprog illegal;\n" +
                "\n";
        test_ListData[1][1] = "procedure proc1 (a : integer; b,c : integer; d: string);\n";
        test_ListData[1][2] = "var d,e:string;\n" +
                "begin\n" +
                "\ta := 10;\n" +
                "\tb := 20;\n" +
                "\tc := 30;\n" +
                "end\n" +
                "\n" +
                "function max (a: integer; b: integer) : integer; \n" +
                "var r,y:float;\n";
        test_ListData[1][3] = "var z;";
        test_ListData[1][4] = "begin\n" +
                "\tif a >= b then return a; \n" +
                "\telse return b;\n" +
                "end\n" +
                "\n" +
                "function func1(a,b : integer) : float;\n" +
                "var fval : float;\n" +
                "begin\n" +
                "\treturn a;\n" +
                "end\n" +
                "\n" +
                "\n" +
                "begin\n" +
                "\n" +
                "   proc1(10,20.0,30.0);\n" +
                "   proc1(10,20);\n";
        test_ListData[1][5] = "   proc1(10)";
        test_ListData[1][6] = "\n" +
                "   a := 33;\n" +
                "   print a;\n" +
                "   print max(10,2.4);\n" +
                "   print func1(3,4);\n" +
                "\n";
        test_ListData[1][7] = "\n   print x;\n";
        test_ListData[1][8] = "end. ";


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
