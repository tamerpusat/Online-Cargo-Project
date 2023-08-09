import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.Socket;

public class userlivechat extends JFrame{

    private JPanel user_livechat;
    private JTextField user_livechat_txfield;
    private JButton user_livechatsend_button;
    private JTextArea user_livechat_txtarea;
    private JButton backButton;

    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;



    public userlivechat() {

        add(user_livechat);
        setSize(500, 500);
        setTitle("USER CHAT");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        user_livechatsend_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String msgout = user_livechat_txfield.getText();
                    user_livechat_txtarea.setText(user_livechat_txtarea.getText()+"\n You: "+msgout);
                    dout.writeUTF(msgout);
                    user_livechat_txfield.setText("");
                } catch (Exception e2){ e2.printStackTrace(); }
            }
        });

        Thread thread = new Thread(){

            public void run(){

                try {
                    s = new Socket("localhost", 1201);

                    din = new DataInputStream(s.getInputStream());
                    dout = new DataOutputStream(s.getOutputStream());

                    while (!s.isClosed()) {
                        if (din.available() > 0) {
                            String msginput = din.readUTF();
                            user_livechat_txtarea.setText(user_livechat_txtarea.getText() + "\n Admin: " + msginput);
                        }

                    }
                } catch (Exception e) { e.printStackTrace(); }
            }
        };
        thread.start();



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                try {
                    s.close();
                } catch (Exception e3){
                    e3.printStackTrace();
                }


            }
        });

    }




    }






