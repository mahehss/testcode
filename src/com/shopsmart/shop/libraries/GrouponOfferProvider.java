package com.shopsmart.shop.libraries;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shopsmart.model.Loc;
import com.shopsmart.model.Offer;
import com.shopsmart.pojo.Deal;
import com.shopsmart.pojo.Deals;
import com.shopsmart.pojo.Division;
import com.shopsmart.pojo.RedemptionLocation;

public class GrouponOfferProvider implements OffersProvider {
	private JSONParser jsonParser;

	// constructor
		public GrouponOfferProvider(){
			jsonParser = new JSONParser();
		}

@Override
 public List<Offer> getOffersByLocation(Location location, String category, String searchText, int pageIndex, int pageSize)
 {
	 String baseUrl = "https://partner-int-api.groupon.com/deals.json?country_code=IN&tsToken=IN_AFF_0_203296_245740_0&CID=IN_AFF_5600_225_5383_1";
	
	 baseUrl = baseUrl + "&lat=" + location.getLatitude()+ "&lng=" + location.getLongitude()+
			 "&radius=5&offset="+ pageIndex + "&limit="+ pageSize;
	 //filters=category:food-and-drink&offset=0&limit=20
	 //lat=53.33415&lng=-6.25946&radius=5&offset=0&limit=20
	 
	 String json = jsonParser.getJSONStringFromUrl(baseUrl);
	 Gson g = new GsonBuilder().create();
	Deals deals =  g.fromJson(json, Deals.class);
	
		
	if(deals !=null && deals.getDeals().size() > 0)
	{
		List<Offer> offers = new ArrayList<Offer>();
		for (Deal item : deals.getDeals()) {
		   
			try {
				Offer offer = new Offer();
				offer.setName(item.getTitle());
				offer.setImage(item.getSmallImageUrl());
				String shortDesc = item.getPriceSummary().getDiscountPercent() + "% Discount <Br/>Current Price: "+
				item.getPriceSummary().getPrice().getFormattedAmount() + " <Br/> Original Price: "+ item.getPriceSummary().getValue().getFormattedAmount() ;
				offer.setShortDesc( shortDesc+ "<br/>From: " + item.getStartAt().substring(0,10) +  "<br/>Ends On: " + item.getEndAt().substring(0,10));
				offer.setCategoryMarker( "https://s3-ap-southeast-1.amazonaws.com/findasale/powered_by_groupon.png");
				offer.setWebsite( item.getDealUrl());
				
				if(item.getDetails() !=null)
				{
					offer.setDescription(item.getDetails().getDescription());
				}

				offer.setDescription( offer.getDescription() +"<br>"+ item.getHighlightsHtml());
				List<RedemptionLocation> locations = item.getRedemptionLocations();
				List<com.shopsmart.model.Location> offerLocations = new ArrayList<com.shopsmart.model.Location>();
				if(locations !=null && locations.size() > 0)
				{				
					for (RedemptionLocation r : locations)
					{
						com.shopsmart.model.Location shop = new com.shopsmart.model.Location();
						Loc loc = new Loc();
						List<Double> coordinates = new ArrayList<Double>();
						coordinates.add(r.getLng());
						coordinates.add(r.getLat());
						loc.setCoordinates(coordinates);
						shop.setAddress(r.getStreetAddress1());
						shop.setLoc(loc);
						offerLocations.add(shop);
					}
				}
				else
				{
					Division divi = item.getDivision();
					com.shopsmart.model.Location shop = new com.shopsmart.model.Location();
					Loc loc = new Loc();
					List<Double> coordinates = new ArrayList<Double>();
					coordinates.add(divi.getLng());
					coordinates.add(divi.getLat());
					loc.setCoordinates(coordinates);
					offerLocations.add(shop);
					
				}
				offer.setLocations(offerLocations);
				offers.add(offer);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return offers;
	}
	
	return null;
 }

@Override
public int getpageIndex() {
	// TODO Auto-generated method stub
	return 0;
}
}
