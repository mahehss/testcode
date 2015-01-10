
package com.shopsmart.pojo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Merchant {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String websiteUrl;
    @Expose
    private String facebookUrl;
    @Expose
    private String twitterUrl;
    @Expose
    private List<Object> ratings = new ArrayList<Object>();
    @Expose
    private String uuid;

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
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The websiteUrl
     */
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * 
     * @param websiteUrl
     *     The websiteUrl
     */
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    /**
     * 
     * @return
     *     The facebookUrl
     */
    public String getFacebookUrl() {
        return facebookUrl;
    }

    /**
     * 
     * @param facebookUrl
     *     The facebookUrl
     */
    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    /**
     * 
     * @return
     *     The twitterUrl
     */
    public String getTwitterUrl() {
        return twitterUrl;
    }

    /**
     * 
     * @param twitterUrl
     *     The twitterUrl
     */
    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    /**
     * 
     * @return
     *     The ratings
     */
    public List<Object> getRatings() {
        return ratings;
    }

    /**
     * 
     * @param ratings
     *     The ratings
     */
    public void setRatings(List<Object> ratings) {
        this.ratings = ratings;
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

}
