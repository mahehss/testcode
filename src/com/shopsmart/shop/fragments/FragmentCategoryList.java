package com.shopsmart.shop.fragments;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.shopsmart.model.Category;
import com.shopsmart.shop.R;
import com.shopsmart.shop.adapters.AdapterCategoryList;
import com.shopsmart.shop.libraries.OfferService;

public class FragmentCategoryList extends SherlockFragment implements OnClickListener {
	OnCategoryListSelectedListener mCallback;
	
	ListView list;
	
	// Declare object of UserFunctions, AdapterCategory and JSONObject class
	AdapterCategoryList la;
	
	// Declare view objects
	Button btnRetry; 
	LinearLayout lytRetry;
	OfferService offerservice;
	List<Category> categories;

	public int mCurrentPosition;
	
	// Get length array from server
	private int intLengthData;

	public interface OnCategoryListSelectedListener{
		
		public void onCategoryListSelected(String mCategoryId, String mCategoryName);
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		offerservice = new OfferService();
		
	};
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_home_list, null);
		
		list = (ListView) v.findViewById(R.id.list);
		lytRetry = (LinearLayout) v.findViewById(R.id.lytRetry);
		btnRetry = (Button) v.findViewById(R.id.btnRetry);
	
		btnRetry.setOnClickListener(this);
		

		
		new getCategoryList().execute();

		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Category category = categories.get(position);
				mCallback.onCategoryListSelected(category.getId(),category.getName());
				mCurrentPosition = position;
				list.setItemChecked(position, true);
				
			}
		});
		
        return v;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnCategoryListSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnCategoryListSelectedListener");
        }
    }
	
	// AsynTask to get list Category data 
	public class getCategoryList extends AsyncTask<Void, Void, Void>{

		ProgressDialog progress;
    	
    	@Override
		 protected void onPreExecute() {
    		progress= ProgressDialog.show(
    				getActivity(), 
    				"", 
    				getString(R.string.loading_data), 
    				true);
    		
    	}

    	@Override
		protected Void doInBackground(Void... params) {
			getDataFromServer();
			return null;
		}
    	
    	@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
    		
    		if(isAdded()){
	            if(categories != null){
	            	lytRetry.setVisibility(View.GONE);
	        		la = new AdapterCategoryList(getActivity(),categories);
	            	list.setAdapter(la);
	            	
	            }else{
	            	lytRetry.setVisibility(View.VISIBLE);
	            	Toast.makeText(getActivity(), getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
	            }
        	}    		
			progress.dismiss();				
		}
	}
	
	// Method to get data from server
	public void getDataFromServer(){
	       
        try {
        	 categories =  offerservice.getCategories();
                            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }      
    }

	@Override
	public void onClick(View v) {
	// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnRetry:
				new getCategoryList().execute();
				break;

			default:
				break;
			}
		
	}
}

