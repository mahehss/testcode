package com.shopsmart.shop.libraries;

import java.util.ArrayList;
import java.util.List;


import android.location.Location;

import com.shopsmart.model.Offer;

public class MainOffersProvider implements OffersProvider {

	@Override
	public List<Offer> getOffersByLocation(Location location,
			String category, String searchText, int pageIndex, int pageSize) {
		
		 List<Offer> offers = new ArrayList<Offer>();
		 try {
			 	OfferService offerService = new OfferService();
			 	offers = offerService.getOffers(pageIndex, location.getLongitude(),location.getLatitude(), category, searchText);
			 	for (Offer offer : offers) {
			 		String desc =  "Offer ends on: " + offer.getEndDate().substring(0,10);
			 		offer.setShortDesc(desc);
				}
		 } catch (Exception e) {
	            // TODO Auto-generated catch block
	          e.printStackTrace();
	        }      
		return offers;
	}

	@Override
	public int getpageIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

}
