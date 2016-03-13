package com.android.jimish.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jimish.hackathon.models.Product;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ProductInfoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Button submitBtn;
    private ImageView productPurchaseImageView;
    private TextView titleTxtView;
    private TextView priceView;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        product = new Product();
        if(extras!=null){
            String _temp = extras.getString("data");
            try {
                JSONObject jsonObject = new JSONObject(_temp);
                product = product.getObj(jsonObject);
            } catch (JSONException e) {

            }

        }
        setContentView(R.layout.activity_product_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.product_info_toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.product_info_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        setSupportActionBar(toolbar);

        submitBtn = (Button) findViewById(R.id.productPurchaseBtn);
        productPurchaseImageView = (ImageView) findViewById(R.id.productPurchaseImage);
        titleTxtView = (TextView) findViewById(R.id.productPurchaseTitle);
        priceView = (TextView) findViewById(R.id.productPurchasePrice);

        fitData(product);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBuyActivity();
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.product_info_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void fitData(Product product) {
        Picasso.with(this).load(product.getImgUrl()).into(productPurchaseImageView);
        titleTxtView.setText(product.getName());
        String mrpPrice = getPriceText(product.getPrice());
        priceView.setText(mrpPrice);
    }

    private String getPriceText(String price){
        price = "M. R. P. : "+price;
        return price;
    }
    private void startBuyActivity() {
        Intent intent = new Intent(this, BuyActivity.class);
        startActivityForResult(intent, 222);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (resultCode == RESULT_OK) {
            if (requestCode == 222) {
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.product_info_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
