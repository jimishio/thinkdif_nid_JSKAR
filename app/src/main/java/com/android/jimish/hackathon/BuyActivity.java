package com.android.jimish.hackathon;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BuyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private RadioButton deliveryYes;
    private RadioButton deliveryNo;

    private Button buyBtn;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.buy_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.buy_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        setSupportActionBar(toolbar);

        deliveryYes = (RadioButton) findViewById(R.id.radio_delivery_yes);
        deliveryNo = (RadioButton) findViewById(R.id.radio_delivery_no);

        buyBtn = (Button) findViewById(R.id.buyBtn);

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increasePoints();
                startSuccessActivity();
            }
        });

        deliveryYes.setChecked(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.buy_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void startSuccessActivity() {
        Intent intent = new Intent(this, SuccessActivity.class);
        startActivityForResult(intent, 333);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (resultCode == RESULT_OK) {
            if (requestCode == 333) {
                setResult(RESULT_OK);
                finish();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.buy_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onDeliverClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_delivery_yes:
                if (checked)
                break;
            case R.id.radio_delivery_no:
                if (checked)
                break;
        }
    }

    public void onAgeRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_credit:
                if (checked)
                break;
            case R.id.radio_debit:
                if (checked)
                break;
            case R.id.radio_net:
                if (checked)
                break;
            case R.id.radio_cod:
                if (checked)
                break;
        }
    }

    private void increasePoints(){
        sharedPreferences = getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE);
        int _temp = sharedPreferences.getInt(Constants.TOTAL_POINTS_LABLE, 250);
        _temp = _temp + 15;
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(Constants.TOTAL_POINTS_LABLE, _temp);
        editor.commit();
    }

}
