package com.example.najwajauhariquiz02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RadioGroup typeGroup, additionGroup;
    private EditText timeEditText;
    private Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        typeGroup = findViewById(R.id.type_group);
        additionGroup = findViewById(R.id.addition_group);
        timeEditText = findViewById(R.id.time_edittext);
        btnOrder = findViewById(R.id.button_order);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int typePrice = getSelectedTypePrice();
                int additionPrice = getSelectedAdditionPrice();
                int time = getTime();

                if (typePrice == 0 || time == 0) {
                    Toast.makeText(MainActivity.this, "Silakan pilih jenis PS dan masukkan waktu bermain.", Toast.LENGTH_SHORT).show();
                } else {
                    int totalPrice = (typePrice * time + additionPrice);

                    // Get selected type and addition
                    RadioButton selectedTypeRadioButton = findViewById(typeGroup.getCheckedRadioButtonId());
                    String selectedType = selectedTypeRadioButton.getText().toString();

                    RadioButton selectedAdditionRadioButton = findViewById(additionGroup.getCheckedRadioButtonId());
                    String selectedAddition = selectedAdditionRadioButton.getText().toString();

                    // Start InvoiceActivity and pass the total price, type, addition, and time
                    Intent intent = new Intent(MainActivity.this, InvoiceActivity.class);
                    intent.putExtra("TOTAL_PRICE", totalPrice);
                    intent.putExtra("TYPE", selectedType);
                    intent.putExtra("ADDITION", selectedAddition);
                    intent.putExtra("TIME", time);
                    startActivity(intent);
                }
            }
        });
    }

    private int getSelectedTypePrice() {
        int price = 0;
        int selectedId = typeGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton radioButton = findViewById(selectedId);
            String type = radioButton.getText().toString();
            switch (type) {
                case "PS5":
                    price = 10000;
                    break;
                case "PS4":
                    price = 8000;
                    break;
                case "PS3":
                    price = 5000;
                    break;
                case "PSVR":
                    price = 20000;
                    break;
            }
        }
        return price;
    }

    private int getSelectedAdditionPrice() {
        int price = 0;
        int selectedId = additionGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton radioButton = findViewById(selectedId);
            String addition = radioButton.getText().toString();
            switch (addition) {
                case "Indomie":
                    price = 7000;
                    break;
                case "Mie Ayam":
                    price = 10000;
                    break;
                case "Somay":
                    price = 5000;
                    break;
            }
        }
        return price;
    }

    private int getTime() {
        String timeStr = timeEditText.getText().toString().trim();
        if (!timeStr.isEmpty()) {
            return Integer.parseInt(timeStr);
        }
        return 0;
    }
}
