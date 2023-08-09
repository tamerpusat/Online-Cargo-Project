import javax.swing.*;
import java.sql.Connection;


public class Main {

    public static void main(String[] args) {

        Connection conn = DBConnection.ConnectDb();


        //windows form
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                userlogin ul = new userlogin();
                ul.setVisible(true);



            }
        });

    }




}
