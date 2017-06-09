package model;

import javafx.geometry.Pos;
import view.UI.Position;

import java.awt.*;

/**
 * Created by Jisu on 6/8/2017.
 */
public class TestCase {
    String[][] list;
    int ListActiveOrder;
    int mergeIdx;
    int lr;

    public String[][] getComparedList(){
        return list;
    }
    public String[][] getMergedList(){ return list; }

    public int getListActiveOrder(){
        return ListActiveOrder;
    }

    public void initComparedList(int caseNum){
        if(caseNum == 1){
            ListActiveOrder = 1;    //true - 홀수  false - 짝수

            list = new String[2][1];
            list[0][0] = "1\n2\n3\n4\n5\n6\n7\n8\n9\n10";
            list[1][0] = "1\n2\n3\n4\n5\n6\n7\n8\n9\n10";
        }
        else if(caseNum == 2){
            ListActiveOrder = 1;    //true - 홀수  false - 짝수

            list = new String[2][3];
            list[0][0] = "1\n";
            list[0][1] = "\n";
            list[0][2] = "3\n4\n5\n6\n7\n8\n9\n10";

            list[1][0] = "1\n";
            list[1][1] = "2\n";
            list[1][2] = "3\n4\n5\n6\n7\n8\n9\n10";
        }
        else if(caseNum == 3){
            ListActiveOrder = 1;
            list = new String[2][3];
            list[0][0] = "1\n";
            list[0][1] = "\n\n\n";
            list[0][2] = "2\n3\n4\n5\n6\n7\n8\n9\n10";

            list[1][0] = "1\n";
            list[1][1] = "\u23CE\n\u23CE\n\u23CE\n";
            list[1][2] = "2\n3\n4\n5\n6\n7\n8\n9\n10";
        }
        else if(caseNum == 4){
            ListActiveOrder = 1;
            list = new String[2][11];
            list[0][0] = "1\n";
            list[0][1] = "\n\n\n";
            list[0][2] = "2\n";
            list[0][3] = "\n\n\n";
            list[0][4] = "3\n4\n";
            list[0][5] = "\n\n\n";
            list[0][6] = "5\n6\n7\n8\n";
            list[0][7] = "\n\n\n";
            list[0][8] = "9\n";
            list[0][9] = "\n\n\n";
            list[0][10] = "10";

            list[1][0] = "1\n";
            list[1][1] = "\u23CE\n\u23CE\n\u23CE\n";
            list[1][2] = "2\n";
            list[1][3] = "\u23CE\n\u23CE\n\u23CE\n";
            list[1][4] = "3\n4\n";
            list[1][5] = "\u23CE\n\u23CE\n\u23CE\n";
            list[1][6] = "5\n6\n7\n8\n";
            list[1][7] = "\u23CE\n\u23CE\n\u23CE\n";
            list[1][8] = "9\n";
            list[1][9] = "\u23CE\n\u23CE\n\u23CE\n";
            list[1][10] = "10";

        }
        else if(caseNum == 5){
            ListActiveOrder = 1;
            list = new String[2][12];
            list[0][0] = "1\n";
            list[0][1] = "1\n1\n1\n\n\n";
            list[0][2] = "2\n";
            list[0][3] = "2\n2\n2\n2\n\n\n";
            list[0][4] = "3\n";
            list[0][5] = "3\n3\n\n";
            list[0][6] = "4\n";
            list[0][7] = "4\n\n\n";
            list[0][8] = "5\n";
            list[0][9] = "5\n5\n5\n";
            list[0][10] = "6\n7\n8\n9\n10\n";
            list[0][11] = "1\n1";

            list[1][0] = "1\n";
            list[1][1] = "\u23CE\n\u23CE\n\u23CE\n\u23CE\n\u23CE\n";
            list[1][2] = "2\n";
            list[1][3] = "\u23CE\n\u23CE\n\u23CE\n\u23CE\n\u23CE\n\u23CE\n";
            list[1][4] = "3\n";
            list[1][5] = "\u23CE\n\u23CE\n\u23CE\n";
            list[1][6] = "4\n";
            list[1][7] = "\u23CE\n\u23CE\n\u23CE\n";
            list[1][8] = "5\n";
            list[1][9] = "\u23CE\n\u23CE\n\u23CE\n";
            list[1][10] = "6\n7\n8\n9\n10\n";
            list[1][11] = "\u23CE\n\u23CE";
        }
        else if(caseNum == 6){
            ListActiveOrder = 1;

            list = new String[2][1];
            list[0][0] = "mainprog illegal;\n" +
                    "\n" +
                    "procedure proc1 (a : integer; b,c : integer);\n" +
                    "var d,e:string;\n" +
                    "begin\n" +
                    "\ta := 10;\n" +
                    "\tb := 20;\n" +
                    "\tc := 30;\n" +
                    "end\n" +
                    "\n" +
                    "function max (a: integer; b: integer) : integer; \n" +
                    "var r,y:float;\n" +
                    "begin\n" +
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
                    "   proc1(10,20);\n" +
                    "\n" +
                    "   a := 33;\n" +
                    "   print a;\n" +
                    "   print max(10,2.4);\n" +
                    "   print func1(3,4);\n" +
                    "\n" +
                    "end. ";
            list[1][0] = "mainprog illegal;\n" +
                    "\n" +
                    "procedure proc1 (a : integer; b,c : integer);\n" +
                    "var d,e:string;\n" +
                    "begin\n" +
                    "\ta := 10;\n" +
                    "\tb := 20;\n" +
                    "\tc := 30;\n" +
                    "end\n" +
                    "\n" +
                    "function max (a: integer; b: integer) : integer; \n" +
                    "var r,y:float;\n" +
                    "begin\n" +
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
                    "   proc1(10,20);\n" +
                    "\n" +
                    "   a := 33;\n" +
                    "   print a;\n" +
                    "   print max(10,2.4);\n" +
                    "   print func1(3,4);\n" +
                    "\n" +
                    "end. ";

        }
        else if(caseNum == 7){
            ListActiveOrder = 1;
            list = new String[2][9];
            list[0][0] = "mainprog illegal;\n" +
                    "\n";
            list[0][1] = "procedure proc1 (a : integer; b,c : integer);\n";
            list[0][2] = "var d,e:string;\n" +
                    "begin\n" +
                    "\ta := 10;\n" +
                    "\tb := 20;\n" +
                    "\tc := 30;\n" +
                    "end\n" +
                    "\n" +
                    "function max (a: integer; b: integer) : integer; \n" +
                    "var r,y:float;\n";
            list[0][3] = "\u23CE\n";
            list[0][4] = "begin\n" +
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
            list[0][5] = "\u23CE\n";
            list[0][6] = "\n" +
                    "   a := 33;\n" +
                    "   print a;\n" +
                    "   print max(10,2.4);\n" +
                    "   print func1(3,4);\n" +
                    "\n";
            list[0][7] = "\u23CE\n\u23CE\n";
            list[0][8] = "end. ";
//\u23CE\n
            list[1][0] = "mainprog illegal;\n" +
                    "\n";
            list[1][1] = "procedure proc1 (a : integer; b,c : integer; d: string);\n";
            list[1][2] = "var d,e:string;\n" +
                    "begin\n" +
                    "\ta := 10;\n" +
                    "\tb := 20;\n" +
                    "\tc := 30;\n" +
                    "end\n" +
                    "\n" +
                    "function max (a: integer; b: integer) : integer; \n" +
                    "var r,y:float;\n";
            list[1][3] = "var z;\n";
            list[1][4] = "begin\n" +
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
            list[1][5] = "   proc1(10)\n";
            list[1][6] = "\n" +
                    "   a := 33;\n" +
                    "   print a;\n" +
                    "   print max(10,2.4);\n" +
                    "   print func1(3,4);\n" +
                    "\n";
            list[1][7] = "\n   print x;\n";
            list[1][8] = "end. ";
        }
        else if(caseNum == 8){
            ListActiveOrder = 0;
            list = new String[2][16];
            list[0][0] = "\n\n";
            list[0][1] = "mainprog illegal;\n\n";
            list[0][2] = "\n\n";
            list[0][3] = "procedure proc1 (a : integer; b,c : integer);\n" +
                    "var d,e:string;\n" +
                    "begin\n" +
                    "\ta := 10;\n" +
                    "\tb := 20;\n" +
                    "\tc := 30;\n" +
                    "end\n" +
                    "\n" +
                    "function max (a: integer; b: integer) : integer; \n";
            list[0][4] = "\n";
            list[0][5] = "var r,y:float;\n";
            list[0][6] = "\n\n";
            list[0][7] = "begin\n" +
                    "\tif a >= b then return a; \n" +
                    "\telse return b;\n" +
                    "end\n" +
                    "\n" +
                    "function func1(a,b : integer) : float;\n";
            list[0][8] = "\n";
            list[0][9] = "var fval : float;\n" +
                    "begin\n";
            list[0][10] = "\n";
            list[0][11] = "\treturn a;\n";
            list[0][12] = "\n";
            list[0][13] = "end\n" +
                    "\n" +
                    "\n" +
                    "begin\n" +
                    "\n" +
                    "   proc1(10,20.0,30.0);\n" +
                    "   proc1(10,20);\n" +
                    "\n";
            list[0][14] = "\n\n";
            list[0][15] = "   a := 33;\n" +
                    "   print a;\n" +
                    "   print max(10,2.4);\n" +
                    "   print func1(3,4);\n" +
                    "\n" +
                    "end. ";

            list[1][0] = "\u23CE\n\u23CE\n";
            list[1][1] = "mainprog illegal;\n\n";
            list[1][2] = "\u23CE\n\u23CE\n";
            list[1][3] = "procedure proc1 (a : integer; b,c : integer);\n" +
                    "var d,e:string;\n" +
                    "begin\n" +
                    "\ta := 10;\n" +
                    "\tb := 20;\n" +
                    "\tc := 30;\n" +
                    "end\n" +
                    "\n" +
                    "function max (a: integer; b: integer) : integer; \n";
            list[1][4] = "\u23CE\n";
            list[1][5] = "var r,y:float;\n";
            list[1][6] = "\u23CE\n\u23CE\n";
            list[1][7] = "begin\n" +
                    "\tif a >= b then return a; \n" +
                    "\telse return b;\n" +
                    "end\n" +
                    "\n" +
                    "function func1(a,b : integer) : float;\n";
            list[1][8] = "\u23CE\n";
            list[1][9] = "var fval : float;\n" +
                    "begin\n";
            list[1][10] = "\u23CE\n";
            list[1][11] = "\treturn a;\n";
            list[1][12] = "\u23CE\n";
            list[1][13] = "end\n" +
                    "\n" +
                    "\n" +
                    "begin\n" +
                    "\n" +
                    "   proc1(10,20.0,30.0);\n" +
                    "   proc1(10,20);\n" +
                    "\n";
            list[1][14] = "\u23CE\n\u23CE\n";
            list[1][15] = "   a := 33;\n" +
                    "   print a;\n" +
                    "   print max(10,2.4);\n" +
                    "   print func1(3,4);\n" +
                    "\n" +
                    "end. ";
        }
        else if(caseNum == 9) {
            ListActiveOrder = 1;

            list = new String[2][9];
            list[0][0] = "A\nAA\nAAA\n";
            list[0][1] = " AAAA\n";
            list[0][2] = "AAAAA\n";
            list[0][3] = "  AAAAAA\n";
            list[0][4] = "AAAAAAA\nAAAAAA\nAAAAA\nAAAA\nAAA\nAA\nA\nA\n";
            list[0][5] = "\n\n\n\n";
            list[0][6] = "A\nA\n";
            list[0][7] = "\n\n";
            list[0][8] = "A";

            list[1][0] = "A\nAA\nAAA\n";
            list[1][1] = "AAAA\n";
            list[1][2] = "AAAAA\n";
            list[1][3] = "AAAAAA\n";
            list[1][4] = "AAAAAAA\nAAAAAA\nAAAAA\nAAAA\nAAA\nAA\nA\nA\n";
            list[1][5] = "\u23CE\n\u23CE\n\u23CE\n\u23CE\n";
            list[1][6] = "A\nA\n";
            list[1][7] = "\u23CE\n\u23CE\n";
            list[1][8] = "A";
        }
    }

