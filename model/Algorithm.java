public class Algorithm{
	private LongestCommonSubseq<Line> lcsLToR;
	private LongestCommonSubseq<Line> lcsRToL;
	
	public Algorithm(Document left, Document right){
		this.lcsLToR = new LongestCommonSubseq(left.getLines(), right.getLines());
		this.lcsRToL = new LongestCommonSubseq(right.getLines(), left.getLines());
	}

	//TODO
}

