package model;
import java.io.*;
import java.util.Scanner;
import java.util.*;

public class FileManager{
	private ArrayList<String> bufLeft;
	private ArrayList<String> bufRight;
	private Scanner lScan;
	private Scanner rScan;

	private String lpath;
	private String rpath;
	
	private String lName;
	private String rName;

	public FileManager(File fl, File fr){
		this.lScan = null;
		this.rScan = null;
		if(!this.loadLeft(fl)){
			this.bufLeft = null;
			this.lpath = null;
			this.lName = null;
		}

		if(!this.loadRight(fr)){
			this.bufRight = null;
			this.rpath = null;
			this.rName = null;
		}			
	}

	public boolean loadLeft(File f){	
		boolean success;
		if(this.lScan != null){
			this.lScan.close();
		}
		if(success = f.isFile()){
			try {
				this.lScan = new Scanner(f);
				this.bufLeft.clear();
				while(this.lScan.hasNextLine()){
					this.bufLeft.add(this.lScan.nextLine());
				}
				this.lpath = f.getAbsolutePath();
				this.lName = f.getName();
				this.lScan.reset();
			}
			catch (Exception e)
			{
				this.lScan = null;
				success = false;
			}
		}

		return success;
	}
	public boolean loadRight(File f){
		boolean success;
		if(this.rScan != null){
			this.rScan.close();
		}
		if(success = f.isFile()){
			try {
				this.rScan = new Scanner(f);
				this.bufRight.clear();
				while(rScan.hasNextLine()){
					this.bufRight.add(rScan.nextLine());
				}
				this.rpath = f.getAbsolutePath();
				this.rName = f.getName();
				this.rScan.reset();
			}
			catch (Exception e)
			{
				this.rScan = null;
				success = false;
			}
		}

		return success;
	}
	public boolean saveLeft(List<String> buf){
		if(this.lScan != null){
			this.lScan.close();
		}
		File f = new File(lpath);
		FileWriter fw;
		ArrayList<String> buflist = new ArrayList<String>(buf);
		boolean success;
		try {
			fw = new FileWriter(f);
			for(int i = 0; i < buflist.size(); i++){
				fw.write(buflist.get(i));
			}
			fw.close();
			success = true;
		}
		catch(Exception e){
			success = false;
		}

		return success;
	}
	
	public boolean saveRight(List<String> buf){
		if(this.rScan != null){
			this.rScan.close();
		}
		File f = new File(lpath);
		FileWriter fw;
		boolean success;
		ArrayList<String> buflist = new ArrayList<String>(buf);
		try {
			fw = new FileWriter(f);
			for(int i = 0; i < buflist.size(); i++){
				fw.write(buflist.get(i));
			}
			fw.close();
			success = true;
		}
		catch(Exception e){
			success = false;
		}

		return success;
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

	public String getNameLeft(){
		return this.lName;
	}
	public String getNameRight(){
		return this.rName;
	}

	public File getFileLeft(){
		return new File(this.lpath);
	}
	public File getFileRight(){
		return new File(this.rpath);
	}
}