package com.wheic.cleanurge.OtherActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wheic.cleanurge.ModelResponse.Registration.RegisterResponse;
import com.wheic.cleanurge.R;
import com.wheic.cleanurge.RetrofitAttachment.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText createAccUserNameInput;
    private EditText createAccUserEmailInput;
    private EditText createAccUserPasswordInput;
    private EditText createAccUserCPasswordInput;
    private EditText createAccUserPhoneInput;
    private EditText createAccUserAddressInput;
    private Button AccountCreateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        createAccUserNameInput = findViewById(R.id.createAccUserNameInput);
        createAccUserEmailInput = findViewById(R.id.createAccUserEmailInput);
        createAccUserPasswordInput = findViewById(R.id.createAccUserPasswordInput);
        createAccUserCPasswordInput = findViewById(R.id.createAccUserCPasswordInput);
        createAccUserPhoneInput = findViewById(R.id.createAccUserPhoneInput);
        createAccUserAddressInput = findViewById(R.id.createAccUserAddressInput);
        AccountCreateBtn = findViewById(R.id.AccountCreateBtn);

        AccountCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = createAccUserNameInput.getText().toString().trim();
                String userEmail = createAccUserEmailInput.getText().toString().trim();
                String userPassword = createAccUserPasswordInput.getText().toString().trim();
                String userCPassword = createAccUserCPasswordInput.getText().toString().trim();
                String userPhone = createAccUserPhoneInput.getText().toString().trim();
                String userAddress = createAccUserAddressInput.getText().toString().trim();

                if(userName.isEmpty()){
                    createAccUserNameInput.setError("Please enter valid information");
                }else if(userEmail.isEmpty()){
                    createAccUserEmailInput.setError("Please enter valid information");
                }else if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                    createAccUserEmailInput.setError("Please enter valid information");
                }else if(userPassword.isEmpty()){
                    createAccUserPasswordInput.setError("Please enter valid information");
                }else if(userCPassword.isEmpty()){
                    createAccUserCPasswordInput.setError("Please enter valid information");
                }else if(userPhone.isEmpty()){
                    createAccUserCPasswordInput.setError("Please enter valid information");
                }
                else if(userAddress.isEmpty()){
                    createAccUserAddressInput.setError("Please enter valid information");
                }
                else if(!userCPassword.equals(userPassword)){
                    createAccUserCPasswordInput.setError("Password didn't match");
                }else {
                    Call<RegisterResponse> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .register(userName,
                                    userEmail,
                                    userPassword,
                                    Integer.parseInt(userPhone),
                                    userAddress);
                    call.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                            RegisterResponse registerResponse = response.body();

//                            Toast.makeText(CreateAccountActivity.this, ""+registerResponse.getUser(), Toast.LENGTH_SHORT).show();

                            if(response.isSuccessful()){
                                Intent loginIntent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                                loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(loginIntent);
                                Toast.makeText(CreateAccountActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(CreateAccountActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {
                            Toast.makeText(CreateAccountActivity.this, "Error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

    }
}