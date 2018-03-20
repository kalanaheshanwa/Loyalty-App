package com.example.kalana.theapp1;

/**
 * Created by Kalana on 1/28/2018.
 */

public class ListItemAvailable {
    private String tvItemAv;
    private String tvPresentAv;
    private String tvDetailAv;
    private String tvStartAv;
    private String tvEndAv;



    public ListItemAvailable(String item, String percentage, String detail, String startDate, String endDate) {
        this.tvItemAv = item;
        this.tvPresentAv = percentage;
        this.tvDetailAv = detail;
        this.tvStartAv = startDate;
        this.tvEndAv = endDate;

    }

    public String getTvItemAv() {
        return tvItemAv;
    }

    public String getTvPresentAv() {
        return tvPresentAv;
    }

    public String getTvDetailAv() {
        return tvDetailAv;
    }

    public String getTvStartAv() {
        return tvStartAv;
    }

    public String getTvEndAv() {
        return tvEndAv;
    }
}
