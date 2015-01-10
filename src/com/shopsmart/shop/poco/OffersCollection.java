package com.shopsmart.shop.poco;

import java.util.ArrayList;
import java.util.List;

import com.shopsmart.model.Offer;

import android.content.Context;



public class OffersCollection {
	
	private List<Offer> offers;
	 private Context mAppContext;
	 private static OffersCollection offerCollection;
	 private OffersCollection(Context appContext)
	 {
		 mAppContext = appContext;
		 offers = new ArrayList<Offer>();
	 }
	 
	  public static OffersCollection get(Context c) {
	        if (offerCollection == null) {
	        	offerCollection = new OffersCollection(c.getApplicationContext());
	        }
	        return offerCollection;
	    }

	  public  List<Offer> getOffers()
	  {
		  return offers;
	  }
	  public void clear()
	  {
		  offers.clear();
	  }
	  
	  public void addOffers( List<Offer> newOffers)
	  {
		  if(newOffers !=null)
			  offers.addAll(newOffers);
	  }
	  public Offer get( int i)
	  {
		return  offers.get(i);
	  }
	  public int size()
	  {
		  return offers.size();
	  }
}
