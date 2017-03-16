package com.offcasoftware.shop2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.view.widget.ProductCardView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-03-14.
 */

public class ProductAdapter extends RecyclerView.Adapter implements ProductCardView.ProductCardViewInterface {
//    private int VIEW_TYPE1 = 0;

    private List<Product> mItems = new ArrayList<>();

    private ProductCardView.ProductCardViewInterface mListener;

    public ProductAdapter(ProductCardView.ProductCardViewInterface mListener) {
        this.mListener = mListener;
    }

    public ProductAdapter(List<Product> products, ProductCardView.ProductCardViewInterface listener) {
        mItems.addAll(products);
        mListener = listener;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        final ProductCardView view = new ProductCardView(context);
        return new ProductHolder(view);
    }

//    @Override
//    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Product product = getItem(position);
        ((ProductHolder) holder).bind(product);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Product getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public void onProductClicked(Product product) {
        if (mListener !=null){
            mListener.onProductClicked(product);
        }

    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        public ProductHolder(View itemView) {
            super(itemView);
        }

        public void bind(Product product) {
            ((ProductCardView) itemView).bindTo(product, ProductAdapter.this);
        }
    }

    public void swapData(List<Product> products) {
        if (products != null) {
            mItems.clear();
            mItems.addAll(products);
            notifyDataSetChanged();
        }
    }

    public void clear (){
        mItems.clear();
        notifyDataSetChanged();
    }
}
