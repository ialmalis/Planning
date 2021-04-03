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

public class AddGui  extends JFrame implements ActionListener{
	
	private ArrayList<Place> places;
	private ArrayList<Way> ways;
	private JPanel panel1,panel2;
	private JButton but1,but2,but3;
	private JLabel label1,label2;
	private JTextField textname,textcor1,textcor2,textway,textitle,textdist,texttime;
	private JComboBox<String> importedCombo,importedCombo2;
	//private static final String TextFILE = "a_all.txt";
	private File filename;
	
	public AddGui(ArrayList<Place> p,ArrayList<Way> w,File f) {
		
		this.filename=f;
		places=p;
		ways=w;
		if (places.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Δε φορτώθηκε Αρχείο Δεδομένων");
			this.dispose();
		}
		importedCombo=new JComboBox<String>();
		importedCombo2=new JComboBox<String>();
		but1=new JButton("Προσθήκη Περιοχής");
		but2=new JButton("Προσθήκη Δρόμου");
		but3=new JButton("Απόθήκευση Στοιχείων");
		label1=new JLabel("Περιοχή");
		label2=new JLabel("Οδός - Δρόμος");
		
		
		label1.setFont(new Font("Courier New", Font.BOLD,20));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("Courier New", Font.BOLD,20));
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (Place p1:places)
		{
			importedCombo.addItem(p1.getName());
			importedCombo2.addItem(p1.getName());
		}
		textname=new JTextField();textitle=new JTextField();textcor1=new JTextField();textcor2=new JTextField();
		textway=new JTextField();textdist=new JTextField();texttime=new JTextField();
		
		panel1=new JPanel(new BorderLayout(0,60));//main Panel
		JPanel helppanel=new JPanel(new GridLayout(0,2,50,35)); 
		helppanel.add(label1);helppanel.add(label2);
		panel1.add(helppanel, BorderLayout.NORTH);
		
		panel2=new JPanel(new GridLayout(0,4,50,35)); 
		panel2.add(new JLabel("Περιοχή "));panel2.add(textname);panel2.add(new JLabel("Ονομασία Δρόμου "));panel2.add(textitle);
		panel2.add(new JLabel("Γεωγ/κό πλάτος"));panel2.add(textcor1);panel2.add(new JLabel("Αναχώρηση"));panel2.add(importedCombo);
		panel2.add(new JLabel("Γεωγ/κό μήκος"));panel2.add(textcor2);panel2.add(new JLabel("’φιξη "));panel2.add(importedCombo2);
		panel2.add(new JLabel("             "));panel2.add(new JLabel("             "));panel2.add(new JLabel("Απόσταση"));panel2.add(textdist);
		panel2.add(new JLabel("             "));panel2.add(new JLabel("             "));panel2.add(new JLabel("Χρόνος"));panel2.add(texttime);
		panel2.add(new JLabel("             "));panel2.add(but1);panel2.add(new JLabel("             "));panel2.add(but2);
		panel1.add(panel2,BorderLayout.CENTER);
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
		
		
		
		
		
		
		this.setContentPane(panel1);
		this.pack();
		//this.setSize(400,450);
		this.setResizable(false);
		this.setTitle("Planning Project - ADD");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);}
		
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==but1) {
			int k =places.size();
			String snull="Λάθος εισαγωγή στοιχείων";
			try{double v1=Double.parseDouble(textcor1.getText());
			double v2=Double.parseDouble(textcor2.getText());
			places.add(new Place(k,textname.getText(),v1,v2));
			textname.setText("");textcor2.setText("");textcor1.setText("");}
			catch (NumberFormatException Snull)
			{
				JOptionPane.showMessageDialog(null, snull);
			}
			//Ανανέωση των δρόμων
			this.dispose();new AddGui(places,ways,this.filename);
			
			} 
			
		else if (e.getSource()==but2){
			String snull="Λάθος εισαγωγή στοιχείων";
			int placehelp=importedCombo.getSelectedIndex();
			int placehelp2=importedCombo2.getSelectedIndex();
			try {
			double v1=Double.parseDouble(textdist.getText());
			double v2=Double.parseDouble(texttime.getText());
			ways.add(new Way(textitle.getText(),places.get(placehelp),places.get(placehelp2),v1,v2) );}
			catch (NumberFormatException Snull)
			{
				JOptionPane.showMessageDialog(null, snull);
			}
			textitle.setText("");textdist.setText("");texttime.setText("");
			importedCombo.setSelectedIndex(0);importedCombo2.setSelectedIndex(0);
			}
		
		else if (e.getSource()==but3){
			
			FileWriter writer;
			try {
				writer = new FileWriter(this.filename, false);
				
				int sizePlaces=places.size();
				//System.out.println(places.size());
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
			
			
		