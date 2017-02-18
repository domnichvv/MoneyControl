package com.domnich.vlad.moneycontrol.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.domnich.vlad.moneycontrol.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText edTextLogin, edTextPass, edTextRepeatPass;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edTextLogin = (EditText) findViewById(R.id.edTextLogin);
        edTextPass = (EditText) findViewById(R.id.edTextPass);
        edTextRepeatPass = (EditText) findViewById(R.id.edTextRepeatPass);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("login", edTextLogin.getText().toString());
                intent.putExtra("pass", edTextPass.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
