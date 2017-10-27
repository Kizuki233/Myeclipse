package ticket;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Train {
	private JFrame frmWelcome;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Train window = new Train();
					window.frmWelcome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();}
		}});
	}
	public Train() {
		initialize();
	}
	private void initialize() {
    	frmWelcome = new JFrame();
    	frmWelcome.setResizable(false);
		frmWelcome.setType(Type.UTILITY);
		frmWelcome.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmWelcome.setSize(397, 307);
		frmWelcome.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		frmWelcome.setTitle("Welcome");
		frmWelcome.setLocationRelativeTo(null);
		frmWelcome.getContentPane().setLayout(null);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(-11, -11, 430, 319);
		frmWelcome.getContentPane().add(btnNewButton_2);
		btnNewButton_2.setIcon(new ImageIcon(Train.class.getResource("/ticket/lieche.png")));
		btnNewButton_2.setLayout(null);
		
				JLabel lblNewLabel = new JLabel("  \u5217\u8F66\u65F6\u523B\u67E5\u8BE2\u7CFB\u7EDF");
				lblNewLabel.setFont(new Font("»ªÎÄÁ¥Êé", Font.PLAIN, 28));
				lblNewLabel.setBounds(68, 24, 251, 74);
				btnNewButton_2.add(lblNewLabel);	
				
				JButton btnNewButton = new JButton("\u67E5\u8BE2\u5165\u53E3");
				btnNewButton.setFont(new Font("Ó×Ô²", Font.PLAIN, 14));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new Search();
						frmWelcome.dispose();
					}
				});
				btnNewButton.setBounds(125, 108, 137, 30);
				btnNewButton_2.add(btnNewButton);
				
				JButton btnNewButton_1 = new JButton("\u7BA1\u7406\u5458\u5165\u53E3");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new Login();
						frmWelcome.dispose();
					}
				});
				btnNewButton_1.setFont(new Font("Ó×Ô²", Font.PLAIN, 14));
				btnNewButton_1.setBounds(125, 154, 137, 30);
				btnNewButton_2.add(btnNewButton_1);

	}
}
