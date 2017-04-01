package d4dMatching;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main2PLS3 {

	 static String outfolder = "C:\\Users\\Alket\\junocode\\DataTel\\Matching D4D\\SET2_4\\";
     public static void main (String[] args)throws Exception{
		List<Long>l = new ArrayList<Long>();
		l.add(120000L);
		l.add(180000L);
		l.add(300000L);
		l.add(420000L);
		l.add(600000L);
		l.add(900000L);
		l.add(1200000L);
		l.add(1500000L);
		l.add(1680000L);
		l.add(1800000L);
		
		for (int i = 0; i < l.size(); i++) {
			long li = l.get(i);
			double m2 = run(0,li);
		}
	
	}
	
	public static double run(double ds, long dt) throws Exception {
		
		
		
		String users2match = "C:\\Users\\Alket\\junocode\\DataTel\\Utenti Matching D4D\\60_utenti-SET2_0.csv";
		String files4matching = "C:\\Users\\Alket\\junocode\\TelecomData\\Coord2D4D_PLS-SET2_0";
		String exclude_file = "C:/Users/Alket/junocode/D4D/NrEventsPerUserD4D.csv";
//		String users2match = "C:\\Users\\Alket\\junocode\\TelecomData\\Coord2test.txt";
//		String files4matching = "C:\\Users\\Alket\\junocode\\TelecomData\\Coord2D4D_test";
	
		List<DataI>l = new ArrayList<DataI>();
        int utenti = 2000;
		
        //1-carico gli utenti che mi servono
		//l = PLSMethods.load(file, utenti);
        l = main.FindUsers.getUsers(utenti, 200, 3, files4matching,exclude_file, 0 );
		PLSMethods.print(utenti+"_Utenti.txt", l);
        System.out.println();
		//2-trovo tutti i loro eventi 
		Map<String, List<DataI>> map = null;
		
		map = PLSMethods.findEvents(l,files4matching, dt+2);
		
		Map<String, List<DataI>>data = new HashMap<String, List<DataI>>();
		
		
		PLSMethods.print(dt+"EventiPer_"+utenti+"_utentiPLS.txt", map);
		
        Set<String>badusers = new HashSet<String>();
        
        //loadBadUsers(badusers,  10,"NightEventsPerUserD4D.csv");
       // badusers = d4dstats.Main2Stats.load250("C:/Users/Alket/junocode/D4D/NrEventsPerUserD4D.csv", 3,150);
        
        
        
        d4dstats.Main2Stats.load200("C:/Users/Alket/junocode/D4D/NrEventsPerUserD4D.csv",200, badusers);
       // System.out.println(badusers.size()+"############################");
        //3-per ogniuno faccio il matching
		for (String s: map.keySet()){
			System.out.println("inizia la fase di matching ");
			data.put(s, PLSMethods.matchPLS(files4matching, badusers,map.get(s),ds,dt));
		}

		PLSMethods.print(dt+"MatchingPer_"+utenti+"_Utenti.txt",data);	
		Map<String, Map<String, Integer>>m = new HashMap<String, Map<String, Integer>>();
        // 4-trovo le frequenze per ogni caregoria di punti in comune
		m = freq2(data);
		print(m, dt+"DatiFinaliPer_"+utenti+"_.txt");
		List<FO>f = new ArrayList<FO>();

		for(String s: m.keySet()){
			for(String s2: m.get(s).keySet()){
				String h1 = s;
				String h2 = s2;
				
				int val = m.get(s).get(s2);
				f.add(new FO(h1, h2, val));
			}
		}

		Map<String, Map<Integer, Integer>>m2 = new HashMap<String, Map<Integer, Integer>>();

		for(FO fo: f) {
			Map<Integer, Integer> mi = m2.get(fo.h1);
			if(mi == null) {
				mi = new TreeMap<Integer,Integer>();
				m2.put(fo.h1, mi);
			}
			Integer fx = mi.get(fo.freq);
			if(fx == null) mi.put(fo.freq, 1);
			else mi.put(fo.freq, fx+1);
		}

		for(Map<Integer, Integer> m_i : m2.values()) {
			int max = 0;
			// trova il massimo elemento tra le chiavi della hashMap 
			for(int x: m_i.keySet()){
				max = Math.max(max, x);
				System.out.println("MAX = "+max);
			}
		}
        PrintWriter out = new PrintWriter(new FileWriter(new File(dt+"test.txt")));
		for(String s: m2.keySet()){
			System.out.print(s);
			out.print(s);
			for(int i=1;i<2000;i++) {
				int somma = 0; 
				for(int j: m2.get(s).keySet())
					if(j>=i) somma += m2.get(s).get(j);

				System.out.print(","+i+"="+somma);
				out.print(","+i+"="+somma);
				if(somma == 1 || somma == 0) break;
			}
			System.out.println();
			out.println();
		}
		out.close();
		
		return 0;
	}

	public static void loadfinalData(String file, List<FO>list)throws Exception{

		BufferedReader br = new BufferedReader(new FileReader(new File(file)));

		String line;

		while((line = br.readLine())!=null){
			//  System.out.println(line);
			String [] riga = line.split(" ");

			String h1 = riga[0];
			String h2 = riga[1];
			int freq = Integer.parseInt(riga[2]);
			list.add(new FO(h1, h2, freq));
		}
		br.close();
	}
	public static void print(Map<String,Map<String, Integer>>map, String file)throws Exception{
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));

		for (String s: map.keySet()) {
			for(String s2 : map.get(s).keySet()) {
				out.print(s+" ");
				out.print(s2+" ");
				out.println(map.get(s).get(s2).toString());
				//System.out.println(map.get(s).get(s2).toString());

			}
		}
		out.close();
	}
	public static void print3(Map<String,Map<Integer, Integer>>map, String file)throws Exception{
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));

		for (String s: map.keySet()) {
			out.print(s+" ");
			for(int s2 : map.get(s).keySet()) {
				
				out.print(s2+"=");
				out.print(map.get(s).get(s2).toString()+",");
				//System.out.println(map.get(s).get(s2).toString());
			}
			out.println();
		}
		out.close();
	}

	
	public static Map<String, Map<String , Integer>> freq2(Map<String, List<DataI>>m)throws Exception{

		Map<String, Map<String , Integer>> map = new HashMap<String, Map<String, Integer>>(); 

		Map<String , Integer>map_i = new HashMap<String, Integer>();

		for(String s:m.keySet()){
			// System.out.println(line);
			map_i = new HashMap<String, Integer>();
			for (int i = 0; i < m.get(s).size(); i++) {

				Integer c = map_i.get(m.get(s).get(i).getUser());
				if(c==null)
					map_i.put(m.get(s).get(i).getUser(), 1);
				else
					map_i.put(m.get(s).get(i).getUser(), c+1);

			}
			map.put(s, map_i);
		}

		return map;
	}
public static void loadBadUsers(Set<String>s, int c, String file) throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		
		String line;
		while((line = br.readLine())!= null){
			String [] r = line.split("=");
			
			String u = r[0];
			int ci = Integer.parseInt(r[1]);
			if(ci > c){
				s.add(u);
			}
		}br.close();
	}
}
