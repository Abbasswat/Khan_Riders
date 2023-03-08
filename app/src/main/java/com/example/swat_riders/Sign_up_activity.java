package com.example.swat_riders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.swat_riders.Model.User;
import com.example.swat_riders.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_up_activity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    MaterialButton btnRegister;



    // Changes has done by Abbas khan
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //getSupportActionBar().hide();
        // auth is used for sign up through email and password
        auth = FirebaseAuth.getInstance();

        //and database is used for saving the data in databse which is written in the Edit Text
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(Sign_up_activity.this);
        progressDialog.setTitle("Creating account");
        progressDialog.setMessage("we are creating your account ");


        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.createUserWithEmailAndPassword(binding.editTextEmail.getText().toString(),binding.editTextpassword1.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()){
                         User user =new User(binding.editTextname.getText().toString(), binding.editTexFname.getText().toString(),binding.Address.getText().toString(),binding.editTextPhone1.getText().toString(),binding.editTextEmail.getText().toString(),binding.editTextpassword1.getText().toString());
                         String id = task.getResult().getUser().getUid();
                         database.getReference().child("User").child(id).setValue(user);

                         Toast.makeText(Sign_up_activity.this, "user Created successfully", Toast.LENGTH_SHORT).show();
                     }
                     else {
                         Toast.makeText(Sign_up_activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                     }
                    }
                });
            }
        });


    }
}