package model;
import java.util.ArrayList;
import java.util.Collection;

class Document{
	private ArrayList<String> lines;
	// public Document(String[] arrStr){
	// 	//TODO
	// }
	public Document(Collection<String> strLst){
		this.lines = new ArrayList<String>(strLst);
	}

	public ArrayList<String> getLines(){
		return this.lines.clone();
	}
	public void setLine(int idx, String newLine){
		this.lines.set(idx, newLine);
	}

	public void insertLine(int idx, String newLine){
		this.lines.add(newLine);
	}

	public void deleteLine(int idx){
		this.lines.remove(idx);
	}

	public void deleteLine(int beginIdx, int endIdx){
		this.lines.removeRange(beginIdx, endIdx);
	}
}