import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class signup extends JFrame{
    private JPanel signup_panel;
    private JButton signup_ok_button;
    private JButton signup_back_button;
    private JCheckBox signup_showpass_checkbox;
    private JTextField signup_username_txtfield;
    private JPasswordField signup_password_passfield;
    private JTextField signup_fullname_txtfield;
    private JTextField signup_tckn_txtfield;
    private JTextField signup_phone_txtfield;
    private JTextField signup_email_txtfield;
    private JTextField signup_adress_txtfield;
    private JLabel signup_username_label;
    private JLabel signup_pass_label;
    private JLabel signup_fullname_label;
    private JLabel signup_tckn_label;
    private JLabel signup_phone_label;
    private JLabel signup_email_label;
    private JLabel signup_adress_label;
    private JPasswordField passwordField1;


    Connection conn = DBConnection.ConnectDb();
    PreparedStatement ps = null;


    public signup() {

        add(signup_panel);
        setSize(500, 500);
        setTitle("SIGN UP PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        signup_ok_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "INSERT INTO user(username, password, fullname, tckn, phone, email, adress) VALUES(?,?,?,?,?,?,?)";

                try {

                    ps = conn.prepareStatement(sql);


                    ps.setString(1, signup_username_txtfield.getText());
                    String pw1 = signup_password_passfield.getText();
                    String pw2 = passwordField1.getText();
                    if(!pw1.equals(pw2)){
                        JOptionPane.showMessageDialog(null,"NOT EQUAL");
                        return;
                    }
                    ps.setString(2, signup_password_passfield.getText());
                    ps.setString(3, signup_fullname_txtfield.getText());
                    ps.setString(4, signup_tckn_txtfield.getText());
                    ps.setString(5, signup_phone_txtfield.getText());
                    ps.setString(6, signup_email_txtfield.getText());
                    ps.setString(7, signup_adress_txtfield.getText());
                    ps.execute();



                    JOptionPane.showMessageDialog(null, "Account is Successfully created!");


                } catch (SQLException e6) {
                    JOptionPane.showMessageDialog(null, e6);
                } finally {
                    try {
                        ps.close();
                    } catch (Exception e2) {
                    }
                }

            }
        });

        signup_back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                userlogin ul = new userlogin();
                ul.setVisible(true);


            }
        });
        signup_showpass_checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (signup_showpass_checkbox.isSelected()){
                    signup_password_passfield.setEchoChar((char)0);
                }
                else {
                    signup_password_passfield.setEchoChar('*');
                }

            }
        });
    }
}
