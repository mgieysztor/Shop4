package com.offcasoftware.shop2.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepository;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

import java.util.List;

/**
 * Created by RENT on 2017-03-16.
 */

public class GetAllProducts extends AsyncTaskLoader<List<Product>> {

    private final ProductRepositoryInterface mProductRepository
            = ProductRepository.getInstance();

    public GetAllProducts(Context context) {
        super(context);
    }

    @Override
    public List<Product> loadInBackground() {
        final List<Product> products = mProductRepository.getProducts();
        return products;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
