package Server;

import java.awt.*;
import java.awt.event.*;
import java.net.ServerSocket;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
//主要功能为定义服务器端的界面，添加时间监听与时间处理。调用ServerListen类来实现服务端用户上线与下线的监听，调用ServerReceive来实现服务器端的消息收发。
public final class ChatServer extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4743788058714759795L;
	//如果我们不希望通过编译来强制划分软件版本，即实现序列化接口的实体能够兼容先前版本
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
    Document docs;//获得文本对象

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
        this.pack();//调整此窗口的大小，以适合其子组件的首选大小和布局
        this.setSize(faceSize);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("聊天室服务器端");
        setVisible(true);
    }

    public void init() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());//

        startServer = new JButton("启动服务");
        stopServer = new JButton("停止服务");
        exitButton = new JButton("退出");
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
        combobox.insertItemAt("所有人", 0);
        combobox.setSelectedIndex(0);
        messageShow = new JTextPane();
        docs = messageShow.getDocument();
        messageShow.setEditable(false);
        messageScrollPane = new JScrollPane(messageShow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        messageScrollPane.setPreferredSize(new Dimension(300, 300));
        messageScrollPane.revalidate();//把布局管理器对应的容器的子组件重新布局并绘制。
        showStatus = new JTextField(25);
        showStatus.setEditable(false);
        sysMessage = new JTextField(25);
        sysMessage.setEnabled(false);
        sysMessageButton = new JButton();
        sysMessageButton.setText("发送");
        sysMessage.addActionListener(this);
        sysMessageButton.addActionListener(this);
        sendToLabel = new JLabel("发送至:");
        messageLabel = new JLabel("系统消息:");
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
            int j = JOptionPane.showConfirmDialog(this, "真的停止服务吗?", "停止服务", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (j == JOptionPane.YES_OPTION) {
                stopService();
            }
        } else if (obj == exitButton) {
            int j = JOptionPane.showConfirmDialog(this, "真的要退出吗?", "退出", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
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
            serverSocket = new ServerSocket(port, 2);//参数 backlog 指定客户连接请求队列的长度
            try {
                docs.insertString(docs.getLength(), "服务端已经启动，在" + port + "端口侦听...\n", attrset);//对文本进行追加
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
                docs.insertString(docs.getLength(), "服务端已经关闭\n", attrset);//对文本进行追加
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            combobox.removeAllItems();
            combobox.addItem("所有人");
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
                node.output.writeObject("服务关闭");
                node.output.flush();//flush()表示强制将缓冲区中的数据发送出去
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
                node.output.writeObject("系统信息");
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
            docs.insertString(docs.getLength(), message, attrset);//对文本进行追加
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        if (toSomebody.equalsIgnoreCase("所有人")) {
            sendMsgToAll(message);
        } else {
            Node node;
            node = userLinkList.findUser(toSomebody);
            try {
                node.output.writeObject("系统信息");
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