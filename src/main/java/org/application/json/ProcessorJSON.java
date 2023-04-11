package org.application.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProcessorJSON {

    public String modifyStructure(String json) {
        String finalJSON = "{\"All\":";
        if (json.charAt(0) != '[') { json = "[" + json + "]"; }
        finalJSON += json + "}";
        return finalJSON;
    }

    public JSONArray SplitData(String json) {
        JSONObject obj = new JSONObject(json);
        JSONArray allValues = obj.getJSONArray("All");
        return allValues;
    }
}
