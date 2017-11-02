package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 31/5/17.
 */

public class ReviewList {

    private String review_id;
    private String created_at;
    private String entity_id;
    private String entity_pk_value;
    private String status_id;
    private String detail_id;
    private String title;
    private String detail;
    private String nickname;
    private String customer_id;
    private String entity_code;
    private ArrayList<RatingVotes> rating_votes;

    public String getReview_id() {
        return review_id;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getEntity_pk_value() {
        return entity_pk_value;
    }

    public void setEntity_pk_value(String entity_pk_value) {
        this.entity_pk_value = entity_pk_value;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public ArrayList<RatingVotes> getRating_votes() {
        return rating_votes;
    }

    public void setRating_votes(ArrayList<RatingVotes> rating_votes) {
        this.rating_votes = rating_votes;
    }


}
