package com.example.swat_riders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class Welcome_Activity extends AppCompatActivity {
MaterialButton mbutton_login, mbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mbutton_login =findViewById(R.id.btn_to_SignIN_Page);
        mbutton =findViewById(R.id.btn_Register_to_registerPage);

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome_Activity.this,Sign_up_activity.class);
                startActivity(intent);
            }
        });
        mbutton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(Welcome_Activity.this,Sign_In_Activity.class);
            startActivity(intent);
            }
        });
    }
}