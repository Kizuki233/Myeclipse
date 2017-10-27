package ticket;
//车站 textfiled 0~2 button 0~3
//车次 textfiled 9~17 24 Newbutton 0~3
//路线 textfiled 18~23 button 12~15
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Manager {
	private JFrame frame;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField nexsta;
	private JTextField distance;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager window = new Manager();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});}

	public Manager() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u7BA1\u7406\u5458\u7A97\u53E3");
		frame.setBounds(100, 100, 515, 397);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 509, 369);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5217\u8F66\u65F6\u523B\u67E5\u8BE2\u7CFB\u7EDF");
		lblNewLabel.setBounds(142, 5, 212, 56);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("华文隶书", Font.PLAIN, 25));
		tabbedPane.setBounds(0, 65, 509, 304);
		panel.add(tabbedPane);
		JPanel pane4 = new JPanel();
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setLayout(null);
		
		JPanel pane2 = new JPanel();
		tabbedPane.addTab("车站", pane2);
		pane2.setLayout(null);
		
		JLabel lblid = new JLabel("\u8F66\u7AD9id\uFF1A");
		lblid.setFont(new Font("宋体", Font.PLAIN, 15));
		lblid.setBounds(36, 17, 70, 45);
		pane2.add(lblid);
		
		textField = new JTextField();
		textField.setBounds(125, 28, 66, 23);
		pane2.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u8F66\u7AD9\u540D\u5B57\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(31, 72, 75, 23);
		pane2.add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(125, 72, 66, 23);
		pane2.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("\u57CE\u5E02\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		label_1.setBounds(41, 117, 50, 30);
		pane2.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(125, 121, 66, 23);
		pane2.add(textField_2);
		
//车站		
		JButton button = new JButton("\u67E5\u627E");
		button.setBounds(351, 29, 75, 33);
		pane2.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
		    	        Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
		    	        Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
		     	        //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		    	        Statement stmt = connect.createStatement();
		    	        ResultSet rs = stmt.executeQuery("select * from Station where sid='"+textField.getText()+"'");
		    	        if(rs.next()){
		    	        	textField_1.setText(rs.getString("sname"));
		    	        	textField_2.setText(rs.getString("city"));	    	             
		    	             stmt.close();
		    	  	         connect.close();
		    	            }
		    	        else{
		    	        	JOptionPane.showMessageDialog(null, "不存在该记录！", "提示", JOptionPane.ERROR_MESSAGE);
		    	        	textField.setText("");
				   	        textField_1.setText("");
				   	        textField_2.setText("");	
		    	        	textField.requestFocus();
		    	         }
		    	 }
		    	  catch(Exception ex){
		              ex.printStackTrace();
		           }
		    	}}
		 );
		
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.setBounds(350, 72, 76, 33);
		pane2.add(button_1);
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
		   	         Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
		   	         Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
			         //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		   	         Statement stmt = connect.createStatement();
		   	        int a=stmt.executeUpdate("Update Station set sname='"+textField_1.getText()+"',city='"+textField_2.getText()+"' where sid='"+textField.getText()+"'" );
		   	        if(a==1)
					{
						JOptionPane.showMessageDialog(null,"记录修改完毕！", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"修改失败", "提示", JOptionPane.ERROR_MESSAGE);
					}		            
		   	        textField.setText("");
		   	        textField_1.setText("");
		   	        textField_2.setText("");		   	   
		   	        stmt.close();
			        connect.close();
		    	  }
		    	  catch(Exception ex){
		            ex.printStackTrace();}
		  }});
		
		
		JButton button_2 = new JButton("\u589E\u52A0");
		button_2.setFont(new Font("宋体", Font.PLAIN, 15));
		button_2.setBounds(351, 116, 75, 33);
		pane2.add(button_2);
		button_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
		   	         Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
		   	         Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
			         //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		   	         Statement stmt = connect.createStatement();
		   	         int a = stmt.executeUpdate("insert into Station(sid,sname,city )values('"+textField.getText()+"','"+textField_1.getText()+"','"+textField_2.getText()+"');");
					if(a==1)
					{
						JOptionPane.showMessageDialog(null,"已成功添加", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"添加失败", "提示", JOptionPane.ERROR_MESSAGE);
					}
		   	        textField.setText("");
		   	        textField_1.setText("");
		   	        textField_2.setText("");	   	   
		   	        stmt.close();
			        connect.close();
		    	  }
		    	  catch(Exception ex){
		            ex.printStackTrace();}
		    	}});
		
		JButton button_3 = new JButton("\u5220\u9664");
		button_3.setBounds(351, 159, 75, 33);
		pane2.add(button_3);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
		    	        Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
		    	        Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
		     	        //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		    	        Statement stmt = connect.createStatement();
		    	        int i=stmt.executeUpdate("delete  from Station where sid = '" + textField.getText() + "'");
						if(i==1){
							JOptionPane.showMessageDialog(null,"删除车站记录成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null,"没有该车站、请重新输入！", "提示", JOptionPane.ERROR_MESSAGE);
						}
						textField.setText("");
			   	        textField_1.setText("");
			   	        textField_2.setText("");	
						textField.requestFocus();
						stmt.close();
					}
		    	  catch(Exception ex){
		              ex.printStackTrace();
		           }
		  }});
