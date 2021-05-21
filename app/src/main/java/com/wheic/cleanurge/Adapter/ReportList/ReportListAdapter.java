package com.wheic.cleanurge.Adapter.ReportList;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.wheic.cleanurge.ModelResponse.Reports.ReportWithAuthor;
import com.wheic.cleanurge.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ViewHolder> {

    private Context context;
    private List<ReportWithAuthor> reportList;

    public ReportListAdapter(Context context, List<ReportWithAuthor> reportList) {
        this.context = context;
        this.reportList = reportList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_report_item_layout, parent, false);
        return new ViewHolder(view);
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.reportSerialText.setText("" + (position + 1));
        holder.reportContentHeadingText.setText(reportList.get(position).getContent());
        if (!reportList.get(position).getResolved()) {
            holder.reportColorAlertView.setBackground(ContextCompat.getDrawable(context, R.drawable.alertview_red_alert_background));
        } else {
            holder.reportColorAlertView.setBackground(ContextCompat.getDrawable(context, R.drawable.alertview_green_alert_background));
        }

        holder.wholeReportCardLayout.setOnClickListener(v -> {

            holder.imageLoadProgressbar.setVisibility(View.VISIBLE);
            if (!reportList.get(position).isExpanded()) {
                reportList.get(position).setExpanded(true);
                holder.reportExpandLayout.setVisibility(View.VISIBLE);
                Glide.with(holder.itemView.getContext())
                        .asBitmap()
                        .load(reportList.get(position).getPictureUrl())
                        .override(800, 400)
                        .timeout(60000)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(true)
                        .placeholder(R.drawable.image_loading_failed_placeholder)
                        .listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                holder.imageLoadProgressbar.setVisibility(View.GONE);
                                Toast.makeText(context, "TODO FIX MESSAGE", Toast.LENGTH_SHORT).show();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                holder.imageLoadProgressbar.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                holder.reportPostImage.setImageBitmap(resource);
                            }
                        });

                String[] date_time = getLocalDateTime(reportList.get(position).getCreatedAt());

                holder.reportAddressText.setText(Html.fromHtml("<b>" + "Address : " + "</b>" + reportList.get(position).getAddress()));
                holder.reportDateText.setText(Html.fromHtml("<b>Date : </b>" + date_time[0]));
                holder.reportTimeText.setText(Html.fromHtml("<b>Time : </b>" + date_time[1] + " " + date_time[2]));

            } else {
                reportList.get(position).setExpanded(false);
                holder.reportExpandLayout.setVisibility(View.GONE);
            }

        });

    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
    private String[] getLocalDateTime(String isoDateTime) {

//        TemporalAccessor temporalAccessor = DateTimeFormatter.ISO_INSTANT.parse(isoDateTime);
//        Instant instant = Instant.from(temporalAccessor);
//        Date date = Date.from(instant);
//        String[] date_time = date.toLocaleString().split(" ");
//        Log.d("DATE: ", date_time[0]);
//        Log.d("TIME: ", date_time[1]);
//        Log.d("MERIDIAN: ", date_time[2]);
//        return date_time;

        SimpleDateFormat resultDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
        Date date = null;
        try {
            date = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).parse(isoDateTime.replaceAll("Z$", "+0000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] date_time = resultDateTimeFormat.format(date).split(" ");

        Log.d("Result Date:", date_time[0]);
        Log.d("Result Time:",  date_time[1] + date_time[2]);

        return date_time;
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView reportSerialText, reportContentHeadingText, reportAddressText, reportDateText, reportTimeText;
        private ImageView reportPostImage;
        private CardView wholeReportCardLayout;
        private ConstraintLayout reportExpandLayout;
        private View reportColorAlertView;
        private ProgressBar imageLoadProgressbar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reportSerialText = itemView.findViewById(R.id.reportSerialText);
            reportContentHeadingText = itemView.findViewById(R.id.reportContentHeadingText);
            reportAddressText = itemView.findViewById(R.id.reportAddressText);
            reportDateText = itemView.findViewById(R.id.reportDateText);
            reportTimeText = itemView.findViewById(R.id.reportTimeText);
            reportPostImage = itemView.findViewById(R.id.reportPostImage);
            reportPostImage.setClipToOutline(true);
            wholeReportCardLayout = itemView.findViewById(R.id.wholeReportCardLayout);
            reportExpandLayout = itemView.findViewById(R.id.reportExpandLayout);
            reportColorAlertView = itemView.findViewById(R.id.reportColorAlertView);
            imageLoadProgressbar = itemView.findViewById(R.id.imageLoadProgressbar);

        }
    }

}
