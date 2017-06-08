package model;
import java.util.*;

class Document{
	private ArrayList<String> lines;
	private boolean edited;
	
	public Document(List<String> strLst){
		this.lines = new ArrayList<String>(strLst);
		this.edited = false;
	}
	public Document(List<String> strLst, boolean isEdited){
		this.lines = new ArrayList<String>(strLst);
		this.edited = isEdited;
	}

	public ArrayList<String> getLines(){
		return this.lines;
	}

	public String getLine(int idx){
		return this.lines.get(idx);
	}

	public int length(){
		return this.lines.size();
	}

	public void setLine(int idx, String newLine){
		this.lines.set(idx, newLine);
		this.edited = true;
	}

	public void insertLine(int idx, String newLine){
		this.lines.add(idx,newLine);
		this.edited = true;
	}

	public void insertLine(int idx, List<String> listLines){
		for(int i = 0; i < listLines.size(); i++){
			this.insertLine(idx, listLines.get(i));
		}
	}

	public void deleteLine(int idx){
		this.lines.remove(idx);
		this.edited = true;
	}

	public void deleteLine(int beginIdx, int endIdx){
		for(int i = beginIdx; i < endIdx; i++){
			this.deleteLine(i);
		}
	}

	public boolean isEdited(){
		return this.edited;
	}

	public static void main(String args){

	}
}