package model;

/**
 * Created by Jisu on 6/8/2017.
 */
public class TestCase {
    String[][] comparedList;
    int ListActiveOrder;

    public String[][] getComparedList(){
        return comparedList;
    }
    public int getListActiveOrder(){
        return ListActiveOrder;
    }

    public boolean initTestCompare(int caseNum){
        //init comparedList
        if(caseNum == 8){
            comparedList = new String[2][16];
            comparedList[0][0] = "\n\n";
            comparedList[0][1] = "mainprog illegal;\n\n";
            comparedList[0][2] = "\n\n";
            comparedList[0][3] = "procedure proc1 (a : integer; b,c : integer);\n" +
                    "var d,e:string;\n" +
                    "begin\n" +
                    "\ta := 10;\n" +
                    "\tb := 20;\n" +
                    "\tc := 30;\n" +
                    "end\n" +
                    "\n" +
                    "function max (a: integer; b: integer) : integer; \n";
            comparedList[0][4] = "\n";
            comparedList[0][5] = "var r,y:float;\n";
            comparedList[0][6] = "\n\n";
            comparedList[0][7] = "begin\n" +
                    "\tif a >= b then return a; \n" +
                    "\telse return b;\n" +
                    "end\n" +
                    "\n" +
                    "function func1(a,b : integer) : float;\n";
            comparedList[0][8] = "\n";
            comparedList[0][9] = "var fval : float;\n" +
                    "begin\n";
            comparedList[0][10] = "\n";
            comparedList[0][11] = "\treturn a;\n";
            comparedList[0][12] = "\n";
            comparedList[0][13] = "end\n" +
                    "\n" +
                    "\n" +
                    "begin\n" +
                    "\n" +
                    "   proc1(10,20.0,30.0);\n" +
                    "   proc1(10,20);\n" +
                    "\n";
            comparedList[0][14] = "\n\n";
            comparedList[0][15] = "   a := 33;\n" +
                    "   print a;\n" +
                    "   print max(10,2.4);\n" +
                    "   print func1(3,4);\n" +
                    "\n" +
                    "end. ";

            comparedList[1][0] = "\23CE\n\23CE\n";
            comparedList[1][1] = "mainprog illegal;\n\n";
            comparedList[1][2] = "\23CE\n\23CE\n";
            comparedList[1][3] = "procedure proc1 (a : integer; b,c : integer);\n" +
                    "var d,e:string;\n" +
                    "begin\n" +
                    "\ta := 10;\n" +
                    "\tb := 20;\n" +
                    "\tc := 30;\n" +
                    "end\n" +
                    "\n" +
                    "function max (a: integer; b: integer) : integer; \n";
            comparedList[1][4] = "\23CE\n";
            comparedList[1][5] = "var r,y:float;\n";
            comparedList[1][6] = "\23CE\n\23CE\n";
            comparedList[1][7] = "begin\n" +
                    "\tif a >= b then return a; \n" +
                    "\telse return b;\n" +
                    "end\n" +
                    "\n" +
                    "function func1(a,b : integer) : float;\n";
            comparedList[1][8] = "\23CE\n";
            comparedList[1][9] = "var fval : float;\n" +
                    "begin\n";
            comparedList[1][10] = "\23CE\n";
            comparedList[1][11] = "\treturn a;\n";
            comparedList[1][12] = "\23CE\n";
            comparedList[1][13] = "end\n" +
                    "\n" +
                    "\n" +
                    "begin\n" +
                    "\n" +
                    "   proc1(10,20.0,30.0);\n" +
                    "   proc1(10,20);\n" +
                    "\n";
            comparedList[1][14] = "\23CE\n\23CE\n";
            comparedList[1][15] = "   a := 33;\n" +
                    "   print a;\n" +
                    "   print max(10,2.4);\n" +
                    "   print func1(3,4);\n" +
                    "\n" +
                    "end. ";

        }
        //init ListActiveOrder
        if(caseNum == 8){
            ListActiveOrder = 0;
        }
        return true;
    }

}
