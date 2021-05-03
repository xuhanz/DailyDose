package com.example.dailydose;
import java.util.*;

// The Entry class represents a journal Entry for
// The Daily Dose app. An Entry is made up of a string representing its
// "content", a double "rating", an int "id" and a List of String "tags"
public class Entry {
    public String content;
    public double rating;
    public int id;
    public List<String> tags;

    /**
     * Constructor
     * @param content - The content of the Entry
     * @param rating - The "mood" rating
     * @param id - The unique id number
     * @param tags - The list of tags on this Entry
     * **/
    public Entry(String content, double rating, int id, List<String> tags) {
        this.content = content;
        this.rating = rating;
        this.id = id;
        this.tags = tags;
    }

    /**
     * Setter for content field
     * @param content to set it to
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter for Content field
     * @return content field
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter for rating field
     * @param rating to set it to
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Getter for rating field
     * @return rating field
     */
    public double getRating() {
        return rating;
    }

    /**
     * Setter for id field
     * @param id to set it to
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for id field
     * @return id field
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for tags field
     * @param tags to set it to
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Getter for tags field
     * @return tags field
     */
    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "content='" + content + '\'' +
                ", rating=" + rating +
                ", id=" + id +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Double.compare(entry.rating, rating) == 0 &&
                id == entry.id &&
                Objects.equals(content, entry.content) &&
                Objects.equals(tags, entry.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, rating, id, tags);
    }
}
