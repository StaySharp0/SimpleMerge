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
	private ArrayList<String> compResultLeft;
	private ArrayList<String> compResultRight;
	
	public Model(){
		this.fm = new FileManager(null,null);
		this.left = null;
		this.right = null;
		this.algo = null;
		this.compResultLeft = null;
		this.compResultRight = null;
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
		this.compResultLeft = null;
		this.compResultRight = null;
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
				listData.add(buf.substring(tmpIdx, newlineIdx));
				tmpIdx = newlineIdx + 1;
			}
			if(tmpIdx < buf.length()){
				listData.add(buf.substring(tmpIdx, buf.length()));
			}
		}

		return listData;
	}
	private String concatData(List<String> data){
		if(data == null){
			return "";
		}

		String buf = new String("");

		for(int i = 0; i < data.size();i++){
			buf += data.get(i) +( (i == data.size() - 1) ? ("\n") : ("") ); 
		}

		return buf;
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
				if(this.isCompared()){
					this.algo = null;
				}
				return new Item(this.left.getLines());
			}
		}

		return null;
	}
	private Item loadRight(File f){
		if(f.isFile()){
			if(this.fm.loadRight(f)){
				this.right = new Document(this.fm.getBufRight());
				if(this.isCompared()){
					this.algo = null;
				}
				return new Item(this.right.getLines());
			}
		}

		return null;
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
		if(data == null){
			return null;
		}

		if(lr == Position.LEFT || lr == Position.ALL){
			// if(this.fm.getPathLeft() == null){
			// 	return null;
			// }
			this.left = new Document(data, true);
		}
		if(lr == Position.RIGHT || lr == Position.ALL){
			// if(this.fm.getPathRight() == null){
			// 	return null;
			// }
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
		if(!this.isCompared()){
			return new ArrayList<String>();
		}

		if(this.compResultLeft != null){
			return this.compResultLeft;
		}
		//StringBuilder data = new StringBuilder(concatData(this.left.getLines()));
		ArrayList<String> result = new ArrayList<String>();
		StringBuilder buf = new StringBuilder();
		ArrayList<IdxPair> diff = this.algo.getResultLeft();
		ArrayList<Integer> same = this.algo.getLcsIdxLeft();

		int cntDiff = 0, cntSame = 0;

		for(int i = 0; i < this.left.length();){
			int chki = i;
			if(i == diff.get(cntDiff).begin  && i != diff.get(cntDiff).end){
				buf.append(this.concatData(this.left.getLines().subList(diff.get(cntDiff).begin, diff.get(cntDiff).end)));
				if(diff.get(cntDiff).distance < this.algo.getResultRight().get(cntDiff).distance){
					for(int j = 0; j < this.algo.getResultRight().get(cntDiff).distance - diff.get(cntDiff).distance; j++){
						buf.append("\n");
					}
				}
				result.add(buf.toString());
				i += diff.get(cntDiff).distance;
				cntDiff++;
			}

			if(i == same.get(cntSame)){
				buf.append(this.left.getLines().get(same.get(cntSame)));
				if(i + 1 < this.left.length() && i + 1 < diff.get(cntDiff).begin){
					buf.append("\n");
					cntSame++;
					i++;
				}
				else {
					result.add(buf.toString());
					i++;
				}
			}

			if(i == chki){ // unexpected condition
				return new ArrayList<String>();
			}
		}

		return result;
	}
	private ArrayList<String> getResultRight(){
		if(!this.isCompared()){
			return new ArrayList<String>();
		}

		if(this.compResultRight != null){
			return this.compResultRight;
		}
		//StringBuilder data = new StringBuilder(concatData(this.left.getLines()));
		ArrayList<String> result = new ArrayList<String>();
		StringBuilder buf = new StringBuilder();
		ArrayList<IdxPair> diff = this.algo.getResultRight();
		ArrayList<Integer> same = this.algo.getLcsIdxRight();

		int cntDiff = 0, cntSame = 0;

		for(int i = 0; i < this.right.length();){
			int chki = i;
			if(i == diff.get(cntDiff).begin && i != diff.get(cntDiff).end){
				buf.append(this.concatData(this.right.getLines().subList(diff.get(cntDiff).begin, diff.get(cntDiff).end)));
				if(diff.get(cntDiff).distance < this.algo.getResultLeft().get(cntDiff).distance){
					for(int j = 0; j < this.algo.getResultLeft().get(cntDiff).distance - diff.get(cntDiff).distance; j++){
						buf.append("\n");
					}
				}
				result.add(buf.toString());
				i += diff.get(cntDiff).distance;
				cntDiff++;
			}

			if(i == same.get(cntSame)){
				buf.append(this.right.getLines().get(same.get(cntSame)));
				if(i + 1 < this.right.length() && i + 1 < diff.get(cntDiff).begin){
					buf.append("\n");
					cntSame++;
					i++;
				}
				else {
					result.add(buf.toString());
					i++;
				}
			}

			if(i == chki){ // unexpected condition
				return new ArrayList<String>();
			}
		}

		return result;		
	}



	public boolean isCompared(){
		return this.algo != null;
	}

	@Override
	public Item merge(List<Integer> idxList, int lr){
		if(this.isCompared()){
			if(!this.algo.isIdentical()){
				if(lr == Position.LEFT){
					for(int i = 0; i < idxList.size();i++){
						this.copyToRight(idxList.get(i).intValue());
					}
				}
				else if(lr == Position.RIGHT){
					for(int i = 0; i < idxList.size();i++){
						this.copyToLeft(idxList.get(i).intValue());	
					}
				}
				else {
					return null;
				}

				if(this.isCompared()){
					this.algo = null;
					this.compare();
				}
			}
			return new Item(this.left.getLines());
		}
		else {
			return null;
		}
	}

	@Override
	public Item merge(int idx, int lr){
		if(this.isCompared()){
			if(!this.algo.isIdentical()){
				if(lr == Position.LEFT){
					this.copyToRight(idx);
				}
				else if(lr == Position.RIGHT){
					this.copyToLeft(idx);
				}
				else {
					return null;
				}
				if(this.isCompared()){
					this.algo = null;
					this.compare();
				}
			} 
			return new Item(this.left.getLines());
		}
		else {
			return null;
		}
	}

	// @Override
	// public Item merge(int lr){
	// 	if(this.isCompared()){
	// 		if(this.algo.isIdentical()){
	// 			return new Item();
	// 		}

	// 		return new Item();
	// 	}
	// 	else {
	// 		return null;
	// 	}
	// }

	private void copyToRight(int idx){
		ArrayList<String> compResult = this.getResultLeft();
		ArrayList<String> dataToCp = this.parseData(compResult.get(idx));
		int diffIdx = (idx - (this.algo.isFirstAreSame() ? 1 : 0) )/ 2;
		this.right.deleteLine(this.algo.getResultRight().get(diffIdx).begin, this.algo.getResultRight().get(diffIdx).end);
		this.right.insertLine(this.algo.getResultRight().get(diffIdx).begin, dataToCp);
	}
	private void copyToLeft(int idx){
		ArrayList<String> compResult = this.getResultRight();
		ArrayList<String> dataToCp = this.parseData(compResult.get(idx));
		int diffIdx = (idx - (this.algo.isFirstAreSame() ? 1 : 0) )/ 2;
		this.left.deleteLine(this.algo.getResultLeft().get(diffIdx).begin, this.algo.getResultLeft().get(diffIdx).end);
		this.left.insertLine(this.algo.getResultLeft().get(diffIdx).begin, dataToCp);
	}
}