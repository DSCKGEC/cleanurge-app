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
        beaconInfoList.setHasFixedSize(true);
        beaconInfoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        sharedPrefManager = new SharedPrefManager(getActivity());
//        beaconList = new ArrayList<>();

//        Call<BeaconListResponse> call = RetrofitClient.getInstance().getApi().fetchBeacons();
//        call.enqueue(new Callback<BeaconListResponse>() {
//
//            @Override
//            public void onResponse(Call<BeaconListResponse> call, Response<BeaconListResponse> response) {
//                BeaconListResponse beaconListResponse = response.body();
//
//                if(response.isSuccessful()){
//                    beaconList = response.body().getBeacons();
//                    beaconInfoList.setAdapter(new BeaconListAdapter(getActivity(), beaconList));
//                }else{
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<BeaconListResponse> call, Throwable t) {
//
//            }
//        });
        Call<BeaconListResponse> call = RetrofitClient.getInstance().getApi().fetchBeacons("Bearer " + sharedPrefManager.getToken());

        call.enqueue(new Callback<BeaconListResponse>() {
            @Override
            public void onResponse(Call<BeaconListResponse> call, Response<BeaconListResponse> response) {

                if(response.isSuccessful()){
                    beaconList = new ArrayList<>(response.body().getBeacons());
                    beaconListAdapter = new BeaconListAdapter(getActivity(), beaconList);
                    beaconInfoList.setAdapter(beaconListAdapter);
                    beaconListAdapter.setOnItemClickListener(new BeaconListAdapter.OnItemsClickListener() {
                        @Override
                        public void onItemClick(Beacon beacon, List<Double> mapCord, String beaconCode, String beaconAddress, String beaconLevel) {
//                            Intent mapIntent = new Intent(getActivity(), LoginActivity.class);
                            Intent mapIntent = new Intent(getActivity(), MapLocationActivity.class);
                            mapIntent.putExtra("BeaconLng", mapCord.get(0));
                            mapIntent.putExtra("BeaconLat", mapCord.get(1));
                            mapIntent.putExtra("BeaconCode", beaconCode);
                            mapIntent.putExtra("BeaconAddress", beaconAddress);
                            mapIntent.putExtra("BeaconLevel", beaconLevel);
                            startActivity(mapIntent);
//                            Toast.makeText(getActivity(), ""+ mapCord.get(0), Toast.LENGTH_SHORT).show(); // long
//                            Toast.makeText(getActivity(), ""+ mapCord.get(1), Toast.LENGTH_SHORT).show(); // lat
                        }
                    });

                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Error1: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BeaconListResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error2: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}