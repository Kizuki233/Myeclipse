package Client;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UserConf extends JDialog {

    private static final long serialVersionUID = 1L;
    JPanel panelUserConf = new JPanel();
    JButton save = new JButton();
    JButton cancel = new JButton();
    JLabel DLGINFO=new JLabel("              Ĭ���û���Ϊ��user");

    JPanel panelSave = new JPanel();
    JLabel message = new JLabel();
    String userInputName;

    JTextField userName ;

    public UserConf(JFrame frame,String str) {
        super(frame, true);
        this.userInputName = str;
        try {
            jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //��������λ�ã�ʹ�Ի������
        setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(300, 120));
        this.setTitle("�û�����");
        message.setText("�������û���:");
        userName = new JTextField(10);
        userName.setText(userInputName);
        save.setText("����");
        cancel.setText("ȡ��");

        panelUserConf.setLayout(new FlowLayout());
        panelUserConf.add(message);
        panelUserConf.add(userName);

        panelSave.add(new Label("              "));
        panelSave.add(save);
        panelSave.add(cancel);
        panelSave.add(new Label("              "));

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panelUserConf, BorderLayout.NORTH);
        contentPane.add(DLGINFO, BorderLayout.CENTER);
        contentPane.add(panelSave, BorderLayout.SOUTH);

        //���水ť���¼�����
        save.addActionListener(
            new ActionListener() {
                public void actionPerformed (ActionEvent a) {
                    if(userName.getText().equals("")){
                        DLGINFO.setText(
                            "                                 �û�������Ϊ�գ�");
                        userName.setText(userInputName);
                        return;
                    }
                    else if(userName.getText().length() > 15){
                        DLGINFO.setText("                    �û������Ȳ��ܴ���15���ַ���");
                        userName.setText(userInputName);
                        return;
                    }
                    userInputName = userName.getText();
                    dispose();
                }
            }
        );
        //ȡ����ť���¼�����
        cancel.addActionListener((ActionEvent e) -> {
                    dispose();
                }); 
    }
}