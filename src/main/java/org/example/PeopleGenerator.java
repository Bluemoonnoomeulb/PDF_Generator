package org.example;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class PeopleGenerator {

    private String t = "https://api.randomdatatools.ru/?typeName=all&unescaped=false&params=LastName,FirstName,FatherName,YearsOld,Gender,DateOfBirth,City,Address&count=";

    public void modifyURI(int n) { t += n; }
    public String returnJson(String uri) throws IOException {
        Content getResult = Request.Get(uri).execute().returnContent();
        return getResult.asString();
    }

    public void printPeople(String json) {

    }


}
