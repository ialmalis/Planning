import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class EditGui extends JFrame implements ActionListener{
	
	private JTextField t1,t2,t3,t6,t7,t8;
	private ArrayList<Place> places;
	private ArrayList<Way> ways;
	private JLabel label1,label2,label3,label4,label5,label6,label7,label8,label9,label10;
	private JButton but1,but2,but3,but4,but5;
	private JPanel panel1,panel2,panel3;
	private int index=-1,index2=-1;
	//private static final String TextFILE = "a_all.txt";
	private JComboBox<String> importedCombo,importedCombo2;
	private File filename;
	
	public EditGui(ArrayList<Place> p,ArrayList<Way> w,File f)
	{ 
		
		this.filename=f;
		places=p;
		ways=w;
		
		importedCombo=new JComboBox<String>();
		importedCombo2=new JComboBox<String>();
		for (Place p1:places)
		{
			importedCombo.addItem(p1.getName());
			importedCombo2.addItem(p1.getName());
		}
		label1=new JLabel("Όνομα Περιοχής");label3=new JLabel("Γεωγ/κό Μήκος");label2=new JLabel("Γεωγ/κό Πλάτος");
		label4=new JLabel("Περιοχή Αναχ/σης");label5=new JLabel("Περιοχή ’φιξης");label6=new JLabel("Ονομασία Δρόμου");
		label7=new JLabel("Απόσταση");label8=new JLabel("Χρόνος");
		t1=new JTextField();t2=new JTextField();t3=new JTextField();t6=new JTextField();t7=new JTextField();t8=new JTextField();
		but1=new JButton("Ενημέρωση Περιοχής");
		but2=new JButton("Ενημέρωση Δρόμου");
		but3=new JButton("Απόθήκευση Στοιχείων");
		but4=new JButton("Αναζήτηση");
		but5=new JButton("Αναζήτηση");
		
		label9=new JLabel("Περιοχή");
		label10=new JLabel("Οδός - Δρόμος");
		
		
		label9.setFont(new Font("Courier New", Font.BOLD,20));label9.setHorizontalAlignment(SwingConstants.CENTER);
		label10.setFont(new Font("Courier New", Font.BOLD,20));label10.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel1=new JPanel(new BorderLayout(0,60));//main Panel
		panel2=new JPanel(new GridLayout(0,2,50,35));
		panel2.add(label9);panel2.add(label10);
		panel1.add(panel2, BorderLayout.NORTH);
		
		panel3=new JPanel(new GridLayout(0,4,50,35)); 
		
		panel3.add(label1);panel3.add(t1);panel3.add(label4);panel3.add(importedCombo);
		panel3.add(but4);panel3.add(new JLabel("             "));panel3.add(label5);panel3.add(importedCombo2);
		panel3.add(label2);panel3.add(t2);panel3.add(but5);panel3.add(new JLabel("             "));
		panel3.add(label3);panel3.add(t3);panel3.add(label6);panel3.add(t6);
		panel3.add(but1);panel3.add(new JLabel("             "));panel3.add(label7);panel3.add(t7);
		panel3.add(new JLabel("             "));panel3.add(new JLabel("             "));panel3.add(label8);panel3.add(t8);
		panel3.add(new JLabel("             "));panel3.add(new JLabel("             "));panel3.add(but2);panel3.add(new JLabel("             "));
		
		panel1.add(panel3,BorderLayout.CENTER);
		JPanel helpPanel2=new JPanel();
		panel1.add(helpPanel2,BorderLayout.WEST);
		JPanel helpPanel3=new JPanel();
		panel1.add(helpPanel3,BorderLayout.EAST);
		
		JPanel helppanel4=new JPanel(new GridLayout(0,4,3,5));
		JLabel temp=new JLabel("  Ιωάννης Αλμαλής");
		temp.setFont(new Font("Courier New", Font.BOLD,15));
		helppanel4.add(temp);helppanel4.add(new JLabel("             "));helppanel4.add(but3);helppanel4.add(new JLabel("             "));
		helppanel4.add(new JLabel("             "));
		panel1.add(helppanel4, BorderLayout.SOUTH);
		
		panel1.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		panel2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		but1.addActionListener(this);
		but2.addActionListener(this);
		but3.addActionListener(this);
		but4.addActionListener(this);
		but5.addActionListener(this);
		

		
		this.setContentPane(panel1);
		this.pack();
		//this.setSize(400,450);
		this.setResizable(false);
		this.setTitle("Planning Project - Edit");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);}
	
	
	public void actionPerformed(ActionEvent e)
	{
		//this.index=-1;
		if (e.getSource()==but4) {//αναζήτηση πόλης
			
			
			t2.setText("");
			t3.setText("");
			String s = t1.getText();
			s=s.replaceAll("\\s+","");//απαλοιφή κενών
			t1.setText(s);
			//System.out.println(s);
			
			for (int i=0;i<places.size();i++)//εύρεση περιοχής
			{
				if (places.get(i).getName().equals(s))
				{
					this.index=i;
					break;
				}
			}
		try {	
			t2.setText(places.get(this.index).getCor1()+"");
			t3.setText(places.get(this.index).getCor2()+"");}
		catch (IndexOutOfBoundsException error) {
            JOptionPane.showMessageDialog(null, "Δεν υπάρχει η περιοχή");
		}
	}
			
		else if (e.getSource()==but5) {//αναζήτηση δρόμων
			this.index2=-1;
			int placehelp=importedCombo.getSelectedIndex();
			int placehelp2=importedCombo2.getSelectedIndex();
			String s = places.get(placehelp).getName();
			String s2 = places.get(placehelp2).getName();
			
			
			t6.setText("");
			t7.setText("");
			t8.setText("");
			
			for (int i=0;i<ways.size();i++)
			{
				if (((ways.get(i).getStart().getName().equals(s))&&(ways.get(i).getEnd().getName().equals(s2)))|| ((ways.get(i).getStart().getName().equals(s2))&&(ways.get(i).getEnd().getName().equals(s))))
				{
					this.index2=i;
					break;
				}
			}
			try {
			t6.setText(ways.get(this.index2).getTitle()+"");t7.setText(ways.get(this.index2).getDistance()+"");
			t8.setText(ways.get(this.index2).getTime()+"");}
			catch (IndexOutOfBoundsException error) {
	            JOptionPane.showMessageDialog(null, "Δεν υπάρχει το επιλεγμένο δρομολόγιο");
			}}
		
		else if (e.getSource()==but1) { 
			String snull="Λάθος εισαγωγή δεδομένων";
			try {
			String thename=places.get(this.index).getName();
			String newname=t1.getText();
			
			
			//Αφού αλλάξαμε το όνομα της περιοχής, αλλαγή και στους δρόμους που την περιέχουν
			if (!(thename.equals(newname)))
			{
				for (Way w :ways)
				{
					if (w.getStart().getName().equals(thename))
					{
						w.getStart().setName(newname);
					}
					if (w.getEnd().getName().equals(thename))
					{
						w.getEnd().setName(newname);
					}	
						
					
				}
				
				
				
			}
			
			
			
			places.get(this.index).setName(newname);
			
			places.get(this.index).setCor1(Double.parseDouble(t2.getText()));
			places.get(this.index).setCor2(Double.parseDouble(t3.getText()));
			
			this.index=-1;
			
			t1.setText("");t2.setText("");t3.setText("");
			//Ανανέωση των comboboxes αφού αλλάξαμε όνομα περιοχής
			this.dispose();new EditGui(places,ways,filename);
			
			}
			catch (IndexOutOfBoundsException error) {
	            JOptionPane.showMessageDialog(null, "Δεν υπάρχει η επιλεγμένη περιοχή");
			}
			catch (NumberFormatException Snull)
			{
				JOptionPane.showMessageDialog(null, snull);
			}}
			
			
		else if (e.getSource()==but2) {
			String snull="Λάθος εισαγωγή δεδομένων";
			try {
			int placehelp=importedCombo.getSelectedIndex();
			int placehelp2=importedCombo2.getSelectedIndex();
			ways.get(this.index2).setStart(places.get(placehelp));	
			ways.get(this.index2).setEnd(places.get(placehelp2));	
			ways.get(this.index2).setTitle(t6.getText());
			
			ways.get(this.index2).setDistance(Double.parseDouble(t7.getText()));
			ways.get(this.index2).setTime(Double.parseDouble(t8.getText()));
			
			this.index2=-1;t6.setText("");t7.setText("");t8.setText("");}
			catch (NumberFormatException Snull)
			{
				JOptionPane.showMessageDialog(null, snull);
			}
			catch (IndexOutOfBoundsException error) {
	            JOptionPane.showMessageDialog(null, "Δεν υπάρχει το επιλεγμένο δρομολόγιο");
			}}
		
		
else if (e.getSource()==but3){//αποθήκευση αλλαγών
			
			FileWriter writer;
			try {
				writer = new FileWriter(this.filename, false);
				
				int sizePlaces=places.size();
				
				String s=sizePlaces+"\n";
				writer.write(s);
				
				for (int i=0;i<sizePlaces;i++) {
					writer.write(places.get(i).getName()+"\n");
					writer.write(places.get(i).getCor1()+"\n");
					writer.write(places.get(i).getCor2()+"\n");}
					
				int sizeWays=ways.size();
				//System.out.println(ways.size());
				s=sizeWays+"\n";
				writer.write(s);
				
				for (int i=0;i<sizeWays;i++) {
					writer.write(ways.get(i).getTitle()+"\n");
					writer.write(ways.get(i).getStart().getName()+"\n");
					writer.write(ways.get(i).getEnd().getName()+"\n");
					writer.write(ways.get(i).getDistance()+"\n");
					writer.write(ways.get(i).getTime()+"\n");
				}
				
	            writer.close();
	            
	            //this.dispose();
	           // new centralGui();
	            
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}}}
			
			
		