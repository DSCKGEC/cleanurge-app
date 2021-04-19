package com.wheic.cleanurge.Adapter.BeaconRecycler;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.wheic.cleanurge.ModelResponse.Beacon.Beacon;
import com.wheic.cleanurge.R;

import java.util.List;

public class BeaconListAdapter extends RecyclerView.Adapter<BeaconListAdapter.ViewHolder> {

    private Context context;
    private List<Beacon> beaconList;

    private OnItemsClickListener listener = null;

    public BeaconListAdapter(Context context, List<Beacon> beaconList) {
        this.context = context;
        this.beaconList = beaconList;
    }

    public void setOnItemClickListener(OnItemsClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_beacon_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeaconListAdapter.ViewHolder holder, int position) {

        double level = 23.456D;

        holder.beaconSerialText.setText("" + (position + 1));
        holder.beaconCodeHeadingText.setText("Beacon: " + beaconList.get(position).getCode());
        holder.beaconAddressText.setText("Address: " + beaconList.get(position).getAddress());
        holder.beaconLevelText.setText("Level: " + beaconList.get(position).getLevel());
        if((80 <= level) && (level <= 100)){
            holder.beaconColorAlertView.setBackgroundColor(Color.RED);
        }else if ((50 <= level) && (level <= 80)){
            holder.beaconColorAlertView.setBackgroundColor(Color.BLUE);
        }else{
            holder.beaconColorAlertView.setBackgroundColor(Color.GREEN);
        }
        holder.wholeCardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onItemClick(
                            beaconList.get(position),
                            beaconList.get(position).getGeo().getCoordinates(),
                            beaconList.get(position).getCode(),
                            beaconList.get(position).getAddress(),
                            beaconList.get(position).getLevel());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return beaconList.size();
    }

    public interface OnItemsClickListener{
        void onItemClick(Beacon beacon, List<Double> mapCord, String beaconCode, String beaconAddress, String beaconLevel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView wholeCardLayout;
        private TextView beaconSerialText, beaconCodeHeadingText, beaconAddressText, beaconLevelText;
        private View beaconColorAlertView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wholeCardLayout = itemView.findViewById(R.id.wholeCardLayout);
            beaconSerialText = itemView.findViewById(R.id.beaconSerialText);
            beaconCodeHeadingText = itemView.findViewById(R.id.beaconCodeHeadingText);
            beaconAddressText = itemView.findViewById(R.id.beaconAddressText);
            beaconLevelText = itemView.findViewById(R.id.beaconLevelText);
            beaconColorAlertView = itemView.findViewById(R.id.beaconColorAlertView);

        }

    }
}
