package com.offcasoftware.shop2.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.adapter.ContactAdapter;
import com.offcasoftware.shop2.model.Contact;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {
    @BindView(R.id.contact_recycler_view)
    RecyclerView mContactRecyclerView;

    private static final int READ_CONTACTS_REQUEST = 1;

    private static final int CONTACT_LOADER = 1;

    private ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);

        mAdapter = new ContactAdapter(this);
        mContactRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mContactRecyclerView.setAdapter(mAdapter);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                Toast.makeText(this, "Błagam kliknij, bo się popłaczę", Toast.LENGTH_LONG).show();
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_REQUEST);
        } else {
            loadContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_CONTACTS_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadContacts();
                } else {
//                    mContactCounterTextView.setText("NO PERMISSION!");
                }
                break;
            }
        }
    }

    private void loadContacts() {
        getSupportLoaderManager().initLoader(CONTACT_LOADER, null, this);
    }


    private void displayContacts(Cursor cursor) {

        if (cursor == null) {
            return;
//            mContactCounterTextView.setText(String.valueOf(cursor.getCount()));
        }

        final List<Contact> items = new ArrayList<>();
        cursor.moveToFirst();

        do {
            Contact contact = new Contact(cursor);
            items.add(contact);
        } while (cursor.moveToNext());

        mAdapter.swapData(items);
        cursor.close();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return  new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        displayContacts(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.clearData();
    }
}
