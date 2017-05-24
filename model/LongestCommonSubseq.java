/**
* 
* 
* 
*/

import java.lang.String;
import java.lang.StringBuilder;
import java.util.ArrayList;

public class LongestCommonSubseq<T>{
	final private T[] scr;
	final private T[] dst;

	// array of indexes for each
	private int[] deleted;
	private int[] added;
	private int[] changed; 
	private int[] match;

	private void 

	
	public LongestCommonSubseq(final T[] scr, final T[] dst){
		this.scr = scr;
		this.dst = dst;
		//...
	}
	public LongestCommonSubseq(final iterable<T> scr, final iterable<T> dst){
		this.scr = scr;
		this.dst = dst;
		//...
	}
	public String toString(){
		//TODO
		return null;
	}
	public static void main(String[] args){
		String src, dst;
		if(args.length >= 2){/*
			if(args[0].equals("-f")){

			}
			else
			{
				
			}*/
			src = args[0];
			dst = args[1];
		}
		else{
			//error msg.
		}

		LongestCommonSubseq<Character> lcs;
		ArrayList<Character> tmpArrSrc = new ArrayList<Character>(src.length());
		ArrayList<Character> tmpArrDst = new ArrayList<Character>(dst.length());

		for(char c : src.toCharArr()){
			tmpArrSrc.add(c);
		}
		for(char c : dst.toCharArr()){
			tmpArrDst.add(c);
		}
		lcs = new LongestCommonSubseq(tmpArrSrc, tmpArrDst);


	}
	
}


