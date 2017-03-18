package com.offcasoftware.shop2.model;

/**
 * Created by RENT on 2017-03-18.
 */

public class Contact {

    private final int mId;
    private final String mName;

    public Contact(int id, String name){
        mId = id;
        mName = name;

    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }
}
