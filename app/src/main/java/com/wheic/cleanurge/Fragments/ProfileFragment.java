package com.wheic.cleanurge.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.wheic.cleanurge.OtherActivity.GatewayActivity;
import com.wheic.cleanurge.ModelResponse.Reports.ReportUserGetResponse;
import com.wheic.cleanurge.ModelResponse.Reports.ReportWithAuthor;
import com.wheic.cleanurge.R;
import com.wheic.cleanurge.RetrofitAttachment.RetrofitClient;
import com.wheic.cleanurge.SharedPrefManager.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private SharedPrefManager sharedPrefManager;
    private TextView currentUserName, currentUserEmail, currentUserPhone;
    private LinearLayout logoutProfileLayout;
    private PieChart reportPieChart;
    private ProgressBar pieDataFetchProgressBar;
    private ConstraintLayout resolvedDetailPieErrorMessage;
    private ConstraintLayout resolvedDetailPieNoItemMessage;
    private LinearLayout pieLayout;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPrefManager = new SharedPrefManager(getActivity());

        currentUserName = view.findViewById(R.id.currentUserName);
        currentUserEmail = view.findViewById(R.id.currentUserEmail);
        currentUserPhone = view.findViewById(R.id.currentUserPhone);
        logoutProfileLayout = view.findViewById(R.id.logoutLayout);
        reportPieChart = view.findViewById(R.id.userReportPieChart);
        pieDataFetchProgressBar = view.findViewById(R.id.pieDataFetchProgressBar);
        resolvedDetailPieErrorMessage = view.findViewById(R.id.resolvedDetailPieErrorMessage);
        resolvedDetailPieNoItemMessage = view.findViewById(R.id.resolvedDetailPieNoItemMessage);
        pieLayout = view.findViewById(R.id.pieLayout);

        currentUserName.setText(sharedPrefManager.getUserDetails().getName());
        currentUserEmail.setText(sharedPrefManager.getUserDetails().getEmail());
//        currentUserPhone.setText(String.valueOf(sharedPrefManager.getUserDetails().getPhone()));
        currentUserPhone.setText(sharedPrefManager.getUserDetails().getPhone());

        fetchPieDataList();

        pieLayout.setOnClickListener(v -> fetchPieDataList());

        logoutProfileLayout.setOnClickListener(v -> {
            sharedPrefManager.logOut();
            Intent loginIntent = new Intent(getActivity(), GatewayActivity.class);
            loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginIntent);

        });

    }

    private void fetchPieDataList() {

        pieDataFetchProgressBar.setVisibility(View.VISIBLE);
        reportPieChart.setVisibility(View.GONE);
        resolvedDetailPieNoItemMessage.setVisibility(View.GONE);
        resolvedDetailPieErrorMessage.setVisibility(View.GONE);

        Call<ReportUserGetResponse> call = RetrofitClient.getInstance().getApi().getUserReports("Bearer " + sharedPrefManager.getToken(),
                sharedPrefManager.getUserForID().getId());

        call.enqueue(new Callback<ReportUserGetResponse>() {
            @Override
            public void onResponse(Call<ReportUserGetResponse> call, Response<ReportUserGetResponse> response) {

                if (response.isSuccessful()) {

                    ArrayList<ReportWithAuthor> reportList = new ArrayList<>(response.body().getReports());
                    int resolvedCount = 0;
                    int unResolvedCount = 0;
                    if (reportList.size() > 0) {
                        reportPieChart.setVisibility(View.VISIBLE);
                        resolvedDetailPieNoItemMessage.setVisibility(View.GONE);
                        for (ReportWithAuthor list : reportList) {
                            if (list.getResolved()) {
                                resolvedCount++;
                            } else {
                                unResolvedCount++;
                            }
                        }

                        setupPieChart();
                        loadPieChart(resolvedCount, unResolvedCount);

                    } else if (reportList.size() == 0) {
                        resolvedDetailPieNoItemMessage.setVisibility(View.VISIBLE);
                    }


                } else {
                    resolvedDetailPieNoItemMessage.setVisibility(View.GONE);
                    resolvedDetailPieErrorMessage.setVisibility(View.VISIBLE);
                    reportPieChart.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "TODO FIX MESSAGE ERROR1: " + response.message(), Toast.LENGTH_SHORT).show();
                }

                pieDataFetchProgressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ReportUserGetResponse> call, Throwable t) {
                resolvedDetailPieNoItemMessage.setVisibility(View.GONE);
                resolvedDetailPieErrorMessage.setVisibility(View.VISIBLE);
                reportPieChart.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "TODO FIX MESSAGE ERROR2: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                pieDataFetchProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void loadPieChart(int resolvedCount, int unResolvedCount) {

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(resolvedCount, "Resolved"));
        entries.add(new PieEntry(unResolvedCount, "Unresolved"));
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }
        for (int color : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }
        PieDataSet dataSet = new PieDataSet(entries, "Issues Reported");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(reportPieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);
        reportPieChart.setData(data);
        reportPieChart.invalidate();

    }

    private void setupPieChart() {
        reportPieChart.setDrawHoleEnabled(false);
        reportPieChart.setUsePercentValues(true);
        reportPieChart.setEntryLabelTextSize(10f);
        reportPieChart.setEntryLabelColor(Color.BLACK);
//        reportPieChart.setCenterTextSize(10);
        reportPieChart.getDescription().setEnabled(false);

        Legend legend = reportPieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setTextSize(15);
        legend.setDrawInside(false);
        legend.setEnabled(true);
    }

}