package com.example.dailydose;
import java.util.*;

public class Entry {
    public String content;
    public double rating;
    public int id;
    public List<String> tags;

    public Entry(String content, double rating, int id, List<String> tags) {
        this.content = content;
        this.rating = rating;
        this.id = id;
        this.tags = tags;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

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
}
