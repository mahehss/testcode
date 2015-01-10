/**
    * File        : ActivityHome.java
    * App name    : Perkutut
    * Version     : 1.1.4
    * Created     : 01/19/14

    * Created by Taufan Erfiyanto & Cahaya Pangripta Alam on 11/24/13.
    * Copyright (c) 2013 pongodev. All rights reserved.
    */

package com.shopsmart.shop;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.shopsmart.model.Device;
import com.shopsmart.shop.R;
import com.shopsmart.shop.ads.Ads;
import com.shopsmart.shop.fragments.FragmentHomeLatestMapsList;
import com.shopsmart.shop.fragments.FragmentMenuList;
import com.shopsmart.shop.libraries.HttpHelper;
import com.shopsmart.shop.libraries.OfferService;
import com.shopsmart.shop.utils.Utils;

public class ActivityHome extends ActivityBase implements FragmentMenuList.OnMenuListSelectedListener, 
FragmentHomeLatestMapsList.OnDataListSelectedListener, OnClickListener{	
	
	Dialog dialog; 
	// Declare object of AdView class
	AdView adView;
	static final String SCOPE = "oauth2:https://www.googleapis.com/auth/userinfo.profile";
	  
	static final int REQUEST_CODE_PICK_ACCOUNT = 1000;
	static final int REQUEST_CODE_RECOVER_FROM_AUTH_ERROR = 1001;
	static final int REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR = 1002;
	// Declare object of Context and Utils class
    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    /**
     * Substitute you own sender ID here. This is the project number you got
     * from the API Console, as described in "Getting Started."
     */
    String SENDER_ID = "575794190320";

    /**
     * Tag used on log messages.
     */
    static final String TAG = "GCM Demo";
    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();

    String regid;
	Context ctx;
	Utils utils;
	String mEmail;
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.actionbar_menu, menu);
		return true;      
    }
	
	public ActivityHome() {
		super(R.string.app_name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ctx = this;
		setContentView(R.layout.activity_home);
		// Declare object of Utils class;
		utils = new Utils(this);
		
		// connect view objects and xml ids
		//adView = (AdView)this.findViewById(R.id.adView);
		//Ads.loadAds(adView); 	
		// Check paramter overlays
		int paramOverlay = utils.loadPreferences(utils.UTILS_OVERLAY);
		
		// Condition if app start in the first time overlay will show 
		if(paramOverlay!=1) showOverLay();
				
		// Sliding menu
		SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        
     	// Change actionbar title
     	int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");    
     	if ( 0 == titleId ) titleId = com.actionbarsherlock.R.id.abs__action_bar_title; 
     			
     	// Change the title color to white
     	TextView txtActionbarTitle = (TextView)findViewById(titleId);
     	txtActionbarTitle.setTextColor(getResources().getColor(R.color.actionbar_title_color));	
     	
        // Checking internet connection. if not enable, show toast alert
     		if(!utils.isNetworkAvailable()){
     			Toast.makeText(this, getString(R.string.internet_alert), Toast.LENGTH_SHORT).show();
     		}
     		else
     		{
     			//get user name
     			if(utils.loadUserInfo("Email") =="unknown")
     				getUsername();	
     			
     			//GCM related code
     			 // Check device for Play Services APK. If check succeeds, proceed with GCM registration.
     	        if (checkPlayServices()) {
     	            gcm = GoogleCloudMessaging.getInstance(this);
     	            regid = getRegistrationId(ctx);

     	            if (regid.isEmpty()) {
     	                registerInBackground();
     	            }
     	        } else {
     	            Log.i(TAG, "No valid Google Play Services APK found.");
     	        }
     			
     			Intent i = getIntent();
     			FragmentHomeLatestMapsList MapsListFrag = new FragmentHomeLatestMapsList();     			
     			//handle categories
     			 String mCategoryId   = i.getStringExtra(utils.EXTRA_CATEGORY_ID);
     			 if(mCategoryId !=null)
     			 {
     				String mCategoryName   = i.getStringExtra(utils.EXTRA_CATEGORY_NAME);
     		 		ActionBar actionbar = getSupportActionBar();
     		 		actionbar.setDisplayHomeAsUpEnabled(true);	
     		 		actionbar.setTitle(mCategoryName);
     		 		Bundle bundle = new Bundle();
     		        bundle.putString(utils.EXTRA_CATEGORY_ID, mCategoryId);
     		        bundle.putString(utils.EXTRA_CATEGORY_NAME, mCategoryName);
     		        MapsListFrag.setArguments(bundle);
     			 }
     			 else
     			 {
     				ActionBar actionbar = getSupportActionBar();
     				actionbar.setDisplayHomeAsUpEnabled(true);
     				actionbar.setTitle("Home");
     			 }
     			 // handle search text
     			 
     			// 	add the fragment to the 'fragment_container' FrameLayout
     			getSupportFragmentManager().beginTransaction()
                     .add(R.id.frame_content, MapsListFrag).commit();
     		}
             
  		// load ads
            

	}	
	
	// Call Activity when Menu selected
	@Override
	public void onMenuListSelected(int mSelectedMenu) {
		// TODO Auto-generated method stub
		Intent i;
		switch (mSelectedMenu) {
		
		case 0:
				ActionBar actionbar = getSupportActionBar();
				actionbar.setDisplayHomeAsUpEnabled(true);
				actionbar.setTitle("Home");
	        FragmentHomeLatestMapsList MapsListFrag = new FragmentHomeLatestMapsList();

	        // add the fragment to the 'fragment_container' FrameLayout
             getSupportFragmentManager().beginTransaction()
                     .replace(R.id.frame_content, MapsListFrag).commit();
             
             getSlidingMenu().toggle(true);
             break;
             
		case 1:
			// Call ActivityCategory
			i = new Intent(this, ActivityCategory.class);
			startActivity(i);
			break;
	
		case 2:
			// Call ActivitySearch
			i = new Intent(this, ActivityHome.class);
			startActivity(i);
			break;	
		
		case 3:
			// Call ActivitySetting
			i = new Intent(this, ActivitySetting.class);
			startActivity(i);
			break;	
			
		case 4:
			// Call ActivityAbout
			i = new Intent(this, ActivityAbout.class);
			startActivity(i);
			break;	
		case 5:
			// Call ActivityAbout
			i = new Intent(this, ActivityAddOffer.class);
			startActivity(i);
			break;		
			
		default:
			break;
		}
		
	}
	
	// Listener when item selected 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.abAroundYou:
	        	// Call ActivityPlaceAroundYou
	        	Intent iCart = new Intent(this, ActivityPlaceAroundYou.class);
				startActivity(iCart);
	            break;

	        default:
	            return super.onOptionsItemSelected(item);
	    }
		return true;
	}

	// Listener when list selected
	@Override
	public void onListSelected(int offer_index) {
		// TODO Auto-generated method stub
		
		// Call ActivityDetailPlace
		Intent i = new Intent(this, ActivityDetailPlace.class);
		i.putExtra(utils.EXTRA_OFFER, offer_index);
		startActivity(i);
	}
	
	// Method is used to show overlay when apps starting in the first time.
	private void showOverLay(){

		dialog = new Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar);

		dialog.setContentView(R.layout.overlay_view);

		LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.overlayLayout);
		layout.setOnClickListener(this);
		dialog.show();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.overlayLayout:
			utils.savePreferences(utils.UTILS_OVERLAY, 1);
			dialog.dismiss();
			break;

		default:
			break;
		}
	}

	  @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == REQUEST_CODE_PICK_ACCOUNT) {
	            if (resultCode == RESULT_OK) {
	                mEmail = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
	                utils.saveUserInfo("Email", mEmail);
	                getUsername();
	            } else if (resultCode == RESULT_CANCELED) {
	                Toast.makeText(this, "You must pick an account", Toast.LENGTH_SHORT).show();
	                getUsername();
	            }
	        } else if ((requestCode == REQUEST_CODE_RECOVER_FROM_AUTH_ERROR ||
	                requestCode == REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR)
	                && resultCode == RESULT_OK) {
	          //  handleAuthorizeResult(resultCode, data);
	            return;
	        }
	        super.onActivityResult(requestCode, resultCode, data);
	    }
	  
	  
	  /** Attempt to get the user name. If the email address isn't known yet,
	     * then call pickUserAccount() method so the user can pick an account.
	     */
	    private void getUsername() {
	        if (mEmail == null) {
	            pickUserAccount();
	        } else {
	            if (utils.isNetworkAvailable()) {
	            	new TokenRetriever().execute();
	            	
	            } else {
	                Toast.makeText(this, "No network connection available", Toast.LENGTH_SHORT).show();
	            }
	        }
	    }

	    /** Starts an activity in Google Play Services so the user can pick an account */
	    private void pickUserAccount() {
	        String[] accountTypes = new String[]{"com.google"};
	        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
	                accountTypes, false, null, null, null, null);
	        startActivityForResult(intent, REQUEST_CODE_PICK_ACCOUNT);
	    }
	    
	    public class TokenRetriever  extends AsyncTask<Void, Void, Boolean>{

			@Override
			protected Boolean doInBackground(Void... params) {
				// TODO Auto-generated method stub
				
				 try {
                     try {
						String token = GoogleAuthUtil.getToken(ctx, mEmail, SCOPE);
						 utils.saveUserInfo("Token", token);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                 } catch (UserRecoverableAuthException userRecoverableException) {
                     // GooglePlayServices.apk is either old, disabled, or not present, which is
                     // recoverable, so we need to show the user some UI through the activity.
                	// Toast.makeText(this, userRecoverableException.getMessage(), Toast.LENGTH_LONG).show();
                	 return false;
                 } catch (GoogleAuthException fatalException) {
                	// Toast.makeText(this, fatalException.getMessage(), Toast.LENGTH_LONG).show();
                	return false;
                 }
				return true;
			}
			
			 @Override
			protected void onPostExecute(Boolean result) {
				 
				 if(!result)
					 Toast.makeText(ctx, "Could not get the token", Toast.LENGTH_LONG).show();
				// TODO Auto-generated method stub
				super.onPostExecute(result);
			}
			
	    }
	
	    /*GCM related methods start*/
	    
	    /**
	     * @return Application's version code from the {@code PackageManager}.
	     */
	    private static int getAppVersion(Context context) {
	        try {
	            PackageInfo packageInfo = context.getPackageManager()
	                    .getPackageInfo(context.getPackageName(), 0);
	            return packageInfo.versionCode;
	        } catch (NameNotFoundException e) {
	            // should never happen
	            throw new RuntimeException("Could not get package name: " + e);
	        }
	    }

	    /**
	     * @return Application's {@code SharedPreferences}.
	     */
	    private SharedPreferences getGcmPreferences(Context context) {
	        // This sample app persists the registration ID in shared preferences, but
	        // how you store the regID in your app is up to you.
	        return getSharedPreferences(ActivityHome.class.getSimpleName(),
	                Context.MODE_PRIVATE);
	    }
	    /**
	     * Sends the registration ID to your server over HTTP, so it can use GCM/HTTP or CCS to send
	     * messages to your app. Not needed for this demo since the device sends upstream messages
	     * to a server that echoes back the message using the 'from' address in the message.
	     */
	    private void sendRegistrationIdToBackend() {
	     
	    	new HttpAsyncTask().execute();
	    }
	    

	    /**
	     * Check the device to make sure it has the Google Play Services APK. If
	     * it doesn't, display a dialog that allows users to download the APK from
	     * the Google Play Store or enable it in the device's system settings.
	     */
	    private boolean checkPlayServices() {
	        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
	        if (resultCode != ConnectionResult.SUCCESS) {
	            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
	                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
	            } else {
	                Log.i(TAG, "This device is not supported.");
	                finish();
	            }
	            return false;
	        }
	        return true;
	    }

	    /**
	     * Stores the registration ID and the app versionCode in the application's
	     * {@code SharedPreferences}.
	     *
	     * @param context application's context.
	     * @param regId registration ID
	     */
	    private void storeRegistrationId(Context context, String regId) {
	        final SharedPreferences prefs = getGcmPreferences(context);
	        int appVersion = getAppVersion(context);
	        Log.i(TAG, "Saving regId on app version " + appVersion);
	        SharedPreferences.Editor editor = prefs.edit();
	        editor.putString(PROPERTY_REG_ID, regId);
	        editor.putInt(PROPERTY_APP_VERSION, appVersion);
	        editor.commit();
	    }

	    /**
	     * Gets the current registration ID for application on GCM service, if there is one.
	     * <p>
	     * If result is empty, the app needs to register.
	     *
	     * @return registration ID, or empty string if there is no existing
	     *         registration ID.
	     */
	    private String getRegistrationId(Context context) {
	        final SharedPreferences prefs = getGcmPreferences(context);
	        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
	        if (registrationId.isEmpty()) {
	            Log.i(TAG, "Registration not found.");
	            return "";
	        }
	        // Check if app was updated; if so, it must clear the registration ID
	        // since the existing regID is not guaranteed to work with the new
	        // app version.
	        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	        int currentVersion = getAppVersion(context);
	        if (registeredVersion != currentVersion) {
	            Log.i(TAG, "App version changed.");
	            return "";
	        }
	        return registrationId;
	    }

	    /**
	     * Registers the application with GCM servers asynchronously.
	     * <p>
	     * Stores the registration ID and the app versionCode in the application's
	     * shared preferences.
	     */
	    private void registerInBackground() {
	        new AsyncTask<Void, Void, String>() {
	            @Override
	            protected String doInBackground(Void... params) {
	                String msg = "";
	                try {
	                    if (gcm == null) {
	                        gcm = GoogleCloudMessaging.getInstance(ctx);
	                    }
	                    regid = gcm.register(SENDER_ID);
	                    msg = "Device registered, registration ID=" + regid;

	                    // Persist the regID - no need to register again.
	                    storeRegistrationId(ctx, regid);
	                    
	                    // You should send the registration ID to your server over HTTP, so it
	                    // can use GCM/HTTP or CCS to send messages to your app.
	                    sendRegistrationIdToBackend();
	                  
	                } catch (IOException ex) {
	                    msg = "Error :" + ex.getMessage();
	                    // If there is an error, don't just keep trying to register.
	                    // Require the user to click a button again, or perform
	                    // exponential back-off.
	                }
	                return msg;
	            }

	            @Override
	            protected void onPostExecute(String msg) {
	               Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
	            }
	        }.execute(null, null, null);
	    }
	    
	    /*GCM implemention over*/
	    /*starting saving device info*/
	    
	    private class HttpAsyncTask extends AsyncTask<Void, Void, String> {
	        @SuppressLint("NewApi") @Override
	        protected String doInBackground(Void... urls) {

	        	Device device = new Device();
	        	device.setEmail(utils.loadUserInfo("Email"));
	        	device.setToken(utils.loadUserInfo("Token"));
	        	device.setRegistrationId(getRegistrationId(ctx));
	        	device.setSenderId(SENDER_ID);
	            return HttpHelper.POST(OfferService.base_url + OfferService.service_register_device,device);
	        	
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(String result) {
	          
	       }
	    }

}