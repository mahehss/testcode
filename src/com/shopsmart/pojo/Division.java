
package com.shopsmart.pojo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;


public class Division {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String timezone;
    @Expose
    private Integer timezoneOffsetInSeconds;
    @Expose
    private String timezoneIdentifier;
    @Expose
    private Double lat;
    @Expose
    private Double lng;
    @Expose
    private String country;
    @Expose
    private List<Object> areas = new ArrayList<Object>();

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
     *     The timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * 
     * @param timezone
     *     The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     * 
     * @return
     *     The timezoneOffsetInSeconds
     */
    public Integer getTimezoneOffsetInSeconds() {
        return timezoneOffsetInSeconds;
    }

    /**
     * 
     * @param timezoneOffsetInSeconds
     *     The timezoneOffsetInSeconds
     */
    public void setTimezoneOffsetInSeconds(Integer timezoneOffsetInSeconds) {
        this.timezoneOffsetInSeconds = timezoneOffsetInSeconds;
    }

    /**
     * 
     * @return
     *     The timezoneIdentifier
     */
    public String getTimezoneIdentifier() {
        return timezoneIdentifier;
    }

    /**
     * 
     * @param timezoneIdentifier
     *     The timezoneIdentifier
     */
    public void setTimezoneIdentifier(String timezoneIdentifier) {
        this.timezoneIdentifier = timezoneIdentifier;
    }

    /**
     * 
     * @return
     *     The lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     * 
     * @param lat
     *     The lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * 
     * @return
     *     The lng
     */
    public Double getLng() {
        return lng;
    }

    /**
     * 
     * @param lng
     *     The lng
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
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

}
