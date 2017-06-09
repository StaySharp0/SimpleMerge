package model;
import dataSet.*;//`ll be changed
import view.UI.FileSaveUI;
import view.UI.Position;//`ll be changed
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Model implements ModelInterface{
	private FileManager fm;
	private Document left;
	private Document right;
	private Document oleft;
	private Document oright;
	private Algorithm algo;
	private ArrayList<String> compResultLeft;
	private ArrayList<String> compResultRight;
	
	public Model(){
		this.fm = new FileManager(null,null);
		this.left = null;
		this.right = null;
		this.oleft = null;
		this.oright = null;
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
        if(data == null) return null;

        return new ArrayList<String>(Arrays.asList(data.split("\n")));
    }

	private ArrayList<String> parseViewData(String data){
		if(data == null) return null;

        ArrayList<String> rtn = new ArrayList<String>();
        String tmp = "";

		for(char ch: data.toCharArray()){
            if(ch != '\n') tmp += ch;
            else {
                tmp += "";
                rtn.add(tmp);
                tmp = "";
            }
        }

        if(tmp.length() != 0) rtn.add(tmp);

		return rtn;
	}

	private String concatData(List<String> data){
		if(data == null){
			return "";
		}

		String buf = new String("");

		for(int i = 0; i < data.size();i++){
			buf += data.get(i) +( (i != data.size() - 1) ? ("\n") : ("") );
		}

		return buf;
	}


	@Override
	public Item load(File f, int lr){
		if(f == null){
			return null;
		}
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
		FileOpen rtn = new Item();

		if(f != null && f.isFile()){
			if(this.fm.loadLeft(f)){
				this.left = new Document(this.fm.getBufLeft());
				if(this.isCompared()){
					this.algo = null;
				}

				rtn.setFileName(f.getName());
				rtn.setTextData(this.concatData(this.left.getLines()));

				return (Item) rtn;
			}
		}

		return null;
	}
	private Item loadRight(File f){
		FileOpen rtn = new Item();

		if(f != null && f.isFile()){
			if(this.fm.loadRight(f)){
				this.right = new Document(this.fm.getBufRight());
				if(this.isCompared()){
					this.algo = null;
				}

				rtn.setFileName(f.getName());
				rtn.setTextData(this.concatData(this.right.getLines()));

				return (Item) rtn;
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
			while(!this.saveLeft());
		}
		if(lr == Position.RIGHT || lr == Position.ALL){
			while(!this.saveRight());
		}

		return null;
	}
	@Override
	public Item save(String data, int lr){
		return this.save(this.parseData(data), lr);
	}
	@Override
	public Item save(List<String> data, int lr){
		FileEditSave rtn = new Item();

		if(data == null){
			return null;
		}

		if(lr == Position.LEFT || lr == Position.ALL){
			// if(this.fm.getPathLeft() == null){
			// 	return null;
			// }
			this.left = new Document(data, false);
			this.oleft = null;
		}
		if(lr == Position.RIGHT || lr == Position.ALL){
			// if(this.fm.getPathRight() == null){
			// 	return null;	
			// }
			this.right = new Document(data, false);
			this.oright = null;
		}

		if(lr == Position.LEFT || lr == Position.ALL){
			while(!this.saveLeft());

			rtn.setFileName(fm.getNameLeft());

			return (Item)rtn;
		}
		if(lr == Position.RIGHT || lr == Position.ALL){
			while(!this.saveRight());

			rtn.setFileName(fm.getNameRight());
			return (Item)rtn;
		}

		return null;
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
		return this.edit(this.parseViewData(data), lr);
	}
	public Item edit(List<String> data, int lr){
		String name = "";
		boolean isEdited = false;
		FileEditSave rtn = new Item();

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
			this.compResultLeft = null;
			this.compResultRight = null;
		}

		rtn.setFileName(name);

		return (Item) rtn;
	}

	private boolean editLeft(List<String> data){
		boolean isEdited = false;

		if(!this.left.isEdited() && this.oleft == null){
			this.oleft = new Document(this.left.getLines());
		}
		if(data.size() == this.oleft.length()){
			for(int i = 0; i < data.size(); i++){
				if(!data.get(i).equals(this.oleft.getLine(i))){
					isEdited = true;

				}
			}
		}
		else {
			isEdited = true;
		}

        this.left = new Document(data);
		
		return isEdited;
	}
	private boolean editRight(List<String> data){
		boolean isEdited = false;

		if(!this.right.isEdited() && this.oright == null){
			this.oright = new Document(this.right.getLines());
		}
		if(data.size() == this.oright.length()){
			for(int i = 0; i < data.size(); i++){
				if(!data.get(i).equals(this.oright.getLine(i))){
					isEdited = true;
				}
			}
		}
		else {
			isEdited = true;
		}

        this.right = new Document(data);
		
		return isEdited;
	}
//}
	@Override
	public Item compare(){
		MergeCompare rtn = new Item();
		if(!this.isCompared()){
		    ArrayList<String> arrLeft = this.left.getLines();
            ArrayList<String> arrRight = this.right.getLines();

		    if(arrLeft.size() == 0 && arrRight.size() == 0) {
		        arrLeft.add("");
                arrRight.add("");
            }

			this.algo = new Algorithm(arrLeft,arrRight);
		}


		String[][] ListData = {
				this.getResultLeft().toArray(new String[this.getResultLeft().size()]),
				this.getResultRight().toArray(new String[this.getResultRight().size()])
		};
		String[] TextData = {
                this.concatData(this.left.getLines()),
                this.concatData(this.right.getLines())
        };

		rtn.setListViewItem(ListData);
		rtn.setListActiveOrder(this.algo.isFirstAreSame());
		rtn.setTextItem(TextData);

		return (Item) rtn;
	}

//	@Override
//	public Item getCompareResult(int lr){
//		if(this.isCompared()){
//			if(lr == Position.LEFT){
//				return new Item(this.getResultLeft());
//			}
//			else if(lr == Position.RIGHT){
//				return new Item(this.getResultRight());
//			}
//			else if(lr == Position.ALL){
//				return new Item(this.getResultLeft(), this.getResultRight());
//			}
//		}
//		else {
//			return null;
//		}
//	}
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

		int cntDiff = 0, cntSame = 0, cntResultLen = 0;

		for(int i = 0; i < this.left.length();){
			int chki = i;
			if(!this.algo.isIdentical() && i == diff.get(cntDiff).begin){
				if(i < diff.get(cntDiff).end){
					for(int j = diff.get(cntDiff).begin; j < diff.get(cntDiff).end; j++){
						buf.append(this.left.getLines().get(j) + "\n");
						cntResultLen++;
					}
					if(diff.get(cntDiff).distance < this.algo.getResultRight().get(cntDiff).distance){
						for(int j = 0; j < this.algo.getResultRight().get(cntDiff).distance - diff.get(cntDiff).distance; j++){
							buf.append("\u23CE\n");
							cntResultLen++;
						}
					}
//					if(cntResultLen >= this.algo.totalLength()){
//						buf.deleteCharAt(buf.length() - 1);
//					}
					result.add(buf.toString());
					buf = new StringBuilder("");
					i += diff.get(cntDiff).distance;
					
				}
				else if(diff.get(cntDiff).begin == diff.get(cntDiff).end && this.algo.getResultRight().get(cntDiff).distance > 0){
					for(int j = 0; j < this.algo.getResultRight().get(cntDiff).distance; j++){
						buf.append("\u23CE\n");
						cntResultLen++;
					}
//					if(cntResultLen >= this.algo.totalLength()){
//						buf.deleteCharAt(buf.length() - 1);
//					}
					result.add(buf.toString());
					buf = new StringBuilder("");
				}

				if(cntDiff < diff.size() - 1){
					cntDiff++;
				}
			}


			if(this.algo.lenLcs() > 0 && i == same.get(cntSame)){
				buf.append(this.left.getLines().get(same.get(cntSame)) + "\n");
				cntResultLen++;
				if(i + 1 < this.left.length()){
					if(cntSame < same.size() - 1){
						cntSame++;
					}
					if((!this.algo.isIdentical() && i + 1 == diff.get(cntDiff).begin)){
//						if(cntResultLen >= this.algo.totalLength()){
//							buf.deleteCharAt(buf.length() - 1);
//						}
						result.add(buf.toString());
						buf = new StringBuilder("");					
					}
				}
				else {
//					if(cntResultLen >= this.algo.totalLength()){
//						buf.deleteCharAt(buf.length() - 1);
//					}
					result.add(buf.toString());
				}
				i++;
			}

			if(i == chki){ // unexpected condition
				return new ArrayList<String>();
			}
		}

		if(!this.algo.isIdentical() && this.left.length() < this.right.length()  && cntResultLen < this.algo.totalLength()){
			buf = new StringBuilder();
			for(int i = 0; i < this.algo.getResultRight().get(this.algo.getResultRight().size() - 1).distance;i++){
				buf.append("\u23CE\n");
				cntResultLen++;
			}
//			if(cntResultLen >= this.algo.totalLength()){
//				buf.deleteCharAt(buf.length() - 1);
//			}
			result.add(buf.toString());
		}

        this.left.getLines().add("");
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

		int cntDiff = 0, cntSame = 0, cntResultLen = 0;

		for(int i = 0; i < this.right.length();){
			int chki = i;
			if(!this.algo.isIdentical() && i == diff.get(cntDiff).begin){
				if(i < diff.get(cntDiff).end){
					for(int j = diff.get(cntDiff).begin; j < diff.get(cntDiff).end; j++){
						buf.append(this.right.getLines().get(j) + "\n");
						cntResultLen++;
					}
					if(diff.get(cntDiff).distance < this.algo.getResultLeft().get(cntDiff).distance){
						for(int j = 0; j < this.algo.getResultLeft().get(cntDiff).distance - diff.get(cntDiff).distance; j++){
							buf.append("\u23CE\n");
							cntResultLen++;
						}
					}
//					if(cntResultLen >= this.algo.totalLength()){
//						buf.deleteCharAt(buf.length() - 1);
//					}
					result.add(buf.toString());
					buf = new StringBuilder("");
					i += diff.get(cntDiff).distance;
				}
				else if(diff.get(cntDiff).begin == diff.get(cntDiff).end && this.algo.getResultLeft().get(cntDiff).distance > 0){
					for(int j = 0; j < this.algo.getResultLeft().get(cntDiff).distance; j++){
						buf.append("\u23CE\n");
						cntResultLen++;
					}
//					if(cntResultLen >= this.algo.totalLength()){
//						buf.deleteCharAt(buf.length() - 1);
//					}
					result.add(buf.toString());
					buf = new StringBuilder("");
				}

				if(cntDiff < diff.size() - 1){
					cntDiff++;
				}
			}

			if(this.algo.lenLcs() > 0 && i == same.get(cntSame)){
				buf.append(this.right.getLines().get(same.get(cntSame)) + "\n");
				cntResultLen++;
				if(i + 1 < this.right.length()){
					if(cntSame < same.size() - 1){
						cntSame++;
					}
					if((!this.algo.isIdentical() && i + 1 == diff.get(cntDiff).begin)){
//						if(cntResultLen >= this.algo.totalLength()){
//							buf.deleteCharAt(buf.length() - 1);
//						}
						result.add(buf.toString());
						buf = new StringBuilder("");					
					}
				}
				else {
//					if(cntResultLen >= this.algo.totalLength()){
//						buf.deleteCharAt(buf.length() - 1);
//					}
					result.add(buf.toString());
				}
				i++;
			}

			if(i == chki){ // unexpected condition
				return new ArrayList<String>();
			}
		}
		if(!this.algo.isIdentical() && this.right.length() < this.left.length() && cntResultLen < this.algo.totalLength()){
			buf = new StringBuilder();
			for(int i = 0; i < this.algo.getResultLeft().get(this.algo.getResultLeft().size() - 1).distance;i++){
				buf.append("\u23CE\n");
				cntResultLen++;
			}
//			if(cntResultLen >= this.algo.totalLength()){
//				buf.deleteCharAt(buf.length() - 1);
//			}
			result.add(buf.toString());
		}

        this.right.getLines().add("");
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
						this.LeftToRight(idxList.get(i).intValue());
					}
				}
				else if(lr == Position.RIGHT){
					for(int i = 0; i < idxList.size();i++){
						this.RightToLeft(idxList.get(i).intValue());
					}
				}
				else {
					return null;
				}

