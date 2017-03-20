package com.offcasoftware.shop2.model;

import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;

/**
 * Created by RENT on 2017-03-18.
 */

public class Contact {

    private final int mId;
    private final String mName;
    private final static String COLUMN_NAME = Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.HONEYCOMB ?
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
            ContactsContract.Contacts.DISPLAY_NAME;

    public Contact(int id, String name){
        mId = id;
        mName = name;

    }

    public Contact (Cursor cursor){
        mId = cursor.getInt(cursor.getColumnIndex("_id"));
        mName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }
}
