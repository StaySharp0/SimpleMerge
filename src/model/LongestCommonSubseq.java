/*
 * LongestCommonSubseq.java : 
 * find the LCS of two sequences of any typed data(such that can check equivalence - public boolean equals() is implemented)
 * testing with two string arg1, arg2 by
 * java LongestCommonSubseq <arg1> <arg2>
 * also, with option "-f", testing with two files path1, path2 by
 * java LongestCommonSubseq -f <path1> <path2>
 */
package model;

import java.lang.String;
import java.lang.StringBuilder;
import java.util.*;
import java.io.*;

public class LongestCommonSubseq<T>{
	final private T[] source;
	final private T[] target;

	private ArrayList<T> lcs;

	// array of indexes for each...
	private int lenLcs;
	// private ArrayList<Integer> lcsIndexSource; // indexes of source
	// private ArrayList<Integer> lcsIndexTarget; // indexes of target

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

	private void run(){
		int sl = this.source.length;
		int tl = this.target.length;

		int lenArr[][] = new int[sl + 1][tl + 1]; // JAVA initializes this array by 0 automatically.
		int i,j;
		int tmplen = 0;
		this.lenLcs = 0;

		for(i = 1; i <= sl; i++) {
			for(j = 1; j <= tl; j++) {
				if(this.source[i - 1].equals(this.target[j - 1])){
					lenArr[i][j] = lenArr[i - 1][j - 1] + 1;
				}
				else {
					lenArr[i][j] = (lenArr[i - 1][j] > lenArr[i][j - 1]) ? lenArr[i - 1][j]	: lenArr[i][j - 1]; 
				}
			}
		}

		this.lenLcs = lenArr[sl][tl];
		if(this.lenLcs > 0){
			this.lcs = new ArrayList<T>(this.lenLcs);
			int cnt = 0;

			for(i = sl; cnt < this.lenLcs && i > 0;){
				for(j = tl; cnt < this.lenLcs && j > 0;){
					if(this.source[i - 1].equals(this.target[j - 1])){
						this.lcs.add(this.source[i - 1]);
						i--;
						j--;
						cnt++;
					}
					else if(lenArr[i - 1][j] > lenArr[i][j - 1]){
						i--;
					}
					else {
						j--;
					}
				}
			}

			Stack<T> s = new Stack<T>();
			while(!this.lcs.isEmpty()){
				s.push(this.lcs.remove(0));
			}

			while(!s.empty()){
				this.lcs.add(s.pop());
			}
		}
		else {
			this.setNullLcs();
		}

		/*
			i = iEnd;
			j = jEnd;
			int cnt = cntFront;

			while(i > iBegin && j > jBegin && cnt < this.lenLcs - cntBack){
				if(this.source[i - 1].equals(this.target[j - 1])) {
					this.lcsIndexSource.add(new Integer(i - 1));
					this.lcsIndexTarget.add(new Integer(j - 1));	
					if(i > iBegin) i--;
					if(j > jBegin) j--;
					cnt++;
				}				
				else if ( 
							(lenArr[i - iBegin - 1][j - jBegin] > 	
						 	lenArr[i - iBegin][j - jBegin - 1])
						) 
				{
				 	i--;
				}
				else {
					j--;
				}
			}
		*/

	}

	private void setNullLcs(){
		this.lenLcs = 0;
		this.lcs = null;
		// this.lcsIndexSource = null;
		// this.lcsIndexTarget = null;		
	}

	
	public int length(){
		return this.lenLcs;
	}

	public ArrayList<T> getLcsList(){
		if(this.lenLcs == 0){
			return null;
		}

		return (ArrayList<T>) this.lcs.clone();
	}

	public String toString(){
		StringBuilder str = new StringBuilder();

		for(int i = 0; i < this.lenLcs; i++){
			str.append(this.lcs.get(i).toString() + " ");
		}

		return str.toString();
	}


	// public ArrayList<Integer> getSourceIdxList(){
	// 	return (ArrayList<Integer>)this.lcsIndexSource.clone();
	// }
	// public ArrayList<Integer> getTargetIdxList(){
	// 	return (ArrayList<Integer>)this.lcsIndexTarget.clone();
	// }
//self-test methods	
//{	
	public static void main(String[] args){
		String source = "", target = "";

		if(args.length >= 2){
			if(args.length >= 3 && args[0].equals("-f")){
				source = args[1];
				target = args[2];
				LongestCommonSubseq.lcsTestFile(source, target);
			}
			else {
				source = args[0];
				target = args[1];
				LongestCommonSubseq.lcsTestString(source, target);
			}
				
		}
		else{
			LongestCommonSubseq.errormsg("Not Enough Argument.");
		}		
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
	
	private static void lcsTestFile(String sp, String tp){
		File sf = new File(sp), tf = new File(tp);
		Scanner ssc, tsc;
		try{
			ssc = new Scanner(sf);
			tsc = new Scanner(tf);

			ArrayList<String> tmpArrsource = new ArrayList<String>();
			ArrayList<String> tmpArrtarget = new ArrayList<String>();

			while(ssc.hasNextLine()){
				tmpArrsource.add(ssc.nextLine());
			}
			while(tsc.hasNextLine()){
				tmpArrtarget.add(tsc.nextLine());
			}

			LongestCommonSubseq lcs = new LongestCommonSubseq<String>(tmpArrsource, tmpArrtarget);
			ArrayList<String> lcsArr = lcs.getLcsList();
			System.out.println("length : " + lcs.length());
			for(int i = 0; i < lcs.length();i++){
				System.out.println(lcsArr.get(i));
			}
		}
		catch(FileNotFoundException e) {
			LongestCommonSubseq.errormsg("File Not Found.");//TODO : error msg. here
		}
	}

	private static void errormsg(String msg){
		System.out.println(msg);
	}
//}
}


