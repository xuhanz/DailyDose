package com.example.dailydose;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TagAnalysisTests {
	private String testFile = "TagTestFile.json";

	@Test
	public void SingleTagAvgTest() {
		List<String> tags = new ArrayList<>();
		tags.add("Gaming");
		Entry entry = new Entry("Video Games", 10, 1, tags);
		List<Entry> entries = new ArrayList<>();
		entries.add(entry);
		assertTrue(JsonUtils.writeEntries(entries, testFile));

		assertEquals(10, TagAnalysis.getAvgRating("Gaming", testFile), 0.0);
	}

	@Test
	public void MultipleTagAvgTest() {
		List<String> tags = new ArrayList<>();
		List<String> tags2 = new ArrayList<>();
		tags.add("Store");
		tags.add("Shopping");
		Entry entry = new Entry("Went to the store today", 9.5, 1, tags);
		tags2.add("Shopping");
		Entry entry2 = new Entry("Shopped online", 7.5, 2, tags2);
		List<Entry> entries = new ArrayList<>();
		entries.add(entry);
		entries.add(entry2);
		assertTrue(JsonUtils.writeEntries(entries, testFile));

		assertEquals(8.5, TagAnalysis.getAvgRating("Shopping", testFile), 0.0);
		assertEquals(9.5, TagAnalysis.getAvgRating("Store", testFile), 0.0);
	}

	@Test
	public void NoEntriesTagAvgTest() {
		List<Entry> entries = new ArrayList<>();
		assertTrue(JsonUtils.writeEntries(entries, testFile));

		assertEquals(0, TagAnalysis.getAvgRating("Gaming", testFile), 0.0);
	}

	@Test
	public void NoneWithTagAvgTest() {
		List<String> tags = new ArrayList<>();
		tags.add("Gaming");
		Entry entry = new Entry("Video Games", 10, 1, tags);
		List<Entry> entries = new ArrayList<>();
		entries.add(entry);
		assertTrue(JsonUtils.writeEntries(entries, testFile));

		assertEquals(0, TagAnalysis.getAvgRating("Shopping", testFile), 0.0);
	}
}
