
package com.shopsmart.pojo;


import com.google.gson.annotations.Expose;

public class Says {

    @Expose
    private String id;
    @Expose
    private String title;
    @Expose
    private String emailContent;
    @Expose
    private String emailContentHtml;
    @Expose
    private String websiteContent;
    @Expose
    private String websiteContentHtml;

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
     *     The emailContent
     */
    public String getEmailContent() {
        return emailContent;
    }

    /**
     * 
     * @param emailContent
     *     The emailContent
     */
    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    /**
     * 
     * @return
     *     The emailContentHtml
     */
    public String getEmailContentHtml() {
        return emailContentHtml;
    }

    /**
     * 
     * @param emailContentHtml
     *     The emailContentHtml
     */
    public void setEmailContentHtml(String emailContentHtml) {
        this.emailContentHtml = emailContentHtml;
    }

    /**
     * 
     * @return
     *     The websiteContent
     */
    public String getWebsiteContent() {
        return websiteContent;
    }

    /**
     * 
     * @param websiteContent
     *     The websiteContent
     */
    public void setWebsiteContent(String websiteContent) {
        this.websiteContent = websiteContent;
    }

    /**
     * 
     * @return
     *     The websiteContentHtml
     */
    public String getWebsiteContentHtml() {
        return websiteContentHtml;
    }

    /**
     * 
     * @param websiteContentHtml
     *     The websiteContentHtml
     */
    public void setWebsiteContentHtml(String websiteContentHtml) {
        this.websiteContentHtml = websiteContentHtml;
    }

}
