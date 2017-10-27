package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

//该类是实现服务器端与客户端消息收发的类
public class ClientReceive extends Thread {
    private  JComboBox<String> combobox;
    private  JTextPane textpane;
    
    Socket socket;
    ObjectOutputStream output;
    ObjectInputStream  input;
    JTextField showStatus;
    String username;
	SimpleAttributeSet attrset = new SimpleAttributeSet();
    Document docs;//获得文本对象


    public ClientReceive(Socket socket,ObjectOutputStream output,
        ObjectInputStream  input,JComboBox<String> combobox,JTextPane textpane,JTextField showStatus,String username){

        this.socket = socket;
        this.output = output;
        this.input = input;
        this.combobox = combobox;
        this.textpane = textpane;
    	docs = textpane.getDocument();
        this.showStatus = showStatus;
		this.username = username;
    }
    

    public void run(){
        while(!socket.isClosed()){
            try{
                String type = (String)input.readObject();
                
                if(type.equalsIgnoreCase("系统信息")){
                    String sysmsg = (String)input.readObject();
                    
                    try {
                        docs.insertString(docs.getLength(), "系统信息: "+sysmsg, attrset);//对文本进行追加
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }

                }
                else if(type.equalsIgnoreCase("服务关闭")){
                    output.close();
                    input.close();
                    socket.close();                  
                    try {
                        docs.insertString(docs.getLength(), "服务器已关闭！\n", attrset);//对文本进行追加
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                else if(type.equalsIgnoreCase("聊天信息")){
                    String message = (String)input.readObject();
                    inserts(textpane,message);
					textpane.setCaretPosition(textpane.getDocument()  
						       .getLength());
                }
                else if(type.equalsIgnoreCase("用户列表")){
                    String userlist = (String)input.readObject();
                    String usernames[] = userlist.split("\n");
                    combobox.removeAllItems();
                    
                    int i =0;
                    combobox.addItem("所有人");
                    while(i < usernames.length){
                    	if(!usernames[i].equals(username)){  
                    	  combobox.addItem(usernames[i]);}
                            i++;
                        
                    }
                    combobox.setSelectedIndex(0);
                    showStatus.setText("在线用户 " + usernames.length + " 人");
                }
            }
            catch (IOException | ClassNotFoundException e ){
                System.out.println(e);
            }
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
				   }catch(Exception ee){
					   
				   }
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
}