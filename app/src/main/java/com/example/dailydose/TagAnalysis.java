package com.example.dailydose;
import android.content.Context;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	/**
	 * Get average rating of the Entries on each date between startDate (inclusive) and
	 * endDate (inclusive)
	 * @param startDate - the start of the date range (inclusive)
	 * @param endDate - the end of the date range (inclusive)
	 * @param entries - the entries to calculate avg ratings over
	 * @return - A TreeMap mapping Date to average rating on that date, based on passed in
	 * entries list and start and end dates
	 */
	public static Map<Date, Double> getAvgRatingByDate (Date startDate, Date endDate, List<Entry> entries) {
		Map<Date, Double> result = new TreeMap<>();
		Map<Date, List<Double>> allRatingsOnDay = new HashMap<>();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		for (Entry entry: entries) {
			try {
				Date entryDate = format.parse(entry.getDate());
				if ((entryDate.before(endDate) || entryDate.equals(endDate)) &&
						(entryDate.after(startDate)  || entryDate.equals(startDate))) {
					if (!result.keySet().contains(entryDate)) {
						result.put(entryDate, entry.getRating());
						ArrayList<Double> ratings = new ArrayList<>();
						ratings.add(entry.getRating());
						allRatingsOnDay.put(entryDate, ratings);
					} else {
						List<Double> ratingsOnDay = allRatingsOnDay.get(entryDate);
						ratingsOnDay.add(entry.getRating());
						double sum = 0;
						for (Double rating: ratingsOnDay) {
							sum += rating;
						}
						result.put(entryDate, sum/ratingsOnDay.size());
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
