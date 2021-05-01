package com.wheic.cleanurge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.wheic.cleanurge.Adapter.FragViewPager.ViewPagerFragsAdapter;
import com.wheic.cleanurge.Adapter.ReportList.ReportListAdapter;
import com.wheic.cleanurge.ModelResponse.Reports.ReportWithAuthor;
import com.wheic.cleanurge.OtherActivity.PostReportActivity;
import com.wheic.cleanurge.SharedPrefManager.SharedPrefManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPrefManager sharedPrefManager;

    private ImageButton addReportBtn;

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefManager = new SharedPrefManager(this);
        addReportBtn = findViewById(R.id.addReportBtn);
        viewPager2 = findViewById(R.id.slidingFragmentsPager);
        tabLayout = findViewById(R.id.viewPagerTabLayout);

        viewPager2.setAdapter(new ViewPagerFragsAdapter(this));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, (tab, position) -> {
                    switch (position){
                        case 0:
                            tab.setText("Home");
                            break;
                        case 1:
                            tab.setText("Beacon");
                            break;
                        case 2:
                            tab.setText("Profile");
                            break;
                    }
                }
        );
        tabLayoutMediator.attach();

        addReportBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PostReportActivity.class)));

    }
}