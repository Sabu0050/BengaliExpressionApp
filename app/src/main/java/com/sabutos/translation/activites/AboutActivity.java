package com.sabutos.translation.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sabutos.translation.R;

public class AboutActivity extends AppCompatActivity {

    TextView tLink1,tLink2,tLink4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.setTitle("About");
        tLink1= (TextView) findViewById(R.id.textView4);
        tLink1.setMovementMethod(LinkMovementMethod.getInstance());
        tLink1.setText(Html.fromHtml(getResources().getString(R.string.about_two)));
        tLink2 = (TextView)findViewById(R.id.textView6);
        tLink2.setText(Html.fromHtml(getResources().getString(R.string.about_four)));
        tLink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailintent = new Intent(android.content.Intent.ACTION_SEND);
                emailintent.setType("plain/text");
                emailintent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] {"english.bangla.com@gmail.com" });
                emailintent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                emailintent.putExtra(android.content.Intent.EXTRA_TEXT,"");
                startActivity(Intent.createChooser(emailintent, "Send mail..."));
            }
        });

        tLink4 = (TextView) findViewById(R.id.textView8);
        tLink4.setMovementMethod(LinkMovementMethod.getInstance());
        tLink4.setText(Html.fromHtml(getResources().getString(R.string.about_six)));



    }
}
