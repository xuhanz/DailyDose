package com.example.dailydose;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TagAnalysisTests {
	private String testFile = "TagTestFile.json";

	@Test
	public void SingleTagAvgTest() {
		List<String> tags = new ArrayList<>();
		List<Entry> entries = new ArrayList<>();
		tags.add("Gaming");
		Entry entry = new Entry("Video Games", 10, 1, tags);
		entries.add(entry);

		assertEquals(10, TagAnalysis.getAvgRating(entries), 0.0);
	}

	@Test
	public void MultipleAvgTest() {
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

		assertEquals(8.5, TagAnalysis.getAvgRating(entries), 0.0);
	}

	@Test
	public void NoEntriesTagTest() {
		List<Entry> entries = new ArrayList<>();

		assertEquals(0, TagAnalysis.getAvgRating(entries), 0.0);
	}

	@Test
	public void NullEntriesTagTest() {
		assertEquals(0, TagAnalysis.getAvgRating(null), 0.0);
	}

	@Test
	public void SingleTagFilterTest() {
		List<String> tags = new ArrayList<>();
		tags.add("Gaming");
		Entry entry = new Entry("Video Games", 10, 1, tags);
		List<Entry> entries = new ArrayList<>();
		entries.add(entry);

		List<Entry> correctEntries = new ArrayList<>();
		correctEntries.add(entry);

		assertEquals(correctEntries, TagAnalysis.filterEntriesByTag(entries, "Gaming"));
	}

	@Test
	public void MultipleTagFilterTest() {
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

		List<Entry> correctEntries1 = new ArrayList<>();
		correctEntries1.add(entry);
		List<Entry> correctEntries2 = new ArrayList<>();
		correctEntries2.add(entry);
		correctEntries2.add(entry2);

		assertEquals(correctEntries1, TagAnalysis.filterEntriesByTag(entries, "Store"));
		assertEquals(correctEntries2, TagAnalysis.filterEntriesByTag(entries, "Shopping"));
	}

	@Test
	public void NoEntriesTagFilterTest() {
		List<Entry> entries = new ArrayList<>();

		assertEquals(new ArrayList<>(), TagAnalysis.filterEntriesByTag(entries, "Store"));
	}

	@Test
	public void NullTagFilterTest() {
		assertEquals(new ArrayList<>(), TagAnalysis.filterEntriesByTag(null, "Store"));
	}

	@Test
	public void NoneWithTagFilterTest() {
		List<String> tags = new ArrayList<>();
		tags.add("Gaming");
		Entry entry = new Entry("Video Games", 10, 1, tags);
		List<Entry> entries = new ArrayList<>();
		entries.add(entry);

		assertEquals(new ArrayList<>(), TagAnalysis.filterEntriesByTag(entries, "Store"));
	}
}
