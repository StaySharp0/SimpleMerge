package model;

 class Algorithm{
 	final Document left;
 	final Document right;
	private LongestCommonSubseq<Line> lcs;
	

	public Algorithm(Document left, Document right){
		this.left = left;
		this.right = right;
		this.lcs = new LongestCommonSubseq(left.getLines(), right.getLines());		
	}

	//TODO
	
}

