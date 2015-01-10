package com.shopsmart.shop.fragments;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.polites.android.GestureImageView;
import com.shopsmart.model.Location;
import com.shopsmart.model.Offer;
import com.shopsmart.shop.ActivityShare;
import com.shopsmart.shop.R;
import com.shopsmart.shop.ads.Ads;
import com.shopsmart.shop.lazylist.ImageLoader;
import com.shopsmart.shop.libraries.OfferService;

import com.shopsmart.shop.utils.Utils;

public class FragmentOfferDetails extends SherlockFragment implements OnClickListener {


		OfferService offerService;
		JSONObject json;
	
		ImageLoader imageLoader;
		WebView webDesc,lblOfferDesc;

		private int mSelectedMapType;
		private float mSelectedMapZoom;
		Utils utils;
		// Declare variables to store data
		private Offer offer;
		private Location location;
		Boolean isOfferDetailsAvailable = false;
		// Declare view objects
		TextView lblOfferName, lblAddress, lblPhone, lblWebsite,lblLocationName;
		GestureImageView imgThumbnail;
		LinearLayout lytMedia, lytRetry, lytDetail;
		ProgressBar prgLoading;
		Button btnRetry;
		ImageButton imgBtnShare, imgBtnDirection;

		// Declare object to handle map
		GoogleMap map;
		GoogleMapOptions options = new GoogleMapOptions();
		CustomMapFragment mMapFragment;
	public 	FragmentOfferDetails(Offer offer)
	{
		this.offer = offer;
		
		
	}
	
	
	
	@Override     
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    	offerService = new OfferService();
    	utils = new Utils(this.getActivity());
    	location = offer.getNearestLocation(getActivity());
		imageLoader = new ImageLoader(this.getActivity());
		
		mSelectedMapType = utils
				.loadPreferences(getString(R.string.preferences_type));
		mSelectedMapZoom = utils
				.loadPreferences(getString(R.string.preferences_zoom));

