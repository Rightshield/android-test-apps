package com.own.rightshield.appv1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.own.rightshield.appv1.R;
import com.own.rightshield.appv1.model.MusicItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MusicListAdapter extends ArrayAdapter<MusicItem>{

    private int resource;
    private List<MusicItem> dataList;
    private List<MusicItem> fullDataList;

    public MusicListAdapter(Context context, int _resource, List<MusicItem> data) {
        super(context, _resource, data);
        resource = _resource;
        dataList = data;
        fullDataList = new ArrayList<MusicItem>();
        fullDataList.addAll(data);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View item = convertView;
        ViewHolder holder;

        // Inflate XML layout if it has not inflated yet
        if(item == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(resource, null);

            holder = new ViewHolder();
            holder.icon = (ImageView)item.findViewById(R.id.imageIcon);
            holder.name = (TextView)item.findViewById(R.id.artistName);
            holder.favorite = (ImageView)item.findViewById(R.id.imageFavorite);

            item.setTag(holder);
        }
        else {
            holder = (ViewHolder)item.getTag();
        }

        MusicItem myView = getItem(position);

        holder.icon.setImageResource(myView.getIconID());
        holder.name.setText(myView.getName());
        holder.favorite.setImageResource(myView.getFavoriteID());

        return item;

    }

    // ListView filter
    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());
        dataList.clear();

        if (charText.length() == 0) dataList.addAll(fullDataList);
        else {
            for (MusicItem mi : fullDataList) {
                if (mi.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    dataList.add(mi);
                }
            }
        }
        notifyDataSetChanged();
    }

    // Add favorite icon or delete it
    public void setFavorite(int index, boolean check) {
        dataList.get(index).setFavoriteID(check ? R.mipmap.favorite : 0);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        ImageView icon;
        TextView name;
        ImageView favorite;
    }
}
