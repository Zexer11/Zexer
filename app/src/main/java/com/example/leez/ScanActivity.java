package com.example.leez;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ScanActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        // Initialize Firestore after barcode scanning
        initiateBarcodeScan();
    }

    private void initiateBarcodeScan() {
        // Initiate the barcode scanner
        new IntentIntegrator(this)
                .setOrientationLocked(false)
                .setPrompt("Scan a barcode")
                .setBeepEnabled(true)
                .initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Handle the result of the barcode scan
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                // Handle the scanned barcode result
                String scannedBarcode = result.getContents();

                // Initialize Firestore after barcode scanning
                initializeFirestore();

                // Retrieve product information from Firestore
                getProductInfoFromFirestore(scannedBarcode);
            }
            finish(); // Finish the activity after scanning
        }
    }

    private void initializeFirestore() {
        // Initialize Firestore
        db = FirebaseFirestore.getInstance();
    }

    private void getProductInfoFromFirestore(String scannedBarcode) {
        db.collection("products")
                .document(scannedBarcode)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {

                        // Extract data from DocumentSnapshot
                        String name = documentSnapshot.getString("name");
                        String quantity = documentSnapshot.getString("quantity");
                        String price = documentSnapshot.getString("price");

                        // Create a ProductInfo object
                        ProductInfo productInfo = new ProductInfo(name, quantity, price);

                        // Create an Intent to pass data to ShopActivity
                        Intent shopIntent = new Intent(ScanActivity.this, ShopActivity.class);
                        shopIntent.putExtra("scannedBarcode", scannedBarcode);
                        shopIntent.putExtra("productInfo", productInfo);

                        // Start ShopActivity with the Intent
                        startActivity(shopIntent);
                    } else {
                        // Document does not exist, notify the user
                        Toast.makeText(this, "Product information not found for the scanned barcode", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    // Log the error message
                    Log.e("ScanActivity", "Error retrieving product information", e);
                    // Handle errors
                    Toast.makeText(this, "Error retrieving product information", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                });
    }
}



