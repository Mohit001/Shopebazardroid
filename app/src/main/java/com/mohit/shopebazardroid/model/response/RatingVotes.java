package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 31/5/17.
 */

public class RatingVotes {

    private String vote_id;
    private String option_id;
    private String remote_ip;
    private String remote_ip_long;
    private String customer_id;
    private String entity_pk_value;
    private String rating_id;
    private String review_id;
    private String percent;
    private String value;
    private String rating_code;
    private String store_id;

    public String getVote_id() {
        return vote_id;
    }

    public void setVote_id(String vote_id) {
        this.vote_id = vote_id;
    }

    public String getOption_id() {
        return option_id;
    }

    public void setOption_id(String option_id) {
        this.option_id = option_id;
    }

    public String getRemote_ip() {
        return remote_ip;
    }

    public void setRemote_ip(String remote_ip) {
        this.remote_ip = remote_ip;
    }

    public String getRemote_ip_long() {
        return remote_ip_long;
    }

    public void setRemote_ip_long(String remote_ip_long) {
        this.remote_ip_long = remote_ip_long;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getEntity_pk_value() {
        return entity_pk_value;
    }

    public void setEntity_pk_value(String entity_pk_value) {
        this.entity_pk_value = entity_pk_value;
    }

    public String getRating_id() {
        return rating_id;
    }

    public void setRating_id(String rating_id) {
        this.rating_id = rating_id;
    }

    public String getReview_id() {
        return review_id;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRating_code() {
        return rating_code;
    }

    public void setRating_code(String rating_code) {
        this.rating_code = rating_code;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

}
