package com.sabutos.translation.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.sabutos.translation.R;
import com.sabutos.translation.model.Category;

import java.util.ArrayList;

/**
 * Created by devil on 15-Nov-16.
 */

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private int textViewResourceId;
    private ArrayList<Category> mCategoryList;


    public ListAdapter(Context mContext, int item_list_view, ArrayList<Category> mCategoryList) {
        this.mContext = mContext;
        this.textViewResourceId = item_list_view;
        this.mCategoryList = mCategoryList;
    }



    @Override
    public int getCount() {
        return mCategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCategoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mCategoryList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) mContext
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = null;

        if (convertView==null){
            convertView = mInflater.inflate(R.layout.item_list_view,null);

            TextView tv_category = (TextView) convertView.findViewById(R.id.textView);
            Category category =mCategoryList.get(position);
            tv_category.setText(Html.fromHtml(category.getTitle()));

            convertView.setBackgroundColor(Color.parseColor("#f1f1f1"));

        }
        return convertView;
    }

}