//				if(this.isCompared()){
//					this.algo = null;
//					return this.compare();
//				}
				this.algo = null;
				this.compResultLeft = null;
				this.compResultRight = null;
				return this.compare();
			}
			return null;
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
					this.LeftToRight(idx);
				}
				else if(lr == Position.RIGHT){
					this.RightToLeft(idx);
				}
				else {
					return null;
				}

//				if(this.isCompared()){
//					this.algo = null;
//					return this.compare();
//				}
				this.algo = null;
				this.compResultLeft = null;
				this.compResultRight = null;


				return this.compare();
			}
			return null;
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

	private ArrayList<String> removeFakeLine(ArrayList<String> data){
		ArrayList<String> rtn = new ArrayList<String>();

		for(String s: data) if(!s.equals("\u23CE")) rtn.add(s);

		return rtn;
	}

	private void LeftToRight(int idx){
		ArrayList<String> compResult = this.getResultLeft();
		ArrayList<String> data = this.parseViewData(compResult.get(idx));
		System.out.println(data);
		data = removeFakeLine(data);

		System.out.println(data);

		int diffIdx = (idx - (this.algo.isFirstAreSame() ? 1 : 0) )/ 2;

        System.out.println(this.right.getLines());
        System.out.println(this.algo.getResultRight().get(diffIdx).begin+" "+this.algo.getResultRight().get(diffIdx).end);

		this.right.deleteLine(this.algo.getResultRight().get(diffIdx).begin, this.algo.getResultRight().get(diffIdx).end);
        System.out.println(this.right.getLines());
		this.right.insertLine(this.algo.getResultRight().get(diffIdx).begin, data);
	}
	private void RightToLeft(int idx){
		ArrayList<String> compResult = this.getResultRight();
		ArrayList<String> data = this.parseViewData(compResult.get(idx));
		System.out.println(data);
		data = removeFakeLine(data);

		System.out.println(data);

		int diffIdx = (idx - (this.algo.isFirstAreSame() ? 1 : 0) )/ 2;

        System.out.println(this.left.getLines());
        System.out.println(this.algo.getResultLeft().get(diffIdx).begin+" "+this.algo.getResultLeft().get(diffIdx).end);


		this.left.deleteLine(this.algo.getResultLeft().get(diffIdx).begin, this.algo.getResultLeft().get(diffIdx).end);
        System.out.println(this.left.getLines());
		this.left.insertLine(this.algo.getResultLeft().get(diffIdx).begin, data);
	}
}