package com.offcasoftware.shop2.view.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepository;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;
import com.offcasoftware.shop2.view.ProductDetailsActivity;

import java.util.List;

/**
 * Created by krzysztofjanik on 13.03.2017.
 */

public class ProductListFragment extends Fragment implements ProductCardView.ProductCardViewInterface {

    @BindDimen(R.dimen.product_list_item_height)
    int mProductListItemHeight;

    @BindView(R.id.line1)
    LinearLayout mContainer;

    @BindView(R.id.product_recycler)
    RecyclerView mRecyclerView;

    private ProductRepositoryInterface mProductRepository
            = ProductRepository.getInstance();

    public interface OnProductSelected{
        void onProductSelected(Product product);
    }

    private OnProductSelected mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayData();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof OnProductSelected){
            mListener = (OnProductSelected) activity;
        }
    }

    private void displayData() {
        List<Product> products = mProductRepository.getProducts();

        for (Product product : products) {

            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            mProductListItemHeight);
            ProductCardView productCardView = new ProductCardView(getActivity());
            productCardView.setLayoutParams(layoutParams);
            productCardView.bindTo(product, this);
            mContainer.addView(productCardView);
        }
    }

    @Override
    public void onProductClicked(Product product) {
        if (mListener != null){
            mListener.onProductSelected(product);
        }
    }
}
