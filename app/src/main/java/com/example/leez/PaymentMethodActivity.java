package com.example.leez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentMethodActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        // Set onClickListeners for payment method buttons
        Button mpesaButton = findViewById(R.id.buttonMpesa);
        mpesaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement M-Pesa payment logic
                // For simplicity, just display a toast message
                // In a real app, you would integrate with M-Pesa SDK or API
                // and handle the payment process
                // For example: Toast.makeText(PaymentMethodActivity.this, "M-Pesa payment selected", Toast.LENGTH_SHORT).show();
            }
        });

        Button paypalButton = findViewById(R.id.buttonPaypal);
        paypalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement PayPal payment logic
                // For simplicity, just display a toast message
                // In a real app, you would integrate with PayPal SDK or API
                // and handle the payment process
                // For example: Toast.makeText(PaymentMethodActivity.this, "PayPal payment selected", Toast.LENGTH_SHORT).show();
            }
        });

        Button creditCardButton = findViewById(R.id.buttonCreditCard);
        creditCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement Credit Card payment logic
                // For simplicity, just display a toast message
                // In a real app, you would integrate with a payment gateway
                // and handle the credit card payment process
                // For example: Toast.makeText(PaymentMethodActivity.this, "Credit Card payment selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
