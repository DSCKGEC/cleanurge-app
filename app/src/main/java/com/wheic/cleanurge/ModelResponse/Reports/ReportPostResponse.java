package com.wheic.cleanurge.ModelResponse.Reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportPostResponse {

    @SerializedName("report")
    @Expose
    private Report report;

    public ReportPostResponse(Report report) {
        this.report = report;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
