package visual;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Tower;
public class MyKML {

	public static String[]style = {"NORTH", "EST", "WEST", "SOUTH", "none"};
	public static Map<String, String>stylemap = new HashMap<String,String>();
	
	public static void printPlc(String file,List<Tower>l)throws Exception{
		stylemap.put("NORTH", "http://earth.google.com/images/kml-icons/track-directional/track-0.png");
		stylemap.put("SOUTH", "http://earth.google.com/images/kml-icons/track-directional/track-8.png");
		stylemap.put("EST", "http://earth.google.com/images/kml-icons/track-directional/track-4.png");
		stylemap.put("WEST", "http://earth.google.com/images/kml-icons/track-directional/track-12.png");
		stylemap.put("none", "http://earth.google.com/images/kml-icons/track-directional/track-none.png");
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
		
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\"");
	    out.println("xmlns:gx=\"http://www.google.com/kml/ext/2.2\">");

		out.println("<Document>");
		out.println(" <name>Views with Time</name>");
		
		for (String s: stylemap.keySet()) {
			printStyle(out, s, stylemap.get(s));
		}
	
		
		for(int i = 0; i < l.size(); i++){
				
			out.println(" <Placemark>");
			
			
			out.println("<styleUrl>#</styleUrl>");
			
			out.println("<Point>");
			out.println(" <coordinates>"+l.get(i).lat+","+l.get(i).lon+",0</coordinates>");
			out.println("</Point>");
			out.println("</Placemark>");
	      }
	      
		  out.println("</Document>");
	      out.println("</kml>");
	      out.close();
	}
	
	public static void printStyle(PrintWriter out, String styleName, String styleUrl){
		out.write("<Style id=\""+styleName+"\">");
		out.write("<IconStyle>");
		out.write("<scale>1.1</scale>");
		out.write("<Icon>");
		out.write("<href>"+styleUrl+"</href>");
		out.write("</Icon>");
		out.write("<hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>");
		out.write("</IconStyle>");
		out.write("</Style>");
	}
}
