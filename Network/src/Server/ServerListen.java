package Server;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

import java.io.*;
import java.net.*;
//����ʵ�ַ������û����������ߵļ�����������û��������ߵļ�����ͨ�������û������ࣨUserLinkList����ʵ�ֵġ����û�������������������仯ʱ������������Ľ��������Ӧ���޸ġ�
public class ServerListen extends Thread {

    ServerSocket server;
    JComboBox<String> combobox;
    JTextPane textpane;
	SimpleAttributeSet attrset = new SimpleAttributeSet();
    Document docs;//����ı�����
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
                    docs.insertString(docs.getLength(), "�û� " + client.username + " ����" + "\n", attrset);//���ı�����׷��
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }

                textfield.setText("�����û�" + userLinkList.getCount() + "��\n");
                recvThread = new ServerReceive(textpane, textfield, combobox, client, userLinkList);
                recvThread.start();
            } catch (IOException | ClassNotFoundException e) {
            }
        }
    }
}