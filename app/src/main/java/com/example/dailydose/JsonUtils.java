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
    public static void writeJson(List<Entry> entries) throws JSONException {
       /* OutputStream out = new FileOutputStream("Entries.json");
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        System.out.println(writer);
        writer.setIndent("  ");
        writeEntry(writer, entry);
        writer.close(); */
        JSONArray entryArr = new JSONArray();
        for (Entry e : entries) {
            JSONObject entryDetails = new JSONObject();
            entryDetails.put("id", e.getId());
            entryDetails.put("content", e.getContent());
            entryDetails.put("tags", e.getTags());
            entryDetails.put("rating", e.getRating());
            entryArr.add(entryDetails);
        }

        try (FileWriter writer = new FileWriter("Entries.json")) {
            writer.write(entryArr.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*public static void writeEntry(JsonWriter writer, Entry entry) throws IOException {
        writer.beginObject();
        writer.name("id");
        writer.value(entry.getId());
        writer.name("content");
        writer.value(entry.getContent());
        if (entry.getTags() != null) {
            writer.name("tags");
            writeTagsArray(writer, entry.getTags());
        } else {
            writer.name("tags");
            writer.nullValue();
        }
        writer.name("rating");
        writer.value(entry.getRating());
        writer.endObject();
    }


    public static void writeTagsArray(JsonWriter writer, List<String> tags) throws IOException {
        writer.beginArray();
        for (String tag : tags) {
            writer.value(tag);
        }
        writer.endArray();
    }
*/
  /*  static String getJsonFromAssets(Context context) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open("Entries.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }
*/

    public static List<Entry> getEntries()  {
       /* String jsonFileContents = getJsonFromAssets(context);
        Gson gson = new Gson();
        Type listEntryType = new TypeToken<List<Entry>>(){}.getType();
        List<Entry> entries = gson.fromJson(jsonFileContents, listEntryType);
        return entries;*/
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("Entries.json")) {
            Object obj = parser.parse(reader);

            JSONArray entryArr = (JSONArray) obj;
            List<Entry> result = new ArrayList<>();
            entryArr.forEach(o -> {
                try {
                    result.add(parseEntry((JSONObject)o));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Entry parseEntry(JSONObject entryObject) throws JSONException {
        Long l = (long) entryObject.get("id");
        return new Entry((String) entryObject.get("content"), (double) entryObject.get("rating"),
                l.intValue() , (List<String>) entryObject.get("tags"));
    }

}
