package com.offcasoftware.shop2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.offcasoftware.shop2.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-03-18.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {

    private List<T> mItems = new ArrayList<>();
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    public abstract void onBind(RecyclerView.ViewHolder holder, T item, int position);

    public BaseAdapter(Context context) {
        this(context, null);
    }

    public BaseAdapter(Context context, List<T> list) {
        if (context == null) {
            throw new IllegalArgumentException("Contect cannot be null");
        }
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        if (list != null) {
            mItems.addAll(list);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final T item = getItem(position);
        onBind(holder, item, position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Context getContext() {
        return mContext;
    }

    public LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    public T getItem(int position) {
        final T item = mItems.get(position);
        return item;
    }

    public void swapData(List<T> items) {
        if (items != null) {
            mItems.clear();
            mItems.addAll(items);
            notifyDataSetChanged();
        }
    }

    public void clearData() {
        mItems.clear();
        notifyDataSetChanged();

    }
}
