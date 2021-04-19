package com.wheic.cleanurge.ModelResponse.Beacon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wheic.cleanurge.ModelResponse.Beacon.Beacon;

import java.util.List;

public class BeaconListResponse {
    @SerializedName("beacons")
    @Expose
    private List<Beacon> beacons = null;

    public BeaconListResponse(List<Beacon> beacons) {
        this.beacons = beacons;
    }

    public List<Beacon> getBeacons() {
        return beacons;
    }

    public void setBeacons(List<Beacon> beacons) {
        this.beacons = beacons;
    }
}
