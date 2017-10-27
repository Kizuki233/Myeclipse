package Server;

import java.io.IOException;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
//������ʵ�ַ�������Ϣ�շ����࣬����ֱ�������ĳ�û��������˷�����Ϣ�ķ��������͵���Ϣ����ʾ����������Ľ����ϡ�
public class ServerReceive extends Thread {

	JTextPane textpane;
    JTextField textfield;
    JComboBox<String> combobox;
    Node client;
    UserLinkList userLinkList;
    public boolean isStop;
    
	SimpleAttributeSet attrset = new SimpleAttributeSet();
    Document docs;//����ı�����

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
                if (type.equalsIgnoreCase("������Ϣ")) {
                    String toSomebody = (String) client.input.readObject();
                    String message = (String) client.input.readObject();
                    String msg = client.username + "�� " + toSomebody + "˵ : " + message+"\n";
                 
                    inserts(textpane,msg);//�ͻ��˵���Ϣչʾ�ڷ����
					textpane.setCaretPosition(textpane.getDocument()  
						       .getLength());
              
                    if (toSomebody.equalsIgnoreCase("������")) {
                        sendToAll(msg);
                    } 
                    
                    else {
                        try {
                            client.output.writeObject("������Ϣ");
                            client.output.flush();
                            client.output.writeObject(msg);
                            client.output.flush();
                        } catch (Exception e) {
                        }
                        Node node = userLinkList.findUser(toSomebody);
                        if (node != null) {
                            node.output.writeObject("������Ϣ");
                            node.output.flush();
                            node.output.writeObject(msg);
                            node.output.flush();
                        }
                    }} 
                 else if (type.equalsIgnoreCase("�û�����")) {
                    Node node = userLinkList.findUser(client.username);
                    userLinkList.delUser(node);
                    String msg = "�û� " + client.username + " ����\n";
                    int count = userLinkList.getCount();
                    combobox.removeAllItems();
                    combobox.addItem("������");
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
                        docs.insertString(docs.getLength(), msg, attrset);//���ı�����׷��
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }

                    textfield.setText("�����û�" + userLinkList.getCount() + "��\n");
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
                node.output.writeObject("������Ϣ");
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
		int length=s.length();//��ȡString ����
		  char[] every=new char[length];
		  int count=0;//��ʼ�ַ���λ�ã��仯
		  
		  Boolean flag=false;
		  for(int i=0;i<length;i++)
		  {
		   every[i]=s.charAt(i);
		     if(every[i]=='/')  //ʶ����Ϣ���Ƿ���/��
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
		       str=s.substring(count,i); //�õ�����ǰ������
		    
		    try{
		     if(str!=null)
		       doc.insertString(doc.getLength(), str, attrset);//AAAA��ӱ���ǰ������
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
                node.output.writeObject("�û��б�");
                node.output.flush();
                node.output.writeObject(userlist);
                node.output.flush();
            } catch (Exception e) {
            }
            i++;
        }
    }
}