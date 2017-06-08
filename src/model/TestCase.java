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
        if(caseNum == 1){
            ListActiveOrder = 1;    //true - 홀수  false - 짝수

            comparedList = new String[2][1];
            comparedList[0][0] = "1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n";
            comparedList[1][0] = "1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n";
        }
        else if(caseNum == 2){
            ListActiveOrder = 1;    //true - 홀수  false - 짝수

            comparedList = new String[2][3];
            comparedList[0][0] = "1\n";
            comparedList[0][1] = "\n";
            comparedList[0][2] = "3\n4\n5\n6\n7\n8\n9\n10\n";

            comparedList[1][0] = "1\n";
            comparedList[1][1] = "2\n";
            comparedList[1][2] = "3\n4\n5\n6\n7\n8\n9\n10\n";
        }
        else if(caseNum == 3){
            ListActiveOrder = 1;
            comparedList = new String[2][3];
            comparedList[0][0] = "1\n";
            comparedList[0][1] = "\n\n\n";
            comparedList[0][2] = "2\n3\n4\n5\n6\n7\n8\n9\n10\n";

            comparedList[1][0] = "1\n";
            comparedList[1][1] = "\n";
            comparedList[1][2] = "2\n3\n4\n5\n6\n7\n8\n9\n10";
        }
        else if(caseNum == 4){
            ListActiveOrder = 1;
            comparedList = new String[2][11];
            comparedList[0][0] = "1\n";
            comparedList[0][1] = "\n\n\n";
            comparedList[0][2] = "2\n";
            comparedList[0][3] = "\n\n\n";
            comparedList[0][4] = "3\n4n\n";
            comparedList[0][5] = "\n\n\n";
            comparedList[0][6] = "5\n6\n7\n8\n";
            comparedList[0][7] = "n\n\n";
            comparedList[0][8] = "9\n";
            comparedList[0][9] = "\n\n\n";
            comparedList[0][10] = "10\n";

            comparedList[1][0] = "1\n";
            comparedList[1][1] = "\u23CE\n\u23CE\n\u23CE\n";
            comparedList[0][2] = "2\n";
            comparedList[0][3] = "\u23CE\n\u23CE\n\u23CE\n";
            comparedList[0][4] = "3\n4n\n";
            comparedList[0][5] = "\u23CE\n\u23CE\n\u23CE\n";
            comparedList[0][6] = "5\n6\n7\n8\n";
            comparedList[0][7] = "\u23CE\n\u23CE\n\u23CE\n";
            comparedList[0][8] = "9\n";
            comparedList[0][9] = "\u23CE\n\u23CE\n\u23CE\n";
            comparedList[0][10] = "10\n";

        }
        else if(caseNum == 5){
            ListActiveOrder = 1;
             comparedList = new String[2][12];
             comparedList[0][0] = "1\n";
             comparedList[0][1] = "1\n1\n1\n\n\n";
             comparedList[0][2] = "2\n";
             comparedList[0][3] = "2\n2\n2\n2\n\n\n";
             comparedList[0][4] = "3\n";
             comparedList[0][5] = "3\n3\n\n";
             comparedList[0][6] = "4\n";
             comparedList[0][7] = "4\n\n\n";
             comparedList[0][8] = "5\n";
             comparedList[0][9] = "5\n5\n5\n";
             comparedList[0][10] = "6\n7\n8\n9\n10\n";
             comparedList[0][11] = "1\n1\n";

             comparedList[1][0] = "1\n";
             comparedList[1][1] = "\u23CE\n\u23CE\n\u23CE\n\u23CE\n\u23CE\n";
             comparedList[1][2] = "2\n";
             comparedList[1][3] = "\u23CE\n\u23CE\n\u23CE\n\u23CE\n\u23CE\n\u23CE\n\u23CE";
             comparedList[1][4] = "3\n";
             comparedList[1][5] = "\u23CE\n\u23CE\n\u23CE\n";
             comparedList[1][6] = "4\n";
             comparedList[1][7] = "\u23CE\n\u23CE\n\u23CE\n";
             comparedList[1][8] = "5\n";
             comparedList[1][9] = "\u23CE\n\u23CE\n\u23CE\n";
             comparedList[1][10] = "6\n7\n8\n9\n10\n";
             comparedList[1][11] = "\u23CE\n\u23CE\n";
        }
        else if(caseNum == 6){
            ListActiveOrder = 1;

            comparedList = new String[2][1];
            comparedList[0][0] = "mainprog illegal;\n" +
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
                    "end. \n";
            comparedList[1][0] = "mainprog illegal;\n" +
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
                    "end. \n";

        }
        else if(caseNum == 7){
            ListActiveOrder = 1;
            comparedList = new String[2][9];
            comparedList[0][0] = "mainprog illegal;\n" +
                    "\n";
            comparedList[0][1] = "procedure proc1 (a : integer; b,c : integer);\n";
            comparedList[0][2] = "var d,e:string;\n" +
                    "begin\n" +
                    "\ta := 10;\n" +
                    "\tb := 20;\n" +
                    "\tc := 30;\n" +
                    "end\n" +
                    "\n" +
                    "function max (a: integer; b: integer) : integer; \n" +
                    "var r,y:float;\n";
            comparedList[0][3] = "\u23CE\n";
            comparedList[0][4] = "begin\n" +
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
            comparedList[0][5] = "\u23CE\n";
            comparedList[0][6] = "\n" +
                    "   a := 33;\n" +
                    "   print a;\n" +
                    "   print max(10,2.4);\n" +
                    "   print func1(3,4);\n" +
                    "\n";
            comparedList[0][7] = "\u23CE\n\u23CE\n";
            comparedList[0][8] = "end. \n";
//\u23CE\n
            comparedList[1][0] = "mainprog illegal;\n" +
                    "\n";
            comparedList[1][1] = "procedure proc1 (a : integer; b,c : integer; d: string);\n";
            comparedList[1][2] = "var d,e:string;\n" +
                    "begin\n" +
                    "\ta := 10;\n" +
                    "\tb := 20;\n" +
                    "\tc := 30;\n" +
                    "end\n" +
                    "\n" +
                    "function max (a: integer; b: integer) : integer; \n" +
                    "var r,y:float;\n";
            comparedList[1][3] = "var z;";
            comparedList[1][4] = "begin\n" +
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
            comparedList[1][5] = "   proc1(10)";
            comparedList[1][6] = "\n" +
                    "   a := 33;\n" +
                    "   print a;\n" +
                    "   print max(10,2.4);\n" +
                    "   print func1(3,4);\n" +
                    "\n";
            comparedList[1][7] = "\n   print x;\n";
            comparedList[1][8] = "end. \n";
        }
        else if(caseNum == 8){
            ListActiveOrder = 0;
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
                    "end. \n";

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
                    "end. \n";
        }

        return true;
    }

}
