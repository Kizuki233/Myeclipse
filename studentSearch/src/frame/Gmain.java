package frame;
import frame.GUIloging;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Gmain extends GlobalGUI{
	
	    //static GUImain g;
	    GridLayout gl = new GridLayout(8,1,3,10);
	    private String saccount = null;
		JButton sView = new JButton("查看个人信息");
	    JButton sModify = new JButton("修改个人信息");

	public Gmain(String saccount){
		this.saccount = saccount;
		this.setTitle("学生主界面");
		sView.setFont(ft2);
		sModify.setFont(ft2);
		//sVgrade.setFont(ft2);
		//设置布局
		
		jpWest.setLayout(gl);
		jpSouth.setLayout(fl);
		jpWest.add(sView);
		jpWest.add(sModify);
		
		//设置边距上左下右
		jpWest.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		//北边按钮
		
	    this.add(jpNorth,BorderLayout.NORTH);
     	this.add(jpWest,BorderLayout.WEST);
    	this.add(jpSouth,BorderLayout.SOUTH);
    	this.add(jpCenter,BorderLayout.CENTER);
		
		sView.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				jpCenter.removeAll();
				JTable table;
				Vector<String> columnNames = new Vector<String>();
				columnNames.add("学号");
				columnNames.add("学院");
				columnNames.add("姓名");
				columnNames.add("班级");
				columnNames.add("性别");
				columnNames.add("英语");
				columnNames.add("数学");
				columnNames.add("电话");
				columnNames.add("入学时间");
				 Vector<Vector<Object>> dataVector = new    
		                    Vector<Vector<Object>>(); 	 
				 try {
				        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
				        Connection connect = DriverManager.getConnection(
				        		"jdbc:mysql://127.0.0.1/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","479400");
				              //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
				        Statement stmt = connect.createStatement();
				        ResultSet rs = stmt.executeQuery("select * from stuinfo where sno = '"+ saccount +"'");
				        while(rs.next()){
				        	Vector<Object> vec = new Vector<Object>();
				            for(int i=1;i<=9;i++)
				        	    vec.add(rs.getObject(i));
				        	dataVector.add(vec);
				        
				        }
			            table=new JTable(dataVector,columnNames);  
			            
			            JScrollPane jsp;
			            jsp=new JScrollPane(table);
			            jsp.setPreferredSize(new Dimension(500,300));
			            jpCenter.add(jsp,"Center");
			            rs.close();
			            stmt.close();
			            connect.close();
				      }
				      catch (Exception ex) {
				        System.out.print("get data error!");
				        ex.printStackTrace();
				      }
				 jpCenter.revalidate();
				 jpCenter.repaint();
			}
			

		});
		sModify.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				jpCenter.removeAll();
				jpCenter.revalidate();
				jpCenter.repaint();
			    JPanel jps1 = new JPanel();
				JButton btSave = new JButton("保存");
				btSave.setFont(ft2);
				//JLabel lpw1 = new JLabel("旧密码");
				//lpw1.setFont(ft2);
				//JPasswordField pfpw1 = new JPasswordField(15);
				JLabel lpw2 = new JLabel("新密码:");
				lpw2.setFont(ft2);
				JPasswordField pfpw2 = new JPasswordField();
				JLabel lpw3 = new JLabel("确认密码:");
				lpw3.setFont(ft2);
				JPasswordField pfpw3 = new JPasswordField();

					jps1.setLayout(new GridLayout(7,1,10,10));
					//上左下右
					jps1.setBorder(BorderFactory.createEmptyBorder(5,100,40,380));

					jps1.add(lpw2);
					jps1.add(pfpw2);
					jps1.add(lpw3);
					jps1.add(pfpw3);
					jps1.add(btSave);
					jpCenter.add(jps1);
					
					btSave.addActionListener(new ActionListener()
					{
						
							public void actionPerformed(ActionEvent e){
								//jpCenter.removeAll();
								//jpSouth.removeAll();
								try{
									Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
							        Connection connect = DriverManager.getConnection(
							        		"jdbc:mysql://127.0.0.1/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","479400");
							             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
							        Statement stmt = connect.createStatement();
							        String np = new String(pfpw2.getPassword());
							        String npc = new String(pfpw3.getPassword());
							        if(npc.equals(np)){
							        	stmt.executeUpdate("update stuinfo set scode = '"+npc+"' where sno = '"+ saccount +"'");
							        	System.out.println(npc+" "+saccount+" "+np);
							        	 JOptionPane.showMessageDialog(null, "记录修改完毕！", "提示", JOptionPane.INFORMATION_MESSAGE);
							        }
							        else{
							        	
							        	JOptionPane.showMessageDialog(null, "两次输入的密码不一致，请重新输入！", "提示", JOptionPane.INFORMATION_MESSAGE);
							        	pfpw2.setText("");
							        	pfpw3.setText("");
							        	pfpw2.requestFocus();
							        	
							        }
							        
								}catch (Exception ex) {
							        System.out.print("get data error!");
							        ex.printStackTrace();
							      }
								
							}
						
					});
					 
			}
			
		});
		
	}
}
	
	