package com.wheic.cleanurge.UploadDataModel;

import android.net.Uri;

public class UploadParamModel{
    private String content;
    private android.net.Uri Uri;
    private String address;

    public UploadParamModel(String content, Uri uri, String address) {
        this.content = content;
        Uri = uri;
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Uri getUri() {
        return Uri;
    }

    public void setUri(Uri uri) {
        Uri = uri;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
