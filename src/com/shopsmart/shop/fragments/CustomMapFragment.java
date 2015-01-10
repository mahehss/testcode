package com.shopsmart.shop.fragments;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;



public class CustomMapFragment extends SupportMapFragment {
	   private GoogleMap map;

	@Override
       public void onActivityCreated(Bundle savedInstanceState) {
           super.onActivityCreated(savedInstanceState);
           map = this.getMap();
           if (map != null) {
               //Your initialization code goes here
           }
       }
	
	 public GoogleMap getMapObject()
	 {
		 return map;
	 }

}
