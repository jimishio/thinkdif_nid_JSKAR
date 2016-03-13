package com.android.jimish.hackathon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jimish.hackathon.models.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<CardView> productCardViews;

    private List<ImageView> productImages;
    private List<TextView> productTitles;
    private List<TextView> productPrices;
    private List<Product> products;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        productCardViews = new ArrayList<CardView>();
        productImages = new ArrayList<ImageView>();
        productTitles = new ArrayList<TextView>();
        productPrices = new ArrayList<TextView>();
        products = new ArrayList<Product>();

        sharedPreferences = getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(Constants.TOTAL_POINTS_LABLE, 250);
        editor.commit();

        initializeAllComponents();

        initProducts();

        fitThings();
        
        productCardViews.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startProductInfoActivity(0);
            }
        });

        productCardViews.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startProductInfoActivity(1);
            }
        });

        productCardViews.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startProductInfoActivity(2);
            }
        });

        productCardViews.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startProductInfoActivity(3);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initProducts() {
        Product product = new Product("PHILIPS Aqua Touch Trimmer", "$58.50", "http://ecx.images-amazon.com/images/I/71wvCBnmnvL._SL1500_.jpg", "1080p");
        products.add(product);

        product = new Product("Cisco Linksys E2500 Router", "$29.95", "http://ecx.images-amazon.com/images/I/61%2BuMFQhphL._SL1500_.jpg", "electrical kettle");
        products.add(product);

        product = new Product("Fastrack Men's Watch", "$51.59","http://ecx.images-amazon.com/images/I/81bFPTdPREL._UY741_.jpg","");
        products.add(product);

        product = new Product("Dell Inspiron 15", "$523.00", "http://ecx.images-amazon.com/images/I/71YBrkYmOdL._SL1500_.jpg", "");
        products.add(product);

    }

    private void fitThings(){
        for(int i=0; i<4; i++){
            Picasso.with(this).load(products.get(i).getImgUrl()).into(productImages.get(i));
            productImages.get(i).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            productTitles.get(i).setText(products.get(i).getName());
            productPrices.get(i).setText(products.get(i).getPrice());
        }
    }

    private void startProductInfoActivity(int i) {
        Intent intent = new Intent(this, ProductInfoActivity.class);
        intent.putExtra("data",products.get(i).getJson().toString());
        startActivity(intent);
    }

    private void initializeAllComponents(){
        CardView cardView = (CardView) findViewById(R.id.productCardView1);
        productCardViews.add(cardView);

        cardView = (CardView) findViewById(R.id.productCardView2);
        productCardViews.add(cardView);

        cardView = (CardView) findViewById(R.id.productCardView3);
        productCardViews.add(cardView);

        cardView = (CardView) findViewById(R.id.productCardView4);
        productCardViews.add(cardView);

        TextView productTitle = (TextView) findViewById(R.id.productTitle1);
        productTitles.add(productTitle);

        productTitle = (TextView) findViewById(R.id.productTitle2);
        productTitles.add(productTitle);

        productTitle = (TextView) findViewById(R.id.productTitle3);
        productTitles.add(productTitle);

        productTitle = (TextView) findViewById(R.id.productTitle4);
        productTitles.add(productTitle);

        ImageView productImage = (ImageView) findViewById(R.id.productImageView1);
        productImages.add(productImage);

        productImage = (ImageView) findViewById(R.id.productImageView2);
        productImages.add(productImage);

        productImage = (ImageView) findViewById(R.id.productImageView3);
        productImages.add(productImage);

        productImage = (ImageView) findViewById(R.id.productImageView4);
        productImages.add(productImage);

        TextView productPrice = (TextView) findViewById(R.id.productPrice1);
        productPrices.add(productPrice);

        productPrice = (TextView) findViewById(R.id.productPrice2);
        productPrices.add(productPrice);

        productPrice = (TextView) findViewById(R.id.productPrice3);
        productPrices.add(productPrice);

        productPrice = (TextView) findViewById(R.id.productPrice4);
        productPrices.add(productPrice);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startWalletActivity();
        } else if (id == R.id.nav_gallery) {
            startHistoryActivity();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startHistoryActivity() {
        Intent intent = new Intent(this, HistoryApi.class);
        startActivity(intent);
    }

    private void startWalletActivity(){
        Intent intent = new Intent(this, WalletActivity.class);
        startActivity(intent);
    }
}