//车站结束		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\u8F66\u6B21", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(251, 5, 1, 1);
		panel_3.setLayout(null);
		panel_2.add(panel_3);
		
		JLabel label_5 = new JLabel("\u8F66\u7AD9id\uFF1A");
		label_5.setFont(new Font("宋体", Font.PLAIN, 15));
		label_5.setBounds(36, 17, 70, 45);
		panel_3.add(label_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(125, 28, 66, 23);
		panel_3.add(textField_6);
		
		JButton button_8 = new JButton("\u67E5\u627E");
		button_8.setBounds(351, 29, 75, 33);
		panel_3.add(button_8);
		
		JLabel label_6 = new JLabel("\u8F66\u7AD9\u540D\u5B57\uFF1A");
		label_6.setFont(new Font("宋体", Font.PLAIN, 15));
		label_6.setBounds(31, 72, 75, 23);
		panel_3.add(label_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(125, 72, 66, 23);
		panel_3.add(textField_7);
		
		JLabel label_7 = new JLabel("\u57CE\u5E02\uFF1A");
		label_7.setFont(new Font("宋体", Font.PLAIN, 15));
		label_7.setBounds(41, 117, 50, 30);
		panel_3.add(label_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(125, 121, 66, 23);
		panel_3.add(textField_8);
		
		JButton button_9 = new JButton("\u589E\u52A0");
		button_9.setBounds(350, 72, 76, 33);
		panel_3.add(button_9);
		
		JButton button_10 = new JButton("\u5220\u9664");
		button_10.setFont(new Font("宋体", Font.PLAIN, 15));
		button_10.setBounds(351, 116, 75, 33);
		panel_3.add(button_10);
		
		JButton button_11 = new JButton("\u4FEE\u6539");
		button_11.setBounds(351, 159, 75, 33);
		panel_3.add(button_11);
		
		JLabel lblNewLabel_1 = new JLabel("\u8F66\u6B21\uFF1A");
		lblNewLabel_1.setBounds(10, 21, 54, 21);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5217\u8F66\u7C7B\u578B\uFF1A");
		lblNewLabel_2.setBounds(10, 57, 68, 18);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u65E5\u671F\uFF1A");
		lblNewLabel_3.setBounds(10, 95, 54, 21);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u59CB\u53D1\u5730\uFF1A");
		lblNewLabel_4.setBounds(10, 134, 54, 21);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u7EC8\u70B9\u7AD9\uFF1A");
		lblNewLabel_5.setBounds(10, 180, 54, 18);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\u53D1\u8F66\u65F6\u95F4\uFF1A");
		lblNewLabel_6.setBounds(10, 224, 68, 18);
		panel_2.add(lblNewLabel_6);
		
		textField_9 = new JTextField();
		textField_9.setBounds(88, 18, 66, 21);
		panel_2.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(88, 54, 66, 21);
		panel_2.add(textField_10);
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setBounds(88, 95, 134, 21);
		panel_2.add(textField_11);
		textField_11.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setBounds(88, 137, 66, 21);
		panel_2.add(textField_12);
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		textField_13.setBounds(88, 179, 66, 21);
		panel_2.add(textField_13);
		textField_13.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setBounds(88, 224, 110, 21);
		panel_2.add(textField_14);
		textField_14.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("\u5230\u8FBE\u65F6\u95F4\uFF1A");
		lblNewLabel_7.setBounds(243, 16, 68, 20);
		panel_2.add(lblNewLabel_7);
		
		textField_15 = new JTextField();
		textField_15.setBounds(321, 21, 105, 21);
		panel_2.add(textField_15);
		textField_15.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("\u8DEF\u7EBF\uFF1A");
		lblNewLabel_8.setBounds(269, 56, 54, 21);
		panel_2.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("\u8DDD\u79BB\uFF1A");
		lblNewLabel_9.setBounds(269, 96, 54, 18);
		panel_2.add(lblNewLabel_9);
		
		textField_16 = new JTextField();
		textField_16.setBounds(321, 56, 66, 21);
		panel_2.add(textField_16);
		textField_16.setColumns(10);
		
		textField_17 = new JTextField();
		textField_17.setBounds(321, 95, 66, 21);
		panel_2.add(textField_17);
		textField_17.setColumns(10);
//车次		
		JButton btnNewButton = new JButton("\u67E5\u627E");
		btnNewButton.setBounds(218, 175, 93, 23);
		panel_2.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
		    	        Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
		    	        Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
		     	        //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		    	        Statement stmt = connect.createStatement();
		    	        ResultSet rs = stmt.executeQuery("select * from Traininfo where trainnumber='"+textField_9.getText()+"'");
		    	        if(rs.next()){
		    	        	textField_10.setText(rs.getString("trainid"));
		    	        	textField_11.setText(rs.getString("date"));
		    	        	textField_12.setText(rs.getString("beginsta"));
		    	        	textField_13.setText(rs.getString("endsta"));
		    	        	textField_14.setText(rs.getString("begintime"));
		    	        	textField_15.setText(rs.getString("endtime"));
		    	        	textField_16.setText(rs.getString("rid"));
		    	        	textField_17.setText(rs.getString("distance"));
		    	        	textField_24.setText(rs.getString("stationnum"));
		    	             stmt.close();
		    	  	         connect.close();
		    	            }
		    	        else{
		    	        	JOptionPane.showMessageDialog(null, "不存在该记录！", "提示", JOptionPane.ERROR_MESSAGE);
		    	        	textField_9.setText("");
		    	        	textField_9.requestFocus();
		    	         }
		    	 }
		    	  catch(Exception ex){
		              ex.printStackTrace();
		           }
		    	}}
		          
		);
		
		JButton btnNewButton_1 = new JButton("\u589E\u52A0");
		btnNewButton_1.setBounds(218, 222, 93, 23);
		panel_2.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
		   	         Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
		   	         Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
			         //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		   	         Statement stmt = connect.createStatement();
		   	         int a = stmt.executeUpdate("insert into Traininfo(trainnumber , trainid , date , beginsta ,endsta , begintime , endtime , rid , distance , stationnum)values('"+textField_9.getText()+"','"+textField_10.getText()+"','"+textField_11.getText()+"','"+textField_12.getText()+"'," +
							"'"+textField_13.getText()+"','"+textField_14.getText()+"','"+textField_15.getText()+"'," +
							"'"+textField_16.getText()+"','"+textField_17.getText()+"','"+textField_24.getText()+"')");
					System.out.println(a);
					if(a==1)
					{
						JOptionPane.showMessageDialog(textField_9,"已成功添加", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(textField_9,"添加失败", "提示", JOptionPane.ERROR_MESSAGE);
					}
		   	        textField_9.setText("");
		   	        textField_10.setText("");
		   	        textField_11.setText("");
		   	        textField_12.setText("");
		   	        textField_13.setText("");
		            textField_14.setText("");
		   	        textField_15.setText("");
		   	        textField_16.setText("");
		   	        textField_17.setText("");
		            textField_24.setText("");		   	   
		   	        stmt.close();
			        connect.close();
		    	  }
		    	  catch(Exception ex){
		            ex.printStackTrace();}
		    	}});
		
		JButton btnNewButton_2 = new JButton("\u4FEE\u6539");
		btnNewButton_2.setBounds(346, 178, 93, 23);
		panel_2.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		   	         Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
		   	         Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
			         //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		   	         Statement stmt = connect.createStatement();
		   	        int a=stmt.executeUpdate("Update Traininfo set trainid='"+textField_10.getText()+"',date='"+textField_11.getText()+"',beginsta='"+textField_12.getText()+"',endsta='"+textField_13.getText()+"',begintime='"+textField_14.getText()+"',rid='"+textField_16.getText()+"',distance='"+textField_17.getText()+"',stationnum='"+textField_24.getText()+"' where trainnumber='"+textField_9.getText()+"'" );
		   	         if(a==1)
					{
						JOptionPane.showMessageDialog(null,"记录修改完毕！", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"修改失败", "提示", JOptionPane.ERROR_MESSAGE);
					}	
		   	       textField_9.setText("");
		   	       textField_10.setText("");
		   	       textField_11.setText("");
		   	       textField_12.setText("");
		   	       textField_13.setText("");
		           textField_14.setText("");
		   	       textField_15.setText("");
		   	       textField_16.setText("");
		   	       textField_17.setText("");
		           textField_24.setText("");
		   	        stmt.close();
			        connect.close();
		    	  }
		    	  catch(Exception ex){
		            ex.printStackTrace();}
		   }});
		
		
		JButton btnNewButton_3 = new JButton("\u5220\u9664");
		btnNewButton_3.setBounds(346, 222, 93, 23);
		panel_2.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
		    	        Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
		    	        Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
		     	        //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		    	        Statement stmt = connect.createStatement();
		    	        int i=stmt.executeUpdate("delete  from Traininfo where trainnumber = '" + textField_9.getText() + "'");
						if(i==1){
							JOptionPane.showMessageDialog(textField_9,"删除车次记录成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(textField_9,"没有该车次、请重新输入！", "提示", JOptionPane.ERROR_MESSAGE);
						}
						     textField_9.setText("");
					   	     textField_10.setText("");
					   	     textField_11.setText("");
					   	     textField_12.setText("");
					   	     textField_13.setText("");
					         textField_14.setText("");
					   	     textField_15.setText("");
					   	     textField_16.setText("");
					   	     textField_17.setText("");
					         textField_24.setText("");
							 textField_9.requestFocus();
						     stmt.close();
					}
		    	  catch(Exception ex){
		              ex.printStackTrace();
		           }
		  }});
