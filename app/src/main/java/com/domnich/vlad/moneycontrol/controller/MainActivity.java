package com.domnich.vlad.moneycontrol.controller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.domnich.vlad.moneycontrol.R;
import com.domnich.vlad.moneycontrol.model.database.DBHelper;
import com.domnich.vlad.moneycontrol.model.entities.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edTextLogin, edTextPass;
    private Button buttonSignIn, buttonSignUp;

    private static final int REQUEST_CODE = 1;

    User user;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTextLogin = (EditText) findViewById(R.id.edTextLogin);
        edTextPass = (EditText) findViewById(R.id.edTextPass);

        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);

        buttonSignIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        String login = edTextLogin.getText().toString();
        String password = edTextPass.getText().toString();

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        switch (v.getId()){
            case R.id.buttonSignIn:
                //checking fields empty or not
                if(login.equals("") || password.equals("")){
                    Toast.makeText(this, R.string.toast_empty_fields, Toast.LENGTH_SHORT).show();
                } else {
                    Cursor cursor = database.query(DBHelper.TABLE_USERS, null, null, null, null, null, null);

                    if(cursor.moveToFirst()){
                        int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                        int loginIndex = cursor.getColumnIndex(DBHelper.KEY_LOGIN);
                        int passIndex = cursor.getColumnIndex(DBHelper.KEY_PASS);
                        do{
                            if(login.equals(cursor.getString(loginIndex)) & password.equals(cursor.getString(passIndex))){
                                user = new User(cursor.getInt(idIndex), login, password);
                                Toast.makeText(this, R.string.toast_success_sign_in, Toast.LENGTH_SHORT).show();
                                intent = new Intent(this, MoneyActivity.class);
                                startActivity(intent);
                            } else if(cursor.isLast()){
                                Toast.makeText(this, R.string.toast_wrong_sign_in, Toast.LENGTH_SHORT).show();
                            }
                        } while (cursor.moveToNext());
                    } else {
                        Toast.makeText(this, R.string.toast_wrong_sign_in, Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                    dbHelper.close();
                }
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

        if(requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String login = data.getStringExtra("login");
                edTextLogin.setText(login);
                String pass = data.getStringExtra("pass");
                edTextPass.setText(pass);
            }
        }
    }
}
