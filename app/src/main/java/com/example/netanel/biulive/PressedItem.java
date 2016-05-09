package com.example.netanel.biulive;

import android.view.View;

public class PressedItem {
    private String title;
    private int icon;
    View.OnClickListener listener;

    /**
     *
     * @param title the title of the item
     * @param icon the icon for the item
     * @param listener the listener for the item
     */
    public PressedItem(String title, int icon, View.OnClickListener listener){
        this.title = title;
        this.icon = icon;
        this.listener = listener;
    }

    /**
     *
     * @return title
     */

    public String getTitle(){
        return this.title;
    }

    /**
     *
     * @return icon
     */
    public int getIcon(){
        return this.icon;
    }

    /**
     *
     * @return listener
     */
    public View.OnClickListener getListener(){
        return this.listener;
    }

}
