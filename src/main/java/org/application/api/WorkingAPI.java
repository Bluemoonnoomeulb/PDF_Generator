package org.application.api;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class WorkingAPI {

    final static private String cityOfBirthURI = "https://api.randomdatatools.ru/?unescaped=false&params=City";
    static private String mainInfoURI = "https://api.randomdatatools.ru/?typeName=all&unescaped=false&" +
            "params=LastName,FirstName,FatherName,Gender,DateOfBirth,Country,Region,City,Street,Apartment,House";

    public static void setQuantity(int n) { mainInfoURI += "&count=" + n; }

    public static String getMainURI() { return mainInfoURI; }

    public static String getCityOfBirthURI() { return cityOfBirthURI; }

    public static String returnJSONResponse(String url) {
        try {
            Content getResult = Request.Get(url).execute().returnContent();
            return getResult.asString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }

    }
}
