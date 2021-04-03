import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class DataGui extends JFrame implements ActionListener{
	private JPanel panel1,panel2;
	private JLabel mainLabel;
	private JButton but1,but2;
	private ArrayList<Place> places;
	private ArrayList<Way> ways;
	private File filename;
	
	public DataGui(ArrayList<Place> p,ArrayList<Way> w,File f)
	{
		this.filename=f;
		places=p;
		ways=w;
		panel1=new JPanel(new BorderLayout(50,50));//main Panel
		panel2=new JPanel(new GridLayout(0,2,50,35));
		but1=new JButton("Προσθήκη");
		but2=new JButton("Ενημέρωση");
		panel2.add(but1);panel2.add(but2);
		mainLabel=new JLabel("Επεξεργασία Δεδομένων)");
		mainLabel.setFont(new Font("Courier New", Font.BOLD,25));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(mainLabel, BorderLayout.NORTH);
		panel1.add(panel2,BorderLayout.CENTER);
		JLabel temp=new JLabel("  Ιωάννης Αλμαλής");
		temp.setFont(new Font("Courier New", Font.BOLD,15));
		panel1.add(temp, BorderLayout.SOUTH);
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
		//this.setSize(450,200);
		this.setResizable(false);
		this.setTitle("Planning Project - Data Manipulation");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==but1) {
			if (this.places.isEmpty())
			{JOptionPane.showMessageDialog(null, "Δε φορτώθηκε Αρχείο Δεδομένων");}
			else{new AddGui(places,ways,filename);}//για προσθήκη
			}
			
		else if (e.getSource()==but2){
			if (this.places.isEmpty())
			{JOptionPane.showMessageDialog(null, "Δε φορτώθηκε Αρχείο Δεδομένων");}
			else{new EditGui(places,ways,filename);}//για ενημέρωση
			
			}}
	
	

}
