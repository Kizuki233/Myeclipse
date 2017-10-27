package frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
public class GUIAdmmain extends GlobalGUI{
	
	static GUIAdmmain s;
	private String account = null;
	JButton aView = new JButton("查看个人信息");
    JButton aModify = new JButton("修改个人信息");
	JButton aVgrade = new JButton("查看/修改学生成绩");
	//JButton aMgrade = new JButton("修改学生成绩");
	JButton aAdd = new JButton("增加学生信息");
	JButton aDelete = new JButton("删除学生信息");
	
    //重写
	//JButton deb = new JButton("删除");
	//JButton am = new JButton("修改");
	JButton mserch = new JButton("查询信息");
		
	GridLayout gl = new GridLayout(8,1,3,10);
	//FlowLayout fl = new FlowLayout(1,10,10);
	JLabel no = new JLabel("学号：");
	JLabel sc = new JLabel("学院：");
	JLabel na = new JLabel("姓名：");
	JLabel cl = new JLabel("班级：");
	JLabel te = new JLabel("电话：");
	JLabel bi = new JLabel("英语：");
	JLabel smathl = new JLabel("数学：");
	JLabel gr = new JLabel("入学时间：");
	JTextField tno = new JTextField(12);
	JTextField tsc = new JTextField();
	JTextField smatht = new JTextField();
	JTextField tna = new JTextField();
	JTextField tcl = new JTextField();
	JTextField tte = new JTextField();
	JTextField tbi = new JTextField();
	JTextField tgr = new JTextField();
	public GUIAdmmain(String account){
		
		this.account = account;
		this.setTitle("管理员主界面");
		
		jpNorth.setLayout(fl);
		jpWest.setLayout(gl);
		jpSouth.setLayout(fl);
		
		aView.setFont(ft2);
		aView.setBorder(BorderFactory.createRaisedBevelBorder());
		aModify.setFont(ft2);
		aModify.setBorder(BorderFactory.createRaisedBevelBorder());
		aVgrade.setFont(ft2);
		aVgrade.setBorder(BorderFactory.createRaisedBevelBorder());
		aAdd.setFont(ft2);
		aAdd.setBorder(BorderFactory.createRaisedBevelBorder());
		aDelete.setFont(ft2);
		aDelete.setBorder(BorderFactory.createRaisedBevelBorder());
		Exit.setBorder(BorderFactory.createRaisedBevelBorder());
		jpWest.add(aView);
		jpWest.add(aModify);
		jpWest.add(aVgrade);
		jpWest.add(aAdd);
		jpWest.add(aDelete);
		//jpWest.add(aMgrade)

		jpWest.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		jp.add(jpNorth,BorderLayout.NORTH);
		jp.add(jpWest,BorderLayout.WEST);
	    jp.add(jpSouth,BorderLayout.SOUTH);
	    jp.add(jpCenter,BorderLayout.CENTER);
		this.add(jp);
		
	    
	    aView.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e){
	    		jpCenter.removeAll();
				jpSouth.removeAll();
				jpCenter.revalidate();
				//jpCenter.validate();
				
			    //jpSouth.validate();
				JTable table;
				Vector<String> columnNames = new Vector<String>();
				columnNames.add("编号");
				
				columnNames.add("姓名");
				
				columnNames.add("性别");
				//columnNames.add("学分");
				 Vector<Vector<Object>> dataVector = new    
	                        Vector<Vector<Object>>(); 	 
				 try {
				        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
				        Connection connect = DriverManager.getConnection(
				        		"jdbc:mysql://127.0.0.1/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","479400");
				             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
				        Statement stmt = connect.createStatement();
				        ResultSet rs = stmt.executeQuery("select * from manager where mno = '"+account+"'");
				        while(rs.next()){
				        	Vector<Object> vec = new Vector<Object>();
				            for(int i=1;i<=3;i++)
				        	    vec.add(rs.getObject(i));
				        	dataVector.add(vec);
				        
				        }
			            table=new JTable(dataVector,columnNames);  
			            
			            JScrollPane jsp;
			            jsp=new JScrollPane(table);
			            //jsp.setHorizontalScrollBarPolicy (JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			            //jsp.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			            jsp.setPreferredSize(new Dimension(500,300));
			            jpCenter.add(jsp);
			            rs.close();
			            stmt.close();
			            connect.close();
			            jpCenter.invalidate();
			            jpCenter.repaint();
			            jpSouth.repaint();
                       
				      }
				      catch (Exception ex) {
				        System.out.print("get data error!");
				        ex.printStackTrace();
				      }
	    	}
	    });
	    aModify.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e){
	    		jpCenter.removeAll();
	    		jpCenter.revalidate();//重绘布局
	    		jpCenter.repaint();//重绘内容
	    		jpSouth.removeAll();
	    		jpSouth.revalidate();
	    		jpSouth.repaint();
	    		//jpCenter.invalidate();
	    		
			    JPanel jps1 = new JPanel();
				JButton btSave = new JButton("保存");
				btSave.setFont(ft2);
				btSave.setBorder(BorderFactory.createRaisedBevelBorder());
				//JLabel lpw1 = new JLabel("旧密码");
				//lpw1.setFont(ft2);
				//JPasswordField pfpw1 = new JPasswordField(15);
				JLabel lpw2 = new JLabel("新密码：");
				lpw2.setFont(ft2);
				JPasswordField pfpw2 = new JPasswordField();
				JLabel lpw3 = new JLabel("确认密码：");
				lpw3.setFont(ft2);
				JPasswordField pfpw3 = new JPasswordField();

					jps1.setLayout(new GridLayout(5,1,10,10));
					//上左下右
					jps1.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
					//jps1.add(lpw1);
					//jps1.add(pfpw1);
					jps1.add(lpw2);
					jps1.add(pfpw2);
					jps1.add(lpw3);
					jps1.add(pfpw3);
					jps1.add(btSave);
					jpCenter.add(jps1);
					
					//jpCenter.validate();
					
					 //jpSouth.validate();
					
					btSave.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e){
							jpCenter.removeAll();
							jpSouth.removeAll();
							try{
								Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
						        Connection connect = DriverManager.getConnection(
						        		"jdbc:mysql://127.0.0.1/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","479400");
						             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
						        Statement stmt = connect.createStatement();
						        String np = new String(pfpw2.getPassword());
						        String npc = new String(pfpw2.getPassword());
						        if(npc.equals(np)){
						        	stmt.executeUpdate("update manager set mcode = '"+npc+"' where mno = '"+ account +"'");
						        	 JOptionPane.showMessageDialog(null, "记录修改完毕！", "提示", JOptionPane.INFORMATION_MESSAGE);
						        }
						        else{
						        	
						        	JOptionPane.showMessageDialog(null, "两次输入的密码不一致，请重新输入！", "提示", JOptionPane.INFORMATION_MESSAGE);
						        	pfpw2.setText("");
						        	pfpw3.setText("");
						        	
						        }
						        
							}catch (Exception ex) {
						        System.out.print("get data error!");
						        ex.printStackTrace();
						      }
							
						}
					});
	    	}
	    });
	    aVgrade.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e){
				jpCenter.removeAll();
				jpSouth.removeAll();
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
				//columnNames.add("学分");
				 Vector<Vector<Object>> dataVector = new    
	                        Vector<Vector<Object>>(); 	 
				 try {
				        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
				        Connection connect = DriverManager.getConnection(
				        		"jdbc:mysql://127.0.0.1/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","479400");
				             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
				        Statement stmt = connect.createStatement();
				        ResultSet rs = stmt.executeQuery("select * from stuinfo");
				        while(rs.next()){
				        	Vector<Object> vec = new Vector<Object>();
				            for(int i=1;i<=9;i++)
				        	    vec.add(rs.getObject(i));
				        	dataVector.add(vec);
				        
				        }
			            table=new JTable(dataVector,columnNames);  
			            
			            JScrollPane jsp;
			            jsp=new JScrollPane(table);
			            //jsp.setHorizontalScrollBarPolicy (JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			            //jsp.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			            jsp.setPreferredSize(new Dimension(500,300));
			            jpCenter.add(jsp);
			            rs.close();
			            stmt.close();
			            connect.close();
                       
				      }
				      catch (Exception ex) {
				        System.out.print("get data error!");
				        ex.printStackTrace();
				      }
				
				JButton search = new JButton("查询");
				 search.setFont(ft2);
				 search.setBorder(BorderFactory.createRaisedBevelBorder());
				 JButton am = new JButton("修改学生成绩");
				 am.setFont(ft2);
				 am.setBorder(BorderFactory.createRaisedBevelBorder());
				 jpSouth.setBorder(BorderFactory.createEmptyBorder(0,10,10,5));
				 jpSouth.add(search);
				 jpSouth.add(am);
				 jpSouth.add(Exit);
				 jpCenter.validate();
				 jpCenter.repaint();
				 jpSouth.validate();
				 jpSouth.repaint();
				 //范围查询
				 search.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent e){
						 jpCenter.removeAll();
						 jpCenter.revalidate();
						 jpCenter.repaint();
						 jpSouth.removeAll();
						 jpSouth.revalidate();
						 jpSouth.repaint();
							JPanel jpl = new JPanel();
							JLabel label1 = new JLabel("输入学号：",JLabel.RIGHT);
							JLabel labelD = new JLabel("输入姓名：",JLabel.RIGHT);
							JLabel label2A = new JLabel("输入英语成绩：",JLabel.RIGHT);
							JLabel label2B = new JLabel("~",JLabel.RIGHT);
							JLabel label3A = new JLabel("输入数学成绩：",JLabel.RIGHT);
							JLabel label3B = new JLabel("~",JLabel.RIGHT);
							JButton serch = new JButton("查询总成绩");
							JButton searcharea = new JButton("查询成绩区间");
							JButton reset = new JButton("重置");
							JTextField sno = new JTextField(15);
							JTextField sengA = new JTextField();
							JTextField sengB = new JTextField();
							JTextField smathA = new JTextField();
							JTextField smathB = new JTextField();
							JTextField sname = new JTextField();
							label1.setFont(ft2);
							labelD.setFont(ft2);
							label2A.setFont(ft2);
							label2B.setFont(ft2);
							label3A.setFont(ft2);
							label3B.setFont(ft2);
							serch.setFont(ft2);
							searcharea.setFont(ft2);	
							serch.setBorder(BorderFactory.createRaisedBevelBorder());
							searcharea.setBorder(BorderFactory.createRaisedBevelBorder());
							reset.setBorder(BorderFactory.createRaisedBevelBorder());
							sno.setFont(ft2);
							sengA.setFont(ft2);
							sengB.setFont(ft2);
							smathA.setFont(ft2);
							smathB.setFont(ft2);
							sname.setFont(ft2);
							reset.setFont(ft2);
							
							GridLayout gl = new GridLayout(3,4,6,5);
							JTable table = new JTable();
							DefaultTableModel   defaultModel   =   new   DefaultTableModel();
							Vector<String> columnNames = new Vector<String>();
							columnNames.add("学号");
							columnNames.add("学院");
							columnNames.add("班级");
							columnNames.add("姓名");
							columnNames.add("英语");
							columnNames.add("数学");
							jpCenter.setBorder(BorderFactory.createEmptyBorder(20,5,5,5));
							
							/*查询面板*/
							jpl.setLayout(gl);
							jpl.add(label1);
							jpl.add(sno);
							jpl.add(labelD);
							jpl.add(sname);
							jpl.add(label2A);
							jpl.add(sengA);
							jpl.add(label2B);
							jpl.add(sengB);
							jpl.add(label3A);
							jpl.add(smathA);
							jpl.add(label3B);
							jpl.add(smathB);
							jpSouth.add(serch);
							jpSouth.add(searcharea);
							jpSouth.add(reset);
							jpSouth.add(am);
							jpSouth.add(Exit);
			                
							jpCenter.add(jpl,BorderLayout.NORTH);
							JScrollPane jsp;
					        jsp=new JScrollPane(table);
					        jsp.setPreferredSize(new Dimension(450,200));
				            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					        jpCenter.add(jsp,"Center");
							
						searcharea.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								
							 Vector<Vector<Object>> dataVector = new    
					                    Vector<Vector<Object>>(); 	 
							 try {
							        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
							        Connection connect = DriverManager.getConnection(
							        		"jdbc:mysql://127.0.0.1/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","479400");
							             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
							        Statement stmt = connect.createStatement();
							        ResultSet rs = stmt.executeQuery("select sno,sacd,sclass,sname,seng,smath from stuinfo where seng > '" + sengA.getText() + "'and seng < '" + sengB.getText() +"' and smath > '" + smathA.getText() + "'and smath < '" + smathB.getText() +"'");
							       
							        while(rs.next()){
							        	 Vector<Object> vec = new Vector<Object>();
							            for(int i=1;i<=6;i++)
							        	    vec.add(rs.getObject(i));
							        	dataVector.add(vec);
							        
							        }
							        table.setModel(defaultModel);//将数据传输进表
							        defaultModel.setDataVector(dataVector,columnNames);
						            
						            rs.close();
						            stmt.close();
						            connect.close();
					                
							      }
							      catch (Exception ex) {
							        System.out.print("get data error!");
							        ex.printStackTrace();
							      }
							}
							
						});
						serch.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
							
							 Vector<Vector<Object>> dataVector = new    
					                    Vector<Vector<Object>>(); 	 
							 try {
							        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
							        Connection connect = DriverManager.getConnection(
							        		"jdbc:mysql://127.0.0.1/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","479400");
							             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
							        Statement stmt = connect.createStatement();
							        ResultSet rs = stmt.executeQuery("select sno,sacd,sclass,sname,seng,smath from stuinfo where sno = '" + sno.getText() + "'or sname = '" + sname.getText() +"'");
							       
							        while(rs.next()){
							        	 Vector<Object> vec = new Vector<Object>();
							            for(int i=1;i<=6;i++)
							        	    vec.add(rs.getObject(i));
							        	dataVector.add(vec);
							        
							        }
							        table.setModel(defaultModel);//将数据传输进表
							        defaultModel.setDataVector(dataVector,columnNames);
						            
						            rs.close();
						            stmt.close();
						            connect.close();
					                
							      }
							      catch (Exception ex) {
							        System.out.print("get data error!");
							        ex.printStackTrace();
							      }
							}
							
						});
					   reset.addActionListener(new ActionListener(){
						   public void actionPerformed(ActionEvent ee){
						    sno.setText("");
							sname.setText("");
							sengA.setText("");
							sengB.setText("");
							smathA.setText("");
							smathB.setText("");
							Vector<Vector<Object>> dataVector = new    
					                Vector<Vector<Object>>(); 	
							defaultModel.setDataVector(dataVector,columnNames);
						   }
					   });
					   Exit.addActionListener(new ActionListener()
					   {
					   	public void actionPerformed(ActionEvent e){
					   		System.exit(0);
					   	}
					   });
						 
					 }
				 });
				 am.addActionListener(new ActionListener(){//修改函数
					 public void actionPerformed(ActionEvent e){
						 jpCenter.removeAll();
						 jpCenter.revalidate();
						 jpCenter.repaint();
						 jpSouth.removeAll();

						 jpSouth.revalidate();
						 jpSouth.repaint();
						 JPanel jp2 = new JPanel(new GridLayout(9,2,1,5));
						 jp2.add(no);jp2.add(tno);
				    	 jp2.add(sc);jp2.add(tsc);
				    	 JButton modi = new JButton("确认修改");	
				    	 modi.setBorder(BorderFactory.createRaisedBevelBorder());
				    	 mserch.setBorder(BorderFactory.createRaisedBevelBorder());
				    	 jp2.add(na);jp2.add(tna);
				    	 jp2.add(cl);jp2.add(tcl);
				         //jp2.add(sex1);jp2.add(sex2);
				    	 //jp2.add(te);jp2.add(tte);
				   		 jp2.add(bi);jp2.add(tbi);
				   		 jp2.add(smathl);jp2.add(smatht);
				   		// jp2.add(gr);jp2.add(tgr);
				   		 jp2.add(mserch);jp2.add(modi);
				   		 jpCenter.add(jp2);
				   		 modi.setEnabled(false);
				   	     tno.setEditable(true);
				   	     tna.setEditable(false);
				   	    // sex.setEditable(false);
				   	     tsc.setEditable(false);
				   	     tcl.setEditable(false);
				   	     smatht.setEditable(false);
				   	     tbi.setEditable(false);
				   		 mserch.addActionListener(new ActionListener(){
				   			 public void actionPerformed(ActionEvent ae){
				   				try {
				   	    	        Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
				   	    	        Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
				   	     	        //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
				   	    	        Statement stmt = connect.createStatement();
				   	    	        ResultSet rs = stmt.executeQuery("select * from stuinfo where sno='"+tno.getText()+"'");
				   	    	        if(rs.next()){
				   	    	        	 modi.setEnabled(true);
				   	    	             tno.setEditable(false);
				   	    	             tna.setEditable(false);
				   	    	            // sex.setEditable(false);
				   	    	             tsc.setEditable(false);
				   	    	             tcl.setEditable(false);
				   	    	             smatht.setEditable(true);
				   	    	             tbi.setEditable(true);
				   	    	             
				   	    	             tno.setText(rs.getString("sno"));
				   	    	             tna.setText(rs.getString("sname"));
				   	    	             //sex.setText(rs.getString("ssex"));
				   	    	             tcl.setText(rs.getString("sclass"));
				   	    	             smatht.setText(rs.getString("smath"));
				   	    	             tbi.setText(rs.getString("seng"));
				   	    	             tsc.setText(rs.getString("sacd"));
				   	    	             
				   	    	             stmt.close();
				   	    	  	         connect.close();
				   	    	            }
				   	    	        else{
				   	    	        	JOptionPane.showMessageDialog(null, "不存在该记录！", "提示", JOptionPane.ERROR_MESSAGE);
				   	    	        	tno.setText("");
				   	    	        	tno.requestFocus();
				   	    	         }
				   	    	 }
				   	    	  catch(Exception e){
				   	              e.printStackTrace();
				   	           }
				   			 }
				   		 });
				   		modi.addActionListener(new ActionListener(){
				   			 public void actionPerformed(ActionEvent ae){
				   				try {
				   	   	         Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
				   	   	         Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
				   		         //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
				   	   	         Statement stmt = connect.createStatement();
				   	   	         int i=stmt.executeUpdate("Update stuinfo set smath='"+smatht.getText()+"',seng='"+tbi.getText()+"' where sno='"+tno.getText()+"'" );
				   	   	         if(i==1)
				   	   	         JOptionPane.showMessageDialog(null, "记录修改完毕！", "提示", JOptionPane.INFORMATION_MESSAGE);
				   	   	         else
				   	   	        JOptionPane.showMessageDialog(null,"修改失败！", "提示", JOptionPane.ERROR_MESSAGE);
										 
				   	   	        tno.setText("");
				   	   	        tna.setText("");
				   	   	       // sex.setText("");
				   	   	        tsc.setText("");
				   	   	        tcl.setText("");
				   	   	        smatht.setText("");
				   	   	        tbi.setText("");
				   	   	         
				   	   	        modi.setEnabled(false);
				   	   	        tno.setEditable(true);
				   	   	        tna.setEditable(false);
				   	   	       // sex.setEditable(false);
				   	   	        tsc.setEditable(false);
				   	   	        tcl.setEditable(false);
				   	   	        smatht.setEditable(false);
				   	   	        tbi.setEditable(false); 
				   	   	       
				   	   	        stmt.close();
				   		        connect.close();
				   	    	  }
				   	    	  catch(Exception e){
				   	            e.printStackTrace();}
				   			 }
				   		 });
					 } 
				 });
			}	   
	    	});
	    aAdd.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e){
	    		
                jpCenter.removeAll();
	    		jpCenter.revalidate();//重绘布局
	    		jpCenter.repaint();//重绘内容
	    		jpSouth.removeAll();
	    		jpSouth.revalidate();
	    		jpSouth.repaint();
	    		JPanel jp1 = new JPanel(new GridLayout(9,2,1,5));
	    		ButtonGroup bgsex = new ButtonGroup();
	    		JRadioButton sex1 = new JRadioButton("男");
	    		JRadioButton sex2 = new JRadioButton("女");
	    		bgsex.add(sex1);bgsex.add(sex2);
	    		JButton jbadd = new JButton("添加信息");
	    		
	    		jp1.add(no);jp1.add(tno);
	    		jp1.add(sc);jp1.add(tsc);
	    	    jbadd.setBorder(BorderFactory.createRaisedBevelBorder());
	    		jp1.add(na);jp1.add(tna);
	    		jp1.add(cl);jp1.add(tcl);
	    		jp1.add(sex1);jp1.add(sex2);
	    		jp1.add(te);jp1.add(tte);
	    		jp1.add(bi);jp1.add(tbi);
	    		jp1.add(smathl);jp1.add(smatht);
	    		jp1.add(gr);jp1.add(tgr);
	    		jpCenter.add(jp1);
	    		jbadd.setFont(ft2);
	    		jpSouth.add(jbadd);
	    		jpSouth.add(Exit);
	    		//modi.setEnabled(true);
   	   	        tno.setEditable(true);
   	   	        tna.setEditable(true);
   	   	       // sex.setEditable(false);
   	   	        tsc.setEditable(true);
   	   	        tcl.setEditable(true);
   	   	        smatht.setEditable(true);
   	   	        tbi.setEditable(true); 
	    		jbadd.addActionListener(new ActionListener(){
	    				public void actionPerformed(ActionEvent e){
	   
	    					try
	    					{
	    						
	    						String choose = null;
	    			    		if(sex1.isSelected()){
	    			    			choose = sex1.getText();
	    			    		}
	    			    		if(sex2.isSelected()){
	    			    			
	    			    			choose = sex2.getText();
	    			    		}
	    			    		
	    						//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	    			            Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","479400");
	    			            Statement stmt=conn.createStatement();
	    						int a = stmt.executeUpdate("insert into stuinfo(sno , sname , ssex , seng , smath ,sclass ,sacd , stime  , stel )values('"+tno.getText()+"','"+tna.getText()+"','"+choose+"','"+tbi.getText()+"','"+smatht.getText()+"','"+tcl.getText()+"'," +
	    								"'"+tsc.getText()+"','"+tgr.getText()+"', '"+tte.getText()+"')");
	    						//System.out.println(a);
	    						if(a==1)
	    						{
	    							JOptionPane.showMessageDialog(s,"已成功添加", "提示", JOptionPane.INFORMATION_MESSAGE);
	    						}
	    						else
	    						{
	    							JOptionPane.showMessageDialog(s,"添加失败", "提示", JOptionPane.ERROR_MESSAGE);
	    						}
	    						stmt.close();
	    						conn.close();
	    					}
	    					catch (SQLException se)
	    					{
	    						
	    						JOptionPane.showMessageDialog(s,se.getMessage());
	    						se.printStackTrace();
	    					}
	    				}
	    			});
	    		
	    	}
	    });
	   
	    aDelete.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e){
                jpCenter.removeAll();
	    		
	    		jpCenter.revalidate();//重绘布局
	    		jpCenter.repaint();//重绘内容
	    		jpSouth.removeAll();
	    		jpSouth.revalidate();
	    		jpSouth.repaint();
	    		JButton deb = new JButton("删除");//更改区
	    		JPanel jp2 = new JPanel();
	    		deb.setBorder(BorderFactory.createRaisedBevelBorder());
	    		jp2.setLayout(new GridLayout(7,1,10,10));
				//上左下右
				jp2.setBorder(BorderFactory.createEmptyBorder(5,100,40,380));
				JLabel dl1 = new JLabel("请输入学号：");
				JTextField sno = new JTextField(15);
				
				jp2.add(dl1);
				jp2.add(sno);
				jp2.add(deb);
				deb.setFont(ft2);
	    		jpSouth.add(deb);
	    		jpSouth.add(Exit);
				jpCenter.add(jp2);
				deb.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						 
				          try {
				    	        Class.forName("com.mysql.jdbc.Driver");//加载MYSQL JDBC驱动程序   
				    	        Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","479400");
				    	        //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
				    	        Statement stmt = connect.createStatement();
				    	        int i=stmt.executeUpdate("delete  from stuinfo where sno='"+sno.getText()+"'");
				    	        if(i==1){
								  JOptionPane.showMessageDialog(null,"删除学生记录成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
								  
								}
								if(i==0)
								{
									JOptionPane.showMessageDialog(null,"没有该学号、请重新输入！", "提示", JOptionPane.ERROR_MESSAGE);
									sno.setText("");
									sno.requestFocus();
								}
				    	        stmt.close();
				    	        connect.close();
							}
				           catch(Exception e){
				              e.printStackTrace();
				           }
				    	
				       }
				});
	    	}
	    });
	    this.setSize(800,500);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		
		
	}
	
	


}
