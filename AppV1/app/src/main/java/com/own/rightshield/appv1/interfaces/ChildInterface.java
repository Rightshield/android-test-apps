package com.own.rightshield.appv1.interfaces;

public interface ChildInterface {

    public void setParent(ParentInterface parent);
    public void setFavorite(int index, boolean check);
    public void filter(CharSequence cs);
    public boolean isVisible();

}