//车次结束		
		JLabel lblNewLabel_16 = new JLabel("\u5230\u7AD9\u4E2A\u6570\uFF1A");
		lblNewLabel_16.setBounds(249, 135, 74, 18);
		panel_2.add(lblNewLabel_16);
		
		textField_24 = new JTextField();
		textField_24.setBounds(321, 134, 66, 21);
		panel_2.add(textField_24);
		textField_24.setColumns(10);
		tabbedPane.addTab("路线",pane4);
		pane4.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(251, 5, 1, 1);
		panel_1.setLayout(null);
		pane4.add(panel_1);
		
		JLabel label_2 = new JLabel("\u8F66\u7AD9id\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 15));
		label_2.setBounds(36, 17, 70, 45);
		panel_1.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(125, 28, 66, 23);
		panel_1.add(textField_3);
		
		JButton button_4 = new JButton("\u67E5\u627E");
		button_4.setBounds(351, 29, 75, 33);
		panel_1.add(button_4);
		
		JLabel label_3 = new JLabel("\u8F66\u7AD9\u540D\u5B57\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 15));
		label_3.setBounds(31, 72, 75, 23);
		panel_1.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(125, 72, 66, 23);
		panel_1.add(textField_4);
		
		JLabel label_4 = new JLabel("\u57CE\u5E02\uFF1A");
		label_4.setFont(new Font("宋体", Font.PLAIN, 15));
		label_4.setBounds(41, 117, 50, 30);
		panel_1.add(label_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(125, 121, 66, 23);
		panel_1.add(textField_5);
		
		JButton button_5 = new JButton("\u589E\u52A0");
		button_5.setBounds(350, 72, 76, 33);
		panel_1.add(button_5);
		
		JButton button_6 = new JButton("\u5220\u9664");
		button_6.setFont(new Font("宋体", Font.PLAIN, 15));
		button_6.setBounds(351, 116, 75, 33);
		panel_1.add(button_6);
		
		JButton button_7 = new JButton("\u4FEE\u6539");
		button_7.setBounds(351, 159, 75, 33);
		panel_1.add(button_7);
		
		JLabel lblNewLabel_10 = new JLabel("\u8DEF\u7EBFid\uFF1A");
		lblNewLabel_10.setBounds(23, 22, 54, 22);
		pane4.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("\u7B2C\u51E0\u4E2A\u7AD9\uFF1A");
		lblNewLabel_11.setBounds(23, 63, 65, 22);
		pane4.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("\u8F66\u7AD9\u540D\u5B57\uFF1A");
		lblNewLabel_12.setBounds(23, 106, 65, 22);
		pane4.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("\u5230\u8FBE\u65E5\u671F\uFF1A");
		lblNewLabel_13.setBounds(23, 153, 65, 26);
		pane4.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("\u53D1\u8F66\u65F6\u95F4\uFF1A");
		lblNewLabel_14.setBounds(23, 189, 65, 22);
		pane4.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("\u5230\u8FBE\u65F6\u95F4\uFF1A");
		lblNewLabel_15.setBounds(23, 221, 65, 30);
		pane4.add(lblNewLabel_15);
		
		textField_18 = new JTextField();
		textField_18.setBounds(109, 23, 66, 21);
		pane4.add(textField_18);
		textField_18.setColumns(10);
		
		textField_19 = new JTextField();
		textField_19.setBounds(109, 64, 66, 21);
		pane4.add(textField_19);
		textField_19.setColumns(10);
		
		textField_20 = new JTextField();
		textField_20.setColumns(10);
		textField_20.setBounds(109, 107, 66, 21);
		pane4.add(textField_20);
		
		textField_21 = new JTextField();
		textField_21.setColumns(10);
		textField_21.setBounds(109, 153, 96, 24);
		pane4.add(textField_21);
		
		textField_22 = new JTextField();
		textField_22.setColumns(10);
		textField_22.setBounds(109, 189, 96, 27);
		pane4.add(textField_22);
		
		textField_23 = new JTextField();
		textField_23.setColumns(10);
		textField_23.setBounds(109, 226, 96, 25);
		pane4.add(textField_23);
		
		nexsta = new JTextField();
		nexsta.setBounds(393, 23, 66, 21);
		pane4.add(nexsta);
		nexsta.setColumns(10);
		
		distance = new JTextField();
		distance.setBounds(393, 64, 66, 21);
		pane4.add(distance);
		distance.setColumns(10);
//路线		
		JButton button_12 = new JButton("\u67E5\u627E");
		button_12.setBounds(271, 106, 78, 43);
		pane4.add(button_12);
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
		    	        Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
		    	        Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
		     	        //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		    	        Statement stmt = connect.createStatement();
		    	        ResultSet rs = stmt.executeQuery("select * from Route where rid='"+textField_18.getText()+"' and arrivesort ='"+textField_19.getText()+"'");
		    	        if(rs.next()){
		    	        	textField_20.setText(rs.getString("sname"));	
		    	        	textField_21.setText(rs.getString("arrivedate"));	
		    	        	textField_22.setText(rs.getString("statrttime"));	
		    	        	textField_23.setText(rs.getString("stoptime"));	
		    	        	nexsta.setText(rs.getString("nextname"));	
		    	        	distance.setText(rs.getString("distance"));	
		    	 
		    	             stmt.close();
		    	  	         connect.close();
		    	            }
		    	        else{
		    	        	JOptionPane.showMessageDialog(null, "不存在该记录！", "提示", JOptionPane.ERROR_MESSAGE);
		    	        	textField_18.setText("");
				   	        textField_19.setText("");
				   	        textField_20.setText("");
				   	        textField_21.setText("");
				   	        textField_22.setText("");
				            textField_23.setText("");
				            nexsta.setText("");
				            distance.setText("");
		    	        	textField_18.requestFocus();
		    	         }
		    	 }
		    	  catch(Exception ex){
		              ex.printStackTrace();
		           }
		    	}}
		          
		);
		
		JButton button_13 = new JButton("\u4FEE\u6539");
		button_13.setBounds(381, 106, 78, 43);
		pane4.add(button_13);
		button_13.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
		   	         Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
		   	         Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
			         //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		   	         Statement stmt = connect.createStatement();
		   	        int a=stmt.executeUpdate("Update Route set sname='"+textField_20.getText()+"',arrivedate='"+textField_21.getText()+"',statrttime='"+textField_22.getText()+"',stoptime='"+textField_23.getText()+"',nextname='"+nexsta.getText()+"',distance='"+distance.getText()+"' where rid='"+textField_18.getText()+"'and arrivesort='"+textField_19.getText()+"'");
		   	        if(a==1)
					{
						JOptionPane.showMessageDialog(null,"记录修改完毕！", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"修改失败", "提示", JOptionPane.ERROR_MESSAGE);
					}	
		   	        textField_18.setText("");
		   	        textField_19.setText("");
		   	        textField_20.setText("");
		   	        textField_21.setText("");
		   	        textField_22.setText("");
		   	        textField_23.setText("");
		   	        nexsta.setText("");
		            distance.setText("");
		   	        stmt.close();
			        connect.close();
		    	  }
		    	  catch(Exception ex){
		            ex.printStackTrace();}
		    	}});
		
		
		JButton button_14 = new JButton("\u589E\u52A0");
		button_14.setBounds(271, 181, 78, 39);
		pane4.add(button_14);
		button_14.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
		   	         Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
		   	         Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
			         //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		   	         Statement stmt = connect.createStatement();
		   	         int a = stmt.executeUpdate("insert into Route(rid , arrivesort , sname , arrivedate ,statrttime , stoptime,nextname,distance)values('"+textField_18.getText()+"','"+textField_19.getText()+"','"+textField_20.getText()+"','"+textField_21.getText()+"'," +
							"'"+textField_22.getText()+"','"+textField_23.getText()+"','"+nexsta.getText()+"','"+distance.getText()+"')");
					System.out.println(a);
					if(a==1)
					{
						JOptionPane.showMessageDialog(null,"已成功添加", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"添加失败", "提示", JOptionPane.ERROR_MESSAGE);
					}
		   	        textField_18.setText("");
		   	        textField_19.setText("");
		   	        textField_20.setText("");
		   	        textField_21.setText("");
		   	        textField_22.setText("");
		            textField_23.setText("");
		            nexsta.setText("");
		            distance.setText("");
		   	        stmt.close();
			        connect.close();
		    	  }
		    	  catch(Exception ex){
		            ex.printStackTrace();}
		    	}});
		
		JButton button_15 = new JButton("\u5220\u9664");
		button_15.setBounds(381, 183, 78, 35);
		pane4.add(button_15);
		
		JLabel lblNewLabel_17 = new JLabel("\u4E0B\u4E00\u7AD9\uFF1A");
		lblNewLabel_17.setBounds(312, 22, 71, 22);
		pane4.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("\u79BB\u59CB\u53D1\u5730\u8DDD\u79BB\uFF1A");
		lblNewLabel_18.setBounds(281, 64, 96, 21);
		pane4.add(lblNewLabel_18);
		
		
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
		    	        Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
		    	        Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
		     	        //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
		    	        Statement stmt = connect.createStatement();
		    	        int i=stmt.executeUpdate("delete  from Route where rid = '" + textField_18.getText() + "'and arrivesort='"+textField_19.getText()+"'");
						if(i==1){
							JOptionPane.showMessageDialog(null,"删除路线记录成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							JOptionPane.showMessageDialog(null,"没有该路线、请重新输入！", "提示", JOptionPane.ERROR_MESSAGE);
							textField_18.setText("");
				   	        textField_19.setText("");
				   	        textField_20.setText("");
				   	        textField_21.setText("");
				   	        textField_22.setText("");
				            textField_23.setText("");
				            nexsta.setText("");
				            distance.setText("");
							textField_18.requestFocus();
						}
						stmt.close();
					}
		    	  catch(Exception ex){
		              ex.printStackTrace();
		           }
		  }});
//路线结束
	}
}
