import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class AlgGui extends JFrame implements ActionListener{
	
	private JPanel panel1,panel2;
	private JLabel mainLabel,label1,label2;
	private JComboBox<String> importedCombo,importedCombo2;
	private JButton but1;
	private JTextArea myText;
	private ArrayList<Place> places;
	private ArrayList<Way> ways;
	private JRadioButton rb1,rb2;
	private ButtonGroup bg;
	private boolean flag;
	
public AlgGui(ArrayList<Place> p,ArrayList<Way> w)	
{
	places=p;
	ways=w;
	panel1=new JPanel(new BorderLayout(0,30));//main Panel
	but1=new JButton("Εκτέλεση");
	bg=new ButtonGroup();
	rb1=new JRadioButton("Ηeuristic : Απόσταση");
	rb2=new JRadioButton("Ηeuristic : Χρόνος");
	
	bg.add(rb1);
	bg.add(rb2);
	
	rb1.setSelected(true);
	myText=new JTextArea(15,40);
	myText.setEditable(false);
	importedCombo=new JComboBox<String>();
	importedCombo2=new JComboBox<String>();
	for (Place p1:places)
	{
		importedCombo.addItem(p1.getName());
		importedCombo2.addItem(p1.getName());
	}
	JScrollPane scroll = new JScrollPane(myText);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	label1=new JLabel("Από :");label2=new JLabel("Προς :");mainLabel=new JLabel("Εκτέλεση Αλγορίθμων");
	mainLabel.setFont(new Font("Courier New", Font.BOLD,20));mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
	panel1.add(mainLabel,BorderLayout.NORTH);
	panel2=new JPanel(new GridLayout(0,2,0,20));
	panel2.add(label1);panel2.add(importedCombo);panel2.add(label2);panel2.add(importedCombo2);
	panel2.add(rb1);panel2.add(rb2);
	panel2.add(but1);//panel2.add(new JLabel("             "));
	//panel2.add(myText);
	panel1.add(panel2,BorderLayout.CENTER);
	JPanel helpPanel2=new JPanel();
	panel1.add(helpPanel2,BorderLayout.WEST);
	JPanel helpPanel3=new JPanel();
	panel1.add(helpPanel3,BorderLayout.EAST);
	panel1.setBorder(new EtchedBorder(EtchedBorder.RAISED));
	panel2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
	JPanel helpPaneldown=new JPanel(); 
	helpPaneldown.add(scroll);
	//JLabel temp=new JLabel("  Ιωάννης Αλμαλής");
	//temp.setFont(new Font("Courier New", Font.BOLD,15));
	panel1.add(helpPaneldown, BorderLayout.SOUTH); 
	but1.addActionListener(this);
	
	this.setContentPane(panel1);
	this.pack();
	//this.setSize(400,450);
	this.setResizable(false);
	this.setTitle("Planning Project - Running Algorithms");
	this.setLocationRelativeTo(null);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
}
	
public void actionPerformed(ActionEvent e)
{
	if (e.getSource()==but1)
	{
		
		int placehelp=importedCombo.getSelectedIndex();
		int placehelp2=importedCombo2.getSelectedIndex();
		myBfs Object_bfs=new myBfs(places,ways,places.get(placehelp),places.get(placehelp2));
		
		//BFS
		long startTime = System.nanoTime();
		Object_bfs.findSolution();
		long stopTime = System.nanoTime();
		long dif=stopTime-startTime;
		String text=Object_bfs.show()+"\n"+ "Time needed for BFS in nsec : "+String.valueOf(dif)+"\n";
		
		myText.setText(text);
		
		//DFS
		myDfs Object_dfs=new myDfs(places,ways,places.get(placehelp),places.get(placehelp2));
		startTime = System.nanoTime();
		Object_dfs.findSolution();
		stopTime = System.nanoTime();
		dif=stopTime-startTime;
		text=Object_dfs.show()+"\n"+ "Time needed for DFS in nsec : "+String.valueOf(dif)+"\n";
		
		myText.append(text);
		
		//BESTFS
		BestFS obj=new BestFS (places,ways,places.get(placehelp),places.get(placehelp2));
		startTime = System.nanoTime();
		obj.findSolution();
		stopTime = System.nanoTime();
		dif=stopTime-startTime;
		text=obj.show()+"\n"+ "Time needed for Best FS in nsec : "+String.valueOf(dif)+"\n";
		
		myText.append(text);
		
		//UNIFORM Km
		Unicost obj4=new Unicost (places,ways,places.get(placehelp),places.get(placehelp2));
		startTime = System.nanoTime();
		obj4.findSolution();
		stopTime = System.nanoTime();
		dif=stopTime-startTime;
		text="\nUnicost (distance) \n";
		text+="-------------------\n";
		text+=obj4.show()+"\n"+ "Time needed for Unicost(Km) in nsec : "+String.valueOf(dif)+"\n";
		
		myText.append(text);
		
		//UNIFORM min
		Unicost obj5=new Unicost (places,ways,places.get(placehelp),places.get(placehelp2));
		startTime = System.nanoTime();
		obj5.findSolution2();
		stopTime = System.nanoTime();
		dif=stopTime-startTime;
		text="\nUnicost (Time) \n";
		text+="-------------------\n";
		text+=obj5.show()+"\n"+ "Time needed for Unicost(min) in nsec : "+String.valueOf(dif)+"\n";
		
		myText.append(text);
		
		//A* Km
		if (rb1.isSelected())
		{
		Astar obj2=new Astar (places,ways,places.get(placehelp),places.get(placehelp2));
		startTime = System.nanoTime();
		obj2.findSolution();
		stopTime = System.nanoTime();
		dif=stopTime-startTime;
		text=" \nA* with h(distance) \n";
		text+="-------------------\n";
		text+=obj2.show()+"\n"+ "Time needed for A*(Km) in nsec : "+String.valueOf(dif)+"\n";
		
		myText.append(text);
		
		}
		
		//A* min(Time)
		else if (rb2.isSelected()){
		Astar obj3=new Astar (places,ways,places.get(placehelp),places.get(placehelp2));
		startTime = System.nanoTime();
		obj3.findSolution2();
		stopTime = System.nanoTime();
		dif=stopTime-startTime;
		text=" \nA* with h(Time) \n";
		text+="-------------------\n";
		text+=obj3.show()+"\n"+ "Time needed for A*(min) in nsec : "+String.valueOf(dif)+"\n";
		
		myText.append(text);
		
		}
	}
}
	
	
	
	
	

}
