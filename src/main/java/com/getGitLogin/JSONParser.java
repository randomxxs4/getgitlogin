package com.getGitLogin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JSONParser {

    /**
     * Метод для получения данных по указанной ссылке
     *
     * @param httpConnection - объект HttpConnection
     * @return содержимое страницы на указанной ссылке в @param httpConnection
     */
    public static String parseUrl(HttpConnection httpConnection) {
        if (httpConnection == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        // открываем соедиение к указанному URL
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getConnection().getInputStream()));
            String inputLine;
            // построчно считываем результат в объект StringBuilder
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * Метод для получения данных из полученного JSON
     *
     * @param resultJson - строка, в которой находится JSON
     * @return List с необходимыми параметрами:
     * 1) количество совпадений с введеным логином
     * 2) логин пользователя
     * 3) id пользователя
     * 4) score пользователя
     */

    public static List<String> parseCurrentLogin(String resultJson) {
        List<String> userParam = new ArrayList<String>();
        try {
            // конвертируем строку с Json в JSONObject для дальнейшего его преобразования
            JSONObject userJsonObject = (JSONObject) JSONValue.parseWithException(resultJson);
            // получаем количество совпадений с заданным параметром
            userParam.add(userJsonObject.get("total_count").toString().trim());
            if (userJsonObject.get("total_count").toString().trim().equals("0")){
                return userParam;
            }
            // получаем массив элементов для поля items
            JSONArray userArray = (JSONArray) userJsonObject.get("items");
            // достаем из массива первый элемент
            JSONObject userData = (JSONObject) userArray.get(0);
            //Добавляем в список значение поля login
            userParam.add(userData.get("login").toString());
            // Добавляем в список значение поля id
            userParam.add(userData.get("id").toString());
            // Добавляем в список значение поля score
            userParam.add(userData.get("score").toString());

        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return userParam;
    }


}
