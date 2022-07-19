package com.example.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginBtn;
    TextView txtRegister;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.textRegister);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mUsername = loginUsername.getText().toString();
                String mPassword = loginPassword.getText().toString();

                if(!mUsername.isEmpty()){
                    loginUsername.setError(null);
                    if(!mPassword.isEmpty()){
                        loginPassword.setError(null);

                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference("UserInfo");

                        Query check_email = databaseReference.orderByChild("user_Username").equalTo(mUsername);
                        check_email.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    loginUsername.setError(null);
                                    String usernameCheck =snapshot.child(mUsername).child("user_Username").getValue(String.class);
                                    String passwordCheck =snapshot.child(mUsername).child("user_Password").getValue(String.class);
                                    if(passwordCheck.equals(mPassword)){
                                        loginPassword.setError(null);
                                        Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        intent.putExtra("message_key",usernameCheck);
                                        startActivity(intent);

                                    } else {
                                        loginPassword.setError("Incorrect Password");
                                    }
                                } else {
                                    loginUsername.setError("Username does not exists");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                    else{
                        loginPassword.setError("Please Enter the Password");
                    }
                }
                else{
                    loginUsername.setError("Please enter the Username");
                }
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}