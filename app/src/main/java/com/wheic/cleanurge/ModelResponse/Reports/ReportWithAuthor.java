package com.wheic.cleanurge.ModelResponse.Reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportWithAuthor {
    @SerializedName("picture_url")
    @Expose
    private String pictureUrl;
    @SerializedName("is_resolved")
    @Expose
    private Boolean isResolved;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("author")
    @Expose
    private ReportAuthor author;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private int v;

    private boolean isExpanded = false;

    public ReportWithAuthor(String pictureUrl,
                            Boolean isResolved,
                            String id,
                            ReportAuthor author,
                            String content,
                            String address,
                            String createdAt,
                            String updatedAt,
                            int v) {
        this.pictureUrl = pictureUrl;
        this.isResolved = isResolved;
        this.id = id;
        this.author = author;
        this.content = content;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.v = v;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Boolean getResolved() {
        return isResolved;
    }

    public void setResolved(Boolean resolved) {
        isResolved = resolved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReportAuthor getAuthor() {
        return author;
    }

    public void setAuthor(ReportAuthor author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }
}
