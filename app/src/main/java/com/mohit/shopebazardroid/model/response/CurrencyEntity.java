package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 17/8/16.
 */
public class CurrencyEntity {

    private String code;
    private String rate;
    private String nametocurrency;
    private String symbol;

    @Override
    public String toString() {
        return "CurrencyEntity{" +
                "code='" + code + '\'' +
                ", rate='" + rate + '\'' +
                ", nametocurrency='" + nametocurrency + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getNametocurrency() {
        return nametocurrency;
    }

    public void setNametocurrency(String nametocurrency) {
        this.nametocurrency = nametocurrency;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
