package com.offcasoftware.shop2.loaders;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepository;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

/**
 * Created by RENT on 2017-03-16.
 */

public class GetProductDetails extends AsyncTaskLoader<Product> {

    public static final String PRODUCT_KEY = GetProductDetails.class.getCanonicalName() + " PRODUCT_KEY";

    private final int mProductId;

    private final ProductRepositoryInterface mProductRepository
            = ProductRepository.getInstance();

    public GetProductDetails(Context context, int id) {
        super(context);
        mProductId = id;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Product loadInBackground() {
        final Product product = mProductId != Product.UNDEFINED
                ? mProductRepository.getProduct(mProductId) : null;
        return product;

        //Dluższa alternatywa poniżej:
//        if (mProductId != Product.UNDEFINED) {
//            final Product product = mProductRepository.getProduct(mProductId);
//            return product;
//        } else {
//            return null;
//        }
    }
}
