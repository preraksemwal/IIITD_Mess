package com.mc.iiitd_mess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TermsUserActivity extends AppCompatActivity {

    Button agree_button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_terms_user);
        agree_button = findViewById(R.id.agree_button);

        agree_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TermsUserActivity.this, DashboardUserActivity.class);
                startActivity(intent);
            }
        });
    }
}
