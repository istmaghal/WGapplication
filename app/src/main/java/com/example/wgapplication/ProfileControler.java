package com.example.wgapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileControler extends AppCompatActivity {

    private TextView nameundVorname;
    private TextView telefon;
    private ArrayList<Benutzer> alleProfile = new ArrayList<>();
    private ProfileListAdapter my_ProfileListAdapter = null;
    private static int PICK_PARTY_REQUEST = 100;
    private static final String TAG = "Profile Kontroler";



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        //Actionbar Name
        getSupportActionBar().setTitle("Profile");

        // Hardgecodet Profile
        Benutzer b1 = new Benutzer("Mahmoud Ghali", "650748097",true);
        alleProfile.add(b1);
        Benutzer b2 = new Benutzer("Michael Kora", "1520456525",true);
        alleProfile.add(b2);

        // Conect View layout
        setContentView(R.layout.activity_profile);
        useProfileListAdapter();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (resultCode == RESULT_OK && requestCode == PICK_PARTY_REQUEST) {

            String benutzername = data.getExtras().getString("Name");
            String telefon = data.getExtras().getString("telefon");

            ListView showEvents = (ListView) findViewById(R.id.profile_listView);

            Benutzer myBenutzer = new Benutzer(benutzername, telefon);

            showEvents.setAdapter(my_ProfileListAdapter);
            alleProfile.add(myBenutzer);

            my_ProfileListAdapter.notifyDataSetChanged();
        }
    }

    private void useProfileListAdapter(){

        // Create the adapter to convert the array to views
        my_ProfileListAdapter = new ProfileListAdapter( this, alleProfile );

        // Attach the adapter to a ListView
        ListView listView1 = (ListView) findViewById( R.id.profile_listView );
        listView1.setAdapter( my_ProfileListAdapter );
    }


}
