/**
    * File        : UserFuntions.java
    * App name    : Perkutut
    * Version     : 1.1.4
    * Created     : 25/05/14

    * Created by Taufan Erfiyanto & Cahaya Pangripta Alam on 11/24/13.
    * Copyright (c) 2013 pongodev. All rights reserved.
    */

package com.shopsmart.shop.libraries;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.shopsmart.model.Category;
import com.shopsmart.model.Location;
import com.shopsmart.model.Offer;


public class OfferService {
	

	public static final String base_url = "http://ec2-54-169-164-88.ap-southeast-1.compute.amazonaws.com/";
	public static final String marker_url ="https://s3-ap-southeast-1.amazonaws.com/findasale/markers/markerRed.png";
	//Modifications for offer starts
	JSONParser jsonParser;
	// getter sevices Service
	private static final String service_offer = "find?";
	private static final String service_categories = "categories";
	public static final String service_hubs = "findhubs";
	
	//setter services
	public static final String service_add_offer = "sendoffer";
	public static final String service_register_device = "devices";

	private final String param_skip ="skip=";
	private final String param_longitude ="longitude=";
	private final String param_lattitude ="lattitude=";
	private final String param_category ="category=";
	private final String param_keyword ="keyword=";
	// LoadUrl
	//keys 

	
	private String webService;
	public final int valueItemsPerPage=10;

	
	// constructor
	public OfferService(){
		jsonParser = new JSONParser();
	}
	
	
	
	public List<Offer> getOffers(int valueStartIndex, double longitute, double lattitute,
			String category, String keyword){
		
		webService = base_url+service_offer+param_skip+(valueStartIndex*valueItemsPerPage)+"&"
		+param_longitude+longitute
				+"&"+param_lattitude+lattitute;
		if(category !=null && category !="")
		{
			webService = webService +"&" + param_category + category;
		}
		if(keyword !=null && keyword != "")
		{
			webService = webService +"&" + param_keyword + keyword;
		}
		
		try {
			String json = jsonParser.getJSONStringFromUrl(webService);
			if(json !=null)
			{
			 Gson g = new GsonBuilder().create();
			 List<Offer> offers =  g.fromJson(json,new TypeToken<List<Offer>>(){}.getType());
			 return offers;
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Category> getCategories()
	{
		webService = base_url + service_categories;
		String json = jsonParser.getJSONStringFromUrl(webService);
		try {
			if(json !=null)
			{
			 Gson g = new GsonBuilder().create();
			 List<Category> categories =  g.fromJson(json, new TypeToken<List<Category>>(){}.getType());
			return categories;
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Location> getShoppingHubs()
	{
		webService = base_url + service_hubs;
		String json = jsonParser.getJSONStringFromUrl(webService);
		try {
			if(json !=null)
			{
			 Gson g = new GsonBuilder().create();
			 List<Location> locations =  g.fromJson(json, new TypeToken<List<Location>>(){}.getType());
			 return locations;
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}