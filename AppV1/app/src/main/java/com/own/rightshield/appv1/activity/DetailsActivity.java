package com.own.rightshield.appv1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.own.rightshield.appv1.R;
import com.squareup.picasso.Picasso;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.bandNameImage) ImageView bandNameImage;
    @BindView(R.id.yearContent) TextView yearContent;
    @BindView(R.id.albumContent) TextView albumContent;
    @BindView(R.id.isCheck) CheckBox isChecked;
    @BindView(R.id.moreInfoLink) TextView moreInfoLink;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Bind Butterknife to activity
        ButterKnife.bind(this);

        // Get the index of the artist selected
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        index = bundle.getInt("INDEX");

        // Offset needed for accesing the information array
        int offset = 4*index;

        // Information array
        String [] info = getResources().getStringArray(R.array.info);

        // Get the information needed
        Picasso.with(this)
                .load(info[offset])
                .into(bandNameImage);
        yearContent.setText(info[1+offset]);
        albumContent.setText(info[2+offset]);

        // Set link to wikipedia
        moreInfoLink.setText(Html.fromHtml(info[3+offset]));
        moreInfoLink.setMovementMethod(LinkMovementMethod.getInstance());

        //Pattern p = Pattern.compile(moreInfoLink.getText().toString());
        //Linkify.addLinks(moreInfoLink, p, info[3+offset]);
        //moreInfoLink.setMovementMethod(LinkMovementMethod.getInstance());
    }

    // Send to MainActivity the status of the CheckBox
    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", index);
        bundle.putBoolean("CHECK", isChecked.isChecked());
        intent.putExtras(bundle);

        setResult(RESULT_OK, intent);
        finish();
    }
}
