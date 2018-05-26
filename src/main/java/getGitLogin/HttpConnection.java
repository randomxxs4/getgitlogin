package getGitLogin;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnection {
    private String gitURL = "https://api.github.com/search/users?q=";
    private HttpURLConnection connection;

    public HttpConnection(String gitLogin) {
        this.gitURL = gitURL + gitLogin;
        try {
            URL url = new URL(gitURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpURLConnection getConnection() {
        return connection;
    }
}
