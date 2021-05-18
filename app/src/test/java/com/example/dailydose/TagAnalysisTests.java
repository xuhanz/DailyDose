package com.example.dailydose;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Hashtable;
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
		Entry entry = new Entry("Video Games", 10, 1, tags, "");
		entries.add(entry);

		assertEquals(10, TagAnalysis.getAvgRating(entries), 0.0);
	}

	@Test
	public void MultipleAvgTest() {
		List<String> tags = new ArrayList<>();
		List<String> tags2 = new ArrayList<>();
		tags.add("Store");
		tags.add("Shopping");
		Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
		tags2.add("Shopping");
		Entry entry2 = new Entry("Shopped online", 7.5, 2, tags2, "");
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
		Entry entry = new Entry("Video Games", 10, 1, tags, "");
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
		Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
		tags2.add("Shopping");
		Entry entry2 = new Entry("Shopped online", 7.5, 2, tags2, "");
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
		Entry entry = new Entry("Video Games", 10, 1, tags, "");
		List<Entry> entries = new ArrayList<>();
		entries.add(entry);

		assertEquals(new ArrayList<>(), TagAnalysis.filterEntriesByTag(entries, "Store"));
	}

	@Test
	public void GetSingleTagTest() {
		List<String> tags = new ArrayList<>();
		tags.add("Gaming");
		Entry entry = new Entry("Video Games", 10, 1, tags, "");
		List<Entry> entries = new ArrayList<>();
		entries.add(entry);

		Set<String> correctEntries = new HashSet<>();
		correctEntries.add("Gaming");

		assertEquals(correctEntries, TagAnalysis.getTagsFromEntries(entries));
	}

	@Test
	public void GetMultipleTagTest() {
		List<String> tags = new ArrayList<>();
		List<String> tags2 = new ArrayList<>();
		List<String> tags3 = new ArrayList<>();
		tags.add("Store");
		tags.add("Shopping");
		Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
		tags2.add("Shopping");
		Entry entry2 = new Entry("Shopped online", 7.5, 2, tags2, "");
		List<Entry> entries = new ArrayList<>();
		tags3.add("Gaming");
		Entry entry3 = new Entry("Video Games", 10, 3, tags3, "");
		entries.add(entry);
		entries.add(entry2);
		entries.add(entry3);

		Set<String> correctEntries = new HashSet<>();
		correctEntries.add("Gaming");
		correctEntries.add("Store");
		correctEntries.add("Shopping");

		assertEquals(correctEntries, TagAnalysis.getTagsFromEntries(entries));
	}

	@Test
	public void NoEntriesGetTagTest() {
		List<Entry> entries = new ArrayList<>();

		assertEquals(new HashSet<>(), TagAnalysis.getTagsFromEntries(entries));
	}

	@Test
	public void NullGetTagTest() {
		assertEquals(new HashSet<>(), TagAnalysis.getTagsFromEntries(null));
	}

	@Test
	public void GetSingleTagAvgTest() {
		List<String> tags = new ArrayList<>();
		tags.add("Gaming");
		Entry entry = new Entry("Video Games", 10, 1, tags, "");
		List<Entry> entries = new ArrayList<>();
		entries.add(entry);

		Dictionary<String, Double> correctEntries = new Hashtable<>();
		correctEntries.put("Gaming", 10.0);

		assertEquals(correctEntries, TagAnalysis.getAllTagAvg(entries));
	}

	@Test
	public void GetMultipleTagAvgTest() {
		List<String> tags = new ArrayList<>();
		List<String> tags2 = new ArrayList<>();
		List<String> tags3 = new ArrayList<>();
		tags.add("Store");
		tags.add("Shopping");
		Entry entry = new Entry("Went to the store today", 9.5, 1, tags, "");
		tags2.add("Shopping");
		Entry entry2 = new Entry("Shopped online", 7.5, 2, tags2, "");
		List<Entry> entries = new ArrayList<>();
		tags3.add("Gaming");
		Entry entry3 = new Entry("Video Games", 10, 3, tags3, "");
		entries.add(entry);
		entries.add(entry2);
		entries.add(entry3);

		Dictionary<String, Double> correctEntries = new Hashtable<>();
		correctEntries.put("Gaming", 10.0);
		correctEntries.put("Store", 9.5);
		correctEntries.put("Shopping", 8.5);

		assertEquals(correctEntries, TagAnalysis.getAllTagAvg(entries));
	}

	@Test
	public void NoEntriesGetTagAvgTest() {
		List<Entry> entries = new ArrayList<>();

		assertEquals(new Hashtable<>(), TagAnalysis.getAllTagAvg(entries));
	}

	@Test
	public void NullGetTagAvgTest() {
		assertEquals(new Hashtable<>(), TagAnalysis.getAllTagAvg(null));
	}

	@Test
	public void getAvgRatingByDateTest() {
		List<Entry> entries = new ArrayList<>();
		entries.add(new Entry("a", 9.0, 1, new ArrayList<String>(), "05/10/2021"));
		entries.add(new Entry("a", 9.0, 2, new ArrayList<String>(), "05/11/2021"));
		entries.add(new Entry("a", 9.0, 3, new ArrayList<String>(), "05/17/2021"));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2021);
		cal.set(Calendar.DAY_OF_MONTH,11);
		cal.set(Calendar.MONTH,4);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		Date startDate = cal.getTime();

		cal.set(Calendar.YEAR, 2021);
		cal.set(Calendar.DAY_OF_MONTH,18);
		cal.set(Calendar.MONTH,4);
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE,59);
		cal.set(Calendar.SECOND,59);
		cal.set(Calendar.MILLISECOND,0);
		Date endDate = cal.getTime();


		Map<Date, Double> result = TagAnalysis.getAvgRatingByDate(startDate, endDate, entries);
		assertEquals(result.keySet().size(), 2);
	}

	@Test
	public void getAvgRatingsByDateMultipleOnSameDayTest() {
		List<Entry> entries = new ArrayList<>();
		entries.add(new Entry("a", 9.0, 1, new ArrayList<String>(), "05/10/2021"));
		entries.add(new Entry("a", 9.0, 2, new ArrayList<String>(), "05/11/2021"));
		entries.add(new Entry("a", 9.0, 3, new ArrayList<String>(), "05/17/2021"));
		entries.add(new Entry("a", 6.5, 4, new ArrayList<String>(), "05/17/2021"));
		entries.add(new Entry("a", 6.0, 5, new ArrayList<String>(), "05/17/2021"));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2021);
		cal.set(Calendar.DAY_OF_MONTH,11);
		cal.set(Calendar.MONTH,4);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		Date startDate = cal.getTime();

		cal.set(Calendar.YEAR, 2021);
		cal.set(Calendar.DAY_OF_MONTH,18);
		cal.set(Calendar.MONTH,4);
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE,59);
		cal.set(Calendar.SECOND,59);
		cal.set(Calendar.MILLISECOND,0);
		Date endDate = cal.getTime();

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date testDate = format.parse("5/17/2021");
			Map<Date, Double> result = TagAnalysis.getAvgRatingByDate(startDate, endDate, entries);
			assertEquals(result.keySet().size(), 2);
			assertEquals((double) result.get(testDate), 21.5/3, 0.01);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
