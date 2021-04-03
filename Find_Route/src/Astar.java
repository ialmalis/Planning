import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Astar{

private ArrayList <Place> uni;
private ArrayList<Place> places;
private ArrayList<Way> ways;	
private Place start,goal;
private LinkedList<Place> astarList; 
private Map<String, Place> prev ;
ArrayList<Place> Neighbours ;
private int count,count_nodes,all_nodes;
private double sumDist,sumTime;


public Astar (ArrayList<Place> p,ArrayList<Way> w,Place start,Place goal){
	this.places=p;
	this.ways=w;
	astarList=new LinkedList<Place>();
	prev = new HashMap<>();//Διαδρομή
	
	uni=new ArrayList<Place>();//Μέτωπο
	this.start=start;
	this.goal=goal;
	//Αρχικοποίηση Visited
	for (Place p1:places)
	{
		p1.setVisited(false);
	}

}

// Εύρεση Λύσης με ευριστική την Απόσταση
public void findSolution()
{
	
	int pos=-1;
		
	Map<String, Place> tempmap;	//βοηθητικό για σύγκριση όταν βρούμε πόλη που ήδη εξετάσαμε
	
	
	//Εύρεση Αφετηρίας 
	for (int i=0;i<places.size();i++)
	{
		if (start.getName().equals(places.get(i).getName()))
		{
			pos=i;
			break;
		}
	}
	
	
	
	Place phelp;
	
	uni.add(places.get(pos));
	
	places.get(pos).setVisited(true);
	
	this.count=0;
	int helpindex;
	
	while (!uni.isEmpty()) 
	{
		
		if (prev.size()==0)//αν είναι η πρώτη πόλη
		{
			helpindex=0;
		}
		else{helpindex=this.chooseByKm(uni, this.goal,this.prev);}//επιλογή από το μέτωπο της πόλης με βάση το κριτήριο της Α*
		
		
		phelp=uni.get(helpindex);
        uni.remove(helpindex);
        this.count++;
	
        if (phelp.getName().equals(this.goal.getName()))//αν βρήκαμε το στόχο
    		
		{break;}
	    else
	    {
	    	Neighbours=new  ArrayList<Place>();
//**************************************************************************   		
	    	//προσθήκη γειτόνων,παιδιών
	    	
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
    	for (Place p2:Neighbours)//για κάθε παιδί
    	{
    		int index3=-1;
    		
    		//εύρεση παιδιού
    		for(int i=0;i<places.size();i++)
    		
    		{
    			if(places.get(i).getName().equals(p2.getName())) 
    			{
    				index3=i;
    				break;
    			}
    			
    		}
    		
    		double sum1=0.0,sum2=0.0;
    		
    		
    		if (places.get(index3).getVisited())//αν έχει ήδη εξετασθεί
    		{
    			
    			//ξεκινά η διαδικασία σύγκρισης
    			ArrayList<Place> temp1List=new ArrayList<Place>();
    			ArrayList<Place> temp2List=new ArrayList<Place>();
    			tempmap=new HashMap<>();
    			
    			tempmap.putAll(prev);
    			boolean flag2=true;
    			if (tempmap.containsValue(places.get(index3))) //για να αποφύγω κυκλικές πορείες στο γράφο
    			{
    				flag2=false;
    			}
    			
    			//if ((!(tempmap.get(phelp.getName()).equals(places.get(index3)))) && (flag))
    			if (flag2)
    			
    			{
    				 
	    			
	    			tempmap.put(places.get(index3).getName(), phelp);
    			
    			//για σύγκριση των καταστάσεων
    			
    			for (Place node = places.get(index3); node != null; node = tempmap.get(node.getName())) 
    			{
    			   
    				
    				temp2List.add(node);
    			}
    			
    			
    			for (Place node = places.get(index3); node != null; node = prev.get(node.getName())) 
    			{
    			    temp1List.add(node);
    			}
 //**********************************************************************************************************************   			
    			for (int j=0;j<temp1List.size()-1;j++)
    			{
    				for (Way w1:ways)
    				{
    					if ((w1.getStart().equals(temp1List.get(j)) && w1.getEnd().equals(temp1List.get(j+1))) || (w1.getStart().equals(temp1List.get(j+1)) && w1.getEnd().equals(temp1List.get(j))))
    					{
    						sum1+=w1.getDistance();
    						
    						break;
    					}
    				}
    				    				
    			}
    			
//**********************************************************************************************************************    			
    			
    			 //**********************************************************************************************************************   			
    			for (int j=0;j<temp2List.size()-1;j++)
    			{
    				for (Way w1:ways)
    				{
    					if ((w1.getStart().equals(temp2List.get(j)) && w1.getEnd().equals(temp2List.get(j+1))) || (w1.getStart().equals(temp2List.get(j+1)) && w1.getEnd().equals(temp2List.get(j))))
    					{
    						sum2+=w1.getDistance();
    						
    						break;
    					}
    				}
    				    				
    			}
    			
//********************************************************************************************************************** 			
    			
    			if (sum2<sum1)//αν η νέα κατάσταση καλύτερη αυτής που είχα με βάση το κριτήριο της Α*
    			{
    				prev=new HashMap<>();
    				prev.putAll(tempmap);
    				
    				
    			}
    			
    		}//όχι κυκλικά
    			}
    		
    		else {
  		
    		if (!places.get(index3).getVisited())//αν ανεξερεύνητη πολη
    		{
    			uni.add(places.get(index3));	
    			places.get(index3).setVisited(true);
    			prev.put(places.get(index3).getName(), phelp);
    		}
    	}//else visited
    		
    	
    	}//for neighbours

    	
}//not goal
        
	}//while


for (Place node = goal; node != null; node = prev.get(node.getName())) 
{
    astarList.add(node);//η λύση
}

Collections.reverse(astarList);

}

//Πώς διαλέγω από το μέτωπο με βάση απόσταση
public int chooseByKm(ArrayList<Place> p, Place parent,Map<String,Place> m)
{
	double min=0.0;
	int index=0;
	
	
	
	
		for (int i=0;i<p.size();i++) //για κάθε πόλη στο μέτωπο
		{
			
			ArrayList<Place> helpList=new ArrayList<Place>() ; 
			
				for (Place node = p.get(i); node != null; node = m.get(node.getName())) 
				{
				    helpList.add(node);//η διαδρομή της από τη ρίζα
				   
				}
			
				//σύνολο κόστους μετάβασης		
			double sum=0.0;
			
			for (int j=0;j<helpList.size()-1;j++)
			{
				for (Way w1:ways)
				{
					if ((w1.getStart().equals(helpList.get(j)) && w1.getEnd().equals(helpList.get(j+1)))
							|| (w1.getStart().equals(helpList.get(j+1)) && w1.getEnd().equals(helpList.get(j))))
					{
						sum+=w1.getDistance();
						
						break;
					}
				}
			}
			
			//ευριστική από το στόχο
			Way tempway =new Way("",p.get(i),parent,1.0,0.8);
			if (i==0) 
			{
				min=tempway.getHdist()+sum;
				index=i;
			}
			else if ((tempway.getHdist()+sum)<min)
			{
				min=tempway.getHdist()+sum;
				index=i;
			}
				
	}
		
	return index;}
	
public String show() 
{
	 ArrayList<Place> list = new ArrayList<Place>(astarList);

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
			}}}
	
	this.count_nodes=this.astarList.size();//λύση
	this.all_nodes=this.uni.size()+this.count;	//σύνολο
	
	String s="";
	
	
	s+="Πλήθος Κόμβων Λύσης :"+this.astarList.size()+"\n";
	int sum=this.uni.size()+this.count;
	s+="Πλήθος Κόμβων που εξετάστηκαν : "+sum+"\n";
	s+="Συνολική Απόσταση :"+sumDist+"  Km   Συνολικός Χρόνος : "+sumTime+" mins\n";
	s+="\n Διαδρομή \n";
	s+="------------\n";

	for(Place p:astarList)
	{
		s+=p.getName()+" -> ";
	}
			
	s=s.substring(0, s.length() - 4)+"\n";
	
	return s;
}
	
