package com.offcasoftware.shop2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.view.ProductDetailsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-03-15.
 */

public class ProductPageAdapter extends FragmentPagerAdapter {

    List<Product> mProducts = new ArrayList<>();


    public ProductPageAdapter(FragmentManager fm, List<Product> list) {
        super(fm);
        mProducts.addAll(list);
    }

    @Override
    public Fragment getItem(int position) {

        final Product product = mProducts.get(position);
        final ProductDetailsFragment fragment = ProductDetailsFragment.newInstance(product.getId());
        return fragment;
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }
}
