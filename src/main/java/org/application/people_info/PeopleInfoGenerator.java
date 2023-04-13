package org.application.people_info;

import org.application.json.ProcessorJSON;
import org.json.JSONArray;
import org.json.JSONObject;
import org.application.api.WorkingAPI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PeopleInfoGenerator {

    /*
        Метод, определяющий соответствие города рождения и проживания.
        true -> соответствует.
     */
    private static boolean matchCity() {
        Random random = new Random();
        return random.nextBoolean();
    }

    //  Метод генерации индекса.
    private static Set<Integer> generateIndex(int count) {
        Random random = new Random();
        Set<Integer> numberSet = new HashSet<>();
        int length = 0;

        while (length < count) {
            numberSet.add(random.nextInt(100000, 1000000));
            if (numberSet.size() == length + 1) { length++; }
        }

        return numberSet;
    }

    private static int countYears(String birthDate) {
        LocalDate startDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate endDate = LocalDate.now();
        Period period = Period.between(startDate, endDate);
        return period.getYears();
    }

    //  Блок модификации данных, полученных от API.
    private static String modifyGender(String gender) { return gender.substring(0,3).toUpperCase(); }

    private static String modifyCity(String city) { return city.substring(3); }

    private static String modifyStreet(String street) {
        if (street.contains(" ул.")) {
            street = street.substring(0, street.indexOf(" ул."));
        }
        return street;
    }

    private static String modifyDate(String date) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate resultDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return resultDate.format(formatter);
    }

    public static String[][] assembleAllPeopleInfo() {
        JSONArray peoples = ProcessorJSON.jsonArray;
        Set<Integer> indexes = generateIndex(peoples.length());
        String[][] res = new String[peoples.length()][14];
        Iterator<Integer> iterator = indexes.iterator();

        for (int i = 0; i < peoples.length(); i++) {
            JSONObject person = peoples.getJSONObject(i);
            String cityBirth = person.getString("City");
            String[] personInfo = new String[14];

            personInfo[0] = person.getString("LastName");
            personInfo[1] = person.getString("FirstName");
            personInfo[2] = person.getString("FatherName");

            personInfo[3] = modifyGender(person.getString("Gender"));

            personInfo[4] = String.valueOf(countYears(person.getString("DateOfBirth")));

            personInfo[5] = modifyDate(person.getString("DateOfBirth"));

            if (!matchCity()) {
                JSONObject obj = new JSONObject(WorkingAPI.returnJSONResponse(WorkingAPI.getCityOfBirthURI()));
                cityBirth = obj.getString("City");
            }
            personInfo[6] = modifyCity(cityBirth);
            personInfo[7] = iterator.next().toString();

            personInfo[8] = person.getString("Country");
            personInfo[9] = person.getString("Region");

            personInfo[10] = modifyCity(person.getString("City"));
            personInfo[11] = modifyStreet(person.getString("Street"));

            personInfo[12] = String.valueOf(person.getInt("House"));
            personInfo[13] = String.valueOf(person.getInt("Apartment"));

            res[i] = personInfo;
        }
        return res;
    }


}
