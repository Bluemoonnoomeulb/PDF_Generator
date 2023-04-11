package org.application;

import org.json.JSONArray;
import org.json.JSONObject;
import org.application.api.WorkingAPI;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class PeopleInfoGenerator {

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

    public static String[][] printPeople(JSONArray peoples) throws IOException {
        Set<Integer> indexes = generateIndex(peoples.length());
        String[][] res = new String[peoples.length()][14];
        Iterator iterator = indexes.iterator();
        for (int i = 0; i < peoples.length(); i++) {
            JSONObject person = peoples.getJSONObject(i);
            String cityBirth = person.getString("City");
            String[] personInfo = new String[14];

            personInfo[0] = person.getString("LastName");
            personInfo[1] = person.getString("FirstName");
            personInfo[2] = person.getString("FatherName");
            personInfo[3] = person.getString("Gender");
            personInfo[4] = String.valueOf(person.getInt("YearsOld"));
            personInfo[5] = person.getString("DateOfBirth");

            if (!matchCity()) {
                JSONObject obj = new JSONObject(WorkingAPI.returnJSONResponse(WorkingAPI.getCityOfBirthURI()));
                cityBirth = obj.getString("City");
            }
            personInfo[6] = cityBirth;
            personInfo[7] = iterator.next().toString();
            personInfo[8] = person.getString("Country");
            personInfo[9] = person.getString("Region");
            personInfo[10] = person.getString("City");
            personInfo[11] = person.getString("Street");
            personInfo[12] = String.valueOf(person.getInt("House"));
            personInfo[13] = String.valueOf(person.getInt("Apartment"));

            res[i] = personInfo;
        }
        return res;
    }


}
