
package com.shopsmart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @Expose
    private String address;
    @SerializedName("_id")
    @Expose
    private String Id;
    @Expose
    private Loc loc;

    /**
     * 
     * @return
     *     The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *     The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

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
     *     The loc
     */
    public Loc getLoc() {
        return loc;
    }

    /**
     * 
     * @param loc
     *     The loc
     */
    public void setLoc(Loc loc) {
        this.loc = loc;
    }

}
