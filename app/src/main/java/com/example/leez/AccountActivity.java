package com.example.leez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_account);

        // Find the User CardView
        CardView userCard = findViewById(R.id.cardUser);
        userCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity when User CardView is clicked
                startActivity(new Intent(AccountActivity.this, LoginActivity.class));
            }
        });

        // Find the Shop CardView
        CardView shopCard = findViewById(R.id.cardShop);
        shopCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity2 when Shop CardView is clicked
                startActivity(new Intent(AccountActivity.this, LoginActivity2.class));
            }
        });
    }
}
