
package com.shopsmart.pojo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;


public class Deals {

    @Expose
    private List<Deal> deals = new ArrayList<Deal>();
    @Expose
    private String serviceLevel;
    @Expose
    private Pagination pagination;

    /**
     * 
     * @return
     *     The deals
     */
    public List<Deal> getDeals() {
        return deals;
    }

    /**
     * 
     * @param deals
     *     The deals
     */
    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    /**
     * 
     * @return
     *     The serviceLevel
     */
    public String getServiceLevel() {
        return serviceLevel;
    }

    /**
     * 
     * @param serviceLevel
     *     The serviceLevel
     */
    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    /**
     * 
     * @return
     *     The pagination
     */
    public Pagination getPagination() {
        return pagination;
    }

    /**
     * 
     * @param pagination
     *     The pagination
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
