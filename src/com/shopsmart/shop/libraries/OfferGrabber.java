package com.shopsmart.shop.libraries;

import java.lang.reflect.Constructor;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import android.location.Location;

import com.shopsmart.model.Offer;


public class OfferGrabber {
	
	private static Dictionary<String,OffersProvider> providers;
	private int grouponPageOffSet = 0;
	public OfferGrabber()
	{
		if(providers ==null)
		{
			providers = new  Hashtable<String, OffersProvider>();
			providers.put("Groupon", new GrouponOfferProvider());
			providers.put("Main", new MainOffersProvider());
		}
	}
	
	public  List<Offer> getOffersByLocation(Location location,
			String category, String searchText, int pageIndex, int pageSize) {
		
		List<Offer>  offers =  providers.get("Main").getOffersByLocation(location, category, searchText, pageIndex, pageSize) ;
		
		//if(offers !=null && offers.size()==pageSize)
			return offers;
		/*	
		if(offers ==null || offers.size() ==0) 
		{
			if(grouponPageOffSet ==0)
			{
				grouponPageOffSet = pageIndex;
			}
			return providers.get("Groupon").getOffersByLocation(location, category, searchText, pageIndex- grouponPageOffSet, pageSize) ;
		}
		else
		{
			int offerFromTop = offers.size();
			if(grouponPageOffSet ==0)
			{
				grouponPageOffSet = pageIndex;
				offers.addAll(providers.get("Groupon").getOffersByLocation(location, category, searchText, pageIndex- grouponPageOffSet, pageSize-offerFromTop));
				grouponPageOffSet = grouponPageOffSet + offerFromTop;
			}
			else
			{
				offers.addAll(providers.get("Groupon").getOffersByLocation(location, category, searchText, pageIndex- grouponPageOffSet, pageSize-offerFromTop));
			}
			
			
			return offers;
		}*/
		//return offers;
		
		
	}
	

}
