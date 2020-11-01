package com.example.fin_ai_3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.view.View;

import android.widget.Button;
import android.os.Bundle;

public class loan_officer_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_officer_details);

        Button button = (Button) findViewById(R.id.signupButton);

        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                goToSecondActivity();

            }

        });
    }

    private void goToSecondActivity() {

        Intent intent = new Intent(this, Login_Activity.class);

        startActivity(intent);

    }




}