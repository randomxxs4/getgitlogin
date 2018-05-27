package getGitLogin;

import org.postgresql.util.PSQLException;

import java.sql.*;

public class DdlScript {
    private DBConnection dbConnection;
    private Statement statement;

    /**
     * Cоздает таблицу git_users в бд, если ее не существует.
     */
    public DdlScript() {
        try {
            dbConnection = new DBConnection();
            if (!checkExists()){
                statement.execute("CREATE TABLE git_users(\n"
                        + "login CHAR(64),\n"
                        +"id INTEGER,\n"
                        +"score REAL);");
            }
            statement.close();
            dbConnection.getConnection().close();
        } catch (PSQLException e) {
            System.err.println("Error in ddl script!" + e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверяет наличие таблицы git_users в базе.
     */
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
