
package com.shopsmart.pojo;


import com.google.gson.annotations.Expose;

public class PriceSummary {

    @Expose
    private Price_ price;
    @Expose
    private Integer minimumPurchaseQuantity;
    @Expose
    private Integer discountPercent;
    @Expose
    private Value_ value;

    /**
     * 
     * @return
     *     The price
     */
    public Price_ getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *     The price
     */
    public void setPrice(Price_ price) {
        this.price = price;
    }

    /**
     * 
     * @return
     *     The minimumPurchaseQuantity
     */
    public Integer getMinimumPurchaseQuantity() {
        return minimumPurchaseQuantity;
    }

    /**
     * 
     * @param minimumPurchaseQuantity
     *     The minimumPurchaseQuantity
     */
    public void setMinimumPurchaseQuantity(Integer minimumPurchaseQuantity) {
        this.minimumPurchaseQuantity = minimumPurchaseQuantity;
    }

    /**
     * 
     * @return
     *     The discountPercent
     */
    public Integer getDiscountPercent() {
        return discountPercent;
    }

    /**
     * 
     * @param discountPercent
     *     The discountPercent
     */
    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    /**
     * 
     * @return
     *     The value
     */
    public Value_ getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(Value_ value) {
        this.value = value;
    }

}
