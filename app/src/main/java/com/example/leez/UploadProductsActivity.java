package com.example.leez;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UploadProductsActivity extends AppCompatActivity {

    private static final int PICK_CSV_FILE = 1;
    private static final String TAG = "UploadProductsActivity";
    private FirebaseFirestore db;
    private List<Product> products;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_products);

        // Button Click Listener for file selection
        Button selectCsvButton = findViewById(R.id.selectCsvButton);
        selectCsvButton.setOnClickListener(v -> openFilePicker());
    }

    private void openFilePicker() {
        // Intent to open file picker
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/csv");
        startActivityForResult(Intent.createChooser(intent, "Select a CSV file"), PICK_CSV_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Handling the result of file selection
        if (requestCode == PICK_CSV_FILE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    try {
                        // Process CSV data
                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        if (inputStream != null) {
                            // Initialize FirebaseFirestore instance (db) and ArrayList (products)
                            db = FirebaseFirestore.getInstance();
                            products = new ArrayList<>();

                            // Show a progress dialog
                            progressDialog = ProgressDialog.show(this, "Processing", "Please wait...", true);

                            // Process CSV data
                            processCSVData(inputStream);

                            // Dismiss the progress dialog
                            progressDialog.dismiss();

                            // Add products to Firestore after processing CSV data
                            addProductsToFirestore();

                            // Display success message
                            Toast.makeText(this, "CSV data processed successfully", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        // Dismiss the progress dialog in case of an error
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(this, "Error processing CSV file", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Error processing CSV file", e);

                    }
                }
            }
        }
    }

    private void processCSVData(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        // Assuming the CSV file has columns: barcode, name, quantity, price, ...
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");

            // Access individual values from the array
            String barcode = values[0];
            String name = values[1];
            String quantity = values[2];
            String price = values[3];

            ProductInfo productInfo = new ProductInfo(name, quantity, price);
            Product product = new Product(barcode, productInfo);

            products.add(product);
        }
    }

    private void addProductsToFirestore() {
        for (Product product : products) {
            // Assuming you have a 'products' collection in Firestore
            db.collection("products")
                    .document(product.getBarcode())
                    .set(product)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "Product added successfully"))
                    .addOnFailureListener(e -> Log.w(TAG, "Error adding product", e));
        }
    }
}