//τα ίδια αλλά ευριστική με βάση το χρόνο
public void findSolution2()
{
	
	int pos=-1;
	
	Map<String, Place> tempmap;	
	for (int i=0;i<places.size();i++)
	{
		if (start.getName().equals(places.get(i).getName()))
		{
			pos=i;
			break;
		}
	}
	
	Place phelp;
	
	uni.add(places.get(pos));
	
	places.get(pos).setVisited(true);
	
	this.count=0;
	int helpindex;
	
	while (!uni.isEmpty()) 
	{
		
		if (prev.size()==0)
		{
			helpindex=0;
		}
		else{helpindex=this.chooseByTime(uni, this.goal,this.prev);}//εδώ η επιλογή από το μέτωπο με βάση το χρόνο
		
		
		phelp=uni.get(helpindex);
        uni.remove(helpindex);
        this.count++;
	
        if (phelp.getName().equals(this.goal.getName()))
    		
		{break;}
	    else
	    {
	    	Neighbours=new  ArrayList<Place>();
//**************************************************************************   		
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
    		
    		double sum1=0.0,sum2=0.0;
    		
    		
    		if (places.get(index3).getVisited())
    		{
    			
    			ArrayList<Place> temp1List=new ArrayList<Place>();
    			ArrayList<Place> temp2List=new ArrayList<Place>();
    			tempmap=new HashMap<>();
    			
    			tempmap.putAll(prev);
    			boolean flag2=true;
    			if (tempmap.containsValue(places.get(index3)))
    			{
    				flag2=false;
    			}
    			
    			//if ((!(tempmap.get(phelp.getName()).equals(places.get(index3)))) && (flag))
    			
    			if (flag2)
    			{
    				 
	    			
	    			tempmap.put(places.get(index3).getName(), phelp);
    			
    			
    			
    			for (Place node = places.get(index3); node != null; node = tempmap.get(node.getName())) 
    			{
    			   
    				
    				temp2List.add(node);
    			}
    		
    			
    			for (Place node = places.get(index3); node != null; node = prev.get(node.getName())) 
    			{
    			    temp1List.add(node);
    			}
 //**********************************************************************************************************************   			
    			for (int j=0;j<temp1List.size()-1;j++)
    			{
    				for (Way w1:ways)
    				{
    					if ((w1.getStart().equals(temp1List.get(j)) && w1.getEnd().equals(temp1List.get(j+1))) || (w1.getStart().equals(temp1List.get(j+1)) && w1.getEnd().equals(temp1List.get(j))))
    					{
    						sum1+=w1.getTime();
    						
    						break;
    					}
    				}
    				    				
    			}
    			
//**********************************************************************************************************************    			
    			
    			 //**********************************************************************************************************************   			
    			for (int j=0;j<temp2List.size()-1;j++)
    			{
    				for (Way w1:ways)
    				{
    					if ((w1.getStart().equals(temp2List.get(j)) && w1.getEnd().equals(temp2List.get(j+1))) || (w1.getStart().equals(temp2List.get(j+1)) && w1.getEnd().equals(temp2List.get(j))))
    					{
    						sum2+=w1.getTime();
    						
    						break;
    					}
    				}
    				    				
    			}
    			
//********************************************************************************************************************** 			
    			
    			if (sum2<sum1)
    			{
    				prev=new HashMap<>();
    				prev.putAll(tempmap);
    				
    				
    			}
    			
    		}//όχι κυκλικά
    			}
    		
    		else {
  		
    		if (!places.get(index3).getVisited())
    		{
    			uni.add(places.get(index3));	
    			places.get(index3).setVisited(true);
    			prev.put(places.get(index3).getName(), phelp);
    		}
    	}//else visited
    		
    	
    	}//for neighbours

    	
}//not goal
        
	}//while


