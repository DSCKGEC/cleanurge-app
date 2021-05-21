package com.wheic.cleanurge.Fragments;

import android.content.Intent;
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

import com.wheic.cleanurge.Adapter.BeaconRecycler.BeaconListAdapter;
import com.wheic.cleanurge.OtherActivity.MapLocationActivity;
import com.wheic.cleanurge.ModelResponse.Beacon.Beacon;
import com.wheic.cleanurge.ModelResponse.Beacon.BeaconListResponse;
import com.wheic.cleanurge.R;
import com.wheic.cleanurge.RetrofitAttachment.RetrofitClient;
import com.wheic.cleanurge.SharedPrefManager.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeaconFragment extends Fragment {

    private RecyclerView beaconInfoList;
    private List<Beacon> beaconList;
    private BeaconListAdapter beaconListAdapter;
    private SharedPrefManager sharedPrefManager;
    private ProgressBar beaconListProgressBar;
    private LinearLayout beaconListCrashMessage;
    private LinearLayout beaconListNoItemMessage;

    public BeaconFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beacon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View itemView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(itemView, savedInstanceState);

        beaconInfoList = itemView.findViewById(R.id.beaconInfoList);
        beaconListProgressBar = itemView.findViewById(R.id.beaconListProgressBar);
        beaconListCrashMessage = itemView.findViewById(R.id.beaconListCrashMessage);
        beaconListNoItemMessage = itemView.findViewById(R.id.beaconListNoItemMessage);
        beaconInfoList.setHasFixedSize(true);
        beaconInfoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        sharedPrefManager = new SharedPrefManager(getActivity());

        fetchBeaconList();
        beaconListCrashMessage.setOnClickListener(v -> {
            beaconListCrashMessage.setVisibility(View.GONE);
            fetchBeaconList();
        });

    }

    private void fetchBeaconList() {
        beaconListProgressBar.setVisibility(View.VISIBLE);
        Call<BeaconListResponse> call = RetrofitClient.getInstance().getApi().fetchBeacons("Bearer " + sharedPrefManager.getToken());

        call.enqueue(new Callback<BeaconListResponse>() {
            @Override
            public void onResponse(Call<BeaconListResponse> call, Response<BeaconListResponse> response) {

                if (response.isSuccessful()) {
                    beaconInfoList.setVisibility(View.VISIBLE);
                    beaconList = new ArrayList<>(response.body().getBeacons());
                    beaconListAdapter = new BeaconListAdapter(getActivity(), beaconList);
                    beaconInfoList.setAdapter(beaconListAdapter);
                    if (beaconList.size() == 0) {
                        beaconListNoItemMessage.setVisibility(View.VISIBLE);
                    }
                    beaconListAdapter.setOnItemClickListener((beacon, mapCord, beaconCode, beaconAddress, beaconLevel) -> {
                        Intent mapIntent = new Intent(getActivity(), MapLocationActivity.class);
                        mapIntent.putExtra("BeaconLng", mapCord.get(0));
                        mapIntent.putExtra("BeaconLat", mapCord.get(1));
                        mapIntent.putExtra("BeaconCode", beaconCode);
                        mapIntent.putExtra("BeaconAddress", beaconAddress);
                        mapIntent.putExtra("BeaconLevel", beaconLevel);
                        startActivity(mapIntent);

                    });

                } else {
                    beaconListCrashMessage.setVisibility(View.VISIBLE);
                    beaconInfoList.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "TODO FIX MESSAGE ERROR1: " + response.message(), Toast.LENGTH_SHORT).show();
                }

                beaconListProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<BeaconListResponse> call, Throwable t) {
                beaconListCrashMessage.setVisibility(View.VISIBLE);
                beaconInfoList.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "TODO FIX MESSAGE ERROR2: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                beaconListProgressBar.setVisibility(View.GONE);
            }
        });
    }
}