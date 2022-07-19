package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEdt, usernameEdt, emailEdt, phoneEdt, passwordEdt;
    private Button registerBtn;
    private TextView loginText, backText;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEdt = findViewById(R.id.regName);
        usernameEdt = findViewById(R.id.regUsername);
        emailEdt = findViewById(R.id.regEmail);
        phoneEdt = findViewById(R.id.regPhone);
        passwordEdt = findViewById(R.id.regPassword);

        registerBtn = findViewById(R.id.btnSignUp);

        loginText = findViewById(R.id.textLogin);
        backText = findViewById(R.id.backText);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserInfo");

        userInfo = new UserInfo();

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameEdt.getText().toString();
                String username = usernameEdt.getText().toString();
                String email = emailEdt.getText().toString();
                String phone = phoneEdt.getText().toString();
                String password = passwordEdt.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    addDataToFirebase(name, username, email, phone, password);
                }
            }
        });
    }

    private void addDataToFirebase(String name, String username, String email, String phone, String password) {

        userInfo.setUser_FullName(name);
        userInfo.setUser_Username(username);
        userInfo.setUser_Email(email);
        userInfo.setUser_Phone(phone);
        userInfo.setUser_Password(password);

        DatabaseReference newRef = databaseReference.child(username);
        newRef.setValue(userInfo);
        Toast.makeText(getApplicationContext(),"Register Successfully",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("message_key",username);
        startActivity(intent);
    }
}