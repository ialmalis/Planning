import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class myBfs {

private Queue<Place> queue;
private ArrayList<Place> places;
private ArrayList<Way> ways;	
private Place start,goal;
private LinkedList<Place> bfsList;  
private Map<String, Place> prev ;
LinkedList<Place> Neighbours ;
private int count,count_nodes,all_nodes;
private double sumDist,sumTime;
	
public myBfs(ArrayList<Place> p,ArrayList<Way> w,Place start,Place goal)
{
	this.places=p;
	this.ways=w;
	bfsList=new LinkedList<Place>();
	prev = new HashMap<>();
	
	queue=new LinkedList<Place>();
	this.start=start;
	this.goal=goal;
	for (Place p1:places)
	{
		p1.setVisited(false);
	}
}
	
	
public void findSolution()
{
	
	int pos=-1;
		
	
	for (int i=0;i<places.size();i++)
	{
		if (start.getName().equals(places.get(i).getName()))
		{
			pos=i;
			break;
		}
	}
	
	Place phelp;
	
	queue.add(places.get(pos));
	places.get(pos).setVisited(true);
	
	this.count=0;
	while (!queue.isEmpty()) 
	{

        phelp = queue.remove();
        this.count++;
       
        if (phelp.getName().equals(this.goal.getName()))
		
			{break;}
        else
        {
        	Neighbours=new  LinkedList<Place>();
  //**************************************************************************88      		
        	for (Way p1:ways)//προσθήκη παιδιών
    		
    			{if (p1.getStart().getName().equals(phelp.getName())) 
    			{
    				
    				Neighbours.add(p1.getEnd());
    			
    			}
    				
    			else if (p1.getEnd().getName().equals(phelp.getName())) 
    			{
    				Neighbours.add(p1.getStart());
    			}}
//************************************************************************************        	
        	for (Place p2:Neighbours)//για κάθε παιδί
        	{
        		int index3=-1;
        		
        		for(int i=0;i<places.size();i++)
        		
        		{
        			if(places.get(i).getName().equals(p2.getName())) 
        			{
        				index3=i;
        				break;
        			}
        			
        		}
        		
        		if (!places.get(index3).getVisited())//αν ανεξερεύνητο
        		{
        			queue.add(places.get(index3));	
        			places.get(index3).setVisited(true);
        			prev.put(places.get(index3).getName(), phelp);
        		}
        	}
    
	
	}}
	
	
    for (Place node = goal; node != null; node = prev.get(node.getName())) {
        bfsList.add(node);
    }
   
    Collections.reverse(bfsList);

    
	
	
}

public String show() 
{
	 ArrayList<Place> list = new ArrayList<Place>(bfsList);

	this.sumDist=0.0;
	this.sumTime=0.0;
	
	for (int i=0;i<list.size()-1;i++)
	{
		
		for (Way w1:ways)
		{
			if ((w1.getStart().equals(list.get(i)) && w1.getEnd().equals(list.get(i+1))) || (w1.getStart().equals(list.get(i+1)) && w1.getEnd().equals(list.get(i))))
			{
				this.sumDist+=w1.getDistance();
				this.sumTime+=w1.getTime();
				break;
			}
		}
		
			
	}
	this.count_nodes=this.bfsList.size();
	this.all_nodes=this.queue.size()+this.count;
	
	String s="\n B F S \n";
	s+="---------\n";
	
	s+="Πλήθος Κόμβων Λύσης :"+this.bfsList.size()+"\n";
	int sum=this.queue.size()+this.count;
	s+="Πλήθος Κόμβων που εξετάστηκαν : "+sum+"\n";
	s+="Συνολική Απόσταση :"+sumDist+"  Km   Συνολικός Χρόνος : "+sumTime+" mins\n";
	s+="\n Διαδρομή \n";
	s+="------------\n";

	for(Place p:bfsList)
	{
		s+=p.getName()+" -> ";
	}
			
	s=s.substring(0, s.length() - 4)+"\n";
	
	return s;
}

public double getSumDist() {
	return this.sumDist;
}
public double getSumTime() {
	return this.sumTime;
}
public int getSumNodes() {
	return this.count_nodes;
}
public int getallNodes() {
	return this.all_nodes;
}

}
