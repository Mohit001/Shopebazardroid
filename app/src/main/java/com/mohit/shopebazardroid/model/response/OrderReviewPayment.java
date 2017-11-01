package com.mohit.shopebazardroid.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by msp on 5/8/16.
 */
public class OrderReviewPayment {


    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("method")
    private String method;
    @SerializedName("cc_exp_month")
    private String ccExpMonth;
    @SerializedName("cc_exp_year")
    private String ccExpYear;
    @SerializedName("cc_ss_start_month")
    private String ccSsStartMonth;
    @SerializedName("cc_ss_start_year")
    private String ccSsStartYear;
    @SerializedName("additional_information")
    private String additionalInformation;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCcExpMonth() {
        return ccExpMonth;
    }

    public void setCcExpMonth(String ccExpMonth) {
        this.ccExpMonth = ccExpMonth;
    }

    public String getCcExpYear() {
        return ccExpYear;
    }

    public void setCcExpYear(String ccExpYear) {
        this.ccExpYear = ccExpYear;
    }

    public String getCcSsStartMonth() {
        return ccSsStartMonth;
    }

    public void setCcSsStartMonth(String ccSsStartMonth) {
        this.ccSsStartMonth = ccSsStartMonth;
    }

    public String getCcSsStartYear() {
        return ccSsStartYear;
    }

    public void setCcSsStartYear(String ccSsStartYear) {
        this.ccSsStartYear = ccSsStartYear;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
