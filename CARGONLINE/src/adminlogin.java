import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminlogin extends JFrame{
    private JPanel admin_login;
    private JTextField adminlogin_username_txtfield;
    private JPasswordField adminlogin_password_passfield;
    private JButton adminlogin_login_button;
    private JButton adminlogin_back_button;
    private JLabel adminlogin_icon_label;
    private JCheckBox adminlogin_showpassword_checkbox;
    private JLabel adminlogin_username_label;
    private JLabel adminlogin_password_label;

    Connection conn = DBConnection.ConnectDb();
    PreparedStatement ps = null;
    ResultSet rs = null;

    public adminlogin() {

        ImageIcon cargologo = new ImageIcon("cargologo.png");
        adminlogin_icon_label.setIcon(cargologo);
        adminlogin_icon_label.setSize(100,100);

        add(admin_login);
        setSize(500, 500);
        setTitle("ADMIN LOGIN PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        adminlogin_login_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "SELECT * FROM admin where username=? and password=?";

                try {

                    ps=conn.prepareStatement(sql);
                    ps.setString(1,adminlogin_username_txtfield.getText());
                    ps.setString(2,adminlogin_password_passfield.getText());
                    rs= ps.executeQuery();

                    if (rs.next()){

                        JOptionPane.showMessageDialog(null,"SUCCESFULLY LOGGED IN!");
                        dispose();
                        adminpanel ap = new adminpanel();
                        ap.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "LOGIN FAILED!","Failure",JOptionPane.ERROR_MESSAGE);
                    }
                }

                catch (SQLException e6) { JOptionPane.showMessageDialog(null, e6); }

                finally {
                    try { rs.close(); ps.close(); }
                    catch (Exception e2){ }

                }

            }
        });
        adminlogin_back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                userlogin ul  = new userlogin();
                ul.setVisible(true);

            }
        });
        adminlogin_showpassword_checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (adminlogin_showpassword_checkbox.isSelected()){
                    adminlogin_password_passfield.setEchoChar((char)0);
                }
                else {
                    adminlogin_password_passfield.setEchoChar('*');
                }

            }
        });
    }
}
