import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class centralGui extends JFrame implements ActionListener{
	
	private JPanel myPanel,helpPanel1;
	private JButton bt1,bt2,bt3,bt4,bt5;
	private JLabel route,print,edit,mainLabel,load,stat;
	private ImageIcon im1,im2,im3,im4,im5;
	//private static final String TextFILE = "a_all.txt";
	private File filename;
	private ArrayList<Place> places ;
	private ArrayList<Way> ways ;
	private JDialog dialog;
	
//***********************************************************************	
	public centralGui()
	{
		
		helpPanel1=new JPanel(new GridLayout(0,4,0,20));
		myPanel=new JPanel(new BorderLayout(10,25));
		
		
		im1=new ImageIcon("route.png");
		route=new JLabel(im1);
		im2=new ImageIcon("show.png");
		print=new JLabel(im2);
		im3=new ImageIcon("edit.png");
		edit=new JLabel(im3);
		im4=new ImageIcon("load.png");
		load=new JLabel(im4);
		im5=new ImageIcon("stat.jpg");
		stat=new JLabel(im5);
		
		
		mainLabel=new JLabel("Κεντρικό Μενού");
		mainLabel.setFont(new Font("Courier New", Font.BOLD,25));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myPanel.add(mainLabel, BorderLayout.NORTH);
		
		bt1=new JButton("Εύρεση Διαδρομής");
		bt2=new JButton("Προβολή Δεδομένων");
		bt3=new JButton("Επεξεργασία Δεδομένων");
		bt4=new JButton("Φόρτωση Δεδομένων");
		bt5=new JButton("Στατιστικά");
		
		helpPanel1.add(load);
		helpPanel1.add(bt4);
		helpPanel1.add(route);
		helpPanel1.add(bt1);
		helpPanel1.add(print);
		helpPanel1.add(bt2);
		helpPanel1.add(edit);
		helpPanel1.add(bt3);
		helpPanel1.add(stat);
		helpPanel1.add(bt5);
		
		
		myPanel.add(helpPanel1,BorderLayout.CENTER);
		JLabel temp=new JLabel("  Ιωάννης Αλμαλής     ");
		temp.setFont(new Font("Courier New", Font.BOLD,17));
		temp.setHorizontalAlignment(SwingConstants.RIGHT);
		myPanel.add(temp, BorderLayout.SOUTH);
		
		// just for fixing the appearance of main windown
		JPanel helpPanel2=new JPanel();
		myPanel.add(helpPanel2,BorderLayout.EAST);
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);
		bt5.addActionListener(this);
		
		this.setContentPane(myPanel);
		this.pack();
		//this.setSize(400,450);
		this.setResizable(false);
		this.setTitle("Planning Project");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.load();//φόρτωση δεδομένων από δίσκο
	}
//***************************************************************************************	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==bt2) {
			if (this.places.isEmpty())
			{JOptionPane.showMessageDialog(null, "Δε φορτώθηκε Αρχείο Δεδομένων");}
			else{new showGui(places,ways);}}//προβολή δεδομένων
			
		else if (e.getSource()==bt3) {
			
			new DataGui(places,ways,filename);}//για ενημέρωση και προσθήκη δρόμων και περιοχών
		else if (e.getSource()==bt4) {
			this.load();}	//φόρτωση δεδομένων
		else if (e.getSource()==bt1) {
			if (this.places.isEmpty())
			{JOptionPane.showMessageDialog(null, "Δε φορτώθηκε Αρχείο Δεδομένων");}
			else{new AlgGui(places,ways);}}//εκτέλεση Αλγορίθμων
		else if (e.getSource()==bt5) {
			if (this.places.isEmpty())
			{JOptionPane.showMessageDialog(null, "Δε φορτώθηκε Αρχείο Δεδομένων");}
			else{
				
				JOptionPane pane = new JOptionPane("Περιμένετε μέχρι να ολοκληρωθεί ο υπολογισμός των Στατιστικών",JOptionPane.INFORMATION_MESSAGE);
				 this.dialog = pane.createDialog("Ενημέρωση Στατιστικών, Αναμένετε......");
				this.dialog.setModal(false);
			     this.dialog.setVisible(true);
				    new Timer(1, new ActionListener() {
				        @Override
				        public void actionPerformed(ActionEvent e) {
				          dialog.setVisible(false);
				        	
				        }
				      }).start();
				
				new stat(places,ways);}}//στατιστικά
			
			
	}
//********************************************************************************		
	//διάβασμα από αρχείο text και ενημέρωση δομών
	public void load()
	{
		String snull="Πρόβλημα στο αρχείο δεδομένων..";
		JFileChooser jfc = new JFileChooser();
	    jfc.showDialog(null,"Επιλέξτε αρχείο δεδομένων..");
	    jfc.setVisible(true);
	    this.filename = jfc.getSelectedFile();
	 		
		places = new ArrayList<Place>();
		ways = new ArrayList<Way>();
		//Initiliazation
		String name="";
		double cor1=0.0;
		double cor2=0.0;
				
		BufferedReader reader;
		try {
			//System.out.println("Reading from text file...");
			reader = new BufferedReader(new FileReader(filename));
			String line;
            line = reader.readLine();
            int numofplaces=Integer.parseInt(line);
         
            for (int i=0;i<numofplaces;i++)
            {
            	for (int k=1;k<=3;k++)
            	{
            		line = reader.readLine();
            		if (k==1)
            		{name=line;	}
            		else if (k==2){	cor1=Double.parseDouble(line);}
            		else{cor2=Double.parseDouble(line);}
            	}
            	
            	this.places.add(new Place(i,name,cor1,cor2));
                        	
            }
//**********************************************************************            
           //Initiliazation
            String title="";
            Place start=null,end=null;
            double dist=0.0;
            double time=0.0; 
            
            line = reader.readLine();
            int numofways=Integer.parseInt(line); 
            
            for (int i=0;i<numofways;i++)
            {
            	for (int k=1;k<=5;k++)
            	{
            		line = reader.readLine();
            		if (k==1)
            		{title=line;	}
            		else if (k==2)
            		{	
            			name=line;
            			
            			for (Place p1:places)
            			{
            				if (p1.getName().equals(name))
            					{start=p1;break;}
            				
            			}
            		            			
            		}
            		
            		else if (k==3)
            		{	
            			name=line;
            			
            			for (Place p1:places)
            			{
            				if (p1.getName().equals(name))
            					{end=p1;break;}
            				
            			}
            		            			
            		}
            		
            		else if (k==4)
            		{	
            			dist=Double.parseDouble(line);}
            			
            		else if (k==5)
            			
            			{time=Double.parseDouble(line);}	
            		            			
            	}
            	this.ways.add(new Way(title,start,end,dist,time));
            		
            		
            		
            	}
 //******************************************************************************           
            reader.close();
            JOptionPane.showMessageDialog(null, "Τα δεδομένα φορτώθηκαν με επιτυχία!");
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NullPointerException Snull)
		{
			JOptionPane.showMessageDialog(null, snull);
		}
		catch (NumberFormatException Snull)
		{
			JOptionPane.showMessageDialog(null, snull);
		}
	}
	
}
	


