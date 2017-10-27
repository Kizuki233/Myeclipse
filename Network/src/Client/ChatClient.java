package Client;

import java.awt.*;
import java.awt.event.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
//��Ҫ����Ϊ����ͻ��˵Ľ��棬���ʱ��������¼��������ඨ����Connect������DisConnect��������ʵ����ͻ��˵�������Ͽ����ӡ�����½��ָ���ķ�����ʱ������ClientReceive��ʵ����Ϣ�շ���ͬʱ���໹������SendMessaga���������������û����ʹ��б������Ϣ��
public final class ChatClient extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5374609359679218810L;
	String ip = "127.0.0.1";  //���ӵ�����˵�ip��ַ
    int port = 8787;         //���ӵ�����˵Ķ˿ں�
    String userName = "user";    //�û���
    int type = 0;      //���û����ӱ�ǣ�����0��ʾδ���ӣ�1��ʾ������

    JComboBox<String> combobox;   //ѡ������Ϣ�Ľ�����
    JTextPane messageShow;  //�ͻ��˵���Ϣ��ʾ
    JScrollPane messageScrollPane; //��Ϣ��ʾ�Ĺ�����

    JLabel express, sendToLabel, messageLabel;

    JTextField clientMessage;//�ͻ�����Ϣ�ķ���
    JComboBox<String> actionlist;//����ѡ��
    JButton clientMessageButton;//������Ϣ
    JTextField showStatus;//��ʾ�û�����״̬

    Socket socket;
    ObjectOutputStream output;//�����׽��������
    ObjectInputStream input;//�����׽���������

    ClientReceive recvThread;

    //����������
    JToolBar toolBar = new JToolBar();
    //�����������еİ�ť���
    JButton loginButton;//�û���¼
    JButton logoffButton;//�û�ע��
    JButton userButton;//�û���Ϣ������
    JButton exitButton;//�˳���ť
    //��ܵĴ�С
    Dimension faceSize = new Dimension(450, 450);

    JPanel downPanel;
    GridBagLayout girdBag;
    GridBagConstraints girdBagCon;
    
    SimpleAttributeSet attrset = new SimpleAttributeSet();
    //StyleConstants.setFontSize(attrset,24);
    //��������
    Document docs;//����ı�����

    public ChatClient() {
        init();//��ʼ������
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();//���ÿ�ܵĴ�С
        this.setSize(faceSize);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("�����ҿͻ���"); //���ñ���

        setVisible(true);

    }

    /**
     * �����ʼ������
     */
    public void init() {

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //��ʼ����ť
        loginButton = new JButton("��¼");
        logoffButton = new JButton("ע��");
        userButton = new JButton("�û�������");
        exitButton = new JButton("�˳�");
        
        //����������ʾ��Ϣ
        loginButton.setToolTipText("���ӵ�ָ���ķ�����");
        logoffButton.setToolTipText("��������Ͽ�����");
        userButton.setToolTipText("�����û���Ϣ");

        //����ť��ӵ�������
        toolBar.add(userButton);
        toolBar.addSeparator();//��ӷָ���
        toolBar.add(loginButton);
        toolBar.add(logoffButton);
        toolBar.addSeparator();//��ӷָ���
        toolBar.add(exitButton);
        
        contentPane.add(toolBar, BorderLayout.NORTH);

        actionlist = new JComboBox<String>();
        actionlist.insertItemAt("����ѡ��", 0);   
        actionlist.addItem("/��ɥ");
        actionlist.addItem("/ѣ��");
        actionlist.addItem("/���");
        actionlist.addItem("/���");
        actionlist.setSelectedIndex(0);

        
        //��ʼʱ
        loginButton.setEnabled(true);
        logoffButton.setEnabled(false);
        
        //��Ӱ�ť���¼�����
        loginButton.addActionListener(this);
        logoffButton.addActionListener(this);
        userButton.addActionListener(this);
        exitButton.addActionListener(this);
        actionlist.addActionListener(this);
        
        combobox = new JComboBox<String>();
        combobox.insertItemAt("������", 0);
        combobox.setSelectedIndex(0);

        messageShow = new JTextPane();
        docs = messageShow.getDocument();
        messageShow.setEditable(false);
        //��ӹ�����
        messageScrollPane = new JScrollPane(messageShow,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        messageScrollPane.setPreferredSize(new Dimension(400, 400));
        messageScrollPane.revalidate();

        clientMessage = new JTextField(23);
        clientMessage.setEnabled(false);
        clientMessageButton = new JButton();
        clientMessageButton.setText("����");

        //���ϵͳ��Ϣ���¼�����
        clientMessage.addActionListener(this);
        clientMessageButton.addActionListener(this);

        sendToLabel = new JLabel("������:");
        express = new JLabel("         ����:   ");
        messageLabel = new JLabel("������Ϣ:");
        downPanel = new JPanel();
        girdBag = new GridBagLayout();
        downPanel.setLayout(girdBag);

        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 0;
        girdBagCon.gridy = 0;
        girdBagCon.gridwidth = 5;
        girdBagCon.gridheight = 2;
        girdBagCon.ipadx = 5;
        girdBagCon.ipady = 5;
        JLabel none = new JLabel("    ");
        girdBag.setConstraints(none, girdBagCon);
        downPanel.add(none);

        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 0;
        girdBagCon.gridy = 2;
        girdBagCon.insets = new Insets(1, 0, 0, 0);
        //girdBagCon.ipadx = 5;
        //girdBagCon.ipady = 5;
        girdBag.setConstraints(sendToLabel, girdBagCon);
        downPanel.add(sendToLabel);

        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 1;
        girdBagCon.gridy = 2;
        girdBagCon.anchor = GridBagConstraints.LINE_START;
        girdBag.setConstraints(combobox, girdBagCon);
        downPanel.add(combobox);

        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 2;
        girdBagCon.gridy = 2;
        girdBagCon.anchor = GridBagConstraints.LINE_END;
        girdBag.setConstraints(express, girdBagCon);
        downPanel.add(express);

        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 3;
        girdBagCon.gridy = 2;
        girdBagCon.anchor = GridBagConstraints.LINE_START;
        //girdBagCon.insets = new Insets(1,0,0,0);
        //girdBagCon.ipadx = 5;
        //girdBagCon.ipady = 5;
        girdBag.setConstraints(actionlist, girdBagCon);
        downPanel.add(actionlist);

        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 4;
        girdBagCon.gridy = 2;
        girdBagCon.insets = new Insets(1, 0, 0, 0);
//        girdBagCon.ipadx = 5;
//        girdBagCon.ipady = 5;


        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 0;
        girdBagCon.gridy = 3;
        girdBag.setConstraints(messageLabel, girdBagCon);
        downPanel.add(messageLabel);

        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 1;
        girdBagCon.gridy = 3;
        girdBagCon.gridwidth = 3;
        girdBagCon.gridheight = 1;
        girdBag.setConstraints(clientMessage, girdBagCon);
        downPanel.add(clientMessage);

        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 4;
        girdBagCon.gridy = 3;
        girdBag.setConstraints(clientMessageButton, girdBagCon);
        downPanel.add(clientMessageButton);

        showStatus = new JTextField(35);
        showStatus.setEditable(false);
        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 0;
        girdBagCon.gridy = 5;
        girdBagCon.gridwidth = 5;
        girdBag.setConstraints(showStatus, girdBagCon);
        downPanel.add(showStatus);

        contentPane.add(messageScrollPane, BorderLayout.CENTER);
        contentPane.add(downPanel, BorderLayout.SOUTH);

        //�رճ���ʱ�Ĳ���
        this.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        if (type == 1) {
                            DisConnect();
                        }
                        System.exit(0);
                    }
                }
        );
    }

    
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == userButton) { //�û���Ϣ����
            //�����û���Ϣ���öԻ���
            UserConf userConf = new UserConf(this, userName);
            userConf.setVisible(true);
            userName = userConf.userInputName;
        } else if (obj == loginButton) { //��¼
        	this.setTitle(userName+"��������");
        	Connect();
        } else if (obj == logoffButton) { //ע��
            DisConnect();
            showStatus.setText("");
        } else if (obj == actionlist) { //���鷢��
        	int index = actionlist.getSelectedIndex();
            if (index != 0) { // ==0��ʾѡ�е��µ�һ��
              String icon = actionlist.getSelectedItem().toString();
              clientMessage.setText(clientMessage.getText()+icon);
              actionlist.setSelectedIndex(0);
          }
        } else if (obj == clientMessage || obj == clientMessageButton) { //������Ϣ
            SendMessage();
            clientMessage.setText("");
        }  else if (obj == exitButton) { //�˳�
            int j = JOptionPane.showConfirmDialog(
                    this, "���Ҫ�˳���?", "�˳�",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (j == JOptionPane.YES_OPTION) {
                if (type == 1) {
                    DisConnect();
                }
                System.exit(0);
            }
        } 
    }
