package com.domnich.vlad.moneycontrol.controller;

import android.app.DatePickerDialog;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import com.domnich.vlad.moneycontrol.R;

public class MoneyActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edTextDate;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        edTextDate = (EditText) findViewById(R.id.edTextDate);
        setCurrentDate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
