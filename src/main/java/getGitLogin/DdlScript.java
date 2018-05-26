package getGitLogin;

import org.postgresql.util.PSQLException;

import java.sql.*;

public class DdlScript {
    public DdlScript() {
        try {
            DBConnection dbConnection = new DBConnection();
            Statement statement = dbConnection.getConnection().createStatement();
            statement.execute("CREATE TABLE git_login(\n"
                    + "login CHAR(64),\n"
                    +"id INTEGER,\n"
                    +"score REAL);");
            statement.close();
            dbConnection.getConnection().close();
        } catch (PSQLException e) {
            System.err.println("Failed to create connection" + e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
