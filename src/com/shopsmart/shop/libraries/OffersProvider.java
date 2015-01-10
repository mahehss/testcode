package com.shopsmart.shop.libraries;

import java.util.List;


import android.location.Location;

import com.shopsmart.model.Offer;

public interface OffersProvider {
	
List<Offer> getOffersByLocation(Location location, String category, String searchText, int pageIndex, int pageSize);
	int getpageIndex();
}