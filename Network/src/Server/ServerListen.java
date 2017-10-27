package Server;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

import java.io.*;
import java.net.*;
//该类实现服务器用户上线与下线的监听。该类对用户上线下线的监听是通过调用用户链表类（UserLinkList）来实现的。当用户上线与下线情况发生变化时，该类会对主类的界面进行相应的修改。
public class ServerListen extends Thread {

    ServerSocket server;
    JComboBox<String> combobox;
    JTextPane textpane;
	SimpleAttributeSet attrset = new SimpleAttributeSet();
    Document docs;//获得文本对象
    JTextField textfield;
    UserLinkList userLinkList;
    Node client;
    ServerReceive recvThread;
    public boolean isStop;

    public ServerListen(ServerSocket server, JComboBox<String> combobox, JTextPane textpane, JTextField textfield, UserLinkList userLinkList) {
        this.server = server;
        this.combobox = combobox;
        this.textpane = textpane;
    	docs = textpane.getDocument();
        this.textfield = textfield;
        this.userLinkList = userLinkList;
        isStop = false;
    }


    public void run() {
        while (!isStop && !server.isClosed()) {
            try {
                client = new Node();
                client.socket = server.accept();
                client.output = new ObjectOutputStream(client.socket.getOutputStream());
                client.output.flush();
                client.input = new ObjectInputStream(client.socket.getInputStream());
                client.username = (String) client.input.readObject();
                combobox.addItem(client.username);
                userLinkList.addUser(client);
                
                try {
                    docs.insertString(docs.getLength(), "用户 " + client.username + " 上线" + "\n", attrset);//对文本进行追加
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }

                textfield.setText("在线用户" + userLinkList.getCount() + "人\n");
                recvThread = new ServerReceive(textpane, textfield, combobox, client, userLinkList);
                recvThread.start();
            } catch (IOException | ClassNotFoundException e) {
            }
        }
    }
}