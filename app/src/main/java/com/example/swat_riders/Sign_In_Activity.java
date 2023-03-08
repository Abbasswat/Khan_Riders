package com.example.swat_riders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
//copy library

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.swat_riders.databinding.ActivitySignInBinding;
import com.example.swat_riders.databinding.ActivitySignUpBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Sign_In_Activity extends AppCompatActivity {

    EditText Et_number, Et_password;
    MaterialButton mbutton;
    TextView btn_ForgotPassword, btn_SignUp;
    ActivitySignInBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(Sign_In_Activity.this);
        progressDialog.setTitle("Login ");
        progressDialog.setMessage("Login to your account ");

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                auth.signInWithEmailAndPassword(binding.editTextPhone.getText().toString(),binding.editTextpassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(Sign_In_Activity.this,MainActivity.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(Sign_In_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

        Et_number =findViewById(R.id.editTextPhone);
        Et_password =findViewById(R.id.editTextpassword);
        mbutton =findViewById(R.id.btn_Login);
        btn_ForgotPassword =findViewById(R.id.forgotPassword);
        btn_SignUp =findViewById(R.id.go_to_signUP);


        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign_In_Activity.this,Sign_up_activity.class);
                startActivity(intent);
            }
        });




    }
}