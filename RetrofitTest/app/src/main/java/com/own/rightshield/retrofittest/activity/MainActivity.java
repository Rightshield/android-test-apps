package com.own.rightshield.retrofittest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.own.rightshield.retrofittest.R;
import com.own.rightshield.retrofittest.model.User;
import com.own.rightshield.retrofittest.interfaces.UserInterface;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.userName) TextView userName;
    @BindView(R.id.avatar) ImageView avatar;
    @BindView(R.id.userURL) TextView userURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getData();
    }

    protected void getData() {

        // Get Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Get UserInterface Service
        UserInterface service = retrofit.create(UserInterface.class);

        // HTTP GET Request for acquiring user information
        Call<User> call = service.getUser("ezmobius");

        // Callback for handling HTTP GET Response data
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    userName.setText(response.body().getLogin());
                    Picasso.with(MainActivity.this)
                            .load(response.body().getAvatarUrl())
                            .into(avatar);
                    userURL.setText(Html.fromHtml("<a href='" + response.body().getHtmlUrl() + "'>Home Page</a>"));
                    userURL.setMovementMethod(LinkMovementMethod.getInstance());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
