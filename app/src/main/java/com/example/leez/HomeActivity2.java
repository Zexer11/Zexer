package com.example.leez;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class HomeActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        // Replace the following buttons with the actual functionalities you want to provide
        Button manageAccountButton = findViewById(R.id.manageAccountButton);
        Button uploadProductsButton = findViewById(R.id.uploadProductsButton);
        Button manageTransactionsButton = findViewById(R.id.manageTransactionsButton);

        manageAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the manage account action
                startActivity(new Intent(HomeActivity2.this, ManageAccountActivity.class));
            }
        });

        uploadProductsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the upload products action
                startActivity(new Intent(HomeActivity2.this,UploadProductsActivity.class));
            }
        });

        manageTransactionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the manage transactions action
                startActivity(new Intent(HomeActivity2.this, ManageTransactionsActivity.class));
            }
        });
    }
}
