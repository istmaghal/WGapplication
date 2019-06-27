package com.example.wgapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private CardView besucheCard, beschwerdeCard, einkaufswagenCard, profileCard, putzplanCard, eventsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hier werden die verschieden cards definiert
        besucheCard = (CardView) findViewById(R.id.besuche_card);
        beschwerdeCard = (CardView) findViewById(R.id.beschwerde_card);
        einkaufswagenCard = (CardView) findViewById(R.id.einkaufswagen_card);
        profileCard = (CardView) findViewById(R.id.profile_card);
        putzplanCard = (CardView) findViewById(R.id.putzplan_card);
        eventsCard = (CardView) findViewById(R.id.events_card);

        //click listner an cards hinfügen damit sie als button funktionieren
        besucheCard.setOnClickListener(this);
        beschwerdeCard.setOnClickListener(this);
        einkaufswagenCard.setOnClickListener(this);
        profileCard.setOnClickListener(this);
        putzplanCard.setOnClickListener(this);
        eventsCard.setOnClickListener(this);

        //settings für die Sprache
        LoadLocale();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    //Sprache Settings menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater infrater = getMenuInflater();
        infrater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        showChangeLanguageDialog();

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ic_konto) {
            // Handle the konto action
        } else if (id == R.id.ic_kontake) {

        }  else if (id == R.id.ic_infos) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // methode ONclick für die aktivation der Buttons
    @Override
    public void onClick(View v) {

        Intent i;
        switch (v.getId()) {
            case R.id.besuche_card:
                i = new Intent(this, BesuchControler.class);
                startActivity(i);
                break;
            case R.id.beschwerde_card:
                break;
            case R.id.events_card:
                i = new Intent(this, Event_Kontroler.class);
                startActivity(i);
                break;
            case R.id.einkaufswagen_card:
                i = new Intent(this, Einkaufswagen.class);
                startActivity(i);
                break;
            case R.id.putzplan_card:
                break;
            case R.id.profile_card:
                i = new Intent(this, ProfileControler.class);
                startActivity(i);
                break;
            default:
                break;

        }
    }

    //Methode zur Sprache configuration
    private void setApplocale(String localeCode) {
        Locale locale = new Locale(localeCode);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.locale = locale;

        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        //save Data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_localeCode", localeCode);
        editor.apply();
    }

    // Load Language saved in shared preferences
    public void LoadLocale(){
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_localeCode","");
        setApplocale(language);
    }

    private void showChangeLanguageDialog(){

        final String[] listLanguages = {"Englisch", "Deutsch", "Spanisch"};

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Sprache Wählen");
        AlertDialog.Builder builder = mBuilder.setSingleChoiceItems(listLanguages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    setApplocale("en");
                    recreate();
                } else if (i == 1) {
                    setApplocale("de");
                    recreate();
                } else if (i == 2) {
                    setApplocale("es");
                    recreate();
                }

                //dismiss alert dialog when language selected
                dialog.dismiss();
            }
        });

        //show alert Dialog
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }


}
