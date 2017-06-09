package model;
import java.util.*;
import java.io.File;

 class Algorithm {
 	final private ArrayList<String> left;
 	final private ArrayList<String> right;
	private LongestCommonSubseq<String> lcs;

	private ArrayList<Integer> lMatch;
	private ArrayList<Integer> rMatch;
	private ArrayList<IdxPair> lChange;
	private ArrayList<IdxPair> rChange;

	private Integer compLen;

	public Algorithm(List<String> left, List<String> right){
		this.left = new ArrayList<String>(left);
		this.right = new ArrayList<String>(right);
		this.lcs = new LongestCommonSubseq(this.left, this.right);
		this.compLen = null;



		if(this.lcs.length() != 0){
			if(this.left == null || this.left.size() < 0){

			}
			else if(this.right == null || this.right.size() < 0){

			}
			this.lChange = new ArrayList<IdxPair>();
			this.rChange = new ArrayList<IdxPair>();
			this.lMatch = new ArrayList<Integer>();
			this.rMatch = new ArrayList<Integer>();
			ArrayList<String> lcs = this.lcs.getLcsList();
			ArrayList<String> tmpl = this.left;
			ArrayList<String> tmpr = this.right;
			IdxPair lIdx, rIdx;
			int ld = 0, rd = 0;

			for(int i = 0; i <= this.lcs.length(); i++){				
				if(i == 0){
					lIdx = new IdxPair(0,this.left.indexOf(lcs.get(i)));
					rIdx = new IdxPair(0,this.right.indexOf(lcs.get(i)));
				}
				else if(i == this.lcs.length()){
					lIdx = new IdxPair(this.lMatch.get(i - 1) + 1, this.left.size());
					rIdx = new IdxPair(this.rMatch.get(i - 1) + 1, this.right.size());
				}
				else {
					lIdx = new IdxPair(i + ld, i + ld + tmpl.indexOf(lcs.get(i)));
					rIdx = new IdxPair(i + rd, i + rd + tmpr.indexOf(lcs.get(i)));
				}


				if(lIdx.distance > 0 || rIdx.distance > 0){
					this.lChange.add(lIdx);
					this.rChange.add(rIdx);
					ld += lIdx.distance;
					rd += rIdx.distance;

				}

				if(i < this.lcs.length()){
					this.lMatch.add(lIdx.end);
					this.rMatch.add(rIdx.end);
					if(tmpl.indexOf(lcs.get(i)) < tmpl.size()){
						tmpl = new ArrayList<String>(tmpl.subList(tmpl.indexOf(lcs.get(i)) + 1, tmpl.size()));
					}
					if(tmpr.indexOf(lcs.get(i)) < tmpr.size()){
						tmpr = new ArrayList<String>(tmpr.subList(tmpr.indexOf(lcs.get(i)) + 1, tmpr.size()));
					}
				}
			}
		}
		else { // no same line; all changed
			this.lChange = new ArrayList<IdxPair>();
			this.lChange = new ArrayList<IdxPair>();

			this.lChange.add(new IdxPair(0,this.left.size()));
			this.rChange.add(new IdxPair(0,this.right.size()));
		}
		if(this.isIdentical()){
			this.lChange = null;
			this.rChange = null;
		}
		this.totalLength();
		
	}

	public ArrayList<IdxPair> getResultLeft(){
		return this.lChange;
	}
	public ArrayList<IdxPair> getResultRight(){
		return this.rChange;
	}

	public ArrayList<Integer> getLcsIdxLeft(){
		return this.lMatch;
	}
	public ArrayList<Integer> getLcsIdxRight(){
		return this.rMatch;
	}

	public boolean isFirstAreSame(){
		return 	(
				 	(this.lChange == null || this.lChange.get(0).begin != 0) &&
				 	(this.rChange == null || this.rChange.get(0).begin != 0)
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

	public int totalLength(){
		if(this.compLen == null){
			this.compLen = new Integer(0);
			this.compLen += this.lenLcs();
			if(!this.isIdentical()){
				for(int i = 0; i < this.lChange.size(); i++){
					this.compLen += 
					(this.lChange.get(i).distance > this.rChange.get(i).distance) ?
						(this.lChange.get(i).distance) :
						(this.rChange.get(i).distance);
				}
			}
		}
		
		return compLen;
	}

	public static void main(String[] args){
		String lp = "", rp = "";
		File lf, rf;
		Scanner lsc, rsc;
		ArrayList<String> lbuf, rbuf;
		boolean isSave = false;
		if(args.length >= 2){	
			lp = args[0];
			rp = args[1];
			
			lf = new File(lp);
			rf = new File(rp);
			try {
				lsc = new Scanner(lf);
				rsc = new Scanner(rf);
				lbuf = new ArrayList<String>();
				rbuf = new ArrayList<String>();
				
				while(lsc.hasNextLine()){
					lbuf.add(lsc.nextLine());
				}
				while(rsc.hasNextLine()){
					rbuf.add(rsc.nextLine());
				}

				Algorithm algo = new Algorithm(lbuf,rbuf);
				if(algo.getResultLeft() != null && algo.getResultRight() != null){
					if(algo.getResultLeft().size() != algo.getResultRight().size()){
						System.out.println("Error!");
						System.exit(0);	
					}
					if(algo.getResultLeft() != null && algo.getResultRight() != null){
						for(int i = 0; i < algo.getResultLeft().size(); i++){
							System.out.println("" + algo.getResultLeft().get(i).begin + ", " + algo.getResultLeft().get(i).end);
							for(int j = algo.getResultLeft().get(i).begin; j < algo.getResultLeft().get(i).end; j++){
								System.out.println("< " + algo.left.get(j));
							}
							System.out.println("" + algo.getResultRight().get(i).begin + ", " + algo.getResultRight().get(i).end);
							for(int j = algo.getResultRight().get(i).begin; j < algo.getResultRight().get(i).end; j++){
								System.out.println("> " + algo.right.get(j));
							}
						}
					}
				}
				else if(algo.isIdentical()){
					System.out.println("identical!");
				}

				System.out.println("lcslen : " + algo.lenLcs());
				System.out.println("len : " + algo.totalLength());
				// for(int i = 0; i < algo.getLcsIdxLeft().size(); i++){
				// 	System.out.println(lbuf.get(algo.getLcsIdxLeft().get(i)));
				// }
				// for(int i = 0; i < algo.getLcsIdxRight().size(); i++){
				// 	System.out.println(rbuf.get(algo.getLcsIdxRight().get(i)));	
				// }
			}
			catch(Exception e){
				System.out.println("Exception on file io : " + e.toString());
			}			
			
		}
	}
}

