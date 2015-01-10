
package com.shopsmart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Device {

    @SerializedName("_id")
    @Expose
    private String Id;
    @Expose
    private User user;
    @SerializedName("__v")
    @Expose
    private Integer V;
    @Expose
    private String created;
    @Expose
    private String email;
    @Expose
    private String registrationId;
    
    @Expose
    private String token;
    
    
    @Expose
    private String senderId;    

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getV() {
		return V;
	}

	public void setV(Integer v) {
		V = v;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	
    

}
