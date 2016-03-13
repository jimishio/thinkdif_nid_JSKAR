package com.android.jimish.hackathon;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.android.jimish.hackathon.models.PurchasedProduct;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private List<PurchasedProduct> products;
    private PurchasedProduct product;
    private WebView userManualWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);


        userManualWebView = new WebView(DetailActivity.this);


        Bundle extras = getIntent().getExtras();
        product = new PurchasedProduct();
        String _temp = extras.getString("data");
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(_temp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        product = product.getObj(jsonObject);



//        userManualWebView = (WebView) findViewById(R.id.userManualWebView);

        products = new ArrayList<PurchasedProduct>();

        getData();


        setContentView(userManualWebView);
//        setSupportActionBar(toolbar);

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.detail_drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//        NavigationView navigationView = (NavigationView) findViewById(R.id.detail_nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadManual(String myPdfUrl){
        String url = "http://docs.google.com/gview?embedded=true&url=" + myPdfUrl;
        userManualWebView.getSettings().setJavaScriptEnabled(true);
        userManualWebView.loadUrl(url);
    }

    private void getData(){
        String url = product.getUmUrl();
        loadManual(url);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.detail_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
