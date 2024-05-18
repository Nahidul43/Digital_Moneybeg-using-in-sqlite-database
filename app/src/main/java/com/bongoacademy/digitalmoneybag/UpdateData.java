package com.bongoacademy.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {

    TextView tvTitle;
    EditText edAmount, edreason;
    Button button;
    OpenDataBaseHelper DBHelper;
    public static boolean EXPENSE = true;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        tvTitle = findViewById(R.id.tvTitle);
        edAmount = findViewById(R.id.edAmount);
        edreason = findViewById(R.id.edreason);
        button = findViewById(R.id.button);
        DBHelper = new OpenDataBaseHelper(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");

        int i = Integer.parseInt(id);

        button.setOnClickListener(view -> {
            String amountString = edAmount.getText().toString();
            String reason = edreason.getText().toString();

            if (!amountString.isEmpty() && !reason.isEmpty()) {
                double upAmount = Double.parseDouble(amountString);
                if (EXPENSE) {
                    DBHelper.updateExpense(i, upAmount, reason);
                    startActivity(new Intent(UpdateData.this,ShowData.class));
                } else {
                    DBHelper.updateIncome(i, upAmount, reason);
                    startActivity(new Intent(UpdateData.this,ShowData.class));
                }
            } else {
                Toast.makeText(UpdateData.this, "Please enter both amount and reason", Toast.LENGTH_LONG).show();
            }
        });
    }
}
