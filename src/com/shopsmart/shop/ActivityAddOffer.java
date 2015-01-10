package com.shopsmart.shop;

import com.actionbarsherlock.view.MenuItem;
import com.shopsmart.shop.fragments.FragmentAddOffer;
import com.shopsmart.shop.fragments.FragmentMenuList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

public class ActivityAddOffer extends ActivityBase implements OnClickListener,FragmentMenuList.OnMenuListSelectedListener {
	
	public ActivityAddOffer() {
		super(R.string.add_offer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_new_offer);
		
		FragmentAddOffer addNewOfferFregment = new FragmentAddOffer();
		// 	add the fragment to the 'fragment_container' FrameLayout
			getSupportFragmentManager().beginTransaction()
             .add(R.id.frame_content, addNewOfferFregment).commit();
		
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	   super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	case android.R.id.home:
	    		
	    		// Previous page or exit
	    		finish();
	    		return true;
	    		
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMenuListSelected(int selectedIdMenu) {
		// TODO Auto-generated method stub
		
	}

}
