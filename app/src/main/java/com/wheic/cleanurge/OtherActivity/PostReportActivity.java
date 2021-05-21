package com.wheic.cleanurge.OtherActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.wheic.cleanurge.FirebaseImageStoreModel.FirebaseImageStorageModel;
import com.wheic.cleanurge.ModelResponse.Reports.ReportPostResponse;
import com.wheic.cleanurge.R;
import com.wheic.cleanurge.RetrofitAttachment.RetrofitClient;
import com.wheic.cleanurge.SharedPrefManager.SharedPrefManager;
import com.wheic.cleanurge.UploadDataModel.UploadParamModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostReportActivity extends AppCompatActivity {

    public static final int CAMERA_PERMISSION_CODE = 100;
    private LinearLayout newReportImageLayout;
    private ImageView newReportImageView, newReportAddImage;
    private ImageButton goBackImageButton;
    private Uri imageUri;
    private EditText newReportTitleInput, newReportAddressInput;
    private String newReportTitleInputText, newReportAddressInputText;
    private SharedPrefManager sharedPrefManager;

    private StorageReference ref = FirebaseStorage.getInstance().getReference();

    private Button reportBtn;

    private DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_report);

        newReportImageLayout = findViewById(R.id.newReportImageLayout);
        newReportImageView = findViewById(R.id.newReportImageView);
        newReportAddImage = findViewById(R.id.newReportAddImage);
        newReportImageView.setClipToOutline(true);
        reportBtn = findViewById(R.id.addReportBtn);
        newReportTitleInput = findViewById(R.id.newReportTitleInput);
        newReportAddressInput = findViewById(R.id.newReportAddressInput);
        goBackImageButton = findViewById(R.id.goBackButton);
        sharedPrefManager = new SharedPrefManager(this);

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        newReportImageLayout.setOnClickListener(v -> askCameraPermission());

        goBackImageButton.setOnClickListener(v -> finish());

        reportBtn.setOnClickListener(v -> {
            newReportTitleInputText = newReportTitleInput.getText().toString().trim();
            newReportAddressInputText = newReportAddressInput.getText().toString().trim();

            if (newReportTitleInputText.isEmpty()) {
                newReportTitleInput.setError("TODO FIX MESSAGE");
            } else if (newReportAddressInputText.isEmpty()) {
                newReportAddressInput.setError("TODO FIX MESSAGE");
            } else if (imageUri == null) {
                Toast.makeText(PostReportActivity.this, "TODO FIX MESSAGE", Toast.LENGTH_SHORT).show();
            } else {
                uploadImageToFireStorage(newReportTitleInputText, imageUri, newReportAddressInputText);
            }
        });

    }

    private void uploadImageToFireStorage(String content, Uri imageUri, String address) {

        UploadAsyncTask upload = new UploadAsyncTask();
        upload.execute(new UploadParamModel(content, imageUri, address));
        finish();
    }

    private void askCameraPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            startCropActivity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCropActivity();
            } else {
                Toast.makeText(this, "TODO FIX MESSAGE", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startCropActivity() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio((displayMetrics.widthPixels), (displayMetrics.widthPixels - 300))
                .setMinCropWindowSize((displayMetrics.widthPixels - 100), (displayMetrics.widthPixels - 400))
                .start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                newReportImageView.setImageURI(imageUri);
                newReportAddImage.setVisibility(View.GONE);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                assert result != null;
                Toast.makeText(this, "TODO FIX MESSAGE ERROR: " + result.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }


    private class UploadAsyncTask extends AsyncTask<UploadParamModel, Void, Void> {

        @Override
        protected Void doInBackground(UploadParamModel... uploadParamModels) {

            StorageReference fileRef = ref.child(System.currentTimeMillis() + ".jpg");
            fileRef.putFile(uploadParamModels[0].getUri()).addOnSuccessListener(taskSnapshot -> fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                String imageUrl = new FirebaseImageStorageModel(uri.toString()).getImgUrl();
                Call<ReportPostResponse> call = RetrofitClient.getInstance().getApi().addReports(
                        "Bearer " + sharedPrefManager.getToken(),
                        uploadParamModels[0].getContent(),
                        imageUrl,
                        uploadParamModels[0].getAddress());
                call.enqueue(new Callback<ReportPostResponse>() {
                    @Override
                    public void onResponse(Call<ReportPostResponse> call, Response<ReportPostResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(PostReportActivity.this, "TODO FIX MESSAGE", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PostReportActivity.this, "TODO FIX MESSAGE ERROR1: " + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ReportPostResponse> call, Throwable t) {
                        Toast.makeText(PostReportActivity.this, "TODO FIX MESSAGE ERROR1: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            })).addOnFailureListener(e -> Toast.makeText(PostReportActivity.this, "TODO FIX MESSAGE", Toast.LENGTH_SHORT).show());

            return null;
        }
    }
}