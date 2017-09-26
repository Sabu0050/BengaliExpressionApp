package com.sabutos.translation.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.sabutos.translation.R;
import com.sabutos.translation.adapter.TranslationListAdapter;
import com.sabutos.translation.database.DatabaseHelper;
import com.sabutos.translation.model.Translation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class ItemsShowActivity extends AppCompatActivity {
    String passedText = null;

    private Context context;
    private ListView mListView;
    EditText editText;
    private TranslationListAdapter adapter;
    private ArrayList<Translation> mTranslationList;
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_show);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8471623365486761~7571366601");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        passedText = getIntent().getStringExtra("TEXT_EXTRA");
        Toast.makeText(ItemsShowActivity.this,passedText,Toast.LENGTH_SHORT).show();
        mListView = (ListView) findViewById(R.id.trListView);
        editText = (EditText) findViewById(R.id.editText);
        mDBHelper = new DatabaseHelper(this);
        mTranslationList = (ArrayList<Translation>) mDBHelper.getAllTranslationOnCategory(passedText);
        adapter = new TranslationListAdapter(getApplicationContext(),R.layout.translation_item_list_view,mTranslationList);
        mListView.setAdapter(adapter);
        mListView.setFastScrollEnabled(true);
        editText.setFocusableInTouchMode(false);
        editText.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                // TODO Auto-generated method stub
                editText.setFocusableInTouchMode(true);
                editText.requestFocus() ;
                return false;
            }});




        try{
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });}catch (Exception e){

        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_menu_bar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {
            Intent i=new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Bengali Expression");
            i.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.sabutos.translation");
            startActivity(Intent.createChooser(i,"Share via"));
            return true;
        }
        else if (id == R.id.about){
            startActivity(new Intent(this,AboutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
