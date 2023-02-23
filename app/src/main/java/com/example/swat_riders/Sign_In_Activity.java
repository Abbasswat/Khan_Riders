package com.example.swat_riders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Sign_In_Activity extends AppCompatActivity {

    EditText Et_number, Et_password;
    MaterialButton mbutton;
    TextView btn_ForgotPassword, btn_SignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Et_number =findViewById(R.id.editTextPhone);
        Et_password =findViewById(R.id.editTextpassword);
        mbutton =findViewById(R.id.btn_Login);
        btn_ForgotPassword =findViewById(R.id.forgotPassword);
        btn_SignUp =findViewById(R.id.go_to_signUP);

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign_In_Activity.this,Services_Categories.class);
                startActivity(intent);
            }
        });
        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign_In_Activity.this,Sign_up_activity.class);
                startActivity(intent);
            }
        });




    }
}