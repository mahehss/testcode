package com.shopsmart.shop.adapters;

import com.shopsmart.shop.fragments.FragmentOfferDetails;
import com.shopsmart.shop.poco.OffersCollection;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class OfferPagerAdapter extends FragmentPagerAdapter {

	OffersCollection offerCollection;
	public OfferPagerAdapter(FragmentManager fm) {
		super(fm);
		
		// TODO Auto-generated constructor stub
	}
	public OfferPagerAdapter(FragmentManager fm, Activity a) {
		super(fm);
		offerCollection = OffersCollection.get(a);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Fragment getItem(int pos) {
		if (pos == offerCollection.getOffers().size()) {
				
		}
		return new FragmentOfferDetails(offerCollection.getOffers().get(pos));
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return offerCollection.size();
	}

}
