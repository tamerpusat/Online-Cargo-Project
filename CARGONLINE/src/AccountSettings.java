import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountSettings extends JFrame {
    private JPanel AccountSettings;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton changeButton;
    private JTextField textField3;
    private JButton updateButton;
    private JButton updateButton1;
    private JButton updateButton2;
    private JButton BACKButton;
    private JCheckBox showPasswordCheckBox;


    //private String username;

    Connection conn = DBConnection.ConnectDb();
    PreparedStatement ps = null;


    public AccountSettings(String username) {

    //this.username = username;



        add(AccountSettings);
        setSize(500, 500);
        setTitle("ACCOUNT SETTINGS");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "Update User SET phone=? where username=?";

                try {
                    if (textField1.getText().length()>0) {
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, textField1.getText());
                        ps.setString(2, username);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Phone Number Changed!");
                    }
                    else {

                        JOptionPane.showMessageDialog(null, "Phone can not be empty!", "Failure",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }


            }
        });
        updateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "Update User SET email=? where username=?";

                try {
                    if (textField2.getText().length()>0) {
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, textField2.getText());
                        ps.setString(2, username);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "E-mail Changed!");
                    }
                    else {

                        JOptionPane.showMessageDialog(null, "E-mail can not be empty!", "Failure",JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }
        });
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "Update User SET password=? where username=?";

                try {
                    if (passwordField1.getText().length()>0) {
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, passwordField1.getText());
                        ps.setString(2, username);
                        ps.execute();
                        JOptionPane.showMessageDialog(null, "Password Changed!");
                    }
                    else {

                        JOptionPane.showMessageDialog(null, "Password can not be empty!", "Failure",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e4) {
                    e4.printStackTrace();
                }
            }
        });
        updateButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "Update User SET Adress=? where username=?";

                try {

                    if (textField3.getText().length()>0){
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, textField3.getText());
                        ps.setString(2,username);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Adress Changed!");
                    }
                    else {

                        JOptionPane.showMessageDialog(null, "Adress can not be empty!", "Failure",JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }


        });
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (showPasswordCheckBox.isSelected()){
                    passwordField1.setEchoChar((char)0);
                }
                else {
                    passwordField1.setEchoChar('*');
                }

            }
        });
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                userpanel up = new userpanel(username);
                up.setVisible(true);
            }
        });
    }
}