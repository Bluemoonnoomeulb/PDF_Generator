package org.example;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class PeopleGenerator {

    public static void printPeople(JSONArray peoples) {
        for (int i = 0; i < peoples.length(); i++) {
            JSONObject person = peoples.getJSONObject(i);
            System.out.println(person.getString("LastName"));
            System.out.println(person.getString("FirstName"));
            System.out.println(person.getString("FatherName"));
            System.out.println(person.getString("Gender"));
            System.out.println(person.getString("DateOfBirth"));
            System.out.println(person.getInt("YearsOld"));
            System.out.println("---------------");
        }
    }


}
