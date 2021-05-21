package com.wheic.cleanurge.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wheic.cleanurge.ModelResponse.Reports.ReportUserGetResponse;
import com.wheic.cleanurge.ModelResponse.Reports.ReportWithAuthor;
import com.wheic.cleanurge.R;
import com.wheic.cleanurge.Adapter.ReportList.ReportListAdapter;
import com.wheic.cleanurge.RetrofitAttachment.RetrofitClient;
import com.wheic.cleanurge.SharedPrefManager.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

//    private Button addReportBtn;

    private RecyclerView reportInfoList;
    private List<ReportWithAuthor> reportList;
    private ReportListAdapter reportListAdapter;
    private SharedPrefManager sharedPrefManager;
    private ProgressBar reportListProgressBar;
    private LinearLayout reportListCrashMessage;
    private LinearLayout reportListNoItemMessage;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reportInfoList = view.findViewById(R.id.homeReportList);
        reportListProgressBar = view.findViewById(R.id.reportListProgressBar);
        reportListCrashMessage = view.findViewById(R.id.reportListCrashMessage);
        reportListNoItemMessage = view.findViewById(R.id.reportListNoItemMessage);

        reportInfoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        reportInfoList.setHasFixedSize(true);

        sharedPrefManager = new SharedPrefManager(getActivity());

        fetchReportList();

        reportListCrashMessage.setOnClickListener(v -> {
            reportListCrashMessage.setVisibility(View.GONE);
            fetchReportList();
        });

    }

    private void fetchReportList() {

        reportListProgressBar.setVisibility(View.VISIBLE);

        Call<ReportUserGetResponse> call = RetrofitClient.getInstance().getApi().getUserReports("Bearer " + sharedPrefManager.getToken(),
                sharedPrefManager.getUserForID().getId());

        call.enqueue(new Callback<ReportUserGetResponse>() {
            @Override
            public void onResponse(Call<ReportUserGetResponse> call, Response<ReportUserGetResponse> response) {

                if (response.isSuccessful()) {

                    reportInfoList.setVisibility(View.VISIBLE);
                    reportList = new ArrayList<>(response.body().getReports());
                    reportListAdapter = new ReportListAdapter(getActivity(), reportList);
                    reportInfoList.setAdapter(reportListAdapter);

                    if (reportList.size() == 0) {
                        reportListNoItemMessage.setVisibility(View.VISIBLE);
                    }

                } else {
                    reportListCrashMessage.setVisibility(View.VISIBLE);
                    reportInfoList.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "TODO FIX MESSAGE ERROR1: " + response.message(), Toast.LENGTH_SHORT).show();
                }

                reportListProgressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ReportUserGetResponse> call, Throwable t) {
                reportListCrashMessage.setVisibility(View.VISIBLE);
                reportInfoList.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "TODO FIX MESSAGE ERROR2: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                reportListProgressBar.setVisibility(View.GONE);
            }
        });
    }
}