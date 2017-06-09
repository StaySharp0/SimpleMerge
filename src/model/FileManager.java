package model;
import java.io.*;
import java.util.Scanner;
import java.util.*;

class FileManager{
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
		if(fl != null && !this.loadLeft(fl)){
			this.bufLeft = null;
			this.lpath = null;
			this.lName = null;
		}

		if(fr != null && !this.loadRight(fr)){
			this.bufRight = null;
			this.rpath = null;
			this.rName = null;
		}			
	}

	public boolean loadLeft(File f){	
		boolean success;
		// if(this.lScan != null){
		// 	this.lScan.close();
		// }
		if(success = f.isFile()){
			if(this.bufLeft == null){
				this.bufLeft = new ArrayList<String>();
			}
			try {
				this.lScan = new Scanner(f);
				this.bufLeft.clear();
				while(this.lScan.hasNextLine()){
					String buf = this.lScan.nextLine();
					this.bufLeft.add(buf);
				}
				this.lpath = f.getAbsolutePath();
				this.lName = f.getName();
				this.lScan.close();
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
		// if(this.rScan != null){
		// 	this.rScan.close();
		// }
		if(success = f.isFile()){
			if(this.bufRight == null){
				this.bufRight = new ArrayList<String>();
			}
			try {
				this.rScan = new Scanner(f);
				this.bufRight.clear();
				while(rScan.hasNextLine()){
					String buf = this.rScan.nextLine();
					this.bufRight.add(buf);
				}
				this.rpath = f.getAbsolutePath();
				this.rName = f.getName();
				this.rScan.close();
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
		// if(this.lScan != null){
		// 	this.lScan.close();
		// }
		File f;
		FileWriter fw;
		ArrayList<String> buflist = new ArrayList<String>(buf);
		boolean success;
		try {
			f = new File(this.lpath);
			fw = new FileWriter(f);
			for(int i = 0; i < buflist.size(); i++){
				fw.write(buflist.get(i) + '\n');
			}
			fw.close();
			success = true;
			this.loadLeft(f);
		}
		catch(Exception e){
			success = false;
		}
		

		return success;
	}
	
	public boolean saveRight(List<String> buf){
		// if(this.rScan != null){
		// 	this.rScan.close();
		// }
		File f;
		FileWriter fw;
		boolean success;
		ArrayList<String> buflist = new ArrayList<String>(buf);
		try {
			f = new File(this.rpath);
			fw = new FileWriter(f);
			for(int i = 0; i < buflist.size(); i++){
				fw.write(buflist.get(i) + '\n');
			}
			fw.close();
			success = true;
			this.loadRight(f);
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

	public static void main(String[] args){
		String lp = "", rp = "";
		File lf, rf;
		Scanner lsc, rsc;
		FileManager fm;
		ArrayList<String> lbuf, rbuf;
		boolean isSave = false;
		if(args.length >= 1){
			if(isSave = args[0].equals("-s")){
				if(args.length >= 2){
					lp = args[1];
					if(args.length >= 3){	
						rp = args[2];
					}						
				}
			}
			else {
				lp = args[0];
				if(args.length >= 2){	
					rp = args[1];
				}
			}
			lf = new File(lp);
			rf = new File(rp);
			fm = new FileManager(lf,rf);
			if((lbuf = fm.getBufLeft()) != null){
				System.out.println(lbuf.size());
				for(int i = 0; i < lbuf.size(); i++){
					System.out.println(lbuf.get(i));
				}
				if(isSave){
					fm.saveRight(fm.getBufLeft());
				}
			}
			if((rbuf = fm.getBufRight()) != null){
				System.out.println(rbuf.size());
				for(int i = 0; i < rbuf.size(); i++){
					System.out.println(rbuf.get(i));
				}
			}
			
		}
	}
}