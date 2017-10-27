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
		JButton sView = new JButton("�鿴������Ϣ");
	    JButton sModify = new JButton("�޸ĸ�����Ϣ");

	public Gmain(String saccount){
		this.saccount = saccount;
		this.setTitle("ѧ��������");
		sView.setFont(ft2);
		sModify.setFont(ft2);
		//sVgrade.setFont(ft2);
		//���ò���
		
		jpWest.setLayout(gl);
		jpSouth.setLayout(fl);
		jpWest.add(sView);
		jpWest.add(sModify);
		
		//���ñ߾���������
		jpWest.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		//���߰�ť
		
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
				columnNames.add("ѧ��");
				columnNames.add("ѧԺ");
				columnNames.add("����");
				columnNames.add("�༶");
				columnNames.add("�Ա�");
				columnNames.add("Ӣ��");
				columnNames.add("��ѧ");
				columnNames.add("�绰");
				columnNames.add("��ѧʱ��");
				 Vector<Vector<Object>> dataVector = new    
		                    Vector<Vector<Object>>(); 	 
				 try {
				        Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
				        Connection connect = DriverManager.getConnection(
				        		"jdbc:mysql://127.0.0.1/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","479400");
				              //����URLΪ   jdbc:mysql//��������ַ/���ݿ���  �������2�������ֱ��ǵ�½�û���������
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
				JButton btSave = new JButton("����");
				btSave.setFont(ft2);
				//JLabel lpw1 = new JLabel("������");
				//lpw1.setFont(ft2);
				//JPasswordField pfpw1 = new JPasswordField(15);
				JLabel lpw2 = new JLabel("������:");
				lpw2.setFont(ft2);
				JPasswordField pfpw2 = new JPasswordField();
				JLabel lpw3 = new JLabel("ȷ������:");
				lpw3.setFont(ft2);
				JPasswordField pfpw3 = new JPasswordField();

					jps1.setLayout(new GridLayout(7,1,10,10));
					//��������
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
									Class.forName("com.mysql.jdbc.Driver");     //����MYSQL JDBC��������   
							        Connection connect = DriverManager.getConnection(
							        		"jdbc:mysql://127.0.0.1/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","479400");
							             //����URLΪ   jdbc:mysql//��������ַ/���ݿ���  �������2�������ֱ��ǵ�½�û���������
							        Statement stmt = connect.createStatement();
							        String np = new String(pfpw2.getPassword());
							        String npc = new String(pfpw3.getPassword());
							        if(npc.equals(np)){
							        	stmt.executeUpdate("update stuinfo set scode = '"+npc+"' where sno = '"+ saccount +"'");
							        	System.out.println(npc+" "+saccount+" "+np);
							        	 JOptionPane.showMessageDialog(null, "��¼�޸���ϣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
							        }
							        else{
							        	
							        	JOptionPane.showMessageDialog(null, "������������벻һ�£����������룡", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
	
	