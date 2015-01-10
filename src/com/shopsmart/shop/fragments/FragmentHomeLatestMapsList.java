package com.shopsmart.shop.fragments;


import java.util.Currency;

import org.json.JSONObject;

import android.app.Activity;

import android.app.ProgressDialog;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.shopsmart.shop.EndlessScrollListener;
import com.shopsmart.shop.R;
import com.shopsmart.shop.adapters.AdapterOfferList;
import com.shopsmart.shop.utils.LocationUtils;
import com.shopsmart.shop.utils.Utils;
import com.shopsmart.shop.libraries.*;
import com.shopsmart.shop.poco.OffersCollection;

public class FragmentHomeLatestMapsList extends SherlockFragment implements OnClickListener,GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener {
	
	private Location mLastLocation;
	
	
	OnDataListSelectedListener mCallback;
	
	boolean loadingMore = false;
    ProgressDialog pDialog;

    // Declare object of userFunctions and Utils class
	OfferService offerService;
	Utils utils;
	// Create instance of list and ListAdapter
	ListView list;
	TextView lblNoResult;
	AdapterOfferList mla;
	OffersCollection offerCollection;
	Button btnRetry; 
	LinearLayout lytRetry;
	
    // flag for current page
	JSONObject json;
    int mCurrentPage = 0;
    int mPreviousPage;
    int mCurrentGrouponPage = 0;
    int mPrevGrouponPage;
    int mPageSize = 10;
    String mCategoryId;
    String mSearchTxt;
	private int intLengthData;
	OfferGrabber offerGrabber;
	LocationClient mLocationClient;
	LocationListener mLocationListener = new LocationListener() {
		
		@Override
		public void onLocationChanged(Location arg0) {
			
			if(isBetterLocation(arg0,mLastLocation))
			{
				mLastLocation = arg0;
				LocationUtils.saveLocation(getActivity(),mLastLocation);
				offerCollection.clear();
				mCurrentPage = 0;
				Toast.makeText(getActivity(), "Loading Again", Toast.LENGTH_SHORT).show();
				new loadFirstListView().execute();
			}
		}
		
		private static final int TWO_MINUTES = 1000 * 60 * 2;

		/** Determines whether one Location reading is better than the current Location fix
		  * @param location  The new Location that you want to evaluate
		  * @param currentBestLocation  The current Location fix, to which you want to compare the new one
		  */
		protected boolean isBetterLocation(Location location, Location currentBestLocation) {
		    if (currentBestLocation == null) {
		        // A new location is always better than no location
		        return true;
		    }
		    
		    if(currentBestLocation.distanceTo(location)<500)
		    	return false;
		    // Check whether the new location fix is newer or older
		    long timeDelta = location.getTime() - currentBestLocation.getTime();
		    boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
		    boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
		    boolean isNewer = timeDelta > 0;

		    // If it's been more than two minutes since the current location, use the new location
		    // because the user has likely moved
		    if (isSignificantlyNewer) {
		        return true;
		    // If the new location is more than two minutes older, it must be worse
		    } else if (isSignificantlyOlder) {
		        return false;
		    }

		    // Check whether the new location fix is more or less accurate
		    int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
		    boolean isLessAccurate = accuracyDelta > 0;
		    boolean isMoreAccurate = accuracyDelta < 0;
		    boolean isSignificantlyLessAccurate = accuracyDelta > 200;

		    // Check if the old and new location are from the same provider
		    boolean isFromSameProvider = isSameProvider(location.getProvider(),
		            currentBestLocation.getProvider());

		    // Determine location quality using a combination of timeliness and accuracy
		    if (isMoreAccurate) {
		        return true;
		    } else if (isNewer && !isLessAccurate) {
		        return true;
		    } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
		        return true;
		    }
		    return false;
		}

