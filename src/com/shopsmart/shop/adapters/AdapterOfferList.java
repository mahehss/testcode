package com.shopsmart.shop.adapters;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopsmart.model.Offer;
import com.shopsmart.shop.R;

import com.shopsmart.shop.lazylist.ImageLoader;
import com.shopsmart.shop.libraries.OfferService;

import com.shopsmart.shop.poco.OffersCollection;

public class AdapterOfferList extends GenericAdapter<Offer> {
	
		public ImageLoader imageLoader;
		
		
		private static LayoutInflater inflater=null;
		
		// Declare object of UserFunctions class
		OfferService offerSevice;
		
		public AdapterOfferList(Activity a, List<Offer> d) {
			super(a, d);
			imageLoader = new ImageLoader(a.getApplicationContext());
	        inflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        
		}
		public void updateReceiptsList() {
		  dataList = OffersCollection.get(mActivity).getOffers();
		  this.notifyDataSetChanged();
		}
		public int getCount() {
			// TODO Auto-generated method stub
			return dataList.size();
		}

		public Offer getItem(int position) {
			// TODO Auto-generated method stub
			return dataList.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			offerSevice = new OfferService();
			
			if(convertView == null){
				convertView = inflater.inflate(R.layout.adapter_row_home, null);
				holder = new ViewHolder();
				
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			// Connect views object and views id on xml

			holder.lblTitle 	= (TextView) convertView.findViewById(R.id.lblTitle);
			
			holder.lblOfferDistance = (TextView) convertView.findViewById(R.id.lblOfferDistance);
			holder.lblOfferShortDesc = (TextView) convertView.findViewById(R.id.lblOfferShortDec);
			holder.imgThumbnail = (ImageView) convertView.findViewById(R.id.imgThumbnail);
			Offer item = new Offer();
	        item = dataList.get(position);
	       
	        
	        String mimeType = "text/html";
	        String encoding = "utf-8";
			// Set data to textview and imageview
			imageLoader.DisplayImage(item.getImage(), holder.imgThumbnail);
			//imageLoader.DisplayMarker(item.getCategoryMarker(), holder.icMarker);

			holder.lblTitle.setText(item.getName());
			String distance = "";
			if(item.getDis() !=null)
			{
				if(item.getDis() >1)
				{
					distance = "Distance: " + String.format( "%.1f", item.getDis()) + "km";
				}
				else
				{
					distance = "Distance: " + String.format( "%f", item.getDis()*1000)  + "m";
				}
			}
			holder.lblOfferDistance.setText(distance);
			holder.lblOfferShortDesc.setText(item.getShortDesc());
		
			return convertView;
		}

		// Method to create instance of views
		static class ViewHolder {
			ImageView imgThumbnail; 
			TextView lblTitle,lblOfferShortDesc, lblOfferDistance;
			
		}

		@Override
		public View getDataRow(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}