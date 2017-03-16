package com.offcasoftware.shop2.view.widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.adapter.ProductAdapter;
import com.offcasoftware.shop2.loaders.GetAllProducts;
import com.offcasoftware.shop2.model.Product;

import java.util.List;

/**
 * Created by krzysztofjanik on 13.03.2017.
 */

public class ProductListFragment extends Fragment
        implements ProductCardView.ProductCardViewInterface, LoaderManager.LoaderCallbacks<List<Product>> {

    @BindView(R.id.product_recycler)
    RecyclerView mRecyclerView;

    ProductAdapter mAdapter;

    @Override
    public Loader<List<Product>> onCreateLoader(int id, Bundle args) {
        return new GetAllProducts(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Product>> loader, List<Product> data) {
        mAdapter.swapData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Product>> loader) {
        mAdapter.clear();
    }

    public interface OnProductSelected {

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new ProductAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        getLoaderManager().initLoader(1, null, this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnProductSelected) {
            mListener = (OnProductSelected) activity;
        }
    }



    @Override
    public void onProductClicked(Product product) {
        if (mListener != null) {
            mListener.onProductSelected(product);
        }
    }
}
