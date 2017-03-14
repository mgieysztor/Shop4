package com.offcasoftware.shop2.view.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.offcasoftware.shop2.R;

public class FagmentCommunicationActivity extends AppCompatActivity implements FragmentEditText.OnButtonClicked {

    FragmentEditText mFragmentEditText;
    FragmentTextView mFragmentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fagment_communication);

        mFragmentEditText = (FragmentEditText) getSupportFragmentManager().findFragmentById(R.id.fragment_edittext);
        mFragmentTextView = (FragmentTextView) getSupportFragmentManager().findFragmentById(R.id.fragment_textview);

    }

    @Override
    public void showText(String text) {
        mFragmentTextView.showText(text);
    }
}
