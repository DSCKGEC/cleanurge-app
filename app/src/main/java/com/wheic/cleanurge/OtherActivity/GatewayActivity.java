package com.wheic.cleanurge.OtherActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.wheic.cleanurge.R;

public class GatewayActivity extends AppCompatActivity {

    private Button gotoLoginButton;
    private LinearLayout gotoCreateAccButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gateway);
        gotoLoginButton = findViewById(R.id.goToLoginButton);
        gotoCreateAccButton = findViewById(R.id.goToCreateAccButton);

        gotoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GatewayActivity.this, LoginActivity.class));
            }
        });

        gotoCreateAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GatewayActivity.this, CreateAccountActivity.class));
            }
        });

    }
}