package com.example.kalana.theapp1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Navi extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        String MY_PREFS_NAME = "login_pref";

    Button btnCousPoint;
    Button btnAddNewPromoUser;
    Button btnChangeVal;
    Button btnAvalPromo;
    Button btnPendingPromo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        btnCousPoint = (Button)findViewById(R.id.btnCousPoint);
        btnCousPoint.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Navi.this,CustomPoint.class);

                        startActivity(intent);
                    }
                });
        btnAddNewPromoUser = (Button)findViewById(R.id.btnAddNewPromoUser);
        btnAddNewPromoUser.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Navi.this,AddNewPromo.class);
                        startActivity(intent);
                    }
                });

        btnChangeVal = (Button)findViewById(R.id.btnChangeVal);
        btnChangeVal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Navi.this,ChangePointValuve.class);
                        startActivity(intent);
                    }
                });
        btnAvalPromo = (Button)findViewById(R.id.btnAvalPromo);
        btnAvalPromo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Navi.this,AvailablePromotion.class);
                        startActivity(intent);
                    }
                });
        btnPendingPromo = (Button)findViewById(R.id.btnPendingPromo);
        btnPendingPromo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Navi.this,PendingPromotion.class);
                        startActivity(intent);
                    }
                });
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.navi, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      //  if (id == R.id.action_settings) {
            return true;
//        }
//
    //    return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.iMyAccount) {

            startActivity(new Intent(Navi.this,MyAccount.class));

        } else if (id == R.id.iChangePassword) {

            startActivity(new Intent(Navi.this,ChangePassword.class));

        } else if (id == R.id.iLogOut) {


            SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, 0);

            preferences.edit().remove("merchantUserId").commit();
            preferences.edit().remove("password").commit();
            preferences.edit().remove("address").apply();
            preferences.edit().putBoolean("loggedIn", false);


            startActivity(new Intent(Navi.this,HomeActivity.class));
            finish();
        }
        else if (id == R.id.iPUPassword) {

            //startActivity(new Intent(Navi.this,PUPassword.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
