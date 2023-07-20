package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname = findViewById(R.id.editTextBMBFullName);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edcontact = findViewById(R.id.editTextBMBContactNo);
        edpincode = findViewById(R.id.editTextBMBPinCode);
        btnBooking = findViewById(R.id.buttonBMBBocking);

        Intent intent = getIntent();
        String[] price = intent.getStringArrayExtra("price");
        if (price != null && price.length > 0) {
            String[] priceParts = price[0].split(":");
            if (priceParts.length > 1) {
                String priceValue = priceParts[1];
                // Use the price value as needed
            }
        }

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");

                Database1 db = new Database1(getApplicationContext(), "healthcare", null, 1);
                db.addOrder(username, edname.getText().toString(),
                        edaddress.getText().toString(), edcontact.getText().toString(), Integer.parseInt(edpincode.getText().toString()), "", "", 0, "medicine");
                db.deleteCart(username, "medicine");
                Toast.makeText(getApplicationContext(), "Your booking is done successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
                btnBooking.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Your existing code for booking the order
                        // Create an Intent to launch the OrderDetailsActivity
                        Intent intent = new Intent(BuyMedicineBookActivity.this, OrderDetailsActivity.class);
                        intent.putExtra("name", edname.getText().toString());
                        intent.putExtra("address", edaddress.getText().toString());
                        intent.putExtra("contact", edcontact.getText().toString());
                        intent.putExtra("pincode", edpincode.getText().toString());

                        // Start the OrderDetailsActivity
                        startActivity(intent);
                    }
                });

            }
        });
    }
}
