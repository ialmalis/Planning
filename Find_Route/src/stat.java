import java.awt.BorderLayout;
import java.awt.Font;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class stat extends JFrame{
	
	private ArrayList<Place> places;
	private ArrayList<Way> ways;	
	private static DecimalFormat df2 = new DecimalFormat("#.###");
	private JTextArea myText;
	private JPanel panel1,panel2,panel3;
	JLabel mainLabel;
	
	
	public stat(ArrayList<Place> p,ArrayList<Way> w)
	{
		df2.setRoundingMode(RoundingMode.DOWN);
		
		
		this.places=p;
		this.ways=w;
		
		int n1=0,n2=0,n3=0,n4=0,n4b=0,n5=0,n5b=0;
		int a1=0,a2=0,a3=0,a4=0,a4b=0,a5=0,a5b=0;
		double s_BFS=0.0,s_DFS=0.0,s_Best=0.0,s_Astar=0.0,s_UNI=0.0,s1=0.0,s2=0.0;
		double t_BFS=0.0,t_DFS=0.0,t_Best=0.0,t_Astar=0.0,t_UNI=0.0,t1=0.0,t2=0.0;
		int k=0;
		
		myBfs ob1;myDfs ob2;BestFS ob3;Astar ob4;Astar ob4b; Unicost ob5;Unicost ob5b;
		String s;
		
		for (Place p1:places)//όλοι οι συνδυασμοί
		{
			for (Place p2:places)
			{
				if (!p1.equals(p2))
				{
					k++;
					
					ob5=new Unicost(places,ways,p1,p2);//απόσταση
					ob5.findSolution();
					s=ob5.show();
					s_UNI+=ob5.getSumDist();
					t_UNI+=ob5.getSumTime();
					n5+=ob5.getSumNodes();
					a5+=ob5.getallNodes();
					
					ob5b=new Unicost(places,ways,p1,p2);//χρόνος
					ob5b.findSolution2();
					s=ob5b.show();
					s2+=ob5b.getSumDist();
					t2+=ob5b.getSumTime();
					n5b+=ob5b.getSumNodes();
					a5b+=ob5b.getallNodes();
										
					ob1=new myBfs(places,ways,p1,p2);
					ob1.findSolution();
					s=ob1.show();
					s_BFS+=ob1.getSumDist();
					t_BFS+=ob1.getSumTime();
					n1+=ob1.getSumNodes();
					a1+=ob1.getallNodes();
					
					ob2=new myDfs(places,ways,p1,p2);
					ob2.findSolution();
					s=ob2.show();
					s_DFS+=ob2.getSumDist();
					t_DFS+=ob2.getSumTime();
					n2+=ob2.getSumNodes();
					a2+=ob2.getallNodes();
					
					ob3=new BestFS(places,ways,p1,p2);
					ob3.findSolution();
					s=ob3.show();
					s_Best+=ob3.getSumDist();
					t_Best+=ob3.getSumTime();
					n3+=ob3.getSumNodes();
					a3+=ob3.getallNodes();
					
					
					ob4=new Astar(places,ways,p1,p2);//απόσταση
					ob4.findSolution();
					s=ob4.show();
					s_Astar+=ob4.getSumDist();
					t_Astar+=ob4.getSumTime();
					n4+=ob4.getSumNodes();
					a4+=ob4.getallNodes();
					
					
					ob4b=new Astar(places,ways,p1,p2);//χρόνος
					ob4b.findSolution2();
					s=ob4b.show();
					s1+=ob4b.getSumDist();
					t1+=ob4b.getSumTime();
					n4b+=ob4b.getSumNodes();
					a4b+=ob4b.getallNodes();
				
				}
				
			}
		}//for
		
		
		String text="Μέσος όρος  BFS κόμβων λύσης: "+df2.format(n1/k)+ "  Mέσος όρος κόμβων εξέτασης: "+df2.format(a1/k)+"\n";
		text+="Μέση Απόσταση : "+df2.format(s_BFS/k)+" Km   Μέσος Χρόνος : "+df2.format(t_BFS/k)+" min \n";
		text+=".......................................\n";
		text+="Μέσος όρος  DFS κόμβων λύσης: "+df2.format(n2/k)+ "  Mέσος όρος κόμβων εξέτασης: "+df2.format(a2/k)+"\n";
		text+="Μέση Απόσταση : "+df2.format(s_DFS/k)+" Km   Μέσος Χρόνος : "+df2.format(t_DFS/k)+" min \n";
		text+=".......................................\n";
		
		text+="Μέσος όρος  Best FS (Km) κόμβων λύσης: "+df2.format(n3/k)+ "  Mέσος όρος κόμβων εξέτασης: "+df2.format(a3/k)+"\n";
		text+="Μέση Απόσταση : "+df2.format(s_Best/k)+" Km   Μέσος Χρόνος : "+df2.format(t_Best/k)+" min \n";
		text+=".......................................\n";
				
		text+="Μέσος όρος  A* (Km) κόμβων λύσης: "+df2.format(n4/k)+ "  Mέσος όρος κόμβων εξέτασης: "+df2.format(a4/k)+"\n";
		text+="Μέση Απόσταση : "+df2.format(s_Astar/k)+" Km   Μέσος Χρόνος : "+df2.format(t_Astar/k)+" min \n";
		text+=".......................................\n";
	
		text+="Μέσος όρος  A* (Min) κόμβων λύσης: "+df2.format(n4b/k)+ "  Mέσος όρος κόμβων εξέτασης: "+df2.format(a4b/k)+"\n";
		text+="Μέση Απόσταση : "+df2.format(s1/k)+" Km   Μέσος Χρόνος : "+df2.format(t1/k)+" min \n";
		text+=".......................................\n";
		
		
		text+="Μέσος όρος  Unicost (Km) κόμβων λύσης: "+df2.format(n5/k)+ "  Mέσος όρος κόμβων εξέτασης: "+df2.format(a5/k)+"\n";
		text+="Μέση Απόσταση : "+df2.format(s_UNI/k)+" Km   Μέσος Χρόνος : "+df2.format(t_UNI/k)+" min \n";
		text+=".......................................\n";
		
		text+="Μέσος όρος  Unicost (min) κόμβων λύσης: "+df2.format(n5b/k)+ "  Mέσος όρος κόμβων εξέτασης: "+df2.format(a5b/k)+"\n";
		text+="Μέση Απόσταση : "+df2.format(s2/k)+" Km   Μέσος Χρόνος : "+df2.format(t2/k)+" min \n";
		text+=".......................................\n";
		
		panel1=new JPanel(new BorderLayout(0,40));//main Panel
		myText=new JTextArea(15,40);
		myText.setEditable(false);
		myText.setText(text);
		JScrollPane scroll = new JScrollPane(myText);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    
	    mainLabel=new JLabel("Στατιστικά Αλγορίθμων");
		mainLabel.setFont(new Font("Courier New", Font.BOLD,20));mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(mainLabel,BorderLayout.NORTH);
			
		panel1.add(scroll,BorderLayout.CENTER);
		JPanel helpPanel2=new JPanel();
		panel1.add(helpPanel2,BorderLayout.WEST);
		JPanel helpPanel3=new JPanel();
		panel1.add(helpPanel3,BorderLayout.EAST);
		panel1.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		JLabel temp=new JLabel("  Ιωάννης Αλμαλής");
		temp.setFont(new Font("Courier New", Font.BOLD,15));
		panel1.add(temp, BorderLayout.SOUTH); 
		
		this.setContentPane(panel1);
		this.pack();
		//this.setSize(400,450);
		this.setResizable(false);
		this.setTitle("Planning Project - Statistics");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}

}
