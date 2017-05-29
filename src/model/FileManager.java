package model;
import java.io.*;
import java.util.Scanner;

public class FileManager{
	private ArrayList<String> bufLeft;
	private ArrayList<String> bufRight;

	private String lpath;
	private String rpath;
	
	public FileManager(String pathLeft, String pathRight){
		File fl = null, fr = null;
		Scanner lScan = null, rScan = null;

		if(pathLeft != null){
			fl = new File(pathLeft);
			if(fl.isFile()){
				this.lpath = fl.getAbsolutePath();
				lScan = new Scanner(fl);
				this.bufLeft = new ArrayList<String>();
				while(lScan.hasNextLine){
					this.bufLeft.add(lScan.nextLne());
				}
				lScan.close();
			}
		}
		else {
			this.bufLeft = null;
			this.lpath = null;
		}
		if(pathRight != null){
			fr = new File(pathRight);
			if(fr.isFile()){
				this.rpath = fr.getAbsolutePath();
				rScan = new Scanner(fl);
				this.bufRight = new ArrayList<String>();
				while(rScan.hasNextLine()){
					this.bufRight.add(rScan.nextLne());
				}
				rScan.close();
			}		
		}
		else {
			this.bufRight = null;
			this.rpath = null;
		}		

	}
	// public FileManager(File left, File right){
	// 	//TODO
	// }	

	public boolean loadLeft(String path){
		File f = new File(path);
		Scanner scan = null;
		boolean success;
		if(success = f.isFile()){
			scan = new Scanner(f);

			this.bufLeft.clear();
			while(scan.hasNextLine()){
				this.bufLeft.add(scan.nextLine());
			}
			scan.close();
		}

		return success;
	}
	
	public boolean loadRight(String path){
		File f = new File(path);
		Scanner scan = null;
		boolean success;
		if(success = f.isFile()){
			scan = new Scanner(f);

			this.bufRight.clear();
			while(scan.hasNextLine()){
				this.bufRight.add(scan.nextLine());
			}
			scan.close();
		}

		return success;	
	}
	public boolean saveLeft(ArrayList<String> buf){
		File f = new File(lpath);
		FileWriter fw = new FileWriter(f);
		for(int i = 0; i < buf.length(); i++){
			fw.write(buf.get(i));
		}
		fw.close();
	}
	
	public boolean saveRight(ArrayList<String> buf){
		File f = new File(rpath);
		FileWriter fw = new FileWriter(f);
		for(int i = 0; i < buf.length(); i++){
			fw.write(buf.get(i));
		}
		fw.close();	
	}

	public ArrayList<String> getBufLeft(){
		return this.bufLeft;
	}
	public ArrayList<String> getBufRight(){
		return this.bufRight;
	}

	public String getPathLeft(){
		return this.lpath;
	}
	public String getPathRight(){
		return this.rpath;
	}

	public File getFileLeft(){
		return new File(this.lpath);
	}
	public File getFileRight(){
		return new File(this.rpath);
	}
}