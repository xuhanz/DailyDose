package com.example.dailydose;
import java.util.*;

public class TagAnalysis {
	/**
	 * Calculate the average rating of entries with the given tag
	 * @param tag the tag to calculate the average rating of
	 * @param filename the file to pull entries from
	 * @return Double of average rating; returns 0 if there are no entries or none with tag
	 */
	public static double getAvgRating(String tag, String filename) {

		double rating = 0;
		int entriesWithTag = 0;
		List<Entry> entries = JsonUtils.getEntries(filename);
		// if tags or entries do not exist, immediately exit
		if (entries == null || !JsonUtils.getAllTags(filename).contains(tag)) { return 0; }
		for (Entry entry : entries) {  // iterate through entries
			if (entry.getTags().contains(tag)) {  // if entry has tag, add rating
				rating += entry.getRating();
				entriesWithTag++;
			}
		}
		return rating / entriesWithTag;
	}
}
