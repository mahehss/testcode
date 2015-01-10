
package com.shopsmart.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;


public class Loc {

    @Expose
    private List<Double> coordinates = new ArrayList<Double>();
    @Expose
    private String type;

    /**
     * 
     * @return
     *     The coordinates
     */
    public List<Double> getCoordinates() {
        return coordinates;
    }

    /**
     * 
     * @param coordinates
     *     The coordinates
     */
    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
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

}
