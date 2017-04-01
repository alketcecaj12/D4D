package utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ConvertString2DataObj {

	public static void main (String [] args) throws Exception{
		
		
		/*String data = "Thu Jun 27 20:00:27 +0000 2013";
		String datastring = "08/03/12 11.01.36";
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH.mm.ss"); 
		Date date;
		date = df.parse(datastring);
		
		System.out.println("la data come oggetto "+date); 
		Timestamp ts = new Timestamp(date.getTime());
		long tsp = ts.getTime();
		System.out.println("la data come timestamp: "+tsp);
		
		Date date2 = new Date();
		long tsp2 = 1330955415501L;
		date2.setTime(tsp);
		//date2.setTime(tsp2);
		//System.out.println("la date2 dopo conversione da tsp è "+date2);
		
		String data4tsp2 = convertTsp2Date(tsp2);
		System.out.println("********"+data4tsp2);
		
		long tsp4 = convertDate2Tsp(datastring);
		System.out.println("_----"+tsp4);
		*/
		//String ds = "Thu Jun 27 20:00:27 +0000 2013";
		//SimpleDateFormat f = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy");
		//Date d2 = f.parse(ds);
		//Date d = f.format(d2.parse(ds));
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",Locale.ENGLISH);
		Date d = format.parse("2011-12-18 15:59:00");
		
		
	    Timestamp ts = new Timestamp(d.getTime());
	    
		long tsp = ts.getTime();
		System.out.println(tsp);
		//System.out.println("la data come timestamp: "+tsp);
		//long t = convertString(d);
		//System.out.println("il timestamp è "+t);
	}
	public static long convertDate2Tsp(String datastring) throws Exception{
		long tsp = 0; 
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH.mm.ss"); 
		Date date;
		date = df.parse(datastring);
		//System.out.println("la data come oggetto "+date); 
		Timestamp ts = new Timestamp(date.getTime());
	    tsp = ts.getTime();
		System.out.println("la data come timestamp: "+tsp);
		return tsp;
	}
	public static String convertTsp2Date(long timestamp){
		
		Date data ;
		
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(timestamp);
		data = c.getTime();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yy HH.mm.ss");
		String datainstringa = new String(dateformat.format(data));
		return datainstringa;
		
	}

	public static long convertString(String data) throws Exception{
	
		long ts = 0; 
	    
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH.mm.ss"); 
		Date date;
		date = df.parse(data);
		//System.out.println("la data come oggetto "+date); 
		Timestamp tps = new Timestamp(date.getTime());
	    ts = tps.getTime();
		System.out.println("la data come timestamp: "+tps);
		
		return ts;
	
	
	}
	}