for (Place node = goal; node != null; node = prev.get(node.getName())) 
{
    astarList.add(node);
}

Collections.reverse(astarList);

}

public int chooseByTime(ArrayList<Place> p, Place parent,Map<String,Place> m)
{
	double min=0.0;
	int index=0;
	
	
	
	
		for (int i=0;i<p.size();i++)
		{
			
			ArrayList<Place> helpList=new ArrayList<Place>() ; 
			
				for (Place node = p.get(i); node != null; node = m.get(node.getName())) 
				{
				    helpList.add(node);
				   
				}
			
						
			double sum=0.0;
			
			for (int j=0;j<helpList.size()-1;j++)
			{
				for (Way w1:ways)
				{
					if ((w1.getStart().equals(helpList.get(j)) && w1.getEnd().equals(helpList.get(j+1))) || (w1.getStart().equals(helpList.get(j+1)) && w1.getEnd().equals(helpList.get(j))))
					{
						sum+=w1.getTime();
						
						break;
					}
				}
			}
			
			boolean flag=false;
			double Hd1=0.0;
			
			for (Way w1:ways)
			{
				if ((w1.getStart().equals(p.get(i)) && w1.getEnd().equals(parent)) || (w1.getStart().equals(parent) && w1.getEnd().equals(p.get(i))))
				{
					Hd1=w1.getHtime();
					flag=true;
					break;
				}}
			
			
			if (flag)
			{
				if (i==0)
				{
					min=Hd1+sum;
					index=i;
				}
				else if ((Hd1+sum)<min)
				{
					min=Hd1+sum;
					index=i;
				}
			}
			
			else
			{
						
			
			
			
			Way tempway =new Way(" ",p.get(i),parent,1.0,0.5);
			
			if (i==0)
			{
				min=tempway.getHtime()+sum;
				index=i;
			}
			else if ((tempway.getHtime()+sum)<min)
			{
				min=tempway.getHtime()+sum;
				index=i;
			}
			}	
	}
		
	return index;}

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
	
	
	
	


