public class Line{
	private StringBuilder str;

	public Line(){
		this.str = new StringBuilder("");
	}
	public Line(CharSequence str){
		this.str = new StringBuilder(str);
	}
	
	public String getStr(){
		return this.str.toString();
	}
	public char[] getCharArr{
		return this.toString().toCharArr();
	}
	@override
	public boolean equals(Line that){
		return this.str.equals(that.str);
	}

}