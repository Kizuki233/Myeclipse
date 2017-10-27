package ticket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import java.awt.Window.Type;
import javax.swing.UIManager;
import java.awt.Dialog.ModalExclusionType;
import java.awt.SystemColor;

public class Login {
	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 new Login();
				} catch (Exception e) {
					e.printStackTrace();}
			}});
	 }

	public Login() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.setBackground(SystemColor.textHighlight);
		frame.getContentPane().setBackground(UIManager.getColor("CheckBox.highlight"));
		frame.setType(Type.POPUP);
		frame.setResizable(false);
		frame.setTitle("\u7BA1\u7406\u5458\u767B\u5F55");
		frame.setBounds(100, 100, 278, 186);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("宋体", Font.PLAIN, 18));
		lblId.setBounds(60, 22, 35, 25);
		frame.getContentPane().add(lblId);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("宋体", Font.PLAIN, 18));
		lblPassword.setBounds(10, 71, 88, 25);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(109, 24, 101, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(108, 71, 102, 25);
		frame.getContentPane().add(passwordField);
		
		JButton button = new JButton("\u767B\u5F55");
		button.setSelectedIcon(null);
		button.setFont(new Font("华文细黑", Font.PLAIN, 15));
		button.setBounds(109, 114, 69, 31);
		button.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		try {
	    			String tx = textField.getText();
	    			char[] password=passwordField.getPassword();
	    		    String pw = new String(password);
	    			
			        Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
			        Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8&useSSL=true","root","479400");
	    	         //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
			        Statement stmt = connect.createStatement();
			       
			        ResultSet rs = stmt.executeQuery("select mpass from Manage where mid='"+tx+"'" );
			        if(rs.next()){
			          String pass = rs.getString("mpass");
			         if(pw.equals(pass)){
			        	new Manager();
			        	frame.dispose();
			         }
			         else{
			        	 JOptionPane.showMessageDialog(null,"密码输入错误，请重新输入！", "提示", JOptionPane.ERROR_MESSAGE);
			        	 textField.setText("");
			        	 passwordField.setText("");
			        	 textField.requestFocus(); 
			       }}
			        stmt.close();
	    	        connect.close();}
	    		    catch(Exception ex){  
	    			ex.printStackTrace();
	    		}
	    	}

	    });
		frame.getContentPane().add(button);
        frame.setVisible(true);
	}
}
