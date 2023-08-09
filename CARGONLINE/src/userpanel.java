import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userpanel extends JFrame{
    private JPanel user_panel;
    private JTextField user_trackingnumber_txtfield;
    private JButton user_search_button;
    private JTextField user_item_txtfield;
    private JTextField user_sendername_txtfield;
    private JTextField user_receivername_txtfield;
    private JTextField user_receiveradress_txtfield;
    private JTextField user_cargostatus_txtfield;
    private JButton user_logout_button;
    private JButton user_livechat_button;
    private JLabel forcargorequest_label;
    private JLabel status_label;
    private JLabel adress_label;
    private JLabel receiver_label;
    private JLabel sender_label;
    private JLabel item_label;
    private JLabel trackingnumber_label;
    private JButton ACCOUNTSETTINGSButton;


   public String username;

    Connection conn = DBConnection.ConnectDb();
    PreparedStatement ps = null;
    ResultSet rs = null;


    public userpanel(String username) {
        this.username = username;



        add(user_panel);
        setSize(500, 500);
        setTitle("USER PAGE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        user_search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "SELECT * FROM cargo where trackingnumber=?";

                try {

                    ps=conn.prepareStatement(sql);
                    ps.setString(1,user_trackingnumber_txtfield.getText());
                    rs=ps.executeQuery();

                    if (rs.next()){

                        String add = rs.getString("item");
                        user_item_txtfield.setText(add);

                        String add1 = rs.getString("sendername");
                        user_sendername_txtfield.setText(add1);

                        String add2 = rs.getString("receivername");
                        user_receivername_txtfield.setText(add2);

                        String add3 = rs.getString("adress");
                       user_receiveradress_txtfield.setText(add3);

                        String add4 = rs.getString("status");
                        user_cargostatus_txtfield.setText(add4);

                    }

                    else {
                        JOptionPane.showMessageDialog(null, "Tracking number does not exist!","Failure",JOptionPane.ERROR_MESSAGE);
                    }

                }

                catch (SQLException e6) { JOptionPane.showMessageDialog(null, e6); }

                finally {
                    try { rs.close(); ps.close(); }
                    catch (Exception e2){ }

                }

            }
        });

        user_livechat_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                userlivechat ulc = new userlivechat();
                ulc.setVisible(true);

            }
        });

        user_logout_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(null,"Sure? You want to exit?", "ARE u sure", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (JOptionPane.YES_OPTION == result){
                    dispose();
                    userlogin up = new userlogin();
                    up.setVisible(true);
                }



            }
        });

        ACCOUNTSETTINGSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AccountSettings ac = new AccountSettings(username);
                ac.setVisible(true);
            }
        });
    }
}