/**
 * ���ӷ�����
 */
    public void Connect() {
        try {
            socket = new Socket(ip, port);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(
                    this, "�������ӵ�ָ���ķ�������\n��ȷ�����������Ƿ���ȷ��", "��ʾ",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            output.flush();
            
            input = new ObjectInputStream(socket.getInputStream());

            output.writeObject(userName);
            output.flush();

            recvThread = new ClientReceive(socket, output, input, combobox, messageShow, showStatus,userName);
            recvThread.start();

            loginButton.setEnabled(false);
            userButton.setEnabled(false);
            logoffButton.setEnabled(true);
            clientMessage.setEnabled(true);
            
            try {
                docs.insertString(docs.getLength(), "���ӷ����� " + ip + ":" + port + " �ɹ�...\n", attrset);//���ı�����׷��
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            type = 1;//��־λ��Ϊ������
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     *������ע��
     */
    public void DisConnect() {
        loginButton.setEnabled(true);
        userButton.setEnabled(true);
        logoffButton.setEnabled(false);
        clientMessage.setEnabled(false);

        if (socket.isClosed()) {
            return;
        }

        try {
            output.writeObject("�û�����");
            output.flush();

            input.close();
            output.close();
            socket.close();
            
            try {
                docs.insertString(docs.getLength(), "�Ѿ���������Ͽ�����...\n", attrset);//���ı�����׷��
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            type = 0;//��־λ��Ϊδ����
        } catch (Exception e) {
            //
        }
    }

    public void SendMessage() {
        String toSomebody = combobox.getSelectedItem().toString();
        String message = clientMessage.getText();

        if (socket.isClosed()) {
            return;
        }

        try {
            output.writeObject("������Ϣ");
            output.flush();
            output.writeObject(toSomebody);
            output.flush();
            output.writeObject(message);
            output.flush();
        } catch (Exception e) {}
    }

    public static void main(String[] args)  {
         new ChatClient();
    }
}