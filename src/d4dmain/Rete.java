package d4dmain;

public class Rete {

	String cellname;
    int bari;
	int lacid;
	int cellid;
	double lat_ant;
	double lon_ant;
	int sette;
	double otto;
	int nove;
	double lat_bari;
	double lon_bari;
	int raggio;
	
	public Rete(String c, int b, int lac, int ceid,double la_a, double lo_a, int sett,
			double ott, int nov, double la_b, double lo_b, int r){
		
		this.cellname = c;
		this.bari = b;
		this.lacid = lac;
		this.cellid = ceid;
		this.lat_ant = la_a;
		this.lon_ant = lo_a;
		this.sette = sett;
		this.otto = ott;
		this.nove = nov;
		this.lat_bari = la_b;
		this.lon_bari = lo_b;
		this.raggio = r;
	}
}
