import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userlogin extends JFrame{
    private JPanel user_login;
    private JLabel username_label;
    private JLabel password_label;
    private JButton userlogin_button;
    private JLabel login_logo_label;
    private JButton user_adminlogin_button;
    private JTextField loginusername_txtfield;
    private JPasswordField login_password_passfield;
    private JButton signup_button;
    private JCheckBox usershowpass_checkbox;

    Connection conn = DBConnection.ConnectDb();
    PreparedStatement ps = null;
    ResultSet rs = null;

    //constructor
    public userlogin() {

        ImageIcon cargologo = new ImageIcon("cargologo.png");
        login_logo_label.setIcon(cargologo);
        login_logo_label.setSize(100,100);

        add(user_login);
        setSize(500, 500);
        setTitle("USER LOGIN PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userlogin_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "SELECT * FROM user where username=? and password=?";

                try {

                    ps=conn.prepareStatement(sql);
                    ps.setString(1,loginusername_txtfield.getText());
                    ps.setString(2,login_password_passfield.getText());
                    rs= ps.executeQuery();

                    if (rs.next()){

                        JOptionPane.showMessageDialog(null,"SUCCESFULLY LOGGED IN!");
                        dispose();
                        userpanel up = new userpanel(rs.getString("username"));
                        up.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "LOGIN FAILED!","Failure",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e6) { JOptionPane.showMessageDialog(null, e6); }

                finally {

                        try { rs.close(); ps.close(); }
                        catch (Exception e2){ }
                }
            }
        });


        signup_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                signup su = new signup();
                su.setVisible(true);

            }
        });
        user_adminlogin_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                adminlogin al = new adminlogin();
                al.setVisible(true);


            }
        });
        usershowpass_checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (usershowpass_checkbox.isSelected()){
                    login_password_passfield.setEchoChar((char)0);
                }
                else {
                    login_password_passfield.setEchoChar('*');
                }

            }
        });
    }
}



