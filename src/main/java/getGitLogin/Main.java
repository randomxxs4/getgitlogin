package getGitLogin;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DdlScript ddlScript = new DdlScript();
        if (args.length == 0){
            System.out.println("Error, to search for users, run the application with the parameters!");
        }
        else if (args.length <=5 ){
            for (int i = 0; i < args.length; i++) {
                int temp = i;
                System.out.print(++temp + ". ");
                Search searcher = new Search();
                searcher.search(args[i]);
            }
        }
        else {
            System.out.println("The entered number of arguments is invalid! (Enter from 1 to 5 parameters)");
        }
    }
}