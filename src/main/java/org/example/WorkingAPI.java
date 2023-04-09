package org.example;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class WorkingAPI {

    final static private String cityOfBirthURI = "https://api.randomdatatools.ru/?unescaped=false&params=City";

    static private String mainInfoURI = "https://api.randomdatatools.ru/?typeName=all&unescaped=false&" +
            "params=LastName,FirstName,FatherName,Gender,DateOfBirth,YearsOld,Country,Region,City,Street,Apartment,House";

    public static void setQuantity(int n) { mainInfoURI += "&count=" + n; }

    public static String getMainURI() { return mainInfoURI; }

    public static String getCityOfBirthURI() { return cityOfBirthURI; }

    public static String returnJSONResponse(String uri) throws IOException {
        Content getResult = Request.Get(uri).execute().returnContent();
        return getResult.asString();
    }
}
