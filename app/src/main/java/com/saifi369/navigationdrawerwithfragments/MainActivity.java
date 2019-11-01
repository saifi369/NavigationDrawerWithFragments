package com.saifi369.navigationdrawerwithfragments;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mNavDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNavDrawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mNavDrawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        mNavDrawer.addDrawerListener(toggle);

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new InboxFragment())
                    .commit();

            navigationView.setCheckedItem(R.id.nav_inbox);


        }


    }

    @Override
    public void onBackPressed() {

        if (mNavDrawer.isDrawerOpen(GravityCompat.START)) {
            mNavDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_inbox:
                //add the inbox fragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new InboxFragment())
                        .commit();
                break;
            case R.id.nav_outbox:
                //add the inbox fragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new OutboxFragment())
                        .commit();
                break;
            case R.id.nav_trash:
                //add the inbox fragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new TrashFragment())
                        .commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "This is Share Item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_help:
                Toast.makeText(this, "This is Help Item", Toast.LENGTH_SHORT).show();
                break;
        }

        mNavDrawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
