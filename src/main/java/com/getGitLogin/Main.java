package com.getGitLogin;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                if (checkEnglish(args[i])){
                    Search searcher = new Search();
                    searcher.search(args[i]);
                }
                else{
                    System.out.println("Enter login in English!!!");
                }
            }
        }
        else {
            System.out.println("The entered number of arguments is invalid! (Enter from 1 to 5 parameters)");
        }
    }

    /**
     * Метод производит проверку, является ли введенная строка, написанной на английском языке.
     *
     * @param str проверяемая строка.
     * @return the boolean
     */
    public static boolean checkEnglish(String str){
        Pattern pattern = Pattern.compile(
                "[" +                   //начало списка допустимых символов
                        "a-zA-Z" +    //буквы английского алфавита
                        "\\d" +         //цифры
                        "\\s" +         //знаки-разделители
                        "\\p{Punct}" +  //знаки пунктуации
                        "]" +                   //конец списка допустимых символов
                        "*");                   //допускается наличие указанных символов в любом количестве
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}