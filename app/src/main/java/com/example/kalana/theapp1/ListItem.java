package com.example.kalana.theapp1;

/**
 * Created by Kalana on 1/28/2018.
 */

public class ListItem {
    private String tvItemAv;
    private String tvPresentAv;
    private String tvDetailAv;
    private String tvStartAv;
    private String tvEndAv;
    private String tvItemCode;
    private String tvPromoId;


    public ListItem(String item, String percentage, String detail, String startDate, String endDate, String promoId, String itemCode) {
        this.tvItemAv = item;
        this.tvPresentAv = percentage;
        this.tvDetailAv = detail;
        this.tvStartAv = startDate;
        this.tvEndAv = endDate;
        this.tvItemCode = itemCode;
        this.tvPromoId = promoId;

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

    public String getTvItemCode() {
        return tvItemCode;
    }

    public String getTvPromoId() {
        return tvPromoId;
    }
}
