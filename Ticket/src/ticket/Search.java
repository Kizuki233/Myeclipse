package ticket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;

public class Search {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Search();
				} catch (Exception e) {
					e.printStackTrace();}
		}});
	}

	public Search() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u67E5\u8BE2\u5165\u53E3");
		frame.setBounds(100, 100, 901, 406);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		JLabel label = new JLabel("\u59CB\u53D1\u5730\uFF1A");
		label.setBounds(10, 20, 56, 27);
		panel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(56, 23, 66, 21);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("\u7EC8\u70B9\u7AD9\uFF1A");
		label_1.setBounds(154, 26, 54, 15);
		panel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(203, 23, 66, 21);
		panel.add(textField_1);
		
		JLabel label_2 = new JLabel("\u65E5\u671F\uFF1A");
		label_2.setBounds(300, 26, 41, 15);
		panel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(334, 23, 103, 21);
		panel.add(textField_2);
		
		JLabel label_3 = new JLabel("\uFF08\u683C\u5F0F\u4E3A\u5E74-\u6708-\u65E5 \u4F8B\uFF1A1995-05-02\uFF09");
		label_3.setBounds(439, 23, 206, 21);
		panel.add(label_3);
		
		JButton button = new JButton("\u6B63\u5E38\u67E5\u8BE2");
		button.setBounds(726, 16, 103, 34);
		panel.add(button);
		
		JLabel checi = new JLabel("\u8F66\u6B21\uFF1A");
		checi.setBounds(20, 63, 41, 27);
		panel.add(checi);
		
		textField_3 = new JTextField();
		textField_3.setBounds(56, 66, 66, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton button2 = new JButton("\u8F66\u6B21\u67E5\u8BE2");
		button2.setBounds(176, 51, 86, 37);
		panel.add(button2);
		
		JPanel jp = new JPanel();
		jp.setBounds(0, 112, 895, 256);
		panel.add(jp);
		jp.setLayout(null);
		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
			    jp.removeAll();
				JTable table;
				Vector<String> columnNames = new Vector<String>();
				columnNames.add("车次");
				columnNames.add("列车类型");
				columnNames.add("日期");
				columnNames.add("出发站");
				columnNames.add("下一站");
				columnNames.add("最终站");
				columnNames.add("到站时间");
				columnNames.add("开车时间");
				columnNames.add("路线");
				columnNames.add("费用");
				 Vector<Vector<Object>> dataVector = new Vector<Vector<Object>>(); 	 
			     try {
				        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
				        Connection connect = DriverManager.getConnection(
				        		"jdbc:mysql://127.0.0.1/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","479400");
				              //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
				        Statement stmt = connect.createStatement();
				        ResultSet rs = stmt.executeQuery("select distinct trainnumber,trainid,date,sname,nextname,endsta,arrivedate,statrttime,rid,money from V_search where trainnumber ='"+textField_3.getText()+"' order by arrivesort");
				        while(rs.next()){
				        	Vector<Object> vec = new Vector<Object>();
				            for(int i=1;i<=10;i++){
				        	    vec.add(rs.getObject(i));
				            }
				        	dataVector.add(vec);
				        }  
			            table=new JTable(dataVector,columnNames);  
			            
			            JScrollPane jsp;
			            jsp=new JScrollPane(table);
			            jsp.setPreferredSize(new Dimension(300,300));
			            jsp.setBounds(0, 0, 895, 256);
			            jp.add(jsp);
			            
			            rs.close();
			            stmt.close();
			            connect.close();
				      }
				      catch (Exception ex) {
				        System.out.print("get data error!");
				        ex.printStackTrace();
				      }

		}});	
	
	button.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e){
            jp.removeAll();
			JTable table;
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("车次");
			columnNames.add("列车类型");
			columnNames.add("日期");
			columnNames.add("出发站");
			columnNames.add("目的站");
			columnNames.add("最终站");
			columnNames.add("开车时间");
			columnNames.add("到站时间");
			columnNames.add("路线");
			columnNames.add("费用");
			 Vector<Vector<Object>> dataVector = new Vector<Vector<Object>>(); 	 
		     try {
			        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
			        Connection connect = DriverManager.getConnection(
			        		"jdbc:mysql://127.0.0.1/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","479400");
			              //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
			        
			        Statement stmt = connect.createStatement();
			        ResultSet rs = stmt.executeQuery("select distinct v1.trainnumber,v1.trainid,v1.date,v1.sname,v2.sname,v1.endsta,v1.statrttime,v2.arrivedate,v1.rid,v2.money-v1.money as cost from V_search v1,V_search v2 where v1.date='"+textField_2.getText()+"'and v1.city='"+textField.getText()+"' and v2.city='"+textField_1.getText()+"' and v1.rid=v2.rid and v2.arrivesort>v1.arrivesort;" );
			        while(rs.next()){ 
			        	Vector<Object> vec = new Vector<Object>();
			            for(int i=1;i<=10;i++){
			        	    vec.add(rs.getObject(i));
			            }
			        	dataVector.add(vec);
			        }  
		            table=new JTable(dataVector,columnNames);  
		            table.updateUI();
		            JScrollPane jsp;
		            jsp=new JScrollPane(table);
		            jsp.setPreferredSize(new Dimension(300,300));
		            jsp.setBounds(0, 0, 900, 280);
		            jp.add(jsp);
		            
		            rs.close();
		            stmt.close();
		            connect.close();
			      }
			      catch (Exception ex) {
			        System.out.print("get data error!");
			        ex.printStackTrace();
			      }
          }});
}	
}


	

