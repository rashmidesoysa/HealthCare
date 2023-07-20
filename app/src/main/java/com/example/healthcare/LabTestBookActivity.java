package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname = findViewById(R.id.editTextLTBFullName);
        edaddress = findViewById(R.id.editTextLTBAddress);
        edcontact = findViewById(R.id.editTextltbContactNumber);
        edpincode = findViewById(R.id.editTextLTBPinCode);
        btnBooking = findViewById(R.id.buttonLTBBocking);

        Intent intent = getIntent();
        String[] price = intent.getStringArrayExtra("price");
        String[] dates = intent.getStringArrayExtra("date");
        String[] time = intent.getStringArrayExtra("time");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");

                if (!username.isEmpty()) {
                    String name = edname.getText().toString();
                    String address = edaddress.getText().toString();
                    String contact = edcontact.getText().toString();
                    String pincode = edpincode.getText().toString();

                    if (!name.isEmpty() && !address.isEmpty() && !contact.isEmpty() && !pincode.isEmpty()
                            && dates != null && dates.length > 0 && time != null && time.length > 0
                            && price != null && price.length > 1) {

                        Database1 db = new Database1(getApplicationContext(), "healthcare", null, 1);
                        db.addOrder(username, name, address, contact, Integer.parseInt(pincode),
                                dates[0], time[0], (int) Float.parseFloat(price[1]), "lab");
                        db.deleteCart(username, "lab");

                        Toast.makeText(getApplicationContext(), "Your booking is done successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Your booking is done successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Username not found", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
