public class Model{
	private FileManager fm;
	
	public Model(){
		//TODO
	}
	public Model(String pathLeft, String pathRight){
		this.fm = new FileManager(pathLeft,pathRight);
		//TODO
	}

	public boolean load(String pathLeft, String pathRight){
		//TODO	
		return false;
	}
	public boolean loadLeft(String pathLeft){
		//TODO
		return false;
	}
	public boolean loadRight(String pathRight){
		//TODO
		return false;
	}

	public boolean save(){
		//TODO
		return false;
	}
	public boolean saveLeft(){
		//TODO
		return false;
	}
	public boolean saveRight(){
		//TODO
		return false;
	}

	public void editLeft(){
		//TODO
	}

	public void editRight(){
		//TODO
	}

	public void compare(){
		//TODO
		return false;
	}

	public void copyLeft(){
		//TODO
	}
	public void copyRight(){
		//TODO
	}
}