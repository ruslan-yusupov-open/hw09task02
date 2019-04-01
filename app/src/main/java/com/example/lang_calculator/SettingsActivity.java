package com.example.lang_calculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private boolean userIsInteracting;

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        userIsInteracting = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        spinner_lang = findViewById(R.id.spinner_lang);

        initSpinner();
    }

    private void initSpinner() {
        Locale current = getResources().getConfiguration().locale;

        // spinner_lang.setSelected(false);  // must
        spinner_lang.setSelection("en".equals(current.toString()) ? 1 : 0);

        spinner_lang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
