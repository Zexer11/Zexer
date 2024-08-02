package com.example.leez;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    TextView tvQuantity;
    TextView tvPrice, tvTotal, tvOverallTotal;
    TableLayout tlList;
    Button btnScan, btnCheckout, btnBack;

    // Variables for scanned items
    private ArrayList<String> scannedItems = new ArrayList<>();
    private ArrayList<String> quantities = new ArrayList<>();
    private ArrayList<String> prices = new ArrayList<>();
    private ArrayList<String> totals = new ArrayList<>();
    private double overallTotal = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        // Initialize UI elements
        tlList = findViewById(R.id.tableLayoutList);
        tvQuantity = findViewById(R.id.editTextQuantity);
        tvPrice = findViewById(R.id.textViewItemPrice);
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
                Intent intent = new Intent(ShopActivity.this, ScanActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        // Set onClickListener for the "Checkout" button
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log the button click for debugging
                Log.d("ShopActivity", "Checkout button clicked");

                // Redirect to ReceiptActivity
                // Transfer scanned items and their details to ReceiptActivity
                Intent intent = new Intent(ShopActivity.this, ReceiptActivity.class);
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
                startActivity(new Intent(ShopActivity.this, HomeActivity.class));
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

    // Override onActivityResult to handle the result from ScanActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                String scannedBarcode = data.getStringExtra("scannedBarcode");
                ProductInfo productInfo = data.getParcelableExtra("productInfo");

                // Process the scanned barcode and product information
                updateUI(scannedBarcode, productInfo);
            }
        }
    }

    // Method to update the UI with the scanned product information
    private void updateUI(String scannedBarcode, ProductInfo productInfo) {
        // ... (existing UI element initialization)

        // Ensure you have the TextViews for item name, price, and quantity
        TextView itemNameTextView = findViewById(R.id.textViewItemName);
        TextView itemPriceTextView = findViewById(R.id.textViewItemPrice);
        EditText quantityEditText = findViewById(R.id.editTextQuantity);

        // Set the values in the TextViews
        itemNameTextView.setText(productInfo.getName());
        itemPriceTextView.setText(productInfo.getPrice());
        quantityEditText.setText("1"); // Assuming the quantity is always 1 for a scanned item

        // Calculate total for the scanned item
        double itemTotal = Double.parseDouble(productInfo.getPrice()); // Assuming total is the same as the price for a scanned item

        // Add the scanned item details to your lists
        scannedItems.add(scannedBarcode);
        quantities.add("1"); // Assuming the quantity is always 1 for a scanned item
        prices.add(productInfo.getPrice());
        totals.add(String.valueOf(itemTotal));

        // Dynamically add a row to the table
        addRowToTable(productInfo.getName(), productInfo.getPrice(), "1");

        // Update the overall total
        overallTotal += itemTotal;
        tvOverallTotal.setText(String.format("Overall Total: $%.2f", overallTotal));
    }
}
