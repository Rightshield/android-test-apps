package com.own.rightshield.appv1.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

import com.own.rightshield.appv1.fragment.CustomListFragment;
import com.own.rightshield.appv1.R;
import com.own.rightshield.appv1.interfaces.*;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity implements ParentInterface{

    @BindView(R.id.searchBar) EditText searchBar;
    List<ChildInterface> childs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // Create Childs list
        childs = new ArrayList<ChildInterface>();

        // Get fragment manager
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.container, new CustomListFragment());
        ft.commit();

        // Hide software keyboard at activity start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    // Filter ListView using EditText
    @OnTextChanged(R.id.searchBar)
    public void onTextChanged(CharSequence cs) {
        for(ChildInterface child : childs) {
            if(child.isVisible()) {
                child.filter(cs.toString());
            }
        }
    }

    @Override
    public void setChild(Fragment frag) {
        childs.add((ChildInterface)frag);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();

            FragmentManager manager = getSupportFragmentManager();
            CustomListFragment fragment = (CustomListFragment)manager.findFragmentById(R.id.container);

            fragment.setFavorite(bundle.getInt("INDEX"), bundle.getBoolean("CHECK"));
        }
    }

}