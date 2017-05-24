public class Document{
	private ArrayList<Line> lines;
	public Document(String[] arrStr){
		this.lines = new ArrayList<Line>();
		//TODO
	}
	public Document(iterable<String> strLst){
		this.lines = new ArrayList<Line>();
		//TODO
	}

	public ArrayList<Line> getLines(){
		return this.lines.clone();
	}
	public void setLine(int index, Line newLine){
		this.lines[index] = newLine;
	}

	public void insertLine(int index, Line newLine){
		this.lines.add(newLine);
	}

	public void deleteLine(int index){
		this.lines.remove(index);
	}

	public void deleteLine(int beginIdx, int endIdx){
		this.lines.removeRange(beginIdx, endIdx);
	}
}