package getGitLogin;

import org.postgresql.util.PSQLException;

import java.sql.*;

public class DBConnection implements AutoCloseable{
    private static final String URL = "jdbc:postgresql://localhost:5432/git_login";
    private static final String USERNAME = "git";
    private static final String PASSWORD = "git";
    private Connection connection;

    public DBConnection() {
        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Failed to create connection" + e);
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws Exception {
        connection.close();
    }

    /**
     * Проверяет существует ли такой логин в базе данных
     *
     * @param gitLogin логин на гитхаб
     * @return true, если запись есть. false, если записи нет.
     */
    public boolean CheckInDB(String gitLogin){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM git_users WHERE login='"+gitLogin+"';");
            statement.close();
        } catch (PSQLException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (resultSet != null){
            return true;
        }
        else return false;
    }

    /**
     * Печатает на консоль данные пользователя гитхаб.
     *
     * @param gitLogin логин на гитхаб
     */
    public void print(String gitLogin){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM git_users WHERE login='"+gitLogin+"';");
            while (resultSet.next()) {
                System.out.println("login={"+resultSet.getString("login").trim()
                        +"} id={"+ resultSet.getInt("id")
                        +"} score={"+ resultSet.getDouble("score")+"}");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Помещает данные в базу.
     *
     * @param gitLogin the git login
     * @param id       the id
     * @param score    the score
     */
    public void putInDB(String gitLogin, int id, double score){
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute( "INSERT INTO git_users(login, id, score) values('"+gitLogin+"', "+id+", "+score+");");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
