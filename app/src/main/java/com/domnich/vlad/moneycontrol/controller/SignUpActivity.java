package com.domnich.vlad.moneycontrol.controller;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.domnich.vlad.moneycontrol.R;
import com.domnich.vlad.moneycontrol.model.database.DBHelper;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edTextLogin, edTextPass, edTextRepeatPass;
    private Button buttonSignUp;

    private static final String PUT_LOGIN = "login";
    private static final String PU_PASS = "pass";

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edTextLogin = (EditText) findViewById(R.id.edTextLogin);
        edTextPass = (EditText) findViewById(R.id.edTextPass);
        edTextRepeatPass = (EditText) findViewById(R.id.edTextRepeatPass);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);

        dbHelper = new DBHelper(this);

        buttonSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String login = edTextLogin.getText().toString();
        String password = edTextPass.getText().toString();
        String repeatPass = edTextRepeatPass.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        if(login.equals("") || password.equals("") || repeatPass.equals("")) {
            Toast.makeText(this, R.string.toast_empty_fields, Toast.LENGTH_SHORT).show();
        } else if(!password.equals(repeatPass)){
            Toast.makeText(this, R.string.toast_different_pass, Toast.LENGTH_SHORT).show();
        } else {
            contentValues.put(DBHelper.KEY_LOGIN, login);
            contentValues.put(DBHelper.KEY_PASS, password);

            database.insert(DBHelper.TABLE_USERS, null, contentValues);

            Toast.makeText(this, R.string.toast_success_sign_up, Toast.LENGTH_SHORT).show();

            dbHelper.close();

            Intent intent = new Intent();
            intent.putExtra(PUT_LOGIN, login);
            intent.putExtra(PU_PASS, password);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
