package com.cricbuzz.medicbuddy.models;

/**
 * Created by Rahil on 9/10/2017.
 */

public class Filter {


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public enum SortBy {
        NEW_TO_OLD, OLD_TO_NEW;
    }

    int page = 0;
    String category = "";

    String publisheer = "";
    SortBy sortBy = SortBy.NEW_TO_OLD;

    public SortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisheer() {
        return publisheer;
    }

    public void setPublisheer(String publisheer) {
        this.publisheer = publisheer;
    }
}
