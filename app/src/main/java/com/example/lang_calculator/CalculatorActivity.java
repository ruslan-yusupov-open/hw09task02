package com.example.lang_calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    TextView text;
    Button but1;
    Button but2;
    Button but3;
    Button but4;
    Button but5;
    Button but6;
    Button but7;
    Button but8;
    Button but9;
    Button but0;
    Button butC;

    Button butPlus;
    Button butMinus;
    Button butDiv;
    Button butMult;
    Button butEq;

    Button butPoint;

    Double num = 0.0;
    Double numM = 0.0;
    Boolean pointMode = false;
    Integer digitsAfterPoint = 1;

    String currentAction = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        text = findViewById(R.id.main_result);
        but1 = findViewById(R.id.but1);
        but2 = findViewById(R.id.but2);
        but3 = findViewById(R.id.but3);
        but4 = findViewById(R.id.but4);
        but5 = findViewById(R.id.but5);
        but6 = findViewById(R.id.but6);
        but7 = findViewById(R.id.but7);
        but8 = findViewById(R.id.but8);
        but9 = findViewById(R.id.but9);
        but0 = findViewById(R.id.but0);
        butPoint = findViewById(R.id.but_point);

        butC = findViewById(R.id.but_c);
        butPlus = findViewById(R.id.but_plus);
        butMinus = findViewById(R.id.but_minus);
        butDiv = findViewById(R.id.but_divide);
        butMult = findViewById(R.id.but_mult);
        butEq = findViewById(R.id.but_eq);

        but1.setOnClickListener(view -> numClick(1));
        but2.setOnClickListener(view -> numClick(2));
        but3.setOnClickListener(view -> numClick(3));
        but4.setOnClickListener(view -> numClick(4));
        but5.setOnClickListener(view -> numClick(5));
        but6.setOnClickListener(view -> numClick(6));
        but7.setOnClickListener(view -> numClick(7));
        but8.setOnClickListener(view -> numClick(8));
        but9.setOnClickListener(view -> numClick(9));
        but0.setOnClickListener(view -> numClick(0));
        butPoint.setOnClickListener(view -> pointMode = true);

        butC.setOnClickListener(view -> {
            num = 0.0;
            text.setText(num.toString());
        });

        butPlus.setOnClickListener(view -> actionClick("plus"));
        butMinus.setOnClickListener(view -> actionClick("minus"));
        butDiv.setOnClickListener(view -> actionClick("div"));
        butMult.setOnClickListener(view -> actionClick("mult"));
        butEq.setOnClickListener(view -> actionClick("eq"));
    }

    private void actionClick(String action) {
        if ("plus".equals(currentAction)) {
            num = numM + num;
        }
        if ("minus".equals(currentAction)) {
            num = numM - num;
        }
        if ("mult".equals(currentAction)) {
            num = numM * num;
        }
        if ("div".equals(currentAction)) {
            if (num != 0) {
                num = numM / num;
            } else {
                Toast.makeText(CalculatorActivity.this, "на ноль не делят", Toast.LENGTH_LONG).show();
            }
        }

        if (!("eq".equals(action))) {
            numM = num;
            num = 0.0;
        }

        currentAction = action;

        text.setText(num.toString());
    }

    private void numClick(int value) {
        if (pointMode) {
            num = num + value / (Math.pow(10, digitsAfterPoint++));
        } else {
            num = num * 10 + value;
        }

        text.setText(num.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_main) {
            Intent intentNotes = new Intent(CalculatorActivity.this, MainActivity.class);
            startActivity(intentNotes);
            finish();
            return true;
        }

        if (id == R.id.action_open_notes) {
            Intent intentNotes = new Intent(CalculatorActivity.this, EditTextActivity.class);
            startActivity(intentNotes);
            finish();
            return true;
        }

        if (id == R.id.action_open_settings) {
            Intent intentNotes = new Intent(CalculatorActivity.this, SettingsActivity.class);
            startActivity(intentNotes);
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
