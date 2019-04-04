package com.example.lang_calculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    Spinner spinner_lang;
    Spinner spinner_margin;

    private boolean userIsInteracting;

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        userIsInteracting = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);

        setContentView(R.layout.activity_settings);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        spinner_lang = findViewById(R.id.spinner_lang);
        spinner_margin = findViewById(R.id.spinner_margin);

        initSpinner();
    }

    private void initSpinner() {
        Locale current = getResources().getConfiguration().locale;

        // spinner_lang.setSelected(false);  // must
        spinner_lang.setSelection("en".equals(current.toString()) ? 1 : 0);

        spinner_margin.setSelection(Utils.getsTheme());

        spinner_lang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                langSpinnerSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner_margin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                colorSpinnerSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void langSpinnerSelected(int position) {
        switch (position) {
            case 0: {
                if (userIsInteracting) {
                    Toast.makeText(
                            SettingsActivity.this,
                            "русский язык выбран",
                            Toast.LENGTH_LONG)
                            .show();

                    Locale locale = new Locale("ru");
                    Configuration config = new Configuration();
                    config.setLocale(locale);
                    getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    recreate();
                }

                break;
            }
            case 1: {
                if (userIsInteracting) {
                    Toast.makeText(
                            SettingsActivity.this,
                            "english selected",
                            Toast.LENGTH_LONG)
                            .show();

                    Locale locale = new Locale("en");
                    Configuration config = new Configuration();
                    config.setLocale(locale);
                    getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    recreate();
                }

                break;
            }
        }
    }

    private void colorSpinnerSelected(int position) {
        switch (position) {
            case 0: {
                if (userIsInteracting) {
                    Toast.makeText(
                            SettingsActivity.this,
                            "малый отступ выбран",
                            Toast.LENGTH_LONG)
                            .show();
                    Utils.changeToTheme(this, Utils.THEME_SMALL);
                }

                break;
            }
            case 1: {
                if (userIsInteracting) {
                    Toast.makeText(
                            SettingsActivity.this,
                            "средний отступ выбран",
                            Toast.LENGTH_LONG)
                            .show();

                    Utils.changeToTheme(this, Utils.THEME_AVG);
                }

                break;
            }
            case 2: {
                if (userIsInteracting) {
                    Toast.makeText(
                            SettingsActivity.this,
                            "большой отступ выбран",
                            Toast.LENGTH_LONG)
                            .show();

                    Utils.changeToTheme(this, Utils.THEME_BIG);
                }

                break;
            }
        }
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

        if (id == R.id.action_open_notes) {
            Intent intentNotes = new Intent(SettingsActivity.this, EditTextActivity.class);
            startActivity(intentNotes);
            finish();
            return true;
        }

        if (id == R.id.action_open_calculator) {
            Intent intentNotes = new Intent(SettingsActivity.this, CalculatorActivity.class);
            startActivity(intentNotes);
            finish();
            return true;
        }

        if (id == R.id.action_main) {
            Intent intentNotes = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intentNotes);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}