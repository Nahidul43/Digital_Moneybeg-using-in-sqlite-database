package com.bongoacademy.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddData extends AppCompatActivity {

    TextView tvTitle;
    EditText edAmount,edreason;
    Button button;
    OpenDataBaseHelper DBHelper;
    public static boolean EXPENSE=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        tvTitle=findViewById(R.id.tvTitle);
        edAmount=findViewById(R.id.edAmount);
        edreason=findViewById(R.id.edreason);
        button=findViewById(R.id.button);
        DBHelper=new OpenDataBaseHelper(this);

        if(AddData.EXPENSE)tvTitle.setText("Add Expense");

        button.setOnClickListener(view -> {
            String sAmount=edAmount.getText().toString();
            String sReason=edreason.getText().toString();
            Double Amount=Double.parseDouble(sAmount);

            if(AddData.EXPENSE){
                DBHelper.addExpense(Amount,sReason);
                tvTitle.setText("Expense Added!");
            }else{
                DBHelper.addincome(Amount,sReason);
                tvTitle.setText("Income Added");
            }


        });

    }
}