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
import com.wheic.cleanurge.Adapter.BeaconRecycler.FragViewPager.ViewPagerFragsAdapter;
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

    private List<ReportWithAuthor> reportList;
    private ReportListAdapter reportListAdapter;

//    private TextView userNameText, userEmailText, userPhoneText, userAddressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefManager = new SharedPrefManager(this);

//        logOut = findViewById(R.id.logOutBtn);
        addReportBtn = findViewById(R.id.addReportBtn);
        viewPager2 = findViewById(R.id.slidingFragmentsPager);
        tabLayout = findViewById(R.id.viewPagerTabLayout);

//        Call<ReportUserGetResponse> call = RetrofitClient.getInstance().getApi().getUserReports("Bearer " + sharedPrefManager.getToken(),
//                sharedPrefManager.getUserForID().getId());
//
//        call.enqueue(new Callback<ReportUserGetResponse>() {
//            @Override
//            public void onResponse(Call<ReportUserGetResponse> call, Response<ReportUserGetResponse> response) {
//                reportList = new ArrayList<>(response.body().getReports());
//                reportListAdapter = new ReportListAdapter(getApplicationContext(), reportList);
//
//                if(response.isSuccessful()){
//                    int resolvedCount = 0;
//                    int unResolvedCount = 0;
//                    for(ReportWithAuthor list : reportList){
//                        if(list.getResolved()){
//                            resolvedCount++;
//                        }else{
//                            unResolvedCount++;
//                        }
//                    }
//                    Toast.makeText(getApplicationContext(), ""+ resolvedCount, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), ""+ unResolvedCount, Toast.LENGTH_SHORT).show();
//                    Common.resolvedIssues = resolvedCount;
//                    Common.unResolvedIssues = unResolvedCount;
//                    Toast.makeText(getApplicationContext(), "Report Showing successfully", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Error1: " + response.message(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ReportUserGetResponse> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Error2: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

        viewPager2.setAdapter(new ViewPagerFragsAdapter(this));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
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
        }
        );
        tabLayoutMediator.attach();

        addReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PostReportActivity.class));
            }
        });


//        logOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sharedPrefManager.logOut();
//                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
//                loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(loginIntent);
//            }
//        });
    }

//    private void getUserFromUid(String uid) {
//
//        Call<UserResponse> call = RetrofitClient.getInstance().getApi().getUID(uid, "Bearer " + sharedPrefManager.getToken());
//        call.enqueue(new Callback<UserResponse>() {
//            @Override
//            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//
//                UserResponse userResponse = response.body();
//
//                if(response.isSuccessful()){
//
//                    Common.currentUserName = userResponse.getUser().getName();
//                    Common.currentUserEmail = userResponse.getUser().getEmail();
//                    Common.currentUserPhone = String.valueOf(userResponse.getUser().getPhone());
//                    Common.currentUserAddress = userResponse.getUser().getAddress();
//
//                    Toast.makeText(MainActivity.this, ""+Common.currentUserName, Toast.LENGTH_SHORT).show();
//
//                }else{
//
//                    Toast.makeText(MainActivity.this, "Error: "+ response.message(), Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Failed: "+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
}