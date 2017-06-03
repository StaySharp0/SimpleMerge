package model;
import java.util.ArrayList;
import java.util.Collection;

class Document{
	private ArrayList<String> lines;
	private boolean edited;
	// public Document(String[] arrStr){
	// 	//TODO
	// }
	public Document(Collection<String> strLst){
		this.lines = new ArrayList<String>(strLst);
		this.edited = false;
	}
	public Document(Collection<String> strLst, boolean isEdited){
		this.lines = new ArrayList<String>(strLst);
		this.edited = isEdited;
	}

	public ArrayList<String> getLines(){
		return (ArrayList<String>)this.lines.clone();
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
		for(int i = beginIdx; i < endIdx; i++){
			this.deleteLine(i);
		}
	}

	public boolean isEdited(){
		return this.edited;
	}
}