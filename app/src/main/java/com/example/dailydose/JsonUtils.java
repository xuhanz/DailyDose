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
import java.net.URISyntaxException;
import java.security.AllPermission;
import java.util.*;

public class JsonUtils {

    /**
     * Writes List of Entries to FILENAME
     * USE: Write all entries to "Entries.json" MAKE SURE LIST
     * CONTAINS ALL! Will overwrite "Entries.json"
     * @param entries the entries to write
     * @param fileName the file to write the entries to
     * @return Whether or not the write succeeded
     */
    public static boolean writeEntries(List<Entry> entries, String fileName, Context context) {
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
        try (OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE))) {
            writer.write(jsonStr);
            //writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Get all Entries from fileName
     * @param fileName the file to get all entries from
     * @return All Entry objects in fileName
     */
    public static List<Entry> getEntries(String fileName, Context context)  {
        JSONParser parser = new JSONParser();
        List<Entry> result = new ArrayList<>();
        try (InputStreamReader reader = new InputStreamReader(context.openFileInput(fileName))) {
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
     * Get all of the tags that are currently in the fileName
     * @param fileName file to get all tags from
     * @return A Set of strings representing all tags in the data
     */
    public static Set<String> getAllTags(String fileName, Context context) {
        Set<String> result = new HashSet<>();
        List<Entry> entries = getEntries(fileName, context);
        for (Entry e: entries) {
            for (String tag: e.getTags()) {
                result.add(tag);
            }
        }
        return result;
    }

    /**
     * Delete entry with the given id from fileName
     * @param id the id of the entry to delete
     * @param fileName the file to delete id from
     * @return True if deleted, false if that id is not in the file
     */
    public static boolean delete(int id, String fileName, Context context) {
        List<Entry> entries = getEntries(fileName, context);
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
        writeEntries(entries, fileName, context);
        return found;
    }

    /**
     * Get a particular entry by id
     * USE: to EDIT an ENTRY call GET(id) then change what needs changed,
     * and finally call WRITEENTRY(entry)
     * @param id the entry to retrieve
     * @param fileName the file to get id from
     * @return the entry the id represents, or null if the
     * id does not exist
     */
    public static Entry get(int id, String fileName, Context context) {
        List<Entry> entries = getEntries(fileName, context);
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getId() == id) {
                return entries.get(i);
            }
        }
        return null;
    }

    /**
     * Add an entry to fileName, returns whether it is replacing an existing entry
     * @param entry entry to write
     * @param fileName file to write entry to
     * @return true if replacing existing entry by same id, false if new entry
     */
    public static boolean writeEntry(Entry entry, String fileName, Context context) {
        boolean result = delete(entry.getId(), fileName, context);
        List<Entry> entries = getEntries(fileName, context);
        entries.add(entry);
        writeEntries(entries, fileName, context);
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
