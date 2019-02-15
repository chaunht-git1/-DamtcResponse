package Bean;

import static Bean.Provider.CONNECTION_URL;
import static Bean.Provider.DRIVER;
import static Bean.Provider.PASSWORD;
import static Bean.Provider.USERNAME;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private static Connection con = null;

    static {
        try {

            Class.forName(DRIVER);

            con = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public static Connection getCon() {

        return con;
    }

    public static void reconnectdbastatic() {

        try {

            Class.forName(DRIVER);

            con = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
        }

    }
}
