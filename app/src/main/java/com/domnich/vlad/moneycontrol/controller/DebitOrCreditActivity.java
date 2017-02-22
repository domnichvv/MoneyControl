package com.domnich.vlad.moneycontrol.controller;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.domnich.vlad.moneycontrol.R;

import java.util.Calendar;

public class DebitOrCreditActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edTextDate, edTextSum, edTextValue, edTextCurrency, edTextCommentary;
    private Button buttonAdd;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit_credit);

        edTextDate = (EditText) findViewById(R.id.edTextDate);
        edTextSum = (EditText) findViewById(R.id.edTextSum);
        edTextValue = (EditText) findViewById(R.id.edTextValue);
        edTextCurrency = (EditText) findViewById(R.id.edTextCurrency);
        edTextCommentary = (EditText) findViewById(R.id.edTextCommentary);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(this);

        String var_debit = getIntent().getStringExtra("Debit");
        String var_credit = getIntent().getStringExtra("Credit");

        if(var_debit == null){
            edTextValue.setText(var_credit);
        } else {
            edTextValue.setText(var_debit);
        }

        setCurrentDate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAdd:

                break;
            case R.id.edTextDate:
                //отображаем диалоговое окно для выбора даты
                new DatePickerDialog(this, dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH))
                        .show();
                break;
        }
    }

    //установка начальной даты
    private void setCurrentDate(){
        edTextDate.setText(DateUtils.formatDateTime(this, calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR ));
    }

    //установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setCurrentDate();
        }
    };
}
