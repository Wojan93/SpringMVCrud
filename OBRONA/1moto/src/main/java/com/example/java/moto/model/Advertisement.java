package com.example.java.moto.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Advertisement extends BaseEntity {

    private Double buyNowPrice;
    private Double startAuctionPrice;
    private Date endDateAd;
    private Boolean auction;

    public Double getBuyNowPrice() {
        return buyNowPrice;
    }

    public void setBuyNowPrice(Double buyNowPrice) {
        this.buyNowPrice = buyNowPrice;
    }

    public Double getStartAuctionPrice() {
        return startAuctionPrice;
    }

    public void setStartAuctionPrice(Double startAuctionPrice) {
        this.startAuctionPrice = startAuctionPrice;
    }

    public Date getEndDateAd() {
        return endDateAd;
    }

    public void setEndDateAd(Date endDateAd) {
        this.endDateAd = endDateAd;
    }

    public Boolean getAuction() {
        return auction;
    }

    public void setAuction(Boolean auction) {
        this.auction = auction;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;
}
