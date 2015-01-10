
package com.shopsmart.pojo;

import com.google.gson.annotations.Expose;

public class Value_ {

    @Expose
    private Integer amount;
    @Expose
    private String formattedAmount;
    @Expose
    private String currencyCode;

    /**
     * 
     * @return
     *     The amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *     The amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 
     * @return
     *     The formattedAmount
     */
    public String getFormattedAmount() {
        return formattedAmount;
    }

    /**
     * 
     * @param formattedAmount
     *     The formattedAmount
     */
    public void setFormattedAmount(String formattedAmount) {
        this.formattedAmount = formattedAmount;
    }

    /**
     * 
     * @return
     *     The currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 
     * @param currencyCode
     *     The currencyCode
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}
