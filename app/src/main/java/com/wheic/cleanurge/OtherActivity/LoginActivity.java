package com.wheic.cleanurge.OtherActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wheic.cleanurge.MainActivity;
import com.wheic.cleanurge.ModelResponse.Registration.LoginResponse;
import com.wheic.cleanurge.R;
import com.wheic.cleanurge.RetrofitAttachment.RetrofitClient;
import com.wheic.cleanurge.SharedPrefManager.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText emailInput, passwordInput;
    private ImageButton goBackButton;
    private ProgressBar loginProgress;

    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);
        emailInput = findViewById(R.id.loginEmailInput);
        passwordInput = findViewById(R.id.loginPasswordInput);
        goBackButton = findViewById(R.id.goBackButton);
        loginProgress = findViewById(R.id.loginProgressbar);

        sharedPrefManager = new SharedPrefManager(this);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if(email.isEmpty()){
                    emailInput.setError("Enter valid information");
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailInput.setError("Enter valid information");
                }else if(password.isEmpty()){
                    passwordInput.setError("Enter valid information");
                }else{
                    userLogin(email, password);
                }
            }
        });

    }

    private void userLogin(String email, String password) {
        loginBtn.setVisibility(View.GONE);
        loginProgress.setVisibility(View.VISIBLE);

        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().login(email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()){

                    LoginResponse loginResponse = response.body();

                    if(!loginResponse.getUser().getAdmin()){
                        assert loginResponse != null;
                        sharedPrefManager.saveSession(loginResponse.getToken());
//                                sharedPrefManager.setUser(loginResponse.getUser());
                        sharedPrefManager.setUserDetail(loginResponse.getUser());
                        sharedPrefManager.setUserID(loginResponse.getUser());
                        Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(homeIntent);
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "Fishy Credential", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    loginBtn.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginBtn.setVisibility(View.VISIBLE);
                loginProgress.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(sharedPrefManager.isLoggedIn()){
            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(homeIntent);
            finish();
        }
    }
}