    public boolean initTestCompare(int caseNum){
        //init list
        if(caseNum == 1){
            ListActiveOrder = 1;    //true - 홀수  false - 짝수
        }
        else if(caseNum == 2){
            ListActiveOrder = 1;    //true - 홀수  false - 짝수
        }
        else if(caseNum == 3){
            ListActiveOrder = 1;
        }
        else if(caseNum == 4){
            ListActiveOrder = 1;
        }
        else if(caseNum == 5){
            ListActiveOrder = 1;
        }
        else if(caseNum == 6){
            ListActiveOrder = 1;
        }
        else if(caseNum == 7){
            ListActiveOrder = 1;
        }
        else if(caseNum == 8){
            ListActiveOrder = 0;
        }
        else if(caseNum == 9){
            ListActiveOrder = 1;
        }
        initComparedList(caseNum);
        return true;
    }

    public int getMergeIdx(){ return mergeIdx; }
    public int getLr(){ return lr; }

    public boolean initMerge(int caseNum){
        if(caseNum == 1) {      //cp to right
            this.mergeIdx = 1;
            this.lr = Position.RIGHT;
            list = new String[2][3];

            list[0][0] = "1\n";
            list[0][1] = "\n";
            list[0][2] = "3\n4\n5\n6\n7\n8\n9\n10";

            list[1][0] = "1\n";
            list[1][1] = "\n";
            list[1][2] = "3\n4\n5\n6\n7\n8\n9\n10";
        }
        else if(caseNum == 2) {      //cp to left
            this.mergeIdx = 1;
            this.lr = Position.LEFT;
            list = new String[2][3];

            list[0][0] = "1\n";
            list[0][1] = "2\n";
            list[0][2] = "3\n4\n5\n6\n7\n8\n9\n10";

            list[1][0] = "1\n";
            list[1][1] = "2\n";
            list[1][2] = "3\n4\n5\n6\n7\n8\n9\n10";
        }
        else if(caseNum == 3){        //cp enter to empty (->)
            this.mergeIdx = 1;
            this.lr = Position.RIGHT;
            list = new String[2][3];

            list[0][0] = "1\n";
            list[0][1] = "\n\n\n";
            list[0][2] = "2\n3\n4\n5\n6\n7\n8\n9\n10";

            list[1][0] = "1\n";
            list[1][1] = "\n\n\n";
            list[1][2] = "2\n3\n4\n5\n6\n7\n8\n9\n10";

        }
        return true;
    }

}
