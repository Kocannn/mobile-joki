package com.semesta.icnema_uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private Spinner locationSpinner;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize TabLayout and ViewPager
        tabLayout = findViewById(R.id.tab_layout_id);
        viewPager = findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Add fragments to the ViewPager
        adapter.AddFragment(new FragmentBeranda(), "BERANDA");
        adapter.AddFragment(new FragmentBioskop(), "BIOSKOP");
        adapter.AddFragment(new FragmentTiket(), "TIKET");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_bioskop);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_tiket);


        locationSpinner = findViewById(R.id.location_spinner);


        String[] locations = {"Palu", "Makassar", "Manado", "Surabaya"};


        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item, // Default spinner layout
                locations
        );


        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        locationSpinner.setAdapter(locationAdapter);
    }
}
