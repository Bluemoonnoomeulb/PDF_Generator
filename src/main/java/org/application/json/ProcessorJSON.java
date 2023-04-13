package org.application.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProcessorJSON {

    public static JSONArray jsonArray;

    /*
        Метод приведения к некоторой однообразной структуре ответа JSON.
        {"ALL":[{},{}]}.
        Получаем из JSON -> JSONArray.
        Даже если исходный JSON содержал один элемент, а не список, то работаем с ним, как с элементом списка.
    */
    private static String modifyStructure(String json) {
        String finalJSON = "{\"All\":";
        if (json.charAt(0) != '[') { json = "[" + json + "]"; }
        finalJSON += json + "}";
        return finalJSON;
    }

    public static void SplitData(String json) {
        JSONObject obj = new JSONObject(modifyStructure(json));
        jsonArray = obj.getJSONArray("All");
    }
}
