
public class Way {
	private String title;
	private Place start;
	private Place end;
	private double distance;
	private double time;
	private double Hdist;
	private double Htime;
	
	
	public Way(String title, Place start, Place end, double distance, double time) {
		super();
		this.title = title;
		this.start = start;
		this.end = end;
		this.distance = distance;
		this.time = time;
		this.Hdist=this.Calculate_Straight_Line();
		double var=this.distance/this.time;
		this.Htime=this.Hdist/var;
		
	}
	
	public double getHdist() {
		return this.Hdist;
	}
	public double getHtime() {
		return this.Htime;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Place getStart() {
		return start;
	}

	public void setStart(Place start) {
		this.start = start;
	}

	public Place getEnd() {
		return end;
	}

	public void setEnd(Place end) {
		this.end = end;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getTime() {
		return this.time;
	}

	public void setTime(double time) {
		this.time = time;
	}
	
	public double Calculate_Straight_Line()
	{
		
		double lat1=this.start.getCor1();double lon1=this.start.getCor2();
		double lat2=this.end.getCor1();double lon2=this.end.getCor2();
		
		double earthRadius = 6371;
		double dF = Math.toRadians(lat2-lat1);
		double dL = Math.toRadians(lon2-lon1);
		double a  = Math.sin(dF/2) * Math.sin(dF/2) +Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
		            Math.sin(dL/2) * Math.sin(dL/2);
		double c  = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double dist = (double) (earthRadius * c);

		return dist;
	 }
	
	
	String showDetails()
	{
		
		String help= String.format("%.2f", this.Hdist);
		String help2= String.format("%.2f", this.Htime);
		String s=" Οδός : "+this.title+"\n"+" Αναχώρηση : "+this.start.getName()+"\n"+" ’φιξη : "+this.end.getName()+"\n";
		s+=" Απόσταση : "+this.distance+"\n"+" Διάρκεια : "+this.time+"\n";
		s+=" Ευριστική Απόσταση : "+help+"\n"+" Ευριστική Χρόνου : "+ help2+"\n";
		return s;
	}

}
	
	