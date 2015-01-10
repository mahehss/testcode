
package com.shopsmart.model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shopsmart.shop.utils.LocationUtils;


public class Offer {

    @SerializedName("_id")
    @Expose
    private String Id;
    @Expose
    private String user;
    @Expose
    private String category;
    @Expose
    private String categoryMarker;
    @Expose
    private String website;
    public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	@Expose
    private String endDate;
    @Expose
    private String startDate;
    @Expose
    private String created;
    @Expose
    private List<Location> locations = new ArrayList<Location>();
    @Expose
    private String image;
    @Expose
    private String shortDesc;
    @Expose
    private String description;
    
    @Expose
    private String email;
    
    @Expose
    private String name;
    @SerializedName("__v")
    @Expose
    private Integer V;
    @Expose
    private Double dis;

    /**
     * 
     * @return
     *     The Id
     */
    public String getId() {
        return Id;
    }

    /**
     * 
     * @param Id
     *     The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * 
     * @return
     *     The user
     */
    public String getUser() {
        return user;
    }

    /**
     * 
     * @param user
     *     The user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * 
     * @return
     *     The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    public void setCategoryMarker(String categoryMarker) {
        this.categoryMarker = categoryMarker;
    }

    /**
     * 
     * @return
     *     The category
     */
    public String getCategoryMarker() {
        return categoryMarker;
    }

    /**
     * 
     * @param category
     *     The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
    /**
     * 
     * 
     * 
     * @return
     *     The endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 
     * @param endDate
     *     The endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 
     * @return
     *     The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 
     * @param startDate
     *     The startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 
     * @return
     *     The created
     */
    public String getCreated() {
        return created;
    }

    /**
     * 
     * @param created
     *     The created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * 
     * @return
     *     The locations
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     * 
     * @param locations
     *     The locations
     */
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The shortDesc
     */
    public String getShortDesc() {
        return shortDesc;
    }

    /**
     * 
     * @param shortDesc
     *     The shortDesc
     */
    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
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
     *     The V
     */
    public Integer getV() {
        return V;
    }

    /**
     * 
     * @param V
     *     The __v
     */
    public void setV(Integer V) {
        this.V = V;
    }

    /**
     * 
     * @return
     *     The dis
     */
    public Double getDis() {
        return dis;
    }

    /**
     * 
     * @param dis
     *     The dis
     */
    public void setDis(Double dis) {
        this.dis = dis;
    }
    
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Location getNearestLocation(Context context)
    {
    	android.location.Location loc = LocationUtils.getLocation(context);
    	if( this.locations ==null || this.locations.size() ==0)
    		return null;
    	
    	double distance = 0;
    	Location nearestlocation = new Location();
    	for (Location location : this.locations) {
			
    		double dis = getDistance(loc, location);
    		if(distance>dis || distance == 0)
    		{
    			distance = dis;
    			nearestlocation = location;
    		}
		}
    	
    	return nearestlocation;
    	
    }
    public double getDistance(android.location.Location mainLocation, Location location) {
        double distance = 0;
        if(location ==null || location.getLoc() ==null)
        	return 99999999;
         List<Double> coords = location.getLoc().getCoordinates();
        android.location.Location locationB = new android.location.Location("B");
       if(coords !=null && coords.size()>1)
       {
        locationB.setLatitude(coords.get(1));
        locationB.setLongitude(coords.get(0));
        distance = mainLocation.distanceTo(locationB);
       }
        return distance;

    }

}
