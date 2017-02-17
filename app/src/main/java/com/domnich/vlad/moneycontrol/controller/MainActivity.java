package com.domnich.vlad.moneycontrol.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.domnich.vlad.moneycontrol.R;
import com.domnich.vlad.moneycontrol.SignUpActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edTextLogin, edTextPass;
    private Button buttonSignIn, buttonSignUp;

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        edTextLogin = (EditText) findViewById(R.id.edTextLogin);
        edTextPass = (EditText) findViewById(R.id.edTextPass);

        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);

        buttonSignIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        String login = edTextLogin.getText().toString();
        String password = edTextPass.getText().toString();

        switch (v.getId()){
            case R.id.buttonSignIn:

                break;
            case R.id.buttonSignUp:
                intent = new Intent(this, SignUpActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            String login = data.getStringExtra("login");
            edTextLogin.setText(login);

        }
    }
}