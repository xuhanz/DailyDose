package com.example.dailydose;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JsonFileTests {
    private String testFile = "Entries.json";

    private Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @Test
    public void fileWriteReadTest() {
        JsonUtils.createDataFile(context, testFile);
        List<String> tags = new ArrayList<>();
        tags.add("Store");
        tags.add("Shopping");
        Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
        Entry entry2 = new Entry("Video Games", 8, 2, tags, "");
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);
        entries.add(entry2);
        assertTrue(JsonUtils.writeEntries(entries, testFile, context));

        List<Entry> result = JsonUtils.getEntries(testFile, context);
        assertEquals(2, result.size());
        assertTrue(result.contains(new Entry("Video Games", 8, 2, tags, "")));
        assertTrue(result.contains(new Entry("Went to the store today", 9.5, 1, tags, "")));
    }

    @Test
    public void fileWriteReadEmptyTest() {
        JsonUtils.createDataFile(context, testFile);
        List<Entry> entries = new ArrayList<>();
        assertTrue(JsonUtils.writeEntries(entries, testFile, context));

        List<Entry> result = JsonUtils.getEntries(testFile, context);
        assertTrue(result.isEmpty());
    }

    @Test
    public void getAllTagsTest() {
        JsonUtils.createDataFile(context, testFile);
        List<String> tags = new ArrayList<>();
        tags.add("Store");
        tags.add("Shopping");
        Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
        Entry entry2 = new Entry("Video Games", 8, 2, tags, "");
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);
        entries.add(entry2);
        JsonUtils.writeEntries(entries, testFile, context);

        Set<String> result = JsonUtils.getAllTags(testFile, context);
        assertArrayEquals(tags.toArray(), result.toArray());
    }

    @Test
    public void getAllTagsEmptyTest() {
        JsonUtils.createDataFile(context, testFile);
        List<String> tags = new ArrayList<>();
        Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
        Entry entry2 = new Entry("Video Games", 8, 2, tags, "");
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);
        entries.add(entry2);
        JsonUtils.writeEntries(entries, testFile, context);

        Set<String> result = JsonUtils.getAllTags(testFile, context);
        assertTrue(result.isEmpty());
    }

    @Test
    public void deleteTest() {
        JsonUtils.createDataFile(context, testFile);
        List<String> tags = new ArrayList<>();
        Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
        Entry entry2 = new Entry("Video Games", 8, 2, tags, "");
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);
        entries.add(entry2);
        JsonUtils.writeEntries(entries, testFile, context);

        assertTrue(JsonUtils.delete(1, testFile, context));
        List<Entry> result = JsonUtils.getEntries(testFile, context);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0), entry2);
    }

    @Test
    public void deleteInvalidIdTest() {
        JsonUtils.createDataFile(context, testFile);
        List<String> tags = new ArrayList<>();
        Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
        Entry entry2 = new Entry("Video Games", 8, 2, tags, "");
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);
        entries.add(entry2);
        JsonUtils.writeEntries(entries, testFile, context);

        assertFalse(JsonUtils.delete(34, testFile, context));
    }

    @Test
    public void getTest() {
        JsonUtils.createDataFile(context, testFile);
        List<String> tags = new ArrayList<>();
        Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
        Entry entry2 = new Entry("Video Games", 8, 2, tags, "");
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);
        entries.add(entry2);
        JsonUtils.writeEntries(entries, testFile, context);

        assertEquals(entry, JsonUtils.get(1, testFile, context));
    }

    @Test
    public void getInvalidIdTest() {
        JsonUtils.createDataFile(context, testFile);
        List<String> tags = new ArrayList<>();
        Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
        Entry entry2 = new Entry("Video Games", 8, 2, tags, "");
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);
        entries.add(entry2);
        JsonUtils.writeEntries(entries, testFile, context);

        assertEquals(null, JsonUtils.get(122, testFile, context));
    }

    @Test
    public void addExistingIdTest() {
        JsonUtils.createDataFile(context, testFile);
        List<String> tags = new ArrayList<>();
        Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
        Entry entry2 = new Entry("Video Games", 8, 2, tags, "");
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);
        entries.add(entry2);
        JsonUtils.writeEntries(entries, testFile, context);
        boolean existingID = JsonUtils.writeEntry(entry2, testFile, context);
        List<Entry> result = JsonUtils.getEntries(testFile, context);
        assertEquals(2, result.size());
        assertTrue(result.contains(new Entry("Video Games", 8, 2, tags, "")));
        assertTrue(result.contains(new Entry("Went to the store today", 9.5, 1, tags, "")));
        assertTrue(existingID);
    }

    @Test
    public void addNewIdTest() {
        JsonUtils.createDataFile(context, testFile);
        List<String> tags = new ArrayList<>();
        tags.add("fun");
        Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
        Entry entry2 = new Entry("Video Games", 8, 2, tags, "");
        Entry entry3 = new Entry("Going Swimming", 5, 3, tags, "");
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);
        entries.add(entry2);
        JsonUtils.writeEntries(entries, testFile, context);
        boolean existingID = JsonUtils.writeEntry(entry3, testFile, context);
        List<Entry> result = JsonUtils.getEntries(testFile, context);
        assertEquals(3, result.size());
        assertTrue(result.contains(new Entry("Video Games", 8, 2, tags, "")));
        assertTrue(result.contains(new Entry("Went to the store today", 9.5, 1, tags, "")));
        assertTrue(result.contains(new Entry("Going Swimming", 5, 3, tags, "")));
        assertFalse(existingID);
    }

    @Test
    public void editTest() {
        JsonUtils.createDataFile(context, testFile);
        List<String> tags = new ArrayList<>();
        tags.add("fun");
        Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);
        JsonUtils.writeEntries(entries, testFile, context);

        Entry entry2 = JsonUtils.get(1, testFile, context);
        entry2.setRating(10);
        JsonUtils.writeEntry(entry2, testFile, context);
        List<Entry> result = JsonUtils.getEntries(testFile, context);
        assertEquals(result.size(), 1);
        assertTrue(result.contains(new Entry("Went to the store today", 10, 1, tags, "")));
    }

    @Test
    public void getHighestIdTest() {
        JsonUtils.createDataFile(context, testFile);
        List<String> tags = new ArrayList<>();
        tags.add("fun");
        Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
        Entry entry2 = new Entry("Video Games", 8, 2, tags, "");
        Entry entry3 = new Entry("Going Swimming", 5, 3, tags, "");
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);
        entries.add(entry2);
        JsonUtils.writeEntries(entries, testFile, context);
        JsonUtils.writeEntry(entry3, testFile, context);
        int result = JsonUtils.getHighestID(testFile, context);
        assertEquals(3, result);
    }

    @Test
    public void getHighestIdTestEmpty() {
        JsonUtils.createDataFile(context, testFile);
        int result = JsonUtils.getHighestID(testFile, context);
    }


}
