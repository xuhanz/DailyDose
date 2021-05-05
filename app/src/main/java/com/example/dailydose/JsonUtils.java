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
        } else {
            jsonStr = entryArr.toJSONString();
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
     * Get all of the tags that are currently in the data file
     * @return A Set of strings representing all tags in the data
     */
    public static Set<String> getAllTags() {
        Set<String> result = new HashSet<>();
        List<Entry> entries = getEntries();
        for (Entry e: entries) {
            for (String tag: e.getTags()) {
                result.add(tag);
            }
        }
        return result;
    }

    /**
     * Delete entry with the given id
     * @param id the id of the entry to delete
     * @return True if deleted, false if that id is not in the file
     */
    public static boolean delete(int id) {
        List<Entry> entries = getEntries();
        boolean found = false;
        int position = -1;
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getId() == id) {
                found = true;
                position = i;
            }
        }

        if (found) {
            entries.remove(position);
        }
        writeEntries(entries);
        return found;
    }

    /**
     * Get a particular entry by id
     * USE: to EDIT an ENTRY call GET(id) then change what needs changed,
     * and finally call WRITEENTRY(entry)
     * @param id the entry to retrieve
     * @return the entry the id represents, or null if the
     * id does not exist
     */
    public static Entry get(int id) {
        List<Entry> entries = getEntries();
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getId() == id) {
                return entries.get(i);
            }
        }
        return null;
    }

    /**
     * Add an entry to the database, returns whether it is replacing an existing entry
     * @param entry entry to write
     * @return true if replacing existing entry by same id, false if new entry
     */
    public static boolean writeEntry(Entry entry) {
        boolean result = delete(entry.getId());
        List<Entry> entries = getEntries();
        entries.add(entry);
        writeEntries(entries);
        return result;
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
}
