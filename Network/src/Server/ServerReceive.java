package Server;

import java.io.IOException;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
//该类是实现服务器消息收发的类，该类分别定义了向某用户及所有人发送消息的方法，发送的消息会显示在主界面类的界面上。
public class ServerReceive extends Thread {

	JTextPane textpane;
    JTextField textfield;
    JComboBox<String> combobox;
    Node client;
    UserLinkList userLinkList;
    public boolean isStop;
    
	SimpleAttributeSet attrset = new SimpleAttributeSet();
    Document docs;//获得文本对象

    public ServerReceive(JTextPane textpane, JTextField textfield, JComboBox<String> combobox, Node client, UserLinkList userLinkList) {
        this.textpane = textpane;
    	docs = textpane.getDocument();
        this.textfield = textfield;
        this.client = client;
        this.userLinkList = userLinkList;
        this.combobox = combobox;
        isStop = false;
    }

    public void run() {
        sendUserList();
        while (!isStop && !client.socket.isClosed()) {
            try {
                String type = (String) client.input.readObject();
                if (type.equalsIgnoreCase("聊天信息")) {
                    String toSomebody = (String) client.input.readObject();
                    String message = (String) client.input.readObject();
                    String msg = client.username + "对 " + toSomebody + "说 : " + message+"\n";
                 
                    inserts(textpane,msg);//客户端的信息展示在服务端
					textpane.setCaretPosition(textpane.getDocument()  
						       .getLength());
              
                    if (toSomebody.equalsIgnoreCase("所有人")) {
                        sendToAll(msg);
                    } 
                    
                    else {
                        try {
                            client.output.writeObject("聊天信息");
                            client.output.flush();
                            client.output.writeObject(msg);
                            client.output.flush();
                        } catch (Exception e) {
                        }
                        Node node = userLinkList.findUser(toSomebody);
                        if (node != null) {
                            node.output.writeObject("聊天信息");
                            node.output.flush();
                            node.output.writeObject(msg);
                            node.output.flush();
                        }
                    }} 
                 else if (type.equalsIgnoreCase("用户下线")) {
                    Node node = userLinkList.findUser(client.username);
                    userLinkList.delUser(node);
                    String msg = "用户 " + client.username + " 下线\n";
                    int count = userLinkList.getCount();
                    combobox.removeAllItems();
                    combobox.addItem("所有人");
                    int i = 0;
                    while (i < count) {
                        node = userLinkList.findUser(i);
                        if (node == null) {
                            i++;
                            continue;
                        }
                        combobox.addItem(node.username);
                        i++;
                    }
                    combobox.setSelectedIndex(0);
                    try {
                        docs.insertString(docs.getLength(), msg, attrset);//对文本进行追加
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }

                    textfield.setText("在线用户" + userLinkList.getCount() + "人\n");
                    sendToAll(msg);
                    sendUserList();
                    break;
                }
            } catch (IOException | ClassNotFoundException e) {
            }
        }
    }

    public void sendToAll(String msg) {
        int count = userLinkList.getCount();
        int i = 0;
        while (i < count) {
            Node node = userLinkList.findUser(i);
            if (node == null) {
                i++;
                continue;
            }
            try {
                node.output.writeObject("聊天信息");
                node.output.flush();
                node.output.writeObject(msg);
                node.output.flush();
            } catch (Exception e) {
            }
            i++;
        }
    }
   
    public void inserts(JTextPane p,String s){
		Document doc=p.getStyledDocument();
		int length=s.length();//获取String 长度
		  char[] every=new char[length];
		  int count=0;//初始字符的位置，变化
		  
		  Boolean flag=false;
		  for(int i=0;i<length;i++)
		  {
		   every[i]=s.charAt(i);
		     if(every[i]=='/')  //识别信息中是否有/号
		    	 flag=true;
		  }
		  for(int i=0;i<length;i++)
		  {
			  every[i]=s.charAt(i);
			  
			   if(flag==false)
			    break;
		   if(every[i]=='/')
		   {
			   String str=null;
		       str=s.substring(count,i); //得到表情前的文字
		    
		    try{
		     if(str!=null)
		       doc.insertString(doc.getLength(), str, attrset);//AAAA添加表情前的文字
		    }catch(Exception e){
		     System.out.println("a big error here");
		    }
			    String icName;
			    icName=s.substring(i+1, i+3);  
			    String pics=icName+".jpg";
			     p.setCaretPosition(doc.getLength());//
	           pics = "D:\\eclipse\\workspace\\Network\\src\\" + pics;
	
			      p.insertIcon(new ImageIcon(pics));
			   count=i+3;
			   if(count==length){
				   try{
					   doc.insertString(doc.getLength(), "\n",attrset);
				   }catch(Exception ee){ }
			   }
		    }
		  }
		 
		  if(count<length)
		  {
			  String theLast=null;
			  theLast=s.substring(count, length);
			  try{
		          doc.insertString(doc.getLength(), theLast+"\n",attrset);
			  }catch(Exception e){
				  System.out.println("a big error here");
			  	}
		  }
	}
    
    
    
    public void sendUserList() {

    	String userlist = "";
        int count = userLinkList.getCount();
        int i = 0;
        while (i < count) {
            Node node = userLinkList.findUser(i);
            if (node == null) {
                i++;
                continue;
            }
            userlist += node.username;
            userlist += '\n';
            i++;
        }
        i = 0;
        while (i < count) {
            Node node = userLinkList.findUser(i);
            if (node == null) {
                i++;
                continue;
            }
            try {
                node.output.writeObject("用户列表");
                node.output.flush();
                node.output.writeObject(userlist);
                node.output.flush();
            } catch (Exception e) {
            }
            i++;
        }
    }
}