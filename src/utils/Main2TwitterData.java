package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main2TwitterData {
	public static void main (String [] args)throws Exception{
		
		 String inflow ="C:\\Users\\Simona\\Documents\\NetBeansProjects\\TwitterDataAnalytics\\streaming\\twitterdata";
		 File Folder = new File(inflow);
		 File files[];
		 files = Folder.listFiles();
		 List<String>list = new ArrayList<String>();
		 Set<String>set = new HashSet<String>(list);
		 List<String>list2 = new ArrayList<String>(set);
		
		try{
	        String line = "";

           if(files.length>1)
           {
            for(int i = 0;i<files.length; i++){
              
              BufferedReader br = new BufferedReader(new FileReader(files[i]));
              System.out.print("reading...");
              System.out.println(files[i].getName()+", number: "+i);
            
               while((line = br.readLine())!=null){
		    
            	   list.add(line);
				  
				  }
		          br.close();
           }
          }
		 }catch( Exception ex){
		    	ex.printStackTrace(); 
	  }
		System.out.println(list.size());
		
		set = new HashSet<String>(list);
		System.out.println(set.size());
	print(list, "TotalTweet.txt");
	}

public static void print(List<String>map , String file)throws Exception{
   	
   	PrintWriter out = new PrintWriter(new FileWriter(file));
   	for(int i = 0; i < map.size(); i++){
   		
				out.println(map.get(i));
				
			
   	}
   	out.close();
   }
}
