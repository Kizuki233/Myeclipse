package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GlobalGUI extends JFrame{
	BorderLayout bdl = new BorderLayout();
	JPanel jp = new JPanel();
	public JPanel jpWest =new JPanel();
	public JPanel jpNorth = new JPanel();
	public JPanel jpSouth = new JPanel();
	public JPanel jpCenter = new JPanel();
	public JLabel tt = new JLabel("ѧ������ϵͳ");
	Font ft1 = new Font("����",0,20);
	Font ft2 = new Font("����",0,13);
	//JButton Goback = new JButton("����");
	JButton Exit = new JButton("�˳�");
	//��������ˮƽ��ֱ���
	GridLayout gl = new GridLayout(6,1,3,10);
	FlowLayout fl = new FlowLayout(1,50,30);
	public GlobalGUI(){
		tt.setFont(ft1);
		//Goback.setFont(ft2);
		Exit.setFont(ft2);
		
		//jpSouth.add(Goback);
		jpSouth.add(Exit);
		
		Exit.setFont(ft2);
		jpNorth.add(tt);
		jp.setLayout(bdl);
		
		this.setSize(800,500);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		
		
		Exit.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e){
	    		System.exit(0);
	    	}
	    });
	
	}
	
	
}