		/** Checks whether two providers are the same */
		private boolean isSameProvider(String provider1, String provider2) {
		    if (provider1 == null) {
		      return provider2 == null;
		    }
		    return provider1.equals(provider2);
		}
		
		
	};
		
	
	// Declare OnListSelected interface
	public interface OnDataListSelectedListener{
		public void onListSelected(int offer_index);
	}
	
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mLocationClient = new LocationClient(getActivity(),this, this);
        mLocationClient.connect();
       
       
    }
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View v = inflater.inflate(R.layout.fragment_home_list, container, false);
		
		list 	 	= (ListView) v.findViewById(R.id.list);
		lblNoResult	= (TextView) v.findViewById(R.id.lblNoResult);
		lytRetry 	= (LinearLayout) v.findViewById(R.id.lytRetry);
		btnRetry 	= (Button) v.findViewById(R.id.btnRetry);
		
		//setup infinite scroll
		list.setOnScrollListener(new EndlessScrollListener(){

			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				if(totalItemsCount-page >5)
				{
					new loadMoreListView().execute();
				}
				pDialog.dismiss();
				
			}});
		
		btnRetry.setOnClickListener(this);
		
		// Declare object of userFunctions and Utils class
		offerService = new OfferService();
		offerGrabber = new OfferGrabber();
		utils = new Utils(getActivity());
		offerCollection =  OffersCollection.get(getActivity());
		offerCollection.clear();
		
		Bundle bundle = this.getArguments();
		if(bundle !=null)
			mCategoryId = bundle.getString(utils.EXTRA_CATEGORY_ID);
		
        
		// Listener to get selected id when list item clicked
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				// Pass id to onListSelected method on HomeActivity
				mCallback.onListSelected(position);

				// Set the item as checked to be highlighted when in two-pane layout
				list.setItemChecked(position, true);
			}
		});
		
		return v;
	}
	
	
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // The callback interface. If not, it throws an exception.
        try {
            mCallback = (OnDataListSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
	
	
	// Load first 10 videos
	private class loadFirstListView extends AsyncTask<Void, Void, Void> {
		 
        @Override
        protected void onPreExecute() {
            // Showing progress dialog before sending http request
            pDialog = new ProgressDialog(
                    getActivity());
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        protected Void doInBackground(Void... unused) {
        	mCurrentPage = 0;
        	getDataFromServer();
        	return (null);
        }
 
        protected void onPostExecute(Void unused) {
        	
            
        	if(isAdded()){
	            if(intLengthData != 0){
	                // Adding load more button to lisview at bottom
	            	lytRetry.setVisibility(View.GONE);
	            	list.setVisibility(View.VISIBLE);
	            	lblNoResult.setVisibility(View.GONE);
	            	// Getting adapter
	            	mla = new AdapterOfferList(getActivity(), offerCollection.getOffers());
	            	list.setAdapter(mla);
	            	
	            } else {
	            	if(offerCollection.getOffers() != null && offerCollection.size()>0){
						lblNoResult.setVisibility(View.VISIBLE);
	            		lytRetry.setVisibility(View.GONE);
	            		
		            } else {
						lblNoResult.setVisibility(View.GONE);
	            		lytRetry.setVisibility(View.VISIBLE);
		            	Toast.makeText(getActivity(), getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
	            	}
	            }
        	}
     
        	pDialog.dismiss();
        }
    }
	
	// Load more videos
    private class loadMoreListView extends AsyncTask<Void, Void, Void> {
 
        @Override
        protected void onPreExecute() {
            // Showing progress dialog before sending http request
        	pDialog = new ProgressDialog(
                    getActivity());
            pDialog.setMessage("Please wait..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show(); 
        }
 
        protected Void doInBackground(Void... unused) {
        	
			// Store previous value of current page
			mPreviousPage = mCurrentPage;
            // Increment current page
			mCurrentPage =mCurrentPage+1;
			getDataFromServer();
            return (null);
        }
 
        protected void onPostExecute(Void unused) {

            if(offerCollection.getOffers() != null){
            	// Get listview current position - used to maintain scroll position
	            int currentPosition = list.getFirstVisiblePosition();
	

            	lytRetry.setVisibility(View.GONE);
	            // Appending new data to menuItems ArrayList
            //	mla = new AdapterOfferList(getActivity(), offerCollection.getOffers());
            	//updateReceiptsList()
	           // list.setAdapter(mla);
	            // Setting new scroll position
	           list.smoothScrollToPosition(currentPosition);
	           
	           AdapterOfferList mla =  (AdapterOfferList)list.getAdapter();
	           if(mla !=null)
	           {
	        	   mla.updateReceiptsList();
	           }

            }else{
            	if(offerCollection.getOffers() != null){
            		mCurrentPage = mPreviousPage;
                	lytRetry.setVisibility(View.GONE);
            	}else{
            		lytRetry.setVisibility(View.VISIBLE);
            	}
            	Toast.makeText(getActivity(), getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
            }
            
            // Closing progress dialog
            pDialog.dismiss();
        }
    }
	
  
	public void getDataFromServer(){
		Location location = new Location("A");
    	location.setLatitude(mLastLocation.getLatitude());
    	location.setLongitude(mLastLocation.getLongitude());
    	if(mCategoryId ==null)
    		mCategoryId = "";
    	if(mSearchTxt ==null)
    		mSearchTxt = "";
    	offerCollection.addOffers(offerGrabber.getOffersByLocation(location,mCategoryId, mSearchTxt, mCurrentPage, mPageSize)); 
    	intLengthData = offerCollection.size();
    }
	
	@Override public void onResume() {
		super.onResume();
		
	};
	
	@Override
	public void onPause() {
	    super.onPause();
	    stopLocationUpdates();
	}

	protected void stopLocationUpdates() {
		if(mLocationClient!=null && mLocationClient.isConnected())
		{
		mLocationClient.removeLocationUpdates(
				mLocationListener);
		}
	}
	
    @Override
    public void onDestroy() {
    	//mLocationClient.removeLocationUpdates(mLocationListener);
    	list.setAdapter(null);
    	super.onDestroy();
    	
    }

   
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnRetry:
			json = null;
			new loadFirstListView().execute();
			break;

		default:
			break;
		}
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		
		 LocationRequest mLocationRequest = new LocationRequest().setInterval(5*60000)
	        		.setFastestInterval(3*60000).setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);	       
	        mLastLocation = mLocationClient.getLastLocation();	
	        LocationUtils.saveLocation(getActivity(),mLastLocation);
	    	new loadFirstListView().execute();
	    	 mLocationClient.requestLocationUpdates(mLocationRequest, mLocationListener);
		
	}

	@Override
	public void onDisconnected() {
		mLocationClient.removeLocationUpdates(mLocationListener);
	}
	
	
}
