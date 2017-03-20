package com.offcasoftware.shop2.model;

import android.database.Cursor;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author maciej.pachciarek on 2017-02-18.
 */

@DatabaseTable(tableName = Product.TABLE_NAME)
public class Product implements Serializable {

    public static final int UNDEFINED = -1;

    static final String TABLE_NAME = "products";

    @DatabaseField(columnName = "id", generatedId = true)
    private int mId;

    @DatabaseField(columnName = "name", canBeNull = false, unique = true)
    private String mName;

    @DatabaseField(columnName = "price", canBeNull = false)
    private int mPrice;

    @DatabaseField(columnName = "imageName", defaultValue = "dom2")
    private String mImage;

    public Product() {
    }

    public Product(final int id, final String name,
                   final int price, final String imageName) {
        mId = id;
        mName = name;
        mPrice = price;
        mImage = imageName;
    }

    public Product(Cursor cursor) {
        mId = cursor.getInt(0);

        int nameColumnIndex = cursor.getColumnIndex("name");
        mName = cursor.getString(nameColumnIndex);

        mPrice = cursor.getInt(2);
        mImage = cursor.getString(3);

    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getPrice() {
        return mPrice;
    }

    public String getImageResId() {
        return mImage;
    }

    @Override
    public String toString() {
        return String.valueOf(mId) + " " + mName + " " + String.valueOf(mPrice);
    }
}
