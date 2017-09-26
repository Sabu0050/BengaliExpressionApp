package com.sabutos.translation.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.sabutos.translation.R;
import com.sabutos.translation.model.Translation;

import java.util.ArrayList;

/**
 * Created by devil on 17-Nov-16.
 */

public class TranslationListAdapter extends BaseAdapter implements Filterable {

    private Context mContext;
    private int textViewResourceId;
    private ArrayList<Translation> mTranslationList;
    private ArrayList<Translation> filterTranslationList;
    ValueFilter valueFilter;

    public TranslationListAdapter(Context mContext, int textViewResourceId, ArrayList<Translation> mTranslationList) {
        this.mContext = mContext;
        this.textViewResourceId = textViewResourceId;
        this.mTranslationList = mTranslationList;
        filterTranslationList = mTranslationList;
    }

    @Override
    public int getCount() {
        return mTranslationList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTranslationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mTranslationList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) mContext
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = null;
        if (convertView==null){
            convertView = mInflater.inflate(R.layout.translation_item_list_view,null);

            TextView tv_enSentence = (TextView) convertView.findViewById(R.id.engTextView);
            TextView tv_bnMeaning = (TextView) convertView.findViewById(R.id.bnTextView);

            Translation translation = mTranslationList.get(position);

            tv_enSentence.setText(translation.getSentence());
            tv_bnMeaning.setText(translation.getS_meaning());

        }

        if (position % 2 == 0) {

            convertView.setBackgroundColor(Color.parseColor("#ffffff"));

        } else {

            convertView.setBackgroundColor(Color.parseColor("#f1f1f1"));

        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(valueFilter==null){
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    class ValueFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Translation> filterList = new ArrayList<Translation>();

                for (int i = 0; i < filterTranslationList.size(); i++) {
                    if ((filterTranslationList.get(i).getSentence().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {
                        Translation translation = new Translation(filterTranslationList.get(i).getId(), filterTranslationList.get(i)
                                .getSentence(), filterTranslationList.get(i).getS_meaning());

                        filterList.add(translation);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = filterTranslationList.size();
                results.values = filterTranslationList;
            }

            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            mTranslationList = (ArrayList<Translation>) results.values;
            notifyDataSetChanged();
        }
    }
}
