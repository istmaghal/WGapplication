package com.example.wgapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import java.util.List;
import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    private ArrayList<Benutzer> profile = new ArrayList<>();
    ListView mylistview;
    int[]    tmp_image;
    private static final String TAG = "Profile";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Profile Toolbar name
        getSupportActionBar().setTitle("Profile");

        // 2 Hard gecodete Benutzer initialisieren
        initProfile();

        // Adapter initialisieren
        mylistview = (ListView)findViewById(R.id.profile_listView);
        mylistview.setAdapter(new ProfileListAdapter(this, profile));/*, int[] arr_foto*/

    }

    public ArrayList<Benutzer> getProfile() {

        return profile;
    }

    public void addProfile(Benutzer mybenutzer) {

        profile.add(mybenutzer);
    }

    // Beispiel Profile
    public void initProfile() {
        Benutzer b1 = new Benutzer("Admin", "Admin", "Ghali", "Mahmoud", "21.01.1998", true, "015239392321", "mahmudghali@gmail.com",
                "Student", "Hochschule Darmstadt", "21.01.2019");
        Benutzer b2 = new Benutzer("Admin2", "Admin2", "Kora", "Michael", "05.12.1996",true, "015335533321",  "michaelkora@gmail.com",
                "Student", "Hochschule Darmstadt", "02.02.2018");

        profile.add(b1);
        profile.add(b2);
    }
}
