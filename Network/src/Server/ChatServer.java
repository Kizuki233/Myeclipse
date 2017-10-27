package Server;

import java.awt.*;
import java.awt.event.*;
import java.net.ServerSocket;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
//��Ҫ����Ϊ����������˵Ľ��棬���ʱ�������ʱ�䴦������ServerListen����ʵ�ַ�����û����������ߵļ���������ServerReceive��ʵ�ַ������˵���Ϣ�շ���
public final class ChatServer extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4743788058714759795L;
	//������ǲ�ϣ��ͨ��������ǿ�ƻ�������汾����ʵ�����л��ӿڵ�ʵ���ܹ�������ǰ�汾
	public static int port = 8787;
    ServerSocket serverSocket;
    JComboBox<String> combobox;
    JTextPane messageShow;
    JScrollPane messageScrollPane;
    JTextField showStatus;
    JLabel sendToLabel, messageLabel;
    JTextField sysMessage;
    JButton sysMessageButton;
    UserLinkList userLinkList;
    
	SimpleAttributeSet attrset = new SimpleAttributeSet();
    //StyleConstants.setFontSize(attrset,20);
    Document docs;//����ı�����

    JToolBar toolBar = new JToolBar();

    JButton startServer;
    JButton stopServer;
    JButton exitButton;

    Dimension faceSize = new Dimension(400, 400);
    ServerListen listenThread;
    JPanel downPanel;

    GridBagLayout girdBag;
    GridBagConstraints girdBagCon;

    public ChatServer() {
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();//�����˴��ڵĴ�С�����ʺ������������ѡ��С�Ͳ���
        this.setSize(faceSize);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("�����ҷ�������");
        setVisible(true);
    }

    public void init() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());//

        startServer = new JButton("��������");
        stopServer = new JButton("ֹͣ����");
        exitButton = new JButton("�˳�");
