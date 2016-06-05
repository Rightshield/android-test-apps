package com.own.rightshield.appv1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.own.rightshield.appv1.Constants;
import com.own.rightshield.appv1.adapter.MusicListAdapter;
import com.own.rightshield.appv1.R;
import com.own.rightshield.appv1.activity.DetailsActivity;
import com.own.rightshield.appv1.interfaces.ChildInterface;
import com.own.rightshield.appv1.interfaces.ParentInterface;
import com.own.rightshield.appv1.model.MusicItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomListFragment extends Fragment implements ChildInterface {

    @BindView(R.id.menuList) ListView list;
    MusicListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setParent((ParentInterface)getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customlist, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create the items of the list
        List<MusicItem> musicItems = new ArrayList<MusicItem>();
        for(String name : Constants.nameItems) {
            MusicItem item = new MusicItem(R.mipmap.dist_icon, name);
            musicItems.add(item);
        }

        // Create and set the adapter to the List View
        adapter = new MusicListAdapter(getContext(), R.layout.item_music, musicItems);
        list.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void filter(CharSequence cs) {
        adapter.filter(cs.toString());
    }

    public void setFavorite(int index, boolean check) {
        adapter.setFavorite(index, check);
    }

    // Start new activity showing artist selected information
    @OnItemClick(R.id.menuList)
    public void onItemClick(int position) {
        Intent intent = new Intent(this.getActivity(), DetailsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", position);

        intent.putExtras(bundle);

        startActivityForResult(intent, position);
    }

    @Override
    public void setParent(ParentInterface parent) {
        parent.setChild(this);
    }
}
