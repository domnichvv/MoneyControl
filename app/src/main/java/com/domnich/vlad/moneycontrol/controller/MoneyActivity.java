package com.domnich.vlad.moneycontrol.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.domnich.vlad.moneycontrol.R;

public class MoneyActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton imButtonAddDebit, imButtonAddCredit;
    private static final String PUT_DEBIT = "Debit";
    private static final String PUT_CREDIT = "Credit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        imButtonAddDebit = (ImageButton) findViewById(R.id.imButtonAddDebit);
        imButtonAddCredit = (ImageButton) findViewById(R.id.imButtonAddCredit);

        imButtonAddDebit.setOnClickListener(this);
        imButtonAddCredit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.imButtonAddDebit:
                intent = new Intent(this, DebitOrCreditActivity.class);
                intent.putExtra(PUT_DEBIT, "Debit");
                startActivity(intent);
                break;
            case R.id.imButtonAddCredit:
                intent = new Intent(this, DebitOrCreditActivity.class);
                intent.putExtra(PUT_CREDIT, "Credit");
                startActivity(intent);
                break;
        }
    }

}
