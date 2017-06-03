package model;
import java.util.ArrayList;
import java.util.Collection;

 class Algorithm {
 	private class IdxPair {
		final int begin;
		final int end;
		final int distance;

		IdxPair(int begin, int end){
			this.begin = begin;
			this.end = end;
			this.distance = end - begin;
		}

		int distance(){
			return this.distance;
		}

		int mid(){
			return this.end / 2;
		}
	}

 	final private ArrayList<String> left;
 	final private ArrayList<String> right;
	private LongestCommonSubseq<String> lcs;
	
	private ArrayList<IdxPair> deleted;
	private ArrayList<IdxPair> added;
	private ArrayList<IdxPair> lChange;
	private ArrayList<IdxPair> rChange;

	private ArrayList<String> resultLeft;
	private ArrayList<String> resultRight;

	public Algorithm(Collection<String> left, Collection<String> right){
		this.left = (ArrayList<String>)left;
		this.right = (ArrayList<String>)right;
		this.lcs = new LongestCommonSubseq(this.left, this.right);


		if(this.lcs.length() == this.left.size() && this.lcs.length() == this.right.size()){//L,R are identical
			this.added = null;
			this.deleted = null;
			this.lChange = null;
			this.rChange = null;
			this.resultLeft = (ArrayList<String>)left;
			this.resultRight = (ArrayList<String>)right;
		}
		else if(this.lcs.length() != 0){
			this.deleted = new ArrayList<IdxPair>();
			this.added = new ArrayList<IdxPair>();
			this.lChange = new ArrayList<IdxPair>();
			this.rChange = new ArrayList<IdxPair>();
			ArrayList<Integer> lcsLIdx = this.lcs.getSourceIdxList();
			ArrayList<Integer> lcsRIdx = this.lcs.getTargetIdxList();

			for(int i = 0; i <= this.lcs.length(); i++){
				IdxPair lIdx, rIdx;
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
				if(lIdx.distance > 0 && rIdx.distance > 0){
					this.lChange.add(lIdx);
					this.rChange.add(rIdx);
				}
				else if(rIdx.distance > 0 && lIdx.distance <= 0){
					this.added.add(rIdx);
				}
				else if(lIdx.distance > 0 && rIdx.distance <= 0){
					this.deleted.add(lIdx);
				}
				else {
					continue;
				}

				this.setResult(lIdx, rIdx);

				// this.setResultLeft(lIdx);
				// this.setResultRight(rIdx);				

				// if(lIdx.distance() > rIdx.distance()){
				// 	for(int j = 0; j < lIdx.distance() - rIdx.distance(); j++){
				// 		this.resultLeft.add("");
				// 	}
				// }
				// else if (lIdx.distance() < rIdx.distance()){
				// 	for(int j = 0; j < rIdx.distance() - lIdx.distance(); j++){
				// 		this.resultRight.add("");	
				// 	}
				// }					
			}
		}
		else { // no same line; all changed
			this.deleted = null;	
			this.added = null;
			this.lChange = new ArrayList<IdxPair>();
			this.lChange = new ArrayList<IdxPair>();

			this.lChange.add(new IdxPair(0,this.left.size()));
			this.rChange.add(new IdxPair(0,this.right.size()));

			this.setResult(this.lChange.get(0), this.rChange.get(0));
			// this.setResultLeft(this.lChange.get(0));
			// this.setResultRight(this.rChange.get(0));

			// if(this.left.size() > this.right.size()){
			// 	for(int i = 0; i < this.left.size() - this.right.size(); i++){
			// 		this.resultLeft.add("");
			// 	}
			// }
			// else if (this.left.size() < this.right.size()){
			// 	for(int i = 0; i < this.right.size() - this.left.size(); i++){
			// 		this.resultRight.add("");	
			// 	}
			// }
		}
	}
	private void setResult(IdxPair lIdx, IdxPair rIdx){
		if(lIdx.distance <= 0 && rIdx.distance <= 0){
			return;
		}
		String lBuf = "";
		String rBuf = "";

		for(int i = lIdx.begin; i < lIdx.end; i++){
			lBuf += this.left.get(i);
		}
		for(int i = rIdx.begin; i < rIdx.end; i++){
			rBuf += this.right.get(i);
		}

		if(lIdx.distance() > rIdx.distance()){
			for(int j = 0; j < lIdx.distance() - rIdx.distance(); j++){
				rBuf += "\n";
			}
		}
		else if (lIdx.distance() < rIdx.distance()){
			for(int j = 0; j < rIdx.distance() - lIdx.distance(); j++){
				lBuf += "\n";
			}
		}
		this.resultLeft.add(lBuf);
		this.resultRight.add(rBuf);
	}

	// private void setResultLeft(IdxPair idx){
	// 	if(idx.distance <= 0){
	// 		return;
	// 	}
	// 	String buf = "";

	// 	for(int i = idx.begin; i < idx.end; i++){
	// 		buf += this.left.get(i);
	// 	}
	// 	this.resultLeft.add(buf);
	// }
	// private void setResultRight(IdxPair idx){
	// 	if(idx.distance <= 0){
	// 		return;
	// 	}
	// 	String buf = "";

	// 	for(int i = idx.begin; i < idx.end; i++){
	// 		buf += this.right.get(i);	
	// 	}
	// 	this.resultLeft.add(buf);
	// }

	public ArrayList<String> getResultLeft(){
		return this.resultLeft;
	}
	public ArrayList<String> getResultRight(){
		return this.resultRight;
	}
}

