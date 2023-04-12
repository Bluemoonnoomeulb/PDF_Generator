package org.application.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProcessorJSON {

    public static JSONArray jsonArray;

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
