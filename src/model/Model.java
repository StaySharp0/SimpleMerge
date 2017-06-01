package model;
import view.UI.DataSet.Item;

public class Model {
	private FileManager fm;
	private Document left;
	private Document right;
	private Algorithm algo;
	
	public Model(){
		this.fm = null;
		this.dm = null;
		this.algo = null;
	}
	public Model(String pathLeft, String pathRight){
		this.fm = new FileManager(pathLeft,pathRight);
		if(this.getPathLeft() != null){
			this.left = new Documnet(this.fm.getBufLeft());
		}
		else {
			this.left = null;
		}

		if(this.getPathRight() != null){
			this.right = new Documnet(this.fm.getBufRight());
		}
		else {
			this.right = null;
		}

		this.algo = null;
		//TODO
	}

	// public boolean load(String pathLeft, String pathRight){
	// 	//TODO	
	// 	return false;
	// }
	public boolean loadLeft(String path){
		boolean success;
		if(success = this.fm.loadLeft(path)){
			this.left = new Document(fm.getBufLeft);
		}
		return success;
	}
	public boolean loadRight(String path){
		boolean success;
		if(success = this.fm.loadRight(path)){
			this.right = new Document(fm.getBufRight);
		}
		return success;
	}

	// public boolean save(){
	// 	//TODO
	// 	return false;
	// }
	public boolean saveLeft(){
		return this.fm.saveLeft(this.left.getLines());
	}
	public boolean saveRight(){
		return this.fm.saveRight(this.right.getLines());
	}
	//pubilc boolean 

	public void editLeft(int idx, String str){
		this.left.setLine(idx,str);
		if(this.isCompared()){
			
		}
	}

	public void editRight(int idx, String str){
		this.right.setLine(idx,str);
	}

	public boolean compare(){
		return this.isCompared();
	}

	public boolean isCompared(){
		return this.algo == null;
	}

	public void copyLeft(){
		//TODO
	}
	public void copyRight(){
		//TODO
	}
}