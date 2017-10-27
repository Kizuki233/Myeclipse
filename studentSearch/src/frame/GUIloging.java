package frame;
import frame.Gmain;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import java.sql.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;


public class GUIloging extends JFrame{

	//�ؼ����ж��룬ˮƽ��ֱ�����Ϊ10
	FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER,20,10);
    //��������
	Font tset = new Font("����",0,15);
	private JLabel ll = new JLabel("�����˺�");
	private JLabel lp = new JLabel("��������");
	public JTextField tfId = new JTextField(15);
	public JPasswordField pfPw = new JPasswordField(11);
	private JButton btLogin = new JButton("��¼");
	private JButton btExit = new JButton("�˳�");
	private JPanel jpl = new JPanel();
	public String stx;
	private String spass;
	private String mpass;
	char[] spw;
	
	//���캯��
	public GUIloging(){
	super("Welcome");
	//����������ɫ
	ll.setFont(tset);
	lp.setFont(tset);
	tfId.setFont(tset);
	btLogin.setFont(tset);
	btExit.setFont(tset);
	tfId.setForeground(Color.gray);
	
	jpl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,2),"ѧ������ϵͳ",TitledBorder.LEFT,TitledBorder.TOP,new java.awt.Font("����",0,20)));
    this.add(jpl);
  
	jpl.setLayout(flowlayout);
	jpl.add(ll);
	jpl.add(tfId);
	jpl.add(lp);
	jpl.add(pfPw);
	jpl.add(btLogin);
	jpl.add(btExit);
	

	this.setSize(250,180);
	this.setVisible(true);
	
	//���þ���
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	String hint1 = "ѧ��/����Ա�˺�";
    this.requestFocus();
	tfId.setText(hint1);
	tfId.addFocusListener(new MyFocusListener(hint1,tfId));
	//�¼�����
	
    
    btLogin.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		//�жϵ�¼����ѧ�����ǹ���Ա
    		try {
    			stx = tfId.getText();
    			char[] password=pfPw.getPassword();
    		    String spw = new String(password);
    			
		        Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
		        Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
    	         //����URLΪ   jdbc:mysql//��������ַ/���ݿ���  �������2�������ֱ��ǵ�½�û���������
		        Statement stmt = connect.createStatement();
		       
		        ResultSet rs = stmt.executeQuery("select scode from stuinfo where sno='"+stx+"'" );
		        if(rs.next()){
		          spass = rs.getString("scode");
		         if(spw.equals(spass)){
		        	new Gmain(stx);
		        	dispose();
		         }
		         else{
		        	 JOptionPane.showMessageDialog(null,"��������������������룡", "��ʾ", JOptionPane.ERROR_MESSAGE);
		        	 tfId.setText("");
		        	 pfPw.setText("");
		        	 tfId.requestFocus(); 
		        }}
		        //System.out.println(stx+' '+spw+' '+spass);
		        
		       else{
		        ResultSet rst = stmt.executeQuery("select mcode from manager where mno='"+stx+"'");
		        if(rst.next()){
		         mpass = rst.getString("mcode");
		         if(spw.equals(mpass)){
		             new GUIAdmmain(stx);
		             dispose();
		         }
		         else{
		        	 JOptionPane.showMessageDialog(null,"��������������������룡", "��ʾ", JOptionPane.ERROR_MESSAGE);
		        	 tfId.setText("");
		        	 pfPw.setText("");
		        	 tfId.requestFocus();  }
		        }
		         else{
		        	 JOptionPane.showMessageDialog(null,"���û������ڣ����������룡", "��ʾ", JOptionPane.ERROR_MESSAGE);
		        	 tfId.setText("");
		        	 pfPw.setText("");
		        	 tfId.requestFocus();	
		         }
		        
		     }
		       
		        stmt.close();
    	        connect.close();
    		}catch(Exception ex){  
    			ex.printStackTrace();
    		}
    	}
    });
    btExit.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		System.exit(0);
    	}
    });

    }
}

class MyFocusListener implements FocusListener{
	String info;
	JTextField jtf;
	public MyFocusListener(String info,JTextField jtf){
		this.info = info;
		this.jtf = jtf;
	}
	@Override
	public void focusGained(FocusEvent e){
	        if(jtf.getText().equals(info)){
	            jtf.setText("");
	}
}
	 @Override
	    public void focusLost(FocusEvent e) {//ʧȥ�����ʱ��,�ж����Ϊ��,����ʾ��ʾ����
	        if(jtf.getText().equals("")){
	            jtf.setText(info);
	        }
	    }
	    
}



