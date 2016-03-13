package com.android.jimish.hackathon;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class WalletActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView totalPoints;
    private SharedPreferences sharedPreferences;

    NotificationManager mNotificationManager;
    android.support.v4.app.NotificationCompat.Builder mNotifyBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.wallet_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.wallet_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        totalPoints = (TextView) findViewById(R.id.totalPoints);
        sharedPreferences = getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE);
        int _temp = sharedPreferences.getInt(Constants.TOTAL_POINTS_LABLE, 250);
        if(_temp > 250){
            initNotification();
        }
        totalPoints.setText(String.valueOf(_temp));

        NavigationView navigationView = (NavigationView) findViewById(R.id.wallet_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            startHistoryAPI();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.wallet_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initNotification(){
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// Sets an ID for the notification, so it can be updated
        int notifyID = 1;
        mNotifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Hey, Amazon here")
                .setContentText("15 Points has been added in your wallet.")
                .setSmallIcon(R.drawable.ic_account_balance_wallet_black_24dp);

        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();

        String[] events = new String[6];
        inboxStyle.setBigContentTitle("Hey, Amazon here");
        for (int i=0; i < events.length; i++) {

            inboxStyle.addLine(events[i]);
        }
        mNotifyBuilder.setStyle(inboxStyle);
        int numMessages = 0;
        mNotificationManager.notify(
                notifyID,
                mNotifyBuilder.build());
    }
    private void startHistoryAPI() {
        Intent intent = new Intent(this, HistoryApi.class);
        startActivity(intent);
    }

}
