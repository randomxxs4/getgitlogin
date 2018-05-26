package getGitLogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Search {

    /**
     * Метод поиска пользователя на гитхаб.
     *
     * @param gitLogin the git login
     */
    public void search(String gitLogin){
        try {
            DBConnection dbConnection = new DBConnection();
            HttpConnection httpConnection = new HttpConnection(gitLogin);
            String jsonString = JSONParser.parseUrl(httpConnection);
            List<String> userParameter = JSONParser.parseCurrentLogin(jsonString);
            if (userParameter.get(0).equals("0")){
                System.out.println("Such a user does not exist! Please enter a different parameter!");
            }
            else if (userParameter.get(0).equals("1")){
                if (dbConnection.CheckInDB(gitLogin)){
                    dbConnection.print(gitLogin);
                }
                else {
                    dbConnection.putInDB(userParameter.get(1), Integer.parseInt(userParameter.get(2)), Double.parseDouble(userParameter.get(3)));
                    dbConnection.print(gitLogin);
                }
            }
            else {
                System.out.println( "More than one result, please specify your username!");
            }
            httpConnection.getConnection().getInputStream().close();
            dbConnection.getConnection().close();
        } catch (SQLException e) {
        System.err.println("Could not create connection to the database" + e);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
