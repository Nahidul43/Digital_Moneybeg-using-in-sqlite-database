package com.bongoacademy.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    TextView tvFinalBalance,tvTotalExpenses,tvAddExpense,tvShowAlldataExpenses,tvTotalIncome,tvAddincomeData,tvShowAlldataincome;

    OpenDataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);



        tvFinalBalance=findViewById(R.id.tvFinalBalance);
        tvTotalExpenses=findViewById(R.id.tvTotalExpenses);
        tvAddExpense=findViewById(R.id.tvAddExpense);
        tvShowAlldataExpenses=findViewById(R.id.tvShowAlldataExpenses);
        tvTotalIncome=findViewById(R.id.tvTotalIncome);
        tvAddincomeData=findViewById(R.id.tvAddincomeData);
        tvShowAlldataincome=findViewById(R.id.tvShowAlldataincome);
        helper=new OpenDataBaseHelper(this);



        tvAddExpense.setOnClickListener(view -> {
            AddData.EXPENSE=true;
            startActivity(new Intent(MainActivity.this,AddData.class));
        });

        tvAddincomeData.setOnClickListener(view -> {
            AddData.EXPENSE=false;
            startActivity(new Intent(MainActivity.this,AddData.class));
        });



        tvShowAlldataExpenses.setOnClickListener(view -> {
            ShowData.EXPENSE=true;
            startActivity(new Intent(MainActivity.this, ShowData.class));

        });
        tvShowAlldataincome.setOnClickListener(view -> {
            ShowData.EXPENSE=false;
            startActivity(new Intent(MainActivity.this, ShowData.class));

        });





        upDateUI();







    }


    //===================================================================
    public void upDateUI(){
        tvTotalExpenses.setText("BDT "+helper.CalculateTotalExpense());
        tvTotalIncome.setText("BDT "+helper.CalculateTotalIncome());
        double finalResult=helper.CalculateTotalIncome()-helper.CalculateTotalExpense();
        tvFinalBalance.setText("BDT "+finalResult);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        upDateUI();

    }
}