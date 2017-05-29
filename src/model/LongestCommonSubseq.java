/*
 * LongestCommonSubseq.java : 
 * find the LCS of two sequences of any typed data(such that can check equivalence - public boolean equals() is implemented)
 * testing with two string arg1, arg2 by
 * java LongestCommonSubseq <arg1> <arg2>
 * ~~also, with option "-f", testing with two files path1, path2 by~~
 * ~~java LongestCommonSubseq -f <path1> <path2>~~
 */

import java.lang.String;
import java.lang.StringBuilder;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
//import java.nio.CharBuffer;
import java.io.*;

public class LongestCommonSubseq<T>{
	final private T[] source;
	final private T[] target;

	// array of indexes for each...
	private int lenLcs;
	private ArrayList<Integer> lcsIndexSource; // indexes of source
	private ArrayList<Integer> lcsIndexTarget; // indexes of target
	//private int[][] changed; // indexes of source and target; changed[0] is source's; changed[1] is target's

	private void run(){
		int sl = this.source.length;
		int tl = this.target.length;
		int iBegin = 0, iEnd = sl;
		int jBegin = 0, jEnd = tl;
		int cntFront = 0, cntBack = 0;

		//TODO : optimization

		int lenArr[][] = new int[iEnd - iBegin + 1][jEnd - jBegin + 1]; // JAVA initializes this array by 0 automatically.
		int i,j;

		for(i = iBegin + 1; i <= iEnd; i++) {
			for(j = jBegin + 1; j <= jEnd; j++) {
				if(this.source[i - 1].equals(this.target[j - 1])){
					lenArr[i - iBegin][j - jBegin] = lenArr[i - iBegin - 1][j - jBegin - 1] + 1;
				}
				else {
					lenArr[i - iBegin][j - jBegin] 	= (
						lenArr[i - iBegin - 1][j - jBegin] 
						> lenArr[i - iBegin][j - jBegin - 1]
						) 
					? lenArr[i - iBegin - 1][j - jBegin] 
					: lenArr[i - iBegin][j - jBegin - 1]; 
				}
			}
		}

		this.lenLcs = cntFront + lenArr[sl][tl] + cntBack;
		System.out.println(lenLcs);
		if(this.lenLcs > 0){
			this.lcsIndexSource = new ArrayList<Integer>(this.lenLcs);
			this.lcsIndexTarget = new ArrayList<Integer>(this.lenLcs);

			for(i = 0; i < cntFront; i++){
				this.lcsIndexSource.add(new Integer(i));
				this.lcsIndexTarget.add(new Integer(i));
			}

			i = iBegin + 1;
			j = jBegin + 1;
			int cnt = cntFront;

			while(i <= iEnd && j <= jEnd && cnt < this.lenLcs - cntBack){
				System.out.println(i);
				System.out.println(j);
				if(this.source[i - 1].equals(this.target[j - 1])) {
					this.lcsIndexSource.add(new Integer(i - 1));
					this.lcsIndexTarget.add(new Integer(j - 1));	
					if(i < iEnd) i++;
					if(j < jEnd) j++;
					cnt++;
				}				
				else if ( (j == jEnd && i < iEnd) || 
							(lenArr[i - iBegin + 1][j - jBegin] > 	
						 	lenArr[i - iBegin][j - jBegin + 1])
						) 
				{
				 	i++;
				}
				else {
					if(j < jEnd) j++;
				}
				if(j == jEnd) {
					j = 1;
					i++;
				}
			}

			for(i = sl - cntBack, j = tl - cntBack;i < sl && j < tl;){

				this.lcsIndexSource.add(new Integer(i));
				this.lcsIndexSource.add(new Integer(j));

				if(i < sl - 1) {
					i++;
				}
				if(j < tl - 1) {
					j++;
				}
			}

		}
		else
		{
			this.setNullLcs();
		}
	}

