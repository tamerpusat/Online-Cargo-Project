import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminpanel extends JFrame{
    private JPanel admin_panel;
    private JTextField admin_item_txtfield;
    private JTextField admin_sendername_txtfield;
    private JTextField admin_receivername_txtfield;
    private JTextField admin_adress_txtfield;
    private JTextField admin_status_txtfield;
    private JTextField admin_trackingnumber_txtfield;
    private JButton admin_ok_button;
    private JButton liveChatButton;
    private JButton admin_logout_button;
    private JLabel admin_item_label;
    private JLabel admin_sendername_label;
    private JLabel admin_receivername_label;
    private JLabel admin_adress_label;
    private JLabel admin_status_label;
    private JLabel admin_trackingnumber_label;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton updateButton;


    Connection conn = DBConnection.ConnectDb();
    PreparedStatement ps = null;
    ResultSet rs = null;


    public adminpanel() {


        add(admin_panel);
        setSize(500, 500);
        setTitle("ADMIN PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        admin_ok_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "INSERT INTO cargo(item,sendername,receivername,adress,status,trackingnumber) VALUES(?,?,?,?,?,?)";

                try {
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, admin_item_txtfield.getText());
                    ps.setString(2, admin_sendername_txtfield.getText());
                    ps.setString(3, admin_receivername_txtfield.getText());
                    ps.setString(4, admin_adress_txtfield.getText());
                    ps.setString(5, admin_status_txtfield.getText());
                    ps.setString(6, admin_trackingnumber_txtfield.getText());

                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "CARGO REQUEST DONE!");

                } catch (SQLException e6) { JOptionPane.showMessageDialog(null, e6); }

                finally {
                    try {  ps.close(); }
                    catch (Exception e4){ e4.printStackTrace(); }
                }

            }

        });

        liveChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                adminlivechat alc = new adminlivechat();
                alc.setVisible(true);

            }
        });
        admin_logout_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                adminlogin al = new adminlogin();
                al.setVisible(true);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "SELECT * FROM cargo where trackingnumber=?";

                try {

                    ps=conn.prepareStatement(sql);

                    ps.setString(1,admin_trackingnumber_txtfield.getText());
                    rs=ps.executeQuery();

                    if (rs.next()){

                        String add = rs.getString("item");
                        admin_item_txtfield.setText(add);

                        String add1 = rs.getString("sendername");
                        admin_sendername_txtfield.setText(add1);

                        String add2 = rs.getString("receivername");
                        admin_receivername_txtfield.setText(add2);

                        String add3 = rs.getString("adress");
                        admin_adress_txtfield.setText(add3);

                        String add4 = rs.getString("status");
                        admin_status_txtfield.setText(add4);

                    }

                    else {
                        JOptionPane.showMessageDialog(null, "Tracking number does not exist!","Failure",JOptionPane.ERROR_MESSAGE);
                    }

                }

                catch (SQLException e6) { JOptionPane.showMessageDialog(null, e6); }

                finally {
                    try { rs.close(); ps.close(); }
                    catch (Exception e2){ e2.printStackTrace();}

                }

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "Delete from cargo where trackingnumber=?";

                        try{
                            ps=conn.prepareStatement(sql);
                            ps.setString(1,admin_trackingnumber_txtfield.getText());
                            ps.execute();
                            JOptionPane.showMessageDialog(null,"Cargo Deleted");

                        } catch (SQLException e4){ e4.printStackTrace(); }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "Update cargo SET item=?, sendername=?, receivername=?, adress=?, status=? where trackingnumber=?";

                try{

                    ps=conn.prepareStatement(sql);

                    ps.setString(1,admin_item_txtfield.getText());
                    ps.setString(2,admin_sendername_txtfield.getText());
                    ps.setString(3,admin_receivername_txtfield.getText());
                    ps.setString(4,admin_adress_txtfield.getText());
                    ps.setString(5,admin_status_txtfield.getText());
                    ps.setString(6,admin_trackingnumber_txtfield.getText());
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Informations are updated!");

                }
                catch (SQLException e2){ e2.printStackTrace();}
                finally {
                    try { ps.close(); }
                    catch (Exception e3){ e3.printStackTrace(); }

                }
            }
        });
    }


}
