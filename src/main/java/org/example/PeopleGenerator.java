package org.example;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class PeopleGenerator {

    private static boolean matchCity() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private static Set<Integer> generateIndex(int count) {
        Random random = new Random();
        Set<Integer> numberSet = new HashSet<>();

        for (int i = 0; i < count; i++) {
            numberSet.add(random.nextInt(100000, 999999));
        }

        return numberSet;
    }

    public static void printPeople(JSONArray peoples) throws IOException {
        Set<Integer> indexes = generateIndex(peoples.length());
        Iterator iterator = indexes.iterator();
        for (int i = 0; i < peoples.length(); i++) {
            JSONObject person = peoples.getJSONObject(i);
            String cityBirth = person.getString("City");

            System.out.println(person.getString("LastName"));
            System.out.println(person.getString("FirstName"));
            System.out.println(person.getString("FatherName"));
            System.out.println(person.getString("Gender"));
            System.out.println(person.getString("DateOfBirth"));
            System.out.println(person.getInt("YearsOld"));
            if (!matchCity()) {
                JSONObject obj = new JSONObject(WorkingAPI.returnJSONResponse(WorkingAPI.getCityOfBirthURI()));
                cityBirth = obj.getString("City");
            }
            System.out.println(cityBirth);
            System.out.println(iterator.next());
            System.out.println(person.getString("Country"));
            System.out.println(person.getString("Region"));
            System.out.println(person.getString("City"));
            System.out.println(person.getString("Street"));
            System.out.println(person.getInt("Apartment"));
            System.out.println(person.getInt("House"));
            System.out.println("---------------");
        }
    }


}
