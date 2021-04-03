
public class Place {
	
	private int id;
	private String name;
	private double cor1;
	private double cor2;
	private boolean visited;//θα το χρησιμποποιήσω αργότερα στους αλγορίθμους

	public Place(int id, String name,double cor1,double cor2)
	{
		this.id=id;
		this.name=name;
		this.cor1=cor1;
		this.cor2=cor2;
		this.visited=false;
	}

	
	public void setVisited(boolean help) {
		this.visited=help;
	}
	public boolean getVisited() {
		return this.visited;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCor1() {
		return cor1;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setCor1(double cor1) {
		this.cor1 = cor1;
	}

	public double getCor2() {
		return cor2;
	}

	public void setCor2(double cor2) {
		this.cor2 = cor2;
	}
	
	String showDetails()
	{
		
		String s=" Τοποθεσία : "+this.name+"\n"+" Latitude : "+this.cor1+"\n"+" Longtitude : "+this.cor2+"\n";
		return s;
	}
	
}
