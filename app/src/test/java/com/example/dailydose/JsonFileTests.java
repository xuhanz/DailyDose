package com.example.dailydose;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonFileTests {
    @Test
    public void fileReadWriteTest() throws JSONException {
        List<String> tags = new ArrayList<>();
        tags.add("Store");
        tags.add("Shopping");
        Entry entry = new Entry("Went to the store today", 9.5, 1, tags);
        Entry entry2 = new Entry("Video Games", 8, 2, tags);
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);
        entries.add(entry2);
        JsonUtils.writeEntries(entries);
        assertEquals(4, 2 + 2);
    }

    @Test
    public void fileReadTest() throws JSONException {
        List<Entry> result = JsonUtils.getEntries();
        assertEquals(2, result.size());
    }
}
