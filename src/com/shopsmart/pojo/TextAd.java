
package com.shopsmart.pojo;

import com.google.gson.annotations.Expose;

public class TextAd {

    @Expose
    private String line1;
    @Expose
    private String line2;
    @Expose
    private String headline;

    /**
     * 
     * @return
     *     The line1
     */
    public String getLine1() {
        return line1;
    }

    /**
     * 
     * @param line1
     *     The line1
     */
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    /**
     * 
     * @return
     *     The line2
     */
    public String getLine2() {
        return line2;
    }

    /**
     * 
     * @param line2
     *     The line2
     */
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    /**
     * 
     * @return
     *     The headline
     */
    public String getHeadline() {
        return headline;
    }

    /**
     * 
     * @param headline
     *     The headline
     */
    public void setHeadline(String headline) {
        this.headline = headline;
    }

}
