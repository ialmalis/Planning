import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class myDfs {

private Stack<Place> stack;
private ArrayList<Place> places;
private ArrayList<Way> ways;	
private Place start,goal;
private LinkedList<Place> dfsList; 
private Map<String, Place> prev ;
ArrayList<Place> Neighbours ;
private int count,count_nodes,all_nodes;
private double sumDist,sumTime;
	
public myDfs(ArrayList<Place> p,ArrayList<Way> w,Place start,Place goal)
{
	this.places=p;
	this.ways=w;
	dfsList=new LinkedList<Place>();
	prev = new HashMap<>();
	
	stack=new Stack<Place>();
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
	
	stack.push(places.get(pos));
	
	places.get(pos).setVisited(true);
	
	this.count=0;
	while (!stack.isEmpty()) 
	{

        phelp = stack.pop();
        this.count++;
       
        if (phelp.getName().equals(this.goal.getName()))
		
			{break;}
        else
        {
        	Neighbours=new  ArrayList<Place>();
  //**************************************************************************88      		
        	for (Way p1:ways)
    		
    			{if (p1.getStart().getName().equals(phelp.getName())) 
    			{
    				
    				Neighbours.add(p1.getEnd());
    			
    			}
    				
    			else if (p1.getEnd().getName().equals(phelp.getName())) 
    			{
    				Neighbours.add(p1.getStart());
    				
    			}}
        	
//************************************************************************************        	
        	for (Place p2:Neighbours)
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
        		
        		if (!places.get(index3).getVisited())
        		{
        			stack.push(places.get(index3));	
        			places.get(index3).setVisited(true);
        			prev.put(places.get(index3).getName(), phelp);
        		}
        	}
    
	
	}}
	
	
    for (Place node = goal; node != null; node = prev.get(node.getName())) {
        dfsList.add(node);
    }
   
    Collections.reverse(dfsList);

}

public String show() 
{
	 ArrayList<Place> list = new ArrayList<Place>(dfsList);

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
	this.count_nodes=this.dfsList.size();
	this.all_nodes=this.stack.size()+this.count;
	
	String s="\n D F S \n";
	s+="---------\n";
	
	s+="Πλήθος Κόμβων Λύσης :"+this.dfsList.size()+"\n";
	int sum=this.stack.size()+this.count;
	s+="Πλήθος Κόμβων που εξετάστηκαν : "+sum+"\n";
	s+="Συνολική Απόσταση :"+sumDist+"  Km   Συνολικός Χρόνος : "+sumTime+" mins\n";
	s+="\n Διαδρομή \n";
	s+="------------\n";

	for(Place p:dfsList)
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
