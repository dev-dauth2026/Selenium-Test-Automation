package com.ecommerce.utils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CountryConfigReader {

    public static List<String> getAllowedCountries() {
        JSONParser parser = new JSONParser();
        List<String> countries = new ArrayList<>();
        try {
            FileReader reader = new FileReader("src/test/resources/data/countries.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray jsonArray = (JSONArray) jsonObject.get("allowedCountries");
            for (Object obj : jsonArray) {
                countries.add((String) obj);
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read country config: " + e.getMessage(), e);
        }
        return countries;
    }
}
