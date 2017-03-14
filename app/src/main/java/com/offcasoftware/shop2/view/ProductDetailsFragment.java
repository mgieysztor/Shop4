package com.offcasoftware.shop2.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepository;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

import java.util.List;

/**
 * Created by krzysztofjanik on 13.03.2017.
 */

public class ProductDetailsFragment extends Fragment {

    public static final String INTENT_PRODUCT_ID =
            ProductDetailsActivity.class.getSimpleName() + "productId";

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.product_name)
    TextView mProductName;

    @BindView(R.id.product_price)
    TextView mProductPrice;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private ProductRepositoryInterface mProductRepository
            = ProductRepository.getInstance();

    public static ProductDetailsFragment newInstance(int id) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(INTENT_PRODUCT_ID, id);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle == null) {
            List<Product> productList = mProductRepository.getProducts();
            if (!productList.isEmpty()) {
                displayData(productList.get(0));
            }
            return;
//            throw new IllegalArgumentException();
        }
        int productId = bundle.getInt(INTENT_PRODUCT_ID, Product.UNDEFINED);
        Log.d("Shop", "Product id: " + productId);
        if (productId != Product.UNDEFINED) {
            Product product = mProductRepository.getProduct(productId);
            displayData(product);
        }
//        Product product = mProductRepository.getProduct(productId);
//
//        displayData(product);
    }

    public void updateProduct(Product product) {
        displayData(product);
    }

    private void displayData(final Product product) {
        int drawableResourceId = this.getResources()
                .getIdentifier(product.getImageResId(), "drawable",
                        getActivity().getPackageName());
        mProductImage.setImageResource(drawableResourceId);
        mProductName.setText(product.getName());
        mProductPrice.setText(String.valueOf(product.getPrice()));
    }

}