		// Condition if Map Zoom is 0
		if (mSelectedMapZoom == 0) {
			mSelectedMapZoom = 12;
		}
	}
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();

	}
	
	@Override 
	public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container,
			Bundle savedInstanceState) 
	{
		View v = inflater.inflate(R.layout.fragment_detail_info, container, false);
		// Connect view objects and xml ids
		lblOfferName = (TextView) v.findViewById(R.id.lblOfferTitle);
		lblLocationName =(TextView) v.findViewById(R.id.lblLocationName);
		lblOfferDesc = (WebView) v.findViewById(R.id.lblOfferDescription);
		lblAddress = (TextView) v.findViewById(R.id.lblAddress);
		//lblPhone = (TextView) v.findViewById(R.id.lblPhone);
		lblWebsite = (TextView) v.findViewById(R.id.lblWebsite);
	//	lytMedia = (LinearLayout) v.findViewById(R.id.lytMedia);
		lytRetry = (LinearLayout) v.findViewById(R.id.lytRetry);
		lytDetail = (LinearLayout) v.findViewById(R.id.lytDetail);
		prgLoading = (ProgressBar) v.findViewById(R.id.prgLoading);
		btnRetry = (Button) v.findViewById(R.id.btnRetry);
		imgBtnShare = (ImageButton) v.findViewById(R.id.imgShare);
		imgBtnDirection = (ImageButton) v.findViewById(R.id.imgDirection);
		imgThumbnail = (GestureImageView) v.findViewById(R.id.imgOffer);
		webDesc = (WebView) v.findViewById(R.id.webDesc);
	
		lblAddress.setVisibility(4);
		 mMapFragment = new  CustomMapFragment();
        map = mMapFragment.getMapObject();
		/*SupportMapFragment mMapFragment = new  SupportMapFragment() {    
     		@Override
     	       public void onActivityCreated(Bundle savedInstanceState) {
     	           super.onActivityCreated(savedInstanceState);
     	           map = this.getMap();
     	           if (map != null) {
     	               //Your initialization code goes here
     	           }
     	       }
        }; */
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.map_container, mMapFragment).commit();

		lblWebsite.setOnClickListener(this);
		imgBtnShare.setOnClickListener(this);
		imgBtnDirection.setOnClickListener(this);
		btnRetry.setOnClickListener(this);

		// Call Method setUpMapIfNeeded
		setUpMapIfNeeded();

		// Call asynctask class
		new getDataAsync().execute();

		return v;
	};

	// AsyncTask to Get Data from Server and put it on View Object
		public class getDataAsync extends AsyncTask<Void, Void, Void> {

	

			@Override
			protected void onPreExecute() {

			}

		

			@Override
			protected void onPostExecute(Void result) {


				if (offer != null) {
					// Display Data
					lytDetail.setVisibility(View.VISIBLE);
					lytRetry.setVisibility(View.GONE);
					lblOfferName.setText(offer.getName());
					if(location !=null)
						lblLocationName.setText("Nearest location: "+location.getAddress());
					lblWebsite.setText("Get the directions:");
				
					// Load data from url
					  String mimeType = "text/html";
				        String encoding = "utf-8";
					webDesc.loadData(offer.getDescription() +
							"<br><br> Other Locations:", mimeType, encoding);
					lblOfferDesc.loadData(offer.getShortDesc(), mimeType, encoding);
					imageLoader.DisplayImage(offer.getImage(),
							imgThumbnail);
					 map = mMapFragment.getMapObject();
					if (map != null && location!=null)
					{
						if(location.getLoc() !=null)
						{
						List<Double> coords = location.getLoc().getCoordinates();
						map.animateCamera(CameraUpdateFactory.newLatLngZoom(
								new LatLng(coords.get(1),coords.get(0)),
								mSelectedMapZoom));
						}
					}
				
					// Call AsynTask class
					new loadMarkerFromServer().execute();

				} else {
					lytDetail.setVisibility(View.GONE);
					lytRetry.setVisibility(View.VISIBLE);
					Toast.makeText(getActivity(),
							getString(R.string.no_connection), Toast.LENGTH_SHORT)
							.show();

				}

			}



			@Override
			protected Void doInBackground(Void... params) {
				return null;
			}

		}

		// AsyncTask class to load marker from server in UI background
		public class loadMarkerFromServer extends AsyncTask<Void, Void, Void> {
			Bitmap bmImg;

			@Override
			protected Void doInBackground(Void... arg0) {
			
				Bitmap.Config conf = Bitmap.Config.ARGB_8888;
				Bitmap bmp = Bitmap.createBitmap(80, 80, conf);
				Canvas canvas = new Canvas(bmp);

				// Paint defines the text color,
				// Stroke width, size
				Paint color = new Paint();
				color.setTextSize(35);
				color.setColor(Color.BLACK);

				URL url;
				try {
					url = new URL(offerService.marker_url);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setDoInput(true);
					conn.connect();
					InputStream is = conn.getInputStream();
					bmImg = BitmapFactory.decodeStream(is);

					// modify canvas
					canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),
							R.drawable.ic_launcher), 0, 0, color);
					canvas.drawText("User Name!", 30, 40, color);

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				
				 map = mMapFragment.getMapObject();
				// Add marker to Map
				if (map != null) {
					 setUpMapIfNeeded();
					if(offer.getLocations() != null && offer.getLocations().size() >0)
					{
						for(Location s : offer.getLocations())
						{
							if(s !=null && s.getLoc() !=null)
							{
							List<Double> coords = s.getLoc().getCoordinates();
							map.addMarker(new MarkerOptions()
							.position(new LatLng(coords.get(1), coords.get(0)))
							.icon(BitmapDescriptorFactory.fromBitmap(bmImg))
							.anchor(0.5f, 1)); 
							}
						}
					}
					
				}
			}

		}

		// Method to check map
		private void setUpMapIfNeeded() {
			// Do a null check to confirm that we have not already instantiated the
			// map.

				if (map != null)
					setUpMap();
		
		}

		// Method to Set Map Type Selected
		private void setUpMap() {
			// Call previous map type setting
			setMapType(mSelectedMapType);
			
		}

		// Method to set map type based on dialog map type setting
		void setMapType(int position) {
			switch (position) {
			case 0:
				map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				break;
			case 1:
				map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
				break;
			case 2:
				map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				break;
			case 3:
				map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
				break;
			}
		}

	
		public void setImageInThread(final ImageView imageView, final String url) {
			final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message message) {
					Drawable result = (Drawable) message.obj;
					imageView.setImageDrawable(result);
				}
			};

			new Thread() {
				@Override
				public void run() {
					Drawable drawable = LoadImageFromWebOperations(url);
					Message message = handler.obtainMessage(1, drawable);
					handler.sendMessage(message);
				}
			}.start();
		}

		private Drawable LoadImageFromWebOperations(String url) {
			try {
				InputStream is = (InputStream) new URL(url).getContent();
				Drawable d = Drawable.createFromStream(is, "image.png");
				return d;
			} catch (Exception e) {
				System.out.println("Exc=" + e);
				return null;
			}
		}

		@Override
		public void onResume() {
			super.onResume();
			setUpMapIfNeeded();
		}

		// Listener for option menu
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
			case android.R.id.home:

				// Previous page or exit
				getActivity().finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
			}
		}

		// Listener for on click
		@Override
		public void onClick(View v) {
			Intent i;
			switch (v.getId()) {
			case R.id.imgShare:
				// Share google play link of this app to other app such as facebook,
				// twitter etc
				i = new Intent(getActivity(), ActivityShare.class);
				i.putExtra(Utils.EXTRA_LOCATION_ID, location.getId());
				i.putExtra(utils.EXTRA_LOCATION_NAME, location.getAddress());
				i.putExtra(utils.EXTRA_LOCATION_DESC, offer.getDescription());
				i.putExtra(utils.EXTRA_LOCATION_IMG, offer.getImage());
				startActivity(i);
				break;

			case R.id.btnRetry:
				// Retry to get Data
				new getDataAsync().execute();
				break;

			case R.id.imgDirection:
				// Open ActivityDirection to get Direction
				  i = new Intent(Intent.ACTION_VIEW);
				  List<Double> coords = location.getLoc().getCoordinates();
				    i.setData(Uri.parse("geo:"+coords.get(1)+"," + coords.get(0)));
				    if (i.resolveActivity(getActivity().getPackageManager()) != null) {
				        startActivity(i);
				    }
				
				break;

			case R.id.lblWebsite:
				
				// Open Built-in browser
				//Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(offer.getImage()));
				//startActivity(browserIntent);
				// Open ActivityDirection to get Direction
				  i = new Intent(Intent.ACTION_VIEW);
				  List<Double> coords1 = location.getLoc().getCoordinates();
				    i.setData(Uri.parse("geo:"+coords1.get(1)+"," + coords1.get(0)));
				    if (i.resolveActivity(getActivity().getPackageManager()) != null) {
				        startActivity(i);
				    }
					break;
			default:
				break;
			}
		}
	

}
