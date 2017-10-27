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

	//控件居中对齐，水平垂直间隔均为10
	FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER,20,10);
    //设置字体
	Font tset = new Font("楷体",0,15);
	private JLabel ll = new JLabel("输入账号");
	private JLabel lp = new JLabel("输入密码");
	public JTextField tfId = new JTextField(15);
	public JPasswordField pfPw = new JPasswordField(11);
	private JButton btLogin = new JButton("登录");
	private JButton btExit = new JButton("退出");
	private JPanel jpl = new JPanel();
	public String stx;
	private String spass;
	private String mpass;
	char[] spw;
	
	//构造函数
	public GUIloging(){
	super("Welcome");
	//设置字体颜色
	ll.setFont(tset);
	lp.setFont(tset);
	tfId.setFont(tset);
	btLogin.setFont(tset);
	btExit.setFont(tset);
	tfId.setForeground(Color.gray);
	
	jpl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,2),"学籍管理系统",TitledBorder.LEFT,TitledBorder.TOP,new java.awt.Font("楷体",0,20)));
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
	
	//设置居中
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	String hint1 = "学号/管理员账号";
    this.requestFocus();
	tfId.setText(hint1);
	tfId.addFocusListener(new MyFocusListener(hint1,tfId));
	//事件触发
	
    
    btLogin.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		//判断登录的是学生还是管理员
    		try {
    			stx = tfId.getText();
    			char[] password=pfPw.getPassword();
    		    String spw = new String(password);
    			
		        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
		        Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
    	         //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		        Statement stmt = connect.createStatement();
		       
		        ResultSet rs = stmt.executeQuery("select scode from stuinfo where sno='"+stx+"'" );
		        if(rs.next()){
		          spass = rs.getString("scode");
		         if(spw.equals(spass)){
		        	new Gmain(stx);
		        	dispose();
		         }
		         else{
		        	 JOptionPane.showMessageDialog(null,"密码输入错误，请重新输入！", "提示", JOptionPane.ERROR_MESSAGE);
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
		        	 JOptionPane.showMessageDialog(null,"密码输入错误，请重新输入！", "提示", JOptionPane.ERROR_MESSAGE);
		        	 tfId.setText("");
		        	 pfPw.setText("");
		        	 tfId.requestFocus();  }
		        }
		         else{
		        	 JOptionPane.showMessageDialog(null,"该用户不存在，请重新输入！", "提示", JOptionPane.ERROR_MESSAGE);
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
	    public void focusLost(FocusEvent e) {//失去焦点的时候,判断如果为空,就显示提示文字
	        if(jtf.getText().equals("")){
	            jtf.setText(info);
	        }
	    }
	    
}



