package com.shopsmart.shop;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.ads.AdView;

import com.shopsmart.shop.R;
import com.shopsmart.shop.ads.Ads;
import com.shopsmart.shop.fragments.FragmentOfferDetails;
import com.shopsmart.shop.poco.OffersCollection;
import com.shopsmart.shop.utils.Utils;

public class ActivityDetailPlace extends SherlockFragmentActivity implements
		OnClickListener {
	ViewPager mViewPager;
	// Create an instance of ActionBar
	ActionBar actionbar;
	Utils utils;

	// Declare object of AdView class
	AdView adView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pager_details);
		utils = new Utils(this);
		// Change actionbar title
		int titleId = Resources.getSystem().getIdentifier("action_bar_title",
				"id", "android");
		if (0 == titleId)
			titleId = com.actionbarsherlock.R.id.abs__action_bar_title;

		// Change the title color to white
		TextView txtActionbarTitle = (TextView) findViewById(titleId);
		txtActionbarTitle.setTextColor(getResources().getColor(
				R.color.actionbar_title_color));

		// Get ActionBar and set back button on actionbar
		actionbar = getSupportActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		actionbar.setHomeButtonEnabled(true);

		Intent i = getIntent();
		int offer_index = (Integer) i.getSerializableExtra(utils.EXTRA_OFFER);

		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		final OffersCollection offers = OffersCollection.get(this);
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
			@Override
			public int getCount() {

				return offers.size();
			}

			@Override
			public Fragment getItem(int pos) {

				if (pos == offers.size()) {

				}
				return new FragmentOfferDetails(offers.get(pos));
			}

		});

		mViewPager.setCurrentItem(offer_index);
		// connect view objects and xml ids
		 //adView = (AdView)this.findViewById(R.id.adView);
		// Ads.loadAds(adView);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case android.R.id.home:
	        //do your own thing here
	    	finish();
	        return true;
	    default: return super.onOptionsItemSelected(item);  
	    }
	}

}