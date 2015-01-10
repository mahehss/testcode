
package com.shopsmart.pojo;

import com.google.gson.annotations.Expose;

public class Detail {

    @Expose
    private String description;

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

}
