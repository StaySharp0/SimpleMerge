package model;
import view.UI.DataSet.*;//`ll be changed
import view.UI.Position;//`ll be changed
import java.io.File;
import java.util.List;

public class Model implements ModelInterface{
	private FileManager fm;
	private Document left;
	private Document right;
	private Algorithm algo;
	
	public Model(){
		this.fm = new FileManager(null,null);
		this.left = null;
		this.right = null;
		this.algo = null;
	}
	public Model(File fl, File fr){
		this.fm = new FileManager(fl,fr);
		if(this.getPathLeft() != null){
			this.left = new Document(this.fm.getBufLeft());
		}
		else {
			this.left = null;
		}

		if(this.getPathRight() != null){
			this.right = new Document(this.fm.getBufRight());
		}
		else {
			this.right = null;
		}

		this.algo = null;
		//TODO
	}
	private ArrayList<String> parseData(String data){
		if(data == null){
			return null;
		}

		StringBuilder buf = new StringBuilder(data);
		ArrayList<String> listData = new ArrayList<String>();
		int tmpIdx = 0;
		int newlineIdx = 0;
		if(data.length() > 0){
			while((newlineIdx = buf.indexOf("\n",tmpIdx)) > 0){
				listData.add(buf.substring(tmpIdx, newlineIdx + 1));
				tmpIdx = newlineIdx + 1;
			}
			if(tmpIdx < buf.length()){
				listData.add(buf.substring(tmpIdx, buf.length()));
			}
		}

		return listData;
	}

	@Override
	public Item load(File f, int lr){
		if(lr == Position.LEFT){
			return this.loadLeft(f);
		}
		else if(lr == Position.RIGHT){
			return this.loadRight(f);
		}
		else {
			//error
			return null;
		}
	}
// load sub-methods
//{
	private Item loadLeft(File f){
		if(f.isFile()){
			if(this.fm.loadLeft(f)){
				this.left = new Document(this.fm.getBufLeft());
				return new Item(this.left.getLines());
			}
			else {
				//error
				return null;
			}
		}
		else {
			return null;
		}
	}
	private Item loadRight(File f){
		if(f.isFile()){
			if(this.fm.loadRight(f)){
				this.right = new Document(this.fm.getBufRight());
				return new Item(this.right.getLines());
			}
			else {
				//error
				return null;
			}
		}
		else {
			return null;
		}
	}
//}
	@Override
	public Item load(String path, int lr){
		File f = new file(path);
		return this.load(f, lr);
	}

	public Item save(int lr){
		if(lr == Position.LEFT || lr == Position.ALL){
			while(this.saveLeft());
		}
		if(lr == Position.RIGHT || lr == Position.ALL){
			while(this.saveRight());
		}

		return new Item();
	}
	@Override
	public Item save(Strign data, int lr){
		return this.save(this.parseData(data), lr);
	}
	@Override
	public Item save(List<String> data, int lr){
		//String msg;
		if(data == null){
			return null;
		}
		if(lr == Position.LEFT || lr == Position.ALL){
			this.left = new Document(data, true);
		}
		if(lr == Position.RIGHT || lr == Position.ALL){
			this.right = new Document(data, true);
		}

		if(lr == Position.LEFT || lr == Position.ALL){
			while(this.saveLeft());
		}
		if(lr == Position.RIGHT || lr == Position.ALL){
			while(this.saveRight());
		}

		return new Item();
	}
// save sub-methods
//{
	// @Override
	// public Item saveLeft(List<String> date){

	// }
	// @Override
	// public Item saveRight(List<String> date){

	// }
	
	private boolean saveLeft(){
		return this.fm.saveLeft(this.left.getLines());
	}
	private boolean saveRight(){
		return this.fm.saveRight(this.right.getLines());
	}
//}

//{
	public Item edit(String data, int lr){
		return this.edit(this.parseData(data), lr);
	}
	public Item edit(List<String> data, int lr){
		String name;

		//TODO

		if(this.isCompared()){
			this.algo = null;
		}

		return new Item(name);
	}

	private boolean editLeft(String line, int idx){
		//TODO
		
		return false;
	}
	private boolean editRight(String line, int idx){
		//TODO
		
		return false;
	}
//}

	public Item compare(){
		//TODO
		if(!this.isCompared()){
			this.algo = new Algorithm(this.left.getLines(), this.right.getLines());
		}

		return new Item(this.algo.getResultLeft(), this.algo.getResultRight());
	}
	@Override
	public Item getCompareResult(int lr){
		if(this.isCompared()){
			if(lr == Position.LEFT){
				return new Item(this.algo.getResultLeft());
			}
			else if(lr == Position.RIGHT){
				return new Item(this.algo.getResultRight());
			}
			else if(lr == Position.ALL){
				return new Item(this.algo.getResultLeft(), this.algo.getResultRight());
			}
		}
		else {
			return null;
		}
	}

	public boolean isCompared(){
		return this.algo != null;
	}

	@Override
	public Item merge(List<Integer> idxList, int lr){
		if(this.isCompared()){
				if(idxList == null){
					return this.merge(lr);
				}
				//TODO
				return null;
		}
		else {
			return null;
		}
	}
	@Override
	public Item merge(int idx, int lr){
		if(this.isCompared()){
			//TODO
		}
		else {
			return null;
		}
	}
	@Override
	public Item merge(int lr){
		if(this.isCompared()){
			//TODO
		}
		else {
			return null;
		}
	}

}