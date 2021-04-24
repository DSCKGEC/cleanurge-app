package com.wheic.cleanurge.ModelResponse.Reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReportUserGetResponse {

    @SerializedName("reports")
    @Expose
    private List<ReportWithAuthor> reports = null;

    public ReportUserGetResponse(List<ReportWithAuthor> reports) {
        this.reports = reports;
    }

    public List<ReportWithAuthor> getReports() {
        return reports;
    }

    public void setReports(List<ReportWithAuthor> reports) {
        this.reports = reports;
    }
}
