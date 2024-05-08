package com.example.najwajauhariquiz02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class InvoiceActivity extends AppCompatActivity {
    private TextView typeTextView, additionTextView, timeTextView, totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        // Inisialisasi TextView
        typeTextView = findViewById(R.id.type_textview);
        additionTextView = findViewById(R.id.addition_textview);
        timeTextView = findViewById(R.id.time_textview);
        totalTextView = findViewById(R.id.total_textview);

        // Mendapatkan data dari Intent
        Intent intent = getIntent();
        String type = intent.getStringExtra("TYPE");
        String addition = intent.getStringExtra("ADDITION");
        int time = intent.getIntExtra("TIME", 0);
        int totalPrice = intent.getIntExtra("TOTAL_PRICE", 0);

        // Format harga ke dalam format mata uang Rupiah
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String totalPriceString = formatRupiah.format(totalPrice);

        // Set nilai TextView sesuai dengan data yang diterima
        typeTextView.setText("Type: " + type);
        additionTextView.setText("Tambahan: " + addition);
        timeTextView.setText("Waktu: " + time + " Jam");
        totalTextView.setText("Total: " + totalPriceString);
    }
}