;
        toolBar.addSeparator();
        toolBar.add(startServer);
        toolBar.addSeparator();
        toolBar.add(stopServer);
        toolBar.addSeparator();
        toolBar.add(exitButton);
        contentPane.add(toolBar, BorderLayout.NORTH);
        stopServer.setEnabled(false);

        startServer.addActionListener(this);
        stopServer.addActionListener(this);
        exitButton.addActionListener(this);
       
        combobox = new JComboBox<String>();
        combobox.insertItemAt("������", 0);
        combobox.setSelectedIndex(0);
        messageShow = new JTextPane();
        docs = messageShow.getDocument();
        messageShow.setEditable(false);
        messageScrollPane = new JScrollPane(messageShow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        messageScrollPane.setPreferredSize(new Dimension(300, 300));
        messageScrollPane.revalidate();//�Ѳ��ֹ�������Ӧ����������������²��ֲ����ơ�
        showStatus = new JTextField(25);
        showStatus.setEditable(false);
        sysMessage = new JTextField(25);
        sysMessage.setEnabled(false);
        sysMessageButton = new JButton();
        sysMessageButton.setText("����");
        sysMessage.addActionListener(this);
        sysMessageButton.addActionListener(this);
        sendToLabel = new JLabel("������:");
        messageLabel = new JLabel("ϵͳ��Ϣ:");
        downPanel = new JPanel();
        girdBag = new GridBagLayout();
        downPanel.setLayout(girdBag);
        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 0;
        girdBagCon.gridy = 0;
        girdBagCon.gridwidth = 3;
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
        girdBagCon.ipadx = 5;
        girdBagCon.ipady = 5;
        girdBag.setConstraints(sendToLabel, girdBagCon);
        downPanel.add(sendToLabel);
        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 1;
        girdBagCon.gridy = 2;
        girdBagCon.anchor = GridBagConstraints.LINE_START;
        girdBag.setConstraints(combobox, girdBagCon);
        downPanel.add(combobox);
        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 0;
        girdBagCon.gridy = 3;
        girdBag.setConstraints(messageLabel, girdBagCon);
        downPanel.add(messageLabel);
        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 1;
        girdBagCon.gridy = 3;
        girdBag.setConstraints(sysMessage, girdBagCon);
        downPanel.add(sysMessage);
        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 2;
        girdBagCon.gridy = 3;
        girdBag.setConstraints(sysMessageButton, girdBagCon);
        downPanel.add(sysMessageButton);
        girdBagCon = new GridBagConstraints();
        girdBagCon.gridx = 0;
        girdBagCon.gridy = 4;
        girdBagCon.gridwidth = 3;
        girdBag.setConstraints(showStatus, girdBagCon);
        downPanel.add(showStatus);
        contentPane.add(messageScrollPane, BorderLayout.CENTER);
        contentPane.add(downPanel, BorderLayout.SOUTH);
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                stopService();
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == startServer) {
            startService();
        } else if (obj == stopServer) {
            int j = JOptionPane.showConfirmDialog(this, "���ֹͣ������?", "ֹͣ����", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (j == JOptionPane.YES_OPTION) {
                stopService();
            }
        } else if (obj == exitButton) {
            int j = JOptionPane.showConfirmDialog(this, "���Ҫ�˳���?", "�˳�", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (j == JOptionPane.YES_OPTION) {
                stopService();
                System.exit(0);
            }
        } else if (obj == sysMessage || obj == sysMessageButton) {
            sendSystemMessage();
        }
    }

    public void startService() {
        try {
            serverSocket = new ServerSocket(port, 2);//���� backlog ָ���ͻ�����������еĳ���
            try {
                docs.insertString(docs.getLength(), "������Ѿ���������" + port + "�˿�����...\n", attrset);//���ı�����׷��
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            startServer.setEnabled(false);
            stopServer.setEnabled(true);
            sysMessage.setEnabled(true);
        } catch (Exception e) {
        }
        userLinkList = new UserLinkList();
        listenThread = new ServerListen(serverSocket, combobox, messageShow, showStatus, userLinkList);
        listenThread.start();
    }

    public void stopService() {
        try {
            sendStopToAll();
            listenThread.isStop = true;
            serverSocket.close();
            int count = userLinkList.getCount();
            int i = 0;
            while (i < count) {
                Node node = userLinkList.findUser(i);
                node.input.close();
                node.output.close();
                node.socket.close();
                i++;
            }
            stopServer.setEnabled(false);
            startServer.setEnabled(true);
            sysMessage.setEnabled(false);
            
            try {
                docs.insertString(docs.getLength(), "������Ѿ��ر�\n", attrset);//���ı�����׷��
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            combobox.removeAllItems();
            combobox.addItem("������");
        } catch (Exception e) {
        }
    }

    public void sendStopToAll() {
        int count = userLinkList.getCount();
        int i = 0;
        while (i < count) {
            Node node = userLinkList.findUser(i);
            if (node == null) {
                i++;
                continue;
            }
            try {
                node.output.writeObject("����ر�");
                node.output.flush();//flush()��ʾǿ�ƽ��������е����ݷ��ͳ�ȥ
            } catch (Exception e) {
            }
            i++;
        }
    }

    public void sendMsgToAll(String msg) {
        int count = userLinkList.getCount();
        int i = 0;
        while (i < count) {
            Node node = userLinkList.findUser(i);
            if (node == null) {
                i++;
                continue;
            }
            try {
                node.output.writeObject("ϵͳ��Ϣ");
                node.output.flush();
                node.output.writeObject(msg);
                node.output.flush();
            } catch (Exception e) {
            }
            i++;
        }
        sysMessage.setText("");
    }

    public void sendSystemMessage() {
        String toSomebody = combobox.getSelectedItem().toString();
        String message = sysMessage.getText() + "\n";
        
        try {
            docs.insertString(docs.getLength(), message, attrset);//���ı�����׷��
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        if (toSomebody.equalsIgnoreCase("������")) {
            sendMsgToAll(message);
        } else {
            Node node;
            node = userLinkList.findUser(toSomebody);
            try {
                node.output.writeObject("ϵͳ��Ϣ");
                node.output.flush();
                node.output.writeObject(message);
                node.output.flush();
            } catch (Exception e) {
            }
            sysMessage.setText("");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
        new ChatServer();
    }
}