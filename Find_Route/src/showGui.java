import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

public class showGui extends JFrame implements ActionListener{
	
	private JPanel panel1,panel2,panel3;
	private JLabel mainLabel,label1,label2;
	private JComboBox<String> importedCombo,importedCombo2;
	private JButton but1,but2;
	private JTextArea myText,myText2;
	private ArrayList<Place> places;
	private ArrayList<Way> ways;
	
	
	
	
	public showGui(ArrayList<Place> p,ArrayList<Way> w)
	{
		places=p;
		ways=w;
		
		panel1=new JPanel(new BorderLayout(0,80));//main Panel
		panel2=new JPanel(new GridLayout(0,2,50,35)); 
		but1=new JButton("Προβολή");
		myText=new JTextArea(3,10);
		myText.setEditable(false);
		
		
		JScrollPane scroll = new JScrollPane(myText);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		mainLabel=new JLabel("Εύρεση Περιοχής-Οδών (Δρόμων)");
		mainLabel.setFont(new Font("Courier New", Font.BOLD,25));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(mainLabel, BorderLayout.NORTH);
		
		label1=new JLabel("Περιοχή");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		importedCombo=new JComboBox<String>();
		importedCombo2=new JComboBox<String>();
		
		for (Place p1:places)
		{
			importedCombo.addItem(p1.getName());
			importedCombo2.addItem(p1.getName());
		}
		
		but2=new JButton("Προβολή Οδών");
		myText2=new JTextArea(5,10);
		myText2.setEditable(false);
		JScrollPane scroll2 = new JScrollPane(myText2);
	    scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    label2=new JLabel("Οδός - Δρόμος");
	    label2.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel2.add(label1); panel2.add(label2);
		panel2.add(importedCombo);panel2.add(importedCombo2);
		panel2.add(but1);panel2.add(but2);
		//panel2.add(scroll);panel2.add(scroll2);
		
		panel1.add(panel2,BorderLayout.CENTER);
		
		JLabel temp=new JLabel("  Ιωάννης Αλμαλής");
		temp.setFont(new Font("Courier New", Font.BOLD,15));
		
		JLabel temp1=new JLabel("Αποτελέσματα Περιοχών");
		temp1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel temp2=new JLabel("Αποτελέσματα Δρόμων");
		temp2.setHorizontalAlignment(SwingConstants.CENTER);

		
		
		JPanel helpPanel1=new JPanel(new GridLayout(0,2,50,1));
		helpPanel1.add(temp1);helpPanel1.add(temp2);helpPanel1.add(scroll);helpPanel1.add(scroll2);helpPanel1.add(temp);
		panel1.add(helpPanel1, BorderLayout.SOUTH);
		 
		JPanel helpPanel2=new JPanel();
		panel1.add(helpPanel2,BorderLayout.WEST);
		JPanel helpPanel3=new JPanel();
		panel1.add(helpPanel3,BorderLayout.EAST);
		
		
		panel1.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		panel2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		but1.addActionListener(this);
		but2.addActionListener(this);
		
		
		this.setContentPane(panel1);
		this.pack();
		//this.setSize(400,450);
		this.setResizable(false);
		this.setTitle("Planning Project - View");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);}
		
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==but1) {//προβολή περιοχής
			myText.setEditable(false);
			int placehelp=importedCombo.getSelectedIndex();
			myText.setText(places.get(placehelp).showDetails());
			}
			
		else if (e.getSource()==but2){//προβολή όλων δρόμων που συμμετέχει η περιοχή
			
			int placehelp=importedCombo2.getSelectedIndex();
			String helpName=places.get(placehelp).getName();
			String s="";
			myText2.setEditable(false);
			for (Way p1:ways)
			{
			if ((p1.getStart().getName().equals(helpName)) || (p1.getEnd().getName().equals(helpName)))
			{
				s+=p1.showDetails();
				myText2.setText(s);}}}}}
				
			