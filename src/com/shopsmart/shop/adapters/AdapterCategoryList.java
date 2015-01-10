package com.shopsmart.shop.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopsmart.model.Category;
import com.shopsmart.shop.R;
import com.shopsmart.shop.fragments.FragmentCategoryList;
import com.shopsmart.shop.lazylist.ImageLoader;
public class AdapterCategoryList extends BaseAdapter {

		private Activity activity;
		private List<Category> categories;
		ImageLoader imageLoader;

		
		public AdapterCategoryList(Activity act,List<Category> inCategories) {
			activity = act;
			categories = inCategories;
			imageLoader=new ImageLoader(activity.getApplicationContext());
		}
		
		public int getCount() {
			// TODO Auto-generated method stub
			return categories.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			
			
			if(convertView == null){
				LayoutInflater inflater = (LayoutInflater) activity
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.adapter_row_category, null);
				holder = new ViewHolder();	
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.lblNameCategory = (TextView) convertView.findViewById(R.id.lblNameCategory);
			holder.icMarker 	= (ImageView) convertView.findViewById(R.id.ic_marker);
			Category category = categories.get(position);
			holder.lblNameCategory.setText(category.getName());
			
			// set data to textview and imageview
			imageLoader.DisplayMarker(category.getImage(), holder.icMarker);
			
			
			return convertView;
		}
		
		static class ViewHolder {
			TextView lblNameCategory;
			ImageView icMarker;
		}
		
		
	}