import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection ConnectDb(){
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:D:\\Project\\CARGONLINE\\onlinecargo.db");
            System.out.println("Connected");
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e + "");
        }
        return  conn;
    }
}