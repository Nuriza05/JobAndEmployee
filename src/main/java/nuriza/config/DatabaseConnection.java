package nuriza.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @created : Lenovo Nuriza
 **/
public class DatabaseConnection {
    public static Connection getConnection(){
        try {
            System.out.println("Success");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
return null;
    }
}
