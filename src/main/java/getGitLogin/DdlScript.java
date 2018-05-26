package getGitLogin;

import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.List;

public class DdlScript {
    private DBConnection dbConnection;
    private Statement statement;

    /**
     * Instantiates a new Ddl script.
     */
    public DdlScript() {
        try {
            dbConnection = new DBConnection();
            if (!checkExists()){
                statement.execute("CREATE TABLE git_login(\n"
                        + "login CHAR(64),\n"
                        +"id INTEGER,\n"
                        +"score REAL);");
            }
            statement.close();
            dbConnection.getConnection().close();
        } catch (PSQLException e) {
            System.err.println("Failed to create connection" + e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkExists() throws SQLException {
        statement = dbConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from pg_tables where tablename !~'^pg'; ");
        while (resultSet.next()){
            if (resultSet.getString("tablename").equals("git_users")){
                return true;
            }
        }
        return false;
    }
}
