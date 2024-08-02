package com.example.leez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReceiptActivity extends AppCompatActivity {

    private TableLayout tlReceiptItems;
    private Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        tlReceiptItems = findViewById(R.id.tableLayoutReceiptItems);
        btnPay = findViewById(R.id.buttonPay);

        // Retrieve scanned items from the intent
        Intent intent = getIntent();
        ArrayList<String> scannedItems = intent.getStringArrayListExtra("scannedItems");
        ArrayList<String> quantities = intent.getStringArrayListExtra("quantities");
        ArrayList<String> prices = intent.getStringArrayListExtra("prices");
        ArrayList<String> totals = intent.getStringArrayListExtra("totals");

        // Display receipt items dynamically
        displayReceiptItems(scannedItems, quantities, prices, totals);

        // Set onClickListener for the payment button
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to PaymentMethodActivity
                Intent paymentIntent = new Intent(ReceiptActivity.this, PaymentMethodActivity.class);
                startActivity(paymentIntent);
            }
        });
    }

    private void displayReceiptItems(
            ArrayList<String> scannedItems,
            ArrayList<String> quantities,
            ArrayList<String> prices,
            ArrayList<String> totals) {

        for (int i = 0; i < scannedItems.size(); i++) {
            TableRow row = new TableRow(this);

            TextView itemName = new TextView(this);
            itemName.setText(scannedItems.get(i));
            row.addView(itemName);

            TextView quantity = new TextView(this);
            quantity.setText(quantities.get(i));
            row.addView(quantity);

            TextView price = new TextView(this);
            price.setText(prices.get(i));
            row.addView(price);

            TextView total = new TextView(this);
            total.setText(totals.get(i));
            row.addView(total);

            tlReceiptItems.addView(row);
        }
    }
}

