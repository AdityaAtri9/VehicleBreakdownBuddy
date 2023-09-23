package com.example.vehiclebreakdownbuddy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vehiclebreakdownbuddy.Model.Users;
import com.example.vehiclebreakdownbuddy.databinding.ActivitySginupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class sginupActivity extends AppCompatActivity {

    ActivitySginupBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySginupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("Please wait while we create your account");
        progressDialog.setCancelable(false); // Prevent dismissing by tapping outside

        binding.Signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.signupName.getText().toString().isEmpty() &&
                        !binding.signupemail.getText().toString().isEmpty() &&
                        !binding.signuppassword.getText().toString().isEmpty()) {
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(
                                    binding.signupemail.getText().toString(),
                                    binding.signuppassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {
                                        Users users = new Users(
                                                binding.signupName.getText().toString(),
                                                binding.signupemail.getText().toString(),
                                                binding.signuppassword.getText().toString());
                                        String id = task.getResult().getUser().getUid();
                                        database.getReference().child("Users").child(id).setValue(users);
                                        Toast.makeText(sginupActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(sginupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(sginupActivity.this, "Enter Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sginupActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });
    }
}
