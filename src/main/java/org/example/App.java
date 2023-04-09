package org.example;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;


public class App
{
    public static void main( String[] args ) throws IOException {
        PeopleGenerator p = new PeopleGenerator();
        p.modifyURI(10);

        Content getResult = Request.Get("https://api.randomdatatools.ru/?count=30&typeName=all&unescaped=false&params=LastName,FirstName,FatherName,YearsOld,Gender,DateOfBirth,City,Address")
                .execute().returnContent();
        //System.out.println(getResult.asString());
        String url = "{\"all\":";
        url += getResult.toString();
        url += "}";
        JSONObject obj = new JSONObject(url);
        JSONArray all = obj.getJSONArray("all");
        int n = all.length();
        for (int i = 0; i < n; i++) {
            JSONObject person = all.getJSONObject(i);
            System.out.println(person.getString("FirstName"));
            System.out.println(person.getString("LastName"));
            System.out.println(person.getString("FatherName"));
            System.out.println(person.getInt("YearsOld"));
            System.out.println(person.getString("Gender"));
            System.out.println(person.getString("DateOfBirth"));
            System.out.println("__________________________");
        }
    }
}
