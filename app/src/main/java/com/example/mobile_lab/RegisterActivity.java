package com.example.mobile_lab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText edtFName, edtPhone, edtUName, edtPassword;
    Button btnReg;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AppCompatActivity activity = (AppCompatActivity) RegisterActivity.this;
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().hide();
        }

        edtFName = findViewById(R.id.fName);
        edtPhone = findViewById(R.id.phone);
        edtUName = findViewById(R.id.uName);
        edtPassword = findViewById(R.id.password);
        btnReg = findViewById(R.id.btnRegister);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = edtFName.getText().toString();
                String phone = edtPhone.getText().toString();
                String uName = edtUName.getText().toString();
                String password = edtPassword.getText().toString();

                if (fName.isEmpty() || phone.isEmpty() || uName.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Password length should be greater than or equal to 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Register(fName, phone, uName, password);
                }

            }
        });
    }


    public void Register(final String fName, final String phoneNum, final String uName, final String pWord){
        firebaseAuth.createUserWithEmailAndPassword(uName, pWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    String userUid = user.getUid();
                    HashMap<Object, String> Users= new HashMap<>();
                    Users.put("username", uName);
                    Users.put("password", pWord);
                    Users.put("ID", userUid);
                    Users.put("fullname", fName);
                    Users.put("phonenumber", phoneNum);
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference ref = db.getReference("User");
                    ref.child(userUid).setValue(Users);

                    Toast.makeText(RegisterActivity.this, "Sign up successfully!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Authentication failed!", Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "An error has occured!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
