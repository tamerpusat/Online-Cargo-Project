import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

 public class adminlivechat extends JFrame {
     private JTextField admininput_txtfield;
     private JButton admin_send_button;
     private JTextArea admin_txtarea;
     private JPanel admin_livechat;
     private JButton backButton;

     static ServerSocket skt;
     static Socket s;
     static DataInputStream din;
     static DataOutputStream dout;


     public adminlivechat() {


         add(admin_livechat);
         setSize(500, 500);
         setTitle("ADMIN CHAT");
         setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


         admin_send_button.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

                 try {
                     String msgout = admininput_txtfield.getText();
                     admin_txtarea.setText(admin_txtarea.getText() + "\n You: " + msgout);
                     dout.writeUTF(msgout);
                     admininput_txtfield.setText("");

                 } catch (Exception e1) { e1.printStackTrace();}
             }
         });

         Thread thread = new Thread(){

             public void run(){

                 try {
                     skt = new ServerSocket(1201);
                     s = skt.accept();
                     din = new DataInputStream(s.getInputStream());
                     dout = new DataOutputStream(s.getOutputStream());

                     while (!skt.isClosed()) {
                         if (din.available() > 0) {
                             String msginput = din.readUTF();
                             admin_txtarea.setText(admin_txtarea.getText() + "\n User: " + msginput);
                         }

                     }
                 }
                 catch (Exception e) { e.printStackTrace(); }
             }
         };
         thread.start();

         backButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 setVisible(false);
                 try {
                     skt.close();
                     s.close();
                 } catch (Exception e3){
                     e3.printStackTrace();
                 }
             }
         });
     }

 }



