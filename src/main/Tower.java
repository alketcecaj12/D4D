package main;

public class Tower {
	
	public double id ;
	public double lat;
	public double lon;
	
	
	public Tower(double id, double lat, double lon){
		this.id = id;
		this.lat = lat; 
		this.lon = lon;
		
	}
	
	
	public String toString(){
		return id+", "+lat+", "+lon;
	}

}