	private void setNullLcs(){
		this.lenLcs = 0;
		this.lcsIndexSource = null;
		this.lcsIndexTarget = null;		
	}

	public LongestCommonSubseq(final Collection<T> source, final Collection<T> target) {
		this.source = (source != null) ?  (T[])source.toArray() : null;
		this.target = (target != null) ?  (T[])target.toArray() : null;

		if(this.source == null || this.target == null || source.isEmpty() || target.isEmpty() ) {
			this.setNullLcs();
		}
		else {
			this.run();	
		}
	}
	public int length(){
		return this.lenLcs;
	}

	public ArrayList<T> getLcsList(){
		if(this.lenLcs == 0){
			return null;
		}

		ArrayList<T> list = new ArrayList<T>(this.lenLcs);
		for(int i = 0; i < this.lenLcs; i++){
			list.add(this.source[this.lcsIndexSource.get(i)]);
		}

		return list;
	}

	public String toString(){
		StringBuilder str = new StringBuilder();

		for(int i = 0; i < this.lenLcs; i++){
			str.append(this.source[this.lcsIndexSource.get(i)].toString() + " ");
		}

		return str.toString();
	}

	private static void lcsTestString(String s, String t){
		ArrayList<Character> tmpArrsource = new ArrayList<Character>(s.length());
		ArrayList<Character> tmpArrtarget = new ArrayList<Character>(t.length());

		for(char c : s.toCharArray()){
			tmpArrsource.add(c);
		}
		for(char c : t.toCharArray()){
			tmpArrtarget.add(c);
		}

		LongestCommonSubseq<Character> lcs = new LongestCommonSubseq<Character>(tmpArrsource, tmpArrtarget);
		System.out.println(lcs.toString());
	}
	// private static void lcsTestFile(String sp, String tp){
	// 	File sf = new File(sp), tf = new File(tp);
	// 	FileReader sfr, tfr;
	// 	try{
	// 		sfr = new FileReader(sf);
	// 		tfr = new FileReader(tf);

	// 		ArrayList<String> tmpArrsource = new ArrayList<String>();
	// 		ArrayList<String> tmpArrtarget = new ArrayList<String>();
	// 		CharBuffer buf = new 
	// 		StringBuilder sb;
	// 		int begin = 0, end;

	// 		while(sfr.read(buf) != -1);
			
	// 		while((end = buf.indexOf('\n')) != -1){
	// 			tmpArrsource.add(buf.substring(begin, end));
	// 			begin = end;
	// 		}
			
	// 		begin = 0;
	// 		while(tfr.read(buf) != -1);
			
	// 		while((end = buf.indexOf('\n')) != -1){
	// 			tmpArrtarget.add(buf.substring(begin, end));
	// 			begin = end;
	// 		}

	// 		LongestCommonSubseq lcs = new LongestCommonSubseq<String>(tmpArrsource, tmpArrtarget);
	// 		ArrayList<String> lcsArr = lcs.getLcsList();
	// 		for(int i = 0; i < lcs.length();i++){
	// 			System.out.println(lcsArr.get(i));
	// 		}
	// 	}
	// 	catch(FileNotFoundException e) {
	// 		LongestCommonSubseq.errormsg("");//TODO : error msg. here
	// 	}
	// 	catch(IOException e){
	// 		LongestCommonSubseq.errormsg("");//TODO : error msg. here	
	// 	}
		
	// }

	private static void errormsg(String msg){
		//TODO
	}

	public static void main(String[] args){
		String source = "", target = "";

		if(args.length >= 2){
			// if(args.length >= 3 && args[0].equals("-f")){
			// 	source = args[1];
			// 	target = args[2];
			// 	LongestCommonSubseq.lcsTestFile(source, target);
			// }
			// else {
				source = args[0];
				target = args[1];
				LongestCommonSubseq.lcsTestString(source, target);
			// }
				
		}
		else{
			LongestCommonSubseq.errormsg("");//TODO : error msg here
		}		
	}	
	
}


