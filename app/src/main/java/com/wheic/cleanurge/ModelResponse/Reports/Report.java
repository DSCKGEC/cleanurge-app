package com.wheic.cleanurge.ModelResponse.Reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report {

    @SerializedName("picture_url")
    @Expose
    private String pictureUrl;
    @SerializedName("is_resolved")
    @Expose
    private boolean isResolved;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("author")
    @Expose
    private String author;
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

    public Report(String pictureUrl,
                  boolean isResolved,
                  String id,
                  String author,
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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
