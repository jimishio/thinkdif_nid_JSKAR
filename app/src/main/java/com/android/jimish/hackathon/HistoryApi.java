package com.android.jimish.hackathon;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jimish.hackathon.models.PurchasedProduct;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HistoryApi extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CardView historyCardView1;
    private CardView historyCardView2;

    private ImageView historyImageView1;
    private TextView historyTitle1;


    private List<PurchasedProduct> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_api);
        Toolbar toolbar = (Toolbar) findViewById(R.id.history_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.history_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        historyCardView1 = (CardView) findViewById(R.id.historyCardView1);
        historyCardView2 = (CardView) findViewById(R.id.historyCardView2);

        historyImageView1 = (ImageView) findViewById(R.id.historyImageView1);
        historyTitle1 = (TextView) findViewById(R.id.historyTitle1);

        historyCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailView();
            }
        });

        historyCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailView();
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.history_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        products = new ArrayList<PurchasedProduct>();
        initProducts();

        fillData();

    }

    private void fillData() {
        Picasso.with(this).load(products.get(0).getImgUrl()).into(historyImageView1);
        historyTitle1.setText(products.get(0).getTitle());
    }

    private void openDetailView() {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("data", products.get(0).getJson().toString());

        startActivity(intent);
    }

    private void initProducts(){
        PurchasedProduct product = new PurchasedProduct("Philips AQUA Trimmer", "$58.50", "http://ecx.images-amazon.com/images/I/71wvCBnmnvL._SL1500_.jpg",
                "http://download.p4c.philips.com/files/a/at620_14/at620_14_dfu_aen.pdf",
                "https://drive.google.com/file/d/0B5hRG6I8gsUlV0hDWHp4Qzl3SWM/view?usp=sharing",
                "https://www.haier.com.au/common/download/haier-warranty-card.pdf");
        products.add(product);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startWalletActivity();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.history_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startWalletActivity() {
        Intent intent = new Intent(this, WalletActivity.class);
        startActivity(intent);
    }
}
