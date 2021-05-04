package com.example.dailydose;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.JsonWriter;
import org.json.simple.*;
import org.json.simple.*;

//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class JsonUtils {
    private static boolean emptyFile = true;
    /**
     * Writes List of Entries to "Entries.json"
     * USE: Write all entries to "Entries.json" MAKE SURE LIST
     * CONTAINS ALL! Will overwrite "Entries.json"
     * @param entries the entries to write
     * @return Whether or not the write succeeded
     */
    public static boolean writeEntries(List<Entry> entries) {
        JSONArray entryArr = new JSONArray();
        for (Entry e : entries) {
            JSONObject entryDetails = new JSONObject();
            entryDetails.put("id", e.getId());
            entryDetails.put("content", e.getContent());
            entryDetails.put("tags", e.getTags());
            entryDetails.put("rating", e.getRating());
            entryArr.add(entryDetails);
        }

        String jsonStr;
        if (entryArr.isEmpty()) {
            jsonStr = "";
            emptyFile = true;
        } else {
            jsonStr = entryArr.toJSONString();
            emptyFile = false;
        }
        try (FileWriter writer = new FileWriter("Entries.json")) {
            writer.write(jsonStr);
            writer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Get all Entries from "Entries.json"
     * USE: Make sure to keep list and only manipulate the part of it
     * you need to edit or delete if you plan to write back to "Entries.json".
     * Can only add all back at once agaon
     * @return All Entry objects in "Entries.json"
     */
    public static List<Entry> getEntries()  {
        JSONParser parser = new JSONParser();
        List<Entry> result = new ArrayList<>();
        try (FileReader reader = new FileReader("Entries.json")) {
            Object obj = parser.parse(reader);

            JSONArray entryArr = (JSONArray) obj;
            entryArr.forEach(o -> {
                result.add(parseEntry((JSONObject)o));
            });
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            //expected behavior for empty file
            return result;
        }
    }

    /**
     * Parses JSON Object back into an Entry
     * @param entryObject JSON Object from file
     * @return Entry that entryObject represented
     */
    private static Entry parseEntry(JSONObject entryObject) {
        Long l = (long) entryObject.get("id");
        return new Entry((String) entryObject.get("content"), (double) entryObject.get("rating"),
                l.intValue() , (List<String>) entryObject.get("tags"));
    }


    /**
     * Gets a set of all tags used in all entries
     * @return set of Strings of all tags used (empty if no entries or tags)
     */
    private static Set<String> getAllTags() {
        List<Entry> entries = getEntries();  // get entries
        Set<String> tags = new HashSet<>();  // new set of tags
        if (entries == null) { return tags; }  // if no entries, return empty set
        for (Entry entry : entries) {  // iterate through entries to add tags
            tags.addAll(entry.getTags());
        }
        return tags;
    }
}
