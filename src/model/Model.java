package model;
import view.UI.DataSet.*;//`ll be changed
import view.UI.Position;//`ll be changed
import java.io.File;
import java.util.List;
import java.util.ArrayList;

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
		if(this.fm.getPathLeft() != null){
			this.left = new Document(this.fm.getBufLeft());
		}
		else {
			this.left = null;
		}

		if(this.fm.getPathRight() != null){
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
		File f = new File(path);
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
	public Item save(String data, int lr){
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
		boolean isEdited;

		if(lr == Position.LEFT){
			name = this.fm.getNameLeft();
			if(isEdited = this.editLeft(data)){
				name = "*" + name;
			}
		}
		else if(lr == Position.RIGHT){
			name = this.fm.getNameRight();
			if(isEdited = this.editRight(data)){
				name = "*" + name;
			}
		}

		if(isEdited && this.isCompared()){
			this.algo = null;
		}

		return new Item(name);
	}

	private boolean editLeft(List<String> data){
		boolean isEdited = false;

		if(data.size() == this.left.length()){
			for(int i = 0; i < data.size(); i++){
				if(!data.get(i).equals(this.left.getLine(i))){
					isEdited = true;
					this.left.setLine(i,data.get(i));
				}
			}
		}
		else {
			isEdited = true;
			this.left = new Document(data);
		}
		
		return isEdited;
	}
	private boolean editRight(List<String> data){
		boolean isEdited = false;

		if(data.size() == this.right.length()){
			for(int i = 0; i < data.size(); i++){
				if(!data.get(i).equals(this.right.getLine(i))){
					isEdited = true;
					this.right.setLine(i,data.get(i));
				}
			}
		}
		else {
			isEdited = true;
			this.right = new Document(data);
		}
		
		return isEdited;
	}
//}
	@Override
	public Item compare(){
		if(!this.isCompared()){
			this.algo = new Algorithm(this.left.getLines(), this.right.getLines());
		}

		return new Item(this.getResultLeft(), this.getResultRight(), this.algo.isFirstAreSame());
		//return new Item(this.algo.isFirstAreSame());
	}
	@Override
	public Item getCompareResult(int lr){
		if(this.isCompared()){
			if(lr == Position.LEFT){
				return new Item(this.getResultLeft());
			}
			else if(lr == Position.RIGHT){
				return new Item(this.getResultRight());
			}
			else if(lr == Position.ALL){
				return new Item(this.getResultLeft(), this.getResultRight());
			}
		}
		else {
			return null;
		}
	}
	private ArrayList<String> getResultLeft(){
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> diffList = null;
		ArrayList<String> sameList = null;
		ArrayList<Integer> sameIdxResult = this.algo.getLcsIdxLeft();

		if(this.algo.getResultLeft() != null && this.algo.getResultLeft().size() > 0){
			diffList = new ArrayList<String>();
			IdxPair idx;
			String buf = "";
			for(int i = 0; i < this.algo.getResultLeft().size(); i++){
				idx = this.algo.getResultLeft().get(i);
				for(int j = idx.begin; j < idx.end; j++){
					buf += this.left.getLine(j);
				}
				if  (
						this.algo.getResultLeft().get(i).distance  <
						this.algo.getResultRight().get(i).distance 
					){
					for (
							int j = 0;
							j < (
									this.algo.getResultRight().get(i).distance -
									this.algo.getResultLeft().get(i).distance);
							j++
						){
						buf += "\n";
					}
				}
				diffList.add(buf);
			}
		}
		
		if(sameIdxResult != null && sameIdxResult.size() > 0){
			sameList = new ArrayList<String>();
			String buf = "";
			for(int i = 0; i <= sameIdxResult.size(); i++){
				if(i == 0 || sameIdxResult.get(i) - sameIdxResult.get(i - 1) == 1){
					buf += this.left.getLine(sameIdxResult.get(i));
				}
				else if(i >= sameIdxResult.size() || sameIdxResult.get(i) - sameIdxResult.get(i - 1) > 1){
					sameList.add(buf);
					buf = "";
					buf += this.left.getLine(sameIdxResult.get(i));	
				}
			}
		}

		if(diffList != null && sameList != null){
			int cnt = 0;
			if(diffList.size() > sameList.size() || !this.algo.isFirstAreSame()){
				result.add(diffList.get(0));
				cnt++;
			}
			for(;cnt < diffList.size();cnt++){
				result.add(diffList.get(cnt));
				result.add(sameList.get(cnt - 1));
			}
			if(sameList.size() >= diffList.size()){
				result.add(sameList.get(sameList.size() - 1));
			}
		}
		else if(diffList != null && sameList == null){
			result = diffList;
		}
		else if(sameList != null){
			result = sameList;
		}
		//else{}

		return result;
	}
	private ArrayList<String> getResultRight(){
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> diffList = null;
		ArrayList<String> sameList = null;
		ArrayList<Integer> sameIdxResult = this.algo.getLcsIdxRight();

		if(this.algo.getResultRight() != null && this.algo.getResultRight().size() > 0){
			diffList = new ArrayList<String>();
			IdxPair idx;
			String buf = "";
			for(int i = 0; i < this.algo.getResultRight().size(); i++){
				idx = this.algo.getResultRight().get(i);
				for(int j = idx.begin; j < idx.end; j++){
					buf += this.left.getLine(j);
				}
				if  (
						this.algo.getResultRight().get(i).distance  <
						this.algo.getResultLeft().get(i).distance 
					){
					for (
							int j = 0;
							j < (
									this.algo.getResultLeft().get(i).distance -
									this.algo.getResultRight().get(i).distance);
							j++
						){
						buf += "\n";
					}
				}
				diffList.add(buf);
			}
		}
		
		if(sameIdxResult != null && sameIdxResult.size() > 0){
			sameList = new ArrayList<String>();
			String buf = "";
			for(int i = 0; i <= sameIdxResult.size(); i++){
				if(i == 0 || sameIdxResult.get(i) - sameIdxResult.get(i - 1) == 1){
					buf += this.left.getLine(sameIdxResult.get(i));
				}
				else if(i >= sameIdxResult.size() || sameIdxResult.get(i) - sameIdxResult.get(i - 1) > 1){
					sameList.add(buf);
					buf = "";
					buf += this.left.getLine(sameIdxResult.get(i));	
				}
			}
		}

		if(diffList != null && sameList != null){
			int cnt = 0;
			if(diffList.size() > sameList.size() || !this.algo.isFirstAreSame()){
				result.add(diffList.get(0));
				cnt++;
			}
			for(;cnt < diffList.size();cnt++){
				result.add(diffList.get(cnt));
				result.add(sameList.get(cnt - 1));
			}
			if(sameList.size() >= diffList.size()){
				result.add(sameList.get(sameList.size() - 1));
			}
		}
		else if(diffList != null && sameList == null){
			result = diffList;
		}
		else if(sameList != null){
			result = sameList;
		}
		//else{}

		return result;
	}

	public boolean isCompared(){
		return this.algo != null;
	}

	@Override
	public Item merge(List<Integer> idxList, int lr){
		if(this.isCompared()){
			if(this.algo.isIdentical()){
				return new Item();
			}



			return new Item();
		}
		else {
			return null;
		}
	}

	@Override
	public Item merge(int idx, int lr){
		if(this.isCompared()){
			if(this.algo.isIdentical()){
				return new Item();
			}

			return new Item();
		}
		else {
			return null;
		}
	}

	@Override
	public Item merge(int lr){
		if(this.isCompared()){
			if(this.algo.isIdentical()){
				return new Item();
			}

			return new Item();
		}
		else {
			return null;
		}
	}

	//private void copyToRight()

}