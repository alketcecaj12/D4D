package d4dmain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Main2D4D2 {

	public static void main (String[]args) throws Exception{
	    
		List<PLS>plist = new ArrayList<PLS>();
		String file = "C:\\DATA\\SUBPREF_POS_SAMPLE_J.TSV";
		String line;
		BufferedReader  br = new BufferedReader(new FileReader(new File(file)));
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",Locale.ENGLISH);
		
		System.out.println(file);
		int count = 0;
		while((line = br.readLine())!= null){
			count++;
			String [] r = line.split("\t");
			
			Date d = format.parse(r[1]);
			Timestamp ts = new Timestamp(d.getTime());
			long tsp = ts.getTime();
			//plist.add(new PLS(Integer.parseInt(r[0]),61203,Integer.parseInt((r[2])), tsp));
		
			
			if (count % 100000 == 0)
			System.out.println("riga "+count);
			
		}br.close();
		
		Collections.sort(plist);
		
		System.out.println(plist.size());
		System.out.println("finito ordinamento ..");
		
		//print(plist, "SetFile.tsv");
		
		long ts_i = 0;
		List<PLS>pls_i = new ArrayList<PLS>();
		for (int i = 0; i < plist.size(); i++) {
			
			if(i == 0){
				ts_i = plist.get(0).ts;
				ts_i += 1800000;
				System.out.println("��� "+ts_i);
			}
			if(plist.get(i).ts <= ts_i){
				pls_i.add(plist.get(i));
				//System.out.println("inserisco i");
			}
			else{
				print(pls_i, "SET3_J\\PLS"+20+"_"+ts_i+".tsv");
				System.out.println("Stampo file "+i);
				ts_i += 1800000;
				pls_i = new ArrayList<PLS>();
				pls_i.add(plist.get(i));
				//System.out.println("---->"+ts_i);
			}
			
			}
			
			
		
	}
	public static void print(List<PLS>p, String file) throws Exception{
		
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
		
		for (int i = 0; i < p.size(); i++) {
			out.print(p.get(i).hash+"\t");
			out.print(p.get(i).imsi+"\t");
			out.print(p.get(i).cellac+"\t");
			out.println(p.get(i).ts);
		}
		out.close();
	}
}
