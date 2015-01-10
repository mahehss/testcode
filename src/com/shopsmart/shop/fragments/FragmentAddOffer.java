package com.shopsmart.shop.fragments;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.http.POST;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.MenuItem;
import com.shopsmart.model.Category;
import com.shopsmart.model.Loc;
import com.shopsmart.model.Location;
import com.shopsmart.model.Offer;
import com.shopsmart.shop.ActivityCategory;
import com.shopsmart.shop.ActivityHome;
import com.shopsmart.shop.R;
import com.shopsmart.shop.libraries.HttpHelper;
import com.shopsmart.shop.libraries.OfferService;
import com.shopsmart.shop.utils.LocationUtils;
import com.shopsmart.shop.utils.Utils;


public class FragmentAddOffer extends SherlockFragment implements OnClickListener {

	private final int SELECT_PHOTO = 1;
	private ImageView imageView;
	OfferService offerservice;
	List<Category> categories;
	Spinner spinner1;
	ArrayAdapter<Category> dataAdapter ;
	int selectedCategory = 0;
	Bitmap selectedImage;
	EditText title, description;
	DatePicker start,end;
	Button save;
	Utils util;
	public 	FragmentAddOffer()
	{
		
	}
	
	
	
	@Override     
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        offerservice = new OfferService();
        categories = new ArrayList<Category>();
        util = new Utils(getActivity());
	}
	
	
	
	@Override 
	public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container,
			Bundle savedInstanceState) 
	{
		View v = inflater.inflate(R.layout.fragment_add_offer, container, false);
		
		   
        imageView = (ImageView)v.findViewById(R.id.imageView);
        title = (EditText)v.findViewById(R.id.EditOfferTitle);
        description = (EditText)v.findViewById(R.id.EditOfferDescription);
        start = (DatePicker)v.findViewById(R.id.date_picker);
        end = (DatePicker)v.findViewById(R.id.enddate_picker);
        Button upload = (Button)v.findViewById(R.id.btn_pick);
        spinner1  = (Spinner)v.findViewById(R.id.spinnerCategory);
       
        upload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {				
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, SELECT_PHOTO);
			}
		});
        
        save = (Button)v.findViewById(R.id.buttonSave);
        save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				save.setClickable(false);
				new HttpAsyncTask().execute();
				
			}
		});
        
        dataAdapter =
        		new ArrayAdapter<Category>(this.getActivity(), android.R.layout.simple_spinner_item,categories);
        
        dataAdapter.setDropDownViewResource
                     (android.R.layout.simple_spinner_dropdown_item);
                      
        spinner1.setAdapter(dataAdapter);
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos,
		            long id)  {
				selectedCategory = pos;
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
		});
        new setCategories().execute();
        
		return v;
	};
	
	public class setCategories extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
			categories = offerservice.getCategories();
			
			return null;
		}
		
		
		@SuppressLint("NewApi") @Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			dataAdapter.addAll(categories);
			dataAdapter.notifyDataSetChanged();
			super.onPostExecute(result);
		}
	
	}
	
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @SuppressLint("NewApi") @Override
        protected String doInBackground(String... urls) {
 
          /* fill object */
        	Offer offer = new Offer();
        	offer.setName(title.getText().toString());
        	offer.setDescription(description.getText().toString());
        	offer.setShortDesc(description.getText().toString());
        	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
        	offer.setCategory(categories.get(selectedCategory).getId());
        	
        
        	Date startdate = new Date(start.getCalendarView().getDate());
        	offer.setStartDate(startdate.toString());
        	
        	Date endDate = new Date(end.getCalendarView().getDate());
        	offer.setEndDate(endDate.toString());
        	
        	selectedImage.compress(Bitmap.CompressFormat.JPEG,40,baos); 
        	byte[] byteImage_photo = baos.toByteArray();

            //generate base64 string of image

           String encodedImage = Base64.encodeToString(byteImage_photo,Base64.DEFAULT);

        	offer.setImage(encodedImage);
        	List<Location> locations = new ArrayList<Location>();
        	Location location = LocationUtils.getCustomLocation(getActivity());
        	locations.add(location);
        	offer.setLocations(locations);
        	offer.setEmail(util.loadUserInfo("Email"));
            return HttpHelper.POST(OfferService.base_url + OfferService.service_add_offer,offer);
        	
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getActivity(), "Data sent successfully.. Waiting for approval", Toast.LENGTH_LONG).show();
        	save.setClickable(true);
         // Call ActivityCategory
         	Intent	i = new Intent(getActivity(), ActivityHome.class);
         	startActivity(i);
       }
    }
 
    private boolean validate(){
      
            return true;    
    }
	
	 @Override
	public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
	        super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

	    switch(requestCode) { 
        case SELECT_PHOTO:
            if(resultCode == Activity.RESULT_OK){
				try {
					final Uri imageUri = imageReturnedIntent.getData();
					Options ops = new Options();
					ops.inSampleSize = 4;
					final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
					selectedImage = BitmapFactory.decodeStream(imageStream, new Rect(),ops);				
					imageView.setImageBitmap(selectedImage);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

            }
        }
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
			switch (v.getId()) {
			case R.id.btn_pick:
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, SELECT_PHOTO);
				break;

			default:
				break;
			}
		}
	

}
