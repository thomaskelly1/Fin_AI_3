package com.example.fin_ai_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference users;

    EditText username, email, fname, lname, password, phone ;
    Button signup_button, login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("clients");

        username = findViewById(R.id.editUsername);
        email = findViewById(R.id.editEmail);
        fname = findViewById(R.id.editfname);
        lname = findViewById(R.id.editLname);
        password = findViewById(R.id.editPassword);
        phone = findViewById(R.id.editPhone);

        signup_button = findViewById(R.id.signupButton);
        login_button = findViewById(R.id.login_activity_Button);


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(SecondActivity.this, Login_Activity.class);
                startActivity(intent);
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User(username.getText().toString(),email.getText().toString(),fname.getText().toString(),
                        lname.getText().toString(),password.getText().toString(),phone.getText().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(user.getUsername()).exists()){
                            Toast.makeText(SecondActivity.this,"Username already used",Toast.LENGTH_SHORT).show();
                        }else{
                            users.child(user.getUsername()).setValue(user);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}