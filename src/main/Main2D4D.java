package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import visual.MyKML;

public class Main2D4D {
	
	
	public static void main(String[] args) throws Exception{
		
		String file ="C:\\Users\\Alket\\Desktop\\d4d\\ANT_POS.TSV";
		List<Tower>t = new ArrayList<Tower>();
		
		loadData(file, t);
		MyKML.printPlc("KMLAnteneD2D.kml", t);
	}

	
	public static void loadData(String file, List<Tower>t) throws Exception {
		
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
	
	    String line;  
	    while((line = br.readLine())!= null){
	    	
	    	t.add(retOb(line));
	    }br.close();
	
	}
	
	public static Tower retOb(String line){
		
		String []r = line.split("\t");
		
		return new Tower(Double.parseDouble(r[0]), Double.parseDouble(r[1]), Double.parseDouble(r[2]));
	}
}
