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
//主要功能为定义客户端的界面，添加时间监听与事件处理。该类定义了Connect（）与DisConnect（）方法实现与客户端的连接与断开连接。当登陆到指定的服务器时，调用ClientReceive类实现消息收发，同时该类还定义了SendMessaga（）方法来其他用户发送带有表情的消息。
public final class ChatClient extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5374609359679218810L;
	String ip = "127.0.0.1";  //连接到服务端的ip地址
    int port = 8787;         //连接到服务端的端口号
    String userName = "user";    //用户名
    int type = 0;      //，用户连接标记，其中0表示未连接，1表示已连接

    JComboBox<String> combobox;   //选择发送消息的接受者
    JTextPane messageShow;  //客户端的信息显示
    JScrollPane messageScrollPane; //信息显示的滚动条

    JLabel express, sendToLabel, messageLabel;

    JTextField clientMessage;//客户端消息的发送
    JComboBox<String> actionlist;//表情选择
    JButton clientMessageButton;//发送消息
    JTextField showStatus;//显示用户连接状态

    Socket socket;
    ObjectOutputStream output;//网络套接字输出流
    ObjectInputStream input;//网络套接字输入流

    ClientReceive recvThread;

    //建立工具栏
    JToolBar toolBar = new JToolBar();
    //建立工具栏中的按钮组件
    JButton loginButton;//用户登录
    JButton logoffButton;//用户注销
    JButton userButton;//用户信息的设置
    JButton exitButton;//退出按钮
    //框架的大小
    Dimension faceSize = new Dimension(450, 450);

    JPanel downPanel;
    GridBagLayout girdBag;
    GridBagConstraints girdBagCon;
    
    SimpleAttributeSet attrset = new SimpleAttributeSet();
    //StyleConstants.setFontSize(attrset,24);
    //插入内容
    Document docs;//获得文本对象

    public ChatClient() {
        init();//初始化程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();//设置框架的大小
        this.setSize(faceSize);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("聊天室客户端"); //设置标题

        setVisible(true);

    }

    /**
     * 程序初始化函数
     */
    public void init() {

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //初始化按钮
        loginButton = new JButton("登录");
        logoffButton = new JButton("注销");
        userButton = new JButton("用户名设置");
        exitButton = new JButton("退出");
        
        //当鼠标放上显示信息
        loginButton.setToolTipText("连接到指定的服务器");
        logoffButton.setToolTipText("与服务器断开连接");
        userButton.setToolTipText("设置用户信息");

        //将按钮添加到工具栏
        toolBar.add(userButton);
        toolBar.addSeparator();//添加分隔栏
        toolBar.add(loginButton);
        toolBar.add(logoffButton);
        toolBar.addSeparator();//添加分隔栏
        toolBar.add(exitButton);
        
        contentPane.add(toolBar, BorderLayout.NORTH);

        actionlist = new JComboBox<String>();
        actionlist.insertItemAt("表情选择", 0);   
        actionlist.addItem("/沮丧");
        actionlist.addItem("/眩晕");
        actionlist.addItem("/酷酷");
        actionlist.addItem("/大哭");
        actionlist.setSelectedIndex(0);

        
        //初始时
        loginButton.setEnabled(true);
        logoffButton.setEnabled(false);
        
        //添加按钮的事件侦听
        loginButton.addActionListener(this);
        logoffButton.addActionListener(this);
        userButton.addActionListener(this);
        exitButton.addActionListener(this);
        actionlist.addActionListener(this);
        
        combobox = new JComboBox<String>();
        combobox.insertItemAt("所有人", 0);
        combobox.setSelectedIndex(0);

        messageShow = new JTextPane();
        docs = messageShow.getDocument();
        messageShow.setEditable(false);
        //添加滚动条
        messageScrollPane = new JScrollPane(messageShow,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        messageScrollPane.setPreferredSize(new Dimension(400, 400));
        messageScrollPane.revalidate();

        clientMessage = new JTextField(23);
        clientMessage.setEnabled(false);
        clientMessageButton = new JButton();
        clientMessageButton.setText("发送");

        //添加系统消息的事件侦听
        clientMessage.addActionListener(this);
        clientMessageButton.addActionListener(this);

        sendToLabel = new JLabel("发送至:");
        express = new JLabel("         表情:   ");
        messageLabel = new JLabel("发送消息:");
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

        //关闭程序时的操作
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

        if (obj == userButton) { //用户信息设置
            //调出用户信息设置对话框
            UserConf userConf = new UserConf(this, userName);
            userConf.setVisible(true);
            userName = userConf.userInputName;
        } else if (obj == loginButton) { //登录
        	this.setTitle(userName+"的聊天室");
        	Connect();
        } else if (obj == logoffButton) { //注销
            DisConnect();
            showStatus.setText("");
        } else if (obj == actionlist) { //表情发送
        	int index = actionlist.getSelectedIndex();
            if (index != 0) { // ==0表示选中的事第一个
              String icon = actionlist.getSelectedItem().toString();
              clientMessage.setText(clientMessage.getText()+icon);
              actionlist.setSelectedIndex(0);
          }
        } else if (obj == clientMessage || obj == clientMessageButton) { //发送消息
            SendMessage();
            clientMessage.setText("");
        }  else if (obj == exitButton) { //退出
            int j = JOptionPane.showConfirmDialog(
                    this, "真的要退出吗?", "退出",
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
 * 连接服务器
 */
    public void Connect() {
        try {
            socket = new Socket(ip, port);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(
                    this, "不能连接到指定的服务器。\n请确认连接设置是否正确。", "提示",
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
                docs.insertString(docs.getLength(), "连接服务器 " + ip + ":" + port + " 成功...\n", attrset);//对文本进行追加
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            type = 1;//标志位设为已连接
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     *服务器注销
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
            output.writeObject("用户下线");
            output.flush();

            input.close();
            output.close();
            socket.close();
            
            try {
                docs.insertString(docs.getLength(), "已经与服务器断开连接...\n", attrset);//对文本进行追加
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            type = 0;//标志位设为未连接
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
            output.writeObject("聊天信息");
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