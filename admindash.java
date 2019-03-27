package com.example.user.smartfarmer;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MenuItem;
public class admindash extends AppCompatActivity {
private DrawerLayout mdrawerlayout;
NavigationView navigation;
private ActionBarDrawerToggle mtoggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindash);

        mdrawerlayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        mtoggle = new ActionBarDrawerToggle(this,mdrawerlayout,R.string.open,R.string.close);
        mdrawerlayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigation=(NavigationView)findViewById(R.id.navigationview);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            int id = menuItem.getItemId();
            switch (id) {
                case R.id.dashboard:

                    break;
                case R.id.addbulls:
                    Intent movetobulls =new Intent(admindash.this,addbulls.class);
                    startActivity(movetobulls);
                    break;
                case R.id.viewbulls:
                    //Do some thing here
                    // add navigation drawer item onclick method here
                    break;
                case R.id.logout:
                    //Do some thing here
                    // add navigation drawer item onclick method here
                    break;
            }
            return false;
        }
    });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item)){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
