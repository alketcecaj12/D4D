package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import d4dMatching.DataI;
import d4dMatching.PLS;

public class FindUsers {
	
	public static void main (String [] args) throws Exception{
	
	
	
	}
	public static List<DataI> getUsers(int nr_users, int max, int min,String path2files,String exclude_file, int nrfile) throws Exception{ 
//		String path2files = "C:\\Users\\Alket\\junocode\\TelecomData\\Coord2D4D_PLS-SET2_0";
//		String exclude_file = "C:\\Users\\Alket\\junocode\\D4D\\NrEventsPerUserD4D.csv";
//		
		Set<String>max_set = new HashSet<String>();
		Set<String>min_set = new HashSet<String>();
		Set<String>exclude_set = new HashSet<String>();
		Set<String>max_users = new HashSet<String>();
		List<DataI>l = new ArrayList<DataI>();
		
		d4dstats.Main2Stats.load200(exclude_file, max, max_set);
		d4dstats.Main2Stats.load3(exclude_file, min, min_set);

		exclude_set.addAll(max_set);
		System.out.println("max_set "+max_set.size());
		
		exclude_set.addAll(min_set);
		System.out.println("min_set "+min_set.size());
	
		System.out.println("exclude_set "+exclude_set.size());
		
		File folder = new File(path2files);
		File [] files = folder.listFiles();
		
		for (int i = nrfile; i < files.length; i++) {
			
			BufferedReader br = new BufferedReader(new FileReader(files[i]));
			String line;
			while((line = br.readLine())!= null){
			
				String [] r = line.split(",");
				String user = r[0];
				max_users.add(user);
				if(exclude_set.contains(user)) continue;
				int imsi = Integer.parseInt(r[1]);
				long cell = Long.parseLong(r[2]);
				long ts = Long.parseLong(r[3]);
				double lat = Double.parseDouble(r[4]);
				double lon = Double.parseDouble(r[5]);
				double raggio = Double.parseDouble(r[6]);
				l.add(new PLS(user, imsi, cell, ts, lat, lon, raggio));
				
				if(max_users.size()== nr_users) break;
			}br.close();
			break;
			
		}
		
		PrintWriter out = new PrintWriter(new FileWriter(new File(nr_users+"UtentiperMatching.csv")));
		
		for (int i = 0; i < l.size(); i++) {
			out.println(l.get(i).toString());
		}
		out.close();
		
		return l;
	}

}
