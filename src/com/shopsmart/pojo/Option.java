
package com.shopsmart.pojo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Option {

    @Expose
    private Integer id;
    @Expose
    private Integer soldQuantity;
    @Expose
    private String soldQuantityMessage;
    @Expose
    private Integer minimumPurchaseQuantity;
    @Expose
    private Integer maximumPurchaseQuantity;
    @Expose
    private Integer discountPercent;
    @Expose
    private Integer initialQuantity;
    @Expose
    private Integer remainingQuantity;
    @Expose
    private Integer limitedQuantityRemaining;
    @Expose
    private Boolean isSoldOut;
    @Expose
    private Boolean isLimitedQuantity;
    @Expose
    private String title;
    @Expose
    private String expiresAt;
    @Expose
    private String endAt;
    @Expose
    private String buyUrl;
    @Expose
    private String externalUrl;
    @Expose
    private Discount discount;
    @Expose
    private Price price;
    @Expose
    private Value value;
    @Expose
    private Tax tax;
    @Expose
    private List<Detail> details = new ArrayList<Detail>();
    @Expose
    private List<Object> customFields = new ArrayList<Object>();
    @Expose
    private List<RedemptionLocation> redemptionLocations = new ArrayList<RedemptionLocation>();
    @Expose
    private Integer expiresInDays;
    @Expose
    private String status;
    @Expose
    private String uuid;
    @Expose
    private List<Object> images = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The soldQuantity
     */
    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    /**
     * 
     * @param soldQuantity
     *     The soldQuantity
     */
    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    /**
     * 
     * @return
     *     The soldQuantityMessage
     */
    public String getSoldQuantityMessage() {
        return soldQuantityMessage;
    }

    /**
     * 
     * @param soldQuantityMessage
     *     The soldQuantityMessage
     */
    public void setSoldQuantityMessage(String soldQuantityMessage) {
        this.soldQuantityMessage = soldQuantityMessage;
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
     *     The maximumPurchaseQuantity
     */
    public Integer getMaximumPurchaseQuantity() {
        return maximumPurchaseQuantity;
    }

    /**
     * 
     * @param maximumPurchaseQuantity
     *     The maximumPurchaseQuantity
     */
    public void setMaximumPurchaseQuantity(Integer maximumPurchaseQuantity) {
        this.maximumPurchaseQuantity = maximumPurchaseQuantity;
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
     *     The initialQuantity
     */
    public Integer getInitialQuantity() {
        return initialQuantity;
    }

    /**
     * 
     * @param initialQuantity
     *     The initialQuantity
     */
    public void setInitialQuantity(Integer initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    /**
     * 
     * @return
     *     The remainingQuantity
     */
    public Integer getRemainingQuantity() {
        return remainingQuantity;
    }

    /**
     * 
     * @param remainingQuantity
     *     The remainingQuantity
     */
    public void setRemainingQuantity(Integer remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    /**
     * 
     * @return
     *     The limitedQuantityRemaining
     */
    public Integer getLimitedQuantityRemaining() {
        return limitedQuantityRemaining;
    }

    /**
     * 
     * @param limitedQuantityRemaining
     *     The limitedQuantityRemaining
     */
    public void setLimitedQuantityRemaining(Integer limitedQuantityRemaining) {
        this.limitedQuantityRemaining = limitedQuantityRemaining;
    }

    /**
     * 
     * @return
     *     The isSoldOut
     */
    public Boolean getIsSoldOut() {
        return isSoldOut;
    }

    /**
     * 
     * @param isSoldOut
     *     The isSoldOut
     */
    public void setIsSoldOut(Boolean isSoldOut) {
        this.isSoldOut = isSoldOut;
    }

    /**
     * 
     * @return
     *     The isLimitedQuantity
     */
    public Boolean getIsLimitedQuantity() {
        return isLimitedQuantity;
    }

    /**
     * 
     * @param isLimitedQuantity
     *     The isLimitedQuantity
     */
    public void setIsLimitedQuantity(Boolean isLimitedQuantity) {
        this.isLimitedQuantity = isLimitedQuantity;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The expiresAt
     */
    public String getExpiresAt() {
        return expiresAt;
    }

    /**
     * 
     * @param expiresAt
     *     The expiresAt
     */
    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * 
     * @return
     *     The endAt
     */
    public String getEndAt() {
        return endAt;
    }

    /**
     * 
     * @param endAt
     *     The endAt
     */
    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    /**
     * 
     * @return
     *     The buyUrl
     */
    public String getBuyUrl() {
        return buyUrl;
    }

    /**
     * 
     * @param buyUrl
     *     The buyUrl
     */
    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
    }

    /**
     * 
     * @return
     *     The externalUrl
     */
    public String getExternalUrl() {
        return externalUrl;
    }

    /**
     * 
     * @param externalUrl
     *     The externalUrl
     */
    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    /**
     * 
     * @return
     *     The discount
     */
    public Discount getDiscount() {
        return discount;
    }

    /**
     * 
     * @param discount
     *     The discount
     */
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**
     * 
     * @return
     *     The price
     */
    public Price getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *     The price
     */
    public void setPrice(Price price) {
        this.price = price;
    }

    /**
     * 
     * @return
     *     The value
     */
    public Value getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(Value value) {
        this.value = value;
    }

    /**
     * 
     * @return
     *     The tax
     */
    public Tax getTax() {
        return tax;
    }

    /**
     * 
     * @param tax
     *     The tax
     */
    public void setTax(Tax tax) {
        this.tax = tax;
    }

    /**
     * 
     * @return
     *     The details
     */
    public List<Detail> getDetails() {
        return details;
    }

    /**
     * 
     * @param details
     *     The details
     */
    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    /**
     * 
     * @return
     *     The customFields
     */
    public List<Object> getCustomFields() {
        return customFields;
    }

    /**
     * 
     * @param customFields
     *     The customFields
     */
    public void setCustomFields(List<Object> customFields) {
        this.customFields = customFields;
    }

    /**
     * 
     * @return
     *     The redemptionLocations
     */
    public List<RedemptionLocation> getRedemptionLocations() {
        return redemptionLocations;
    }

    /**
     * 
     * @param redemptionLocations
     *     The redemptionLocations
     */
    public void setRedemptionLocations(List<RedemptionLocation> redemptionLocations) {
        this.redemptionLocations = redemptionLocations;
    }

    /**
     * 
     * @return
     *     The expiresInDays
     */
    public Integer getExpiresInDays() {
        return expiresInDays;
    }

    /**
     * 
     * @param expiresInDays
     *     The expiresInDays
     */
    public void setExpiresInDays(Integer expiresInDays) {
        this.expiresInDays = expiresInDays;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 
     * @param uuid
     *     The uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 
     * @return
     *     The images
     */
    public List<Object> getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(List<Object> images) {
        this.images = images;
    }

}
