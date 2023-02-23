package com.example.swat_riders;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Services_Categories extends AppCompatActivity {
    TextView tv_btn_1, tv_btn_2, tv_btn_3, tv_btn_4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_categories);

        tv_btn_1 = findViewById(R.id.btn_simple_booking);
        tv_btn_2 = findViewById(R.id.btn_LoaderBooking);
        tv_btn_3 = findViewById(R.id.btn_touristBooking);
        tv_btn_4 = findViewById(R.id.btnSchoolBus_booking);

        tv_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Services_Categories.this,MainActivity.class);
                startActivity(intent);
            }
        });
        tv_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Services_Categories.this,MainActivity.class);
                startActivity(intent);
            }
        });
        tv_btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Services_Categories.this,MainActivity.class);
                startActivity(intent);
            }
        });
        tv_btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Services_Categories.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}