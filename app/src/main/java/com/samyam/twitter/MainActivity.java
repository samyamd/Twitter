package com.samyam.twitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView account, login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = findViewById(R.id.textView2);
        login = findViewById(R.id.login);
        register = findViewById(R.id.btnRegister);

        account.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);

            case R.id.textView2:
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);

            case R.id.btnRegister:
                Intent intent2 = new Intent(this, RegisterActivity.class);
                startActivity(intent2);
        }
    }
}
