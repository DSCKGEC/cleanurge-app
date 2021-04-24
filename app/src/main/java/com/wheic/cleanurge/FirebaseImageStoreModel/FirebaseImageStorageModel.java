package com.wheic.cleanurge.FirebaseImageStoreModel;

public class FirebaseImageStorageModel {
    public String imgUrl;

    public FirebaseImageStorageModel () {}

    public FirebaseImageStorageModel(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
