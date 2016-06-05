package com.own.rightshield.appv1.model;

public class MusicItem {

    private int iconID;
    private String name;
    private int favoriteID;

    public MusicItem(int icon, String nam) {
        iconID = icon;
        name = nam;
        favoriteID = 0;
    }

    public int getIconID() {
        return iconID;
    }

    public String getName() {
        return name;
    }

    public int getFavoriteID() {
        return favoriteID;
    }

    public void setFavoriteID(int favID) {
        favoriteID = favID;
    }

}
