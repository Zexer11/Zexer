package com.example.leez;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;

public class LeeMartActivity extends AppCompatActivity {

    EditText edQuantity;
    TextView tvPrice, tvTotal, tvOverallTotal;
    TableLayout tlList;
    Button btnScan, btnCheckout, btnBack;


    // Variables for scanned items
    private ArrayList<String> scannedItems = new ArrayList<>();
    private ArrayList<String> quantities = new ArrayList<>();
    private ArrayList<String> prices = new ArrayList<>();
    private ArrayList<String> totals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lee_mart);

        // Initialize UI elements
        tlList = findViewById(R.id.tableLayoutList);
        edQuantity = findViewById(R.id.editTextQuantity);
        tvPrice = findViewById(R.id.textViewPrice);
        tvTotal = findViewById(R.id.textViewTotal);
        tvOverallTotal = findViewById(R.id.textViewOverallTotal);
        btnScan = findViewById(R.id.buttonScan);
        btnCheckout = findViewById(R.id.buttonCheckout);
        btnBack = findViewById(R.id.buttonBack);

        // Set onClickListener for the "Scan to add item" button
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to BarcodeScannerActivity for scanning
                Intent intent = new Intent(LeeMartActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for the "Checkout" button
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log the button click for debugging
                Log.d("LeeMartActivity", "Checkout button clicked");

                // Redirect to ReceiptActivity
                // Transfer scanned items and their details to ReceiptActivity
                // Redirect to ReceiptActivity
                Intent intent = new Intent(LeeMartActivity.this, ReceiptActivity.class);
                intent.putStringArrayListExtra("scannedItems", scannedItems);
                intent.putStringArrayListExtra("quantities", quantities);
                intent.putStringArrayListExtra("prices", prices);
                intent.putStringArrayListExtra("totals", totals);
                startActivity(intent);

            }
        });

        // Set onClickListener for the "Back" button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Implement logic to handle the "Back" button click
                startActivity(new Intent(LeeMartActivity.this, HomeActivity.class));
            }
        });
    }

    // Method to dynamically add rows to the table
    private void addRowToTable(String itemName, String itemPrice, String quantity) {
        // Create a new row
        TableRow row = new TableRow(this);

        // Add TextViews for item name, price, and quantity
        TextView itemNameTextView = new TextView(this);
        itemNameTextView.setText(itemName);
        row.addView(itemNameTextView);

        TextView itemPriceTextView = new TextView(this);
        itemPriceTextView.setText(itemPrice);
        row.addView(itemPriceTextView);

        TextView quantityTextView = new TextView(this);
        quantityTextView.setText(quantity);
        row.addView(quantityTextView);

        // Add the row to the table
        tlList.addView(row);
    }

    // TODO: Add any additional methods for calculating total prices, updating UI, etc.
    // For example, you might want to update total prices based on user input.
    // UpdateTotalPrices();
}
