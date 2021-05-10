package com.example.dailydose;
import android.content.Context;

import java.lang.reflect.Array;
import java.util.*;

public class TagAnalysis {
	/**
	 * Filters a list of entries for those with the given tag
	 * @param entries the entries to filter by tags
	 * @param tag the tag to filter by
	 * @return List of entries with tag; returns empty list if entries arg null or empty
	 */
	public static List<Entry> filterEntriesByTag(List<Entry> entries, String tag) {
		List<Entry> filteredEntries = new ArrayList<>();
		if (entries == null || entries.size() == 0) { return filteredEntries; }
		for (Entry entry : entries) {
			if (entry.getTags().contains(tag)) {
				filteredEntries.add(entry);
			}
		}
		return filteredEntries;
	}

	/**
	 * Calculate the average rating of the given entries
	 * @param entries the entries to get the average rating of
	 * @return Double of average rating; returns 0 if there are no entries
	 */
	public static double getAvgRating(List<Entry> entries) {

		double rating = 0;
		// if tags or entries do not exist, immediately exit
		if (entries == null || entries.size() == 0) { return 0; }
		for (Entry entry : entries) {  // iterate through entries
			rating += entry.getRating();
		}
		return rating / entries.size();
	}

	/**
	 * Gets a set of all tags used by given entries
	 * @param entries the entries to get tags from
	 * @return Set of tags found in given entries
	 */
	public static Set<String> getTagsFromEntries(List<Entry> entries) {
		Set<String> tags = new HashSet<>();

		if (entries == null) { return tags; }
		for (Entry e: entries) {
			for (String tag: e.getTags()) {
				tags.add(tag);
			}
		}
		return tags;
	}

	/**
	 * Calculate the average rating of each tag in the given list of entries
	 * @param entries the entries to get the average tag ratings of
	 * @return Dictionary of tags to average rating; empty dictionary if entries null or empty
	 */
	public static Dictionary<String, Double> getAllTagAvg(List<Entry> entries) {
		Dictionary<String, Double> tagRatings = new Hashtable<>();
		// if tags or entries do not exist, immediately exit
		if (entries == null) { return tagRatings; }
		Set<String> tags = getTagsFromEntries(entries);
		for (String tag : tags) {  // iterate through entries
			double avgRating = getAvgRating(filterEntriesByTag(entries, tag));
			tagRatings.put(tag, avgRating);
		}
		return tagRatings;
	}
}
