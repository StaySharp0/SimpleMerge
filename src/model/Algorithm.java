package model;
import java.util.*;

 class Algorithm {
 	final private ArrayList<String> left;
 	final private ArrayList<String> right;
	private LongestCommonSubseq<String> lcs;
	
	private ArrayList<IdxPair> lChange;
	private ArrayList<IdxPair> rChange;

	public Algorithm(List<String> left, List<String> right){
		this.left = new ArrayList<String>(left);
		this.right = new ArrayList<String>(right);
		this.lcs = new LongestCommonSubseq(this.left, this.right);

		if(this.isIdentical()){
			this.lChange = null;
			this.rChange = null;
		}
		else if(this.lcs.length() != 0){
			this.lChange = new ArrayList<IdxPair>();
			this.rChange = new ArrayList<IdxPair>();
			ArrayList<Integer> lcsLIdx = this.lcs.getSourceIdxList();
			ArrayList<Integer> lcsRIdx = this.lcs.getTargetIdxList();
			IdxPair lIdx, rIdx;

			for(int i = 0; i <= this.lcs.length(); i++){
//{				
				if(i == 0){
					lIdx = new IdxPair(0,lcsLIdx.get(i));
					rIdx = new IdxPair(0,lcsRIdx.get(i));
				}
				else if(i == this.lcs.length()){
					lIdx = new IdxPair(lcsLIdx.get(i - 1) + 1, this.left.size());
					rIdx = new IdxPair(lcsRIdx.get(i - 1) + 1, this.right.size());
				}
				else {
					lIdx = new IdxPair(lcsLIdx.get(i - 1) + 1, lcsLIdx.get(i));
					rIdx = new IdxPair(lcsRIdx.get(i - 1) + 1, lcsRIdx.get(i));
				}
//}
				if(lIdx.distance > 0 || rIdx.distance > 0){
					this.lChange.add(lIdx);
					this.rChange.add(rIdx);
				}
				else /*if(lIdx,distance <= 0 && rIdx.distance <= 0)*/{
					continue;
				}	
			}
		}
		else { // no same line; all changed
			this.lChange = new ArrayList<IdxPair>();
			this.lChange = new ArrayList<IdxPair>();

			this.lChange.add(new IdxPair(0,this.left.size()));
			this.rChange.add(new IdxPair(0,this.right.size()));
		}
	}

	public ArrayList<IdxPair> getResultLeft(){
		return this.lChange;
	}
	public ArrayList<IdxPair> getResultRight(){
		return this.rChange;
	}

	public ArrayList<Integer> getLcsIdxLeft(){
		return this.lcs.getSourceIdxList();
	}
	public ArrayList<Integer> getLcsIdxRight(){
		return this.lcs.getTargetIdxList();
	}

	public boolean isFirstAreSame(){
		return 	(
				 	(this.lChange == null || this.lChange.get(0).begin == 0) &&
				 	(this.rChange == null || this.rChange.get(0).begin == 0)
				 );

	}

	public boolean isIdentical(){
		return  (
					this.lcs.length() == this.left.size()  && 
					this.lcs.length() == this.right.size()
				);
	}

	public int lenLcs(){
		return this.lcs.length();
	}

	// public static void main(String args){
	// 	String lp = "", rp = "";
	// 	File lf, rf;
	// 	Scanner lsc, rsc;
	// 	ArrayList<String> lbuf, rbuf;
	// 	boolean isSave = false;
	// 	if(args.length >= 1){	
	// 		lp = args[0];
	// 		if(args.length >= 2){	
	// 			rp = args[1];
	// 		}
			
	// 		lf = new File(lp);
	// 		rf = new File(rp);
			
			
	// 	}
	// }
}

