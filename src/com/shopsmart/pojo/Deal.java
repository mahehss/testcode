
package com.shopsmart.pojo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Deal {

    @Expose
    private String id;
    @Expose
    private String title;
    @Expose
    private String status;
    @Expose
    private String type;
    @Expose
    private String dealUrl;
    @Expose
    private String startAt;
    @Expose
    private String endAt;
    @Expose
    private String smallImageUrl;
    @Expose
    private String mediumImageUrl;
    @Expose
    private String largeImageUrl;
    @Expose
    private String sidebarImageUrl;
    @Expose
    private String grid4ImageUrl;
    @Expose
    private String grid6ImageUrl;
    @Expose
    private String placeholderUrl;
    @Expose
    private String highlightsHtml;
    @Expose
    private String pitchHtml;
    @Expose
    private String shortAnnouncementTitle;
    @Expose
    private String announcementTitle;
    @Expose
    private String newsletterTitle;
    @Expose
    private String placementPriority;
    @Expose
    private Boolean shippingAddressRequired;
    @Expose
    private Boolean isTipped;
    @Expose
    private Integer tippingPoint;
    @Expose
    private Boolean isSoldOut;
    @Expose
    private Integer soldQuantity;
    @Expose
    private String soldQuantityMessage;
    @Expose
    private Boolean isInventoryDeal;
    @Expose
    private Boolean isGiftable;
    @Expose
    private List<Option> options = new ArrayList<Option>();
    @Expose
    private Division division;
    @Expose
    private Merchant merchant;
    @Expose
    private Says says;
    @Expose
    private List<Object> areas = new ArrayList<Object>();
    @Expose
    private TextAd textAd;
    @Expose
    private List<Tag> tags = new ArrayList<Tag>();
    @Expose
    private Integer priority;
    @Expose
    private Boolean isNowDeal;
    @Expose
    private PriceSummary priceSummary;
    @Expose
    private Detail details;
    
    public Detail getDetails() {
		return details;
	}

	public void setDetails(Detail details) {
		this.details = details;
	}

	@Expose
    private List<RedemptionLocation> redemptionLocations =new ArrayList<RedemptionLocation>();;
    public List<RedemptionLocation> getRedemptionLocations() {
		return redemptionLocations;
	}

	public void setRedemptionLocations(List<RedemptionLocation> redemptionLocations) {
		this.redemptionLocations = redemptionLocations;
	}

	@Expose
    private List<DealType> dealTypes = new ArrayList<DealType>();
    @Expose
    private List<Channel> channels = new ArrayList<Channel>();
    @Expose
    private String finePrint;
    @Expose
    private String locationNote;
    @Expose
    private String uuid;
    @Expose
    private Boolean isInviteOnly;
    @Expose
    private String accessType;
    @Expose
    private Integer limitedQuantityRemaining;
    @Expose
    private List<DisplayOption> displayOptions = new ArrayList<DisplayOption>();
    @Expose
    private String salesforceLink;
    @Expose
    private Boolean isTravelBookableDeal;
    @Expose
    private Boolean isOptionListComplete;
    @Expose
    private Boolean isMerchandisingDeal;
    @Expose
    private List<Image> images = new ArrayList<Image>();
    @Expose
    private Boolean isSellerOfRecord;
    @Expose
    private Boolean supportsPass;
    @Expose
    private String descriptor;
    @Expose
    private List<Object> traits = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
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
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The dealUrl
     */
    public String getDealUrl() {
        return dealUrl;
    }

    /**
     * 
     * @param dealUrl
     *     The dealUrl
     */
    public void setDealUrl(String dealUrl) {
        this.dealUrl = dealUrl;
    }

    /**
     * 
     * @return
     *     The startAt
     */
    public String getStartAt() {
        return startAt;
    }

    /**
     * 
     * @param startAt
     *     The startAt
     */
    public void setStartAt(String startAt) {
        this.startAt = startAt;
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
     *     The smallImageUrl
     */
    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    /**
     * 
     * @param smallImageUrl
     *     The smallImageUrl
     */
    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    /**
     * 
     * @return
     *     The mediumImageUrl
     */
    public String getMediumImageUrl() {
        return mediumImageUrl;
    }

    /**
     * 
     * @param mediumImageUrl
     *     The mediumImageUrl
     */
    public void setMediumImageUrl(String mediumImageUrl) {
        this.mediumImageUrl = mediumImageUrl;
    }

    /**
     * 
     * @return
     *     The largeImageUrl
     */
    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    /**
     * 
     * @param largeImageUrl
     *     The largeImageUrl
     */
    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    /**
     * 
     * @return
     *     The sidebarImageUrl
     */
    public String getSidebarImageUrl() {
        return sidebarImageUrl;
    }

    /**
     * 
     * @param sidebarImageUrl
     *     The sidebarImageUrl
     */
    public void setSidebarImageUrl(String sidebarImageUrl) {
        this.sidebarImageUrl = sidebarImageUrl;
    }

    /**
     * 
     * @return
     *     The grid4ImageUrl
     */
    public String getGrid4ImageUrl() {
        return grid4ImageUrl;
    }

    /**
     * 
     * @param grid4ImageUrl
     *     The grid4ImageUrl
     */
    public void setGrid4ImageUrl(String grid4ImageUrl) {
        this.grid4ImageUrl = grid4ImageUrl;
    }

    /**
     * 
     * @return
     *     The grid6ImageUrl
     */
    public String getGrid6ImageUrl() {
        return grid6ImageUrl;
    }

    /**
     * 
     * @param grid6ImageUrl
     *     The grid6ImageUrl
     */
    public void setGrid6ImageUrl(String grid6ImageUrl) {
        this.grid6ImageUrl = grid6ImageUrl;
    }

    /**
     * 
     * @return
     *     The placeholderUrl
     */
    public String getPlaceholderUrl() {
        return placeholderUrl;
    }

    /**
     * 
     * @param placeholderUrl
     *     The placeholderUrl
     */
    public void setPlaceholderUrl(String placeholderUrl) {
        this.placeholderUrl = placeholderUrl;
    }

    /**
     * 
     * @return
     *     The highlightsHtml
     */
    public String getHighlightsHtml() {
        return highlightsHtml;
    }

    /**
     * 
     * @param highlightsHtml
     *     The highlightsHtml
     */
    public void setHighlightsHtml(String highlightsHtml) {
        this.highlightsHtml = highlightsHtml;
    }

    /**
     * 
     * @return
     *     The pitchHtml
     */
    public String getPitchHtml() {
        return pitchHtml;
    }

    /**
     * 
     * @param pitchHtml
     *     The pitchHtml
     */
    public void setPitchHtml(String pitchHtml) {
        this.pitchHtml = pitchHtml;
    }

    /**
     * 
     * @return
     *     The shortAnnouncementTitle
     */
    public String getShortAnnouncementTitle() {
        return shortAnnouncementTitle;
    }

    /**
     * 
     * @param shortAnnouncementTitle
     *     The shortAnnouncementTitle
     */
    public void setShortAnnouncementTitle(String shortAnnouncementTitle) {
        this.shortAnnouncementTitle = shortAnnouncementTitle;
    }

    /**
     * 
     * @return
     *     The announcementTitle
     */
    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    /**
     * 
     * @param announcementTitle
     *     The announcementTitle
     */
    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    /**
     * 
     * @return
     *     The newsletterTitle
     */
    public String getNewsletterTitle() {
        return newsletterTitle;
    }

    /**
     * 
     * @param newsletterTitle
     *     The newsletterTitle
     */
    public void setNewsletterTitle(String newsletterTitle) {
        this.newsletterTitle = newsletterTitle;
    }

    /**
     * 
     * @return
     *     The placementPriority
     */
    public String getPlacementPriority() {
        return placementPriority;
    }

    /**
     * 
     * @param placementPriority
     *     The placementPriority
     */
    public void setPlacementPriority(String placementPriority) {
        this.placementPriority = placementPriority;
    }

    /**
     * 
     * @return
     *     The shippingAddressRequired
     */
    public Boolean getShippingAddressRequired() {
        return shippingAddressRequired;
    }

    /**
     * 
     * @param shippingAddressRequired
     *     The shippingAddressRequired
     */
    public void setShippingAddressRequired(Boolean shippingAddressRequired) {
        this.shippingAddressRequired = shippingAddressRequired;
    }

    /**
     * 
     * @return
     *     The isTipped
     */
    public Boolean getIsTipped() {
        return isTipped;
    }

    /**
     * 
     * @param isTipped
     *     The isTipped
     */
    public void setIsTipped(Boolean isTipped) {
        this.isTipped = isTipped;
    }

    /**
     * 
     * @return
     *     The tippingPoint
     */
    public Integer getTippingPoint() {
        return tippingPoint;
    }

    /**
     * 
     * @param tippingPoint
     *     The tippingPoint
     */
    public void setTippingPoint(Integer tippingPoint) {
        this.tippingPoint = tippingPoint;
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
     *     The isInventoryDeal
     */
    public Boolean getIsInventoryDeal() {
        return isInventoryDeal;
    }

    /**
     * 
     * @param isInventoryDeal
     *     The isInventoryDeal
     */
    public void setIsInventoryDeal(Boolean isInventoryDeal) {
        this.isInventoryDeal = isInventoryDeal;
    }

    /**
     * 
     * @return
     *     The isGiftable
     */
    public Boolean getIsGiftable() {
        return isGiftable;
    }

    /**
     * 
     * @param isGiftable
     *     The isGiftable
     */
    public void setIsGiftable(Boolean isGiftable) {
        this.isGiftable = isGiftable;
    }

    /**
     * 
     * @return
     *     The options
     */
    public List<Option> getOptions() {
        return options;
    }

    /**
     * 
     * @param options
     *     The options
     */
    public void setOptions(List<Option> options) {
        this.options = options;
    }

    /**
     * 
     * @return
     *     The division
     */
    public Division getDivision() {
        return division;
    }

    /**
     * 
     * @param division
     *     The division
     */
    public void setDivision(Division division) {
        this.division = division;
    }

    /**
     * 
     * @return
     *     The merchant
     */
    public Merchant getMerchant() {
        return merchant;
    }

    /**
     * 
     * @param merchant
     *     The merchant
     */
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    /**
     * 
     * @return
     *     The says
     */
    public Says getSays() {
        return says;
    }

    /**
     * 
     * @param says
     *     The says
     */
    public void setSays(Says says) {
        this.says = says;
    }

    /**
     * 
     * @return
     *     The areas
     */
    public List<Object> getAreas() {
        return areas;
    }

    /**
     * 
     * @param areas
     *     The areas
     */
    public void setAreas(List<Object> areas) {
        this.areas = areas;
    }

    /**
     * 
     * @return
     *     The textAd
     */
    public TextAd getTextAd() {
        return textAd;
    }

    /**
     * 
     * @param textAd
     *     The textAd
     */
    public void setTextAd(TextAd textAd) {
        this.textAd = textAd;
    }

    /**
     * 
     * @return
     *     The tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    /**
     * 
     * @return
     *     The priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 
     * @param priority
     *     The priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 
     * @return
     *     The isNowDeal
     */
    public Boolean getIsNowDeal() {
        return isNowDeal;
    }

    /**
     * 
     * @param isNowDeal
     *     The isNowDeal
     */
    public void setIsNowDeal(Boolean isNowDeal) {
        this.isNowDeal = isNowDeal;
    }

    /**
     * 
     * @return
     *     The priceSummary
     */
    public PriceSummary getPriceSummary() {
        return priceSummary;
    }

    /**
     * 
     * @param priceSummary
     *     The priceSummary
     */
    public void setPriceSummary(PriceSummary priceSummary) {
        this.priceSummary = priceSummary;
    }



    /**
     * 
     * @return
     *     The dealTypes
     */
    public List<DealType> getDealTypes() {
        return dealTypes;
    }

    /**
     * 
     * @param dealTypes
     *     The dealTypes
     */
    public void setDealTypes(List<DealType> dealTypes) {
        this.dealTypes = dealTypes;
    }

    /**
     * 
     * @return
     *     The channels
     */
    public List<Channel> getChannels() {
        return channels;
    }

    /**
     * 
     * @param channels
     *     The channels
     */
    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    /**
     * 
     * @return
     *     The finePrint
     */
    public String getFinePrint() {
        return finePrint;
    }

    /**
     * 
     * @param finePrint
     *     The finePrint
     */
    public void setFinePrint(String finePrint) {
        this.finePrint = finePrint;
    }

    /**
     * 
     * @return
     *     The locationNote
     */
    public String getLocationNote() {
        return locationNote;
    }

    /**
     * 
     * @param locationNote
     *     The locationNote
     */
    public void setLocationNote(String locationNote) {
        this.locationNote = locationNote;
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
     *     The isInviteOnly
     */
    public Boolean getIsInviteOnly() {
        return isInviteOnly;
    }

    /**
     * 
     * @param isInviteOnly
     *     The isInviteOnly
     */
    public void setIsInviteOnly(Boolean isInviteOnly) {
        this.isInviteOnly = isInviteOnly;
    }

    /**
     * 
     * @return
     *     The accessType
     */
    public String getAccessType() {
        return accessType;
    }

    /**
     * 
     * @param accessType
     *     The accessType
     */
    public void setAccessType(String accessType) {
        this.accessType = accessType;
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
     *     The displayOptions
     */
    public List<DisplayOption> getDisplayOptions() {
        return displayOptions;
    }

    /**
     * 
     * @param displayOptions
     *     The displayOptions
     */
    public void setDisplayOptions(List<DisplayOption> displayOptions) {
        this.displayOptions = displayOptions;
    }

    /**
     * 
     * @return
     *     The salesforceLink
     */
    public String getSalesforceLink() {
        return salesforceLink;
    }

    /**
     * 
     * @param salesforceLink
     *     The salesforceLink
     */
    public void setSalesforceLink(String salesforceLink) {
        this.salesforceLink = salesforceLink;
    }

    /**
     * 
     * @return
     *     The isTravelBookableDeal
     */
    public Boolean getIsTravelBookableDeal() {
        return isTravelBookableDeal;
    }

    /**
     * 
     * @param isTravelBookableDeal
     *     The isTravelBookableDeal
     */
    public void setIsTravelBookableDeal(Boolean isTravelBookableDeal) {
        this.isTravelBookableDeal = isTravelBookableDeal;
    }

    /**
     * 
     * @return
     *     The isOptionListComplete
     */
    public Boolean getIsOptionListComplete() {
        return isOptionListComplete;
    }

    /**
     * 
     * @param isOptionListComplete
     *     The isOptionListComplete
     */
    public void setIsOptionListComplete(Boolean isOptionListComplete) {
        this.isOptionListComplete = isOptionListComplete;
    }

    /**
     * 
     * @return
     *     The isMerchandisingDeal
     */
    public Boolean getIsMerchandisingDeal() {
        return isMerchandisingDeal;
    }

    /**
     * 
     * @param isMerchandisingDeal
     *     The isMerchandisingDeal
     */
    public void setIsMerchandisingDeal(Boolean isMerchandisingDeal) {
        this.isMerchandisingDeal = isMerchandisingDeal;
    }

    /**
     * 
     * @return
     *     The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * 
     * @return
     *     The isSellerOfRecord
     */
    public Boolean getIsSellerOfRecord() {
        return isSellerOfRecord;
    }

    /**
     * 
     * @param isSellerOfRecord
     *     The isSellerOfRecord
     */
    public void setIsSellerOfRecord(Boolean isSellerOfRecord) {
        this.isSellerOfRecord = isSellerOfRecord;
    }

    /**
     * 
     * @return
     *     The supportsPass
     */
    public Boolean getSupportsPass() {
        return supportsPass;
    }

    /**
     * 
     * @param supportsPass
     *     The supportsPass
     */
    public void setSupportsPass(Boolean supportsPass) {
        this.supportsPass = supportsPass;
    }

    /**
     * 
     * @return
     *     The descriptor
     */
    public String getDescriptor() {
        return descriptor;
    }

    /**
     * 
     * @param descriptor
     *     The descriptor
     */
    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * 
     * @return
     *     The traits
     */
    public List<Object> getTraits() {
        return traits;
    }

    /**
     * 
     * @param traits
     *     The traits
     */
    public void setTraits(List<Object> traits) {
        this.traits = traits;
    }

}
