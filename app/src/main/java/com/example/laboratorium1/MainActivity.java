package com.example.laboratorium1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean validate(EditText poleTekstowe, String pole){
        if (poleTekstowe.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.puste_pole, pole), Toast.LENGTH_SHORT).show();
            poleTekstowe.setError(getString(R.string.puste_pole, pole));
            return  false;
        }
        else if (pole.equals(getString(R.string.liczba_ocen))) {
            try {
                int liczbaOcen = Integer.parseInt(poleTekstowe.getText().toString().trim());

                if (liczbaOcen < 5 || liczbaOcen > 15) {
                    Toast.makeText(getApplicationContext(), getString(R.string.zla_liczba_ocen), Toast.LENGTH_SHORT).show();
                    poleTekstowe.setError(getString(R.string.zla_liczba_ocen));
                    return false;
                }
                else return true;
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), getString(R.string.zle_dane), Toast.LENGTH_SHORT).show();
                poleTekstowe.setError(getString(R.string.zle_dane));
                return false;
            }
        }
        else if (pole.equals(getString(R.string.imie))) {
            String regex = "^[A-ZŻŹŃŁÓĘĄĆŚ][a-zżźńłóąęćś]*$";
            if (!poleTekstowe.getText().toString().trim().matches(regex)){
                Toast.makeText(getApplicationContext(), getString(R.string.zle_dane), Toast.LENGTH_SHORT).show();
                poleTekstowe.setError(getString(R.string.zle_dane));
                return false;
            }
            else return true;
        }
        else if (pole.equals(getString(R.string.nazwisko))) {
            String regex = "^[A-ZŻŹŃŁÓĘĄĆŚ][a-zżźńłóąęćś]*$";
            String regex2 = "^[A-ZŻŹŃŁÓĘĄĆŚ][a-zżźńłóąęćś]*[-][A-ZŻŹŃŁÓĘĄĆŚ][a-zżźńłóąęćś]*$";
            if (!poleTekstowe.getText().toString().trim().matches(regex) && !poleTekstowe.getText().toString().trim().matches(regex2)){
                Toast.makeText(getApplicationContext(), getString(R.string.zle_dane), Toast.LENGTH_SHORT).show();
                poleTekstowe.setError(getString(R.string.zle_dane));
                return false;
            }
            else return true;
        }
        else return true;
    }
    private boolean validate2(EditText poleTekstowe, String pole){
        if (poleTekstowe.getText().toString().isEmpty()) {
            return  false;
        }
        else if (pole.equals(getString(R.string.liczba_ocen))) {
            try {
                int liczbaOcen = Integer.parseInt(poleTekstowe.getText().toString().trim());

                if (liczbaOcen < 5 || liczbaOcen > 15) {
                    return false;
                }
                else return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        else if (pole.equals(getString(R.string.imie))) {
            String regex = "^[A-ZŻŹŃŁÓĘĄĆŚ][a-zżźńłóąęćś]*$";
            if (!poleTekstowe.getText().toString().trim().matches(regex)){
                return false;
            }
            else return true;
        }
        else if (pole.equals(getString(R.string.nazwisko))) {
            String regex = "^[A-ZŻŹŃŁÓĘĄĆŚ][a-zżźńłóąęćś]*$";
            String regex2 = "^[A-ZŻŹŃŁÓĘĄĆŚ][a-zżźńłóąęćś]*[-][A-ZŻŹŃŁÓĘĄĆŚ][a-zżźńłóąęćś]*$";
            if (!poleTekstowe.getText().toString().trim().matches(regex) && !poleTekstowe.getText().toString().trim().matches(regex2)){
                return false;
            }
            else return true;
        }
        else return true;
    }
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boolean allFieldsValid = checkFieldsValidaty();
            if (allFieldsValid) {
                button.setVisibility(View.VISIBLE);
            } else {
                button.setVisibility(View.GONE);
            }
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };
    private boolean checkFieldsValidaty() {
        return validate2(poleTekstowe, getString(R.string.imie)) && validate2(poleTekstowe2, getString(R.string.nazwisko)) && validate2(poleTekstowe3, getString(R.string.liczba_ocen));
    }
    EditText tv, tv2, tv3;
    Button button, przyciskKoniec;
    TextView tvSrednia;
    boolean buttonVisibility;
    EditText poleTekstowe, poleTekstowe2, poleTekstowe3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        poleTekstowe = findViewById(R.id.podanieImienia);
        poleTekstowe2 = findViewById(R.id.podanieNazwiska);
        poleTekstowe3 = findViewById(R.id.podanieLiczbyOcen);
        poleTekstowe.addTextChangedListener(watcher);
        poleTekstowe2.addTextChangedListener(watcher);
        poleTekstowe3.addTextChangedListener(watcher);
        poleTekstowe.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    validate(poleTekstowe, getString(R.string.imie));
                }
            }
        });
        poleTekstowe2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    validate(poleTekstowe2, getString(R.string.nazwisko));
                }
            }
        });
        poleTekstowe3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    validate(poleTekstowe3, getString(R.string.liczba_ocen));
                }
            }
        });
        tv = findViewById(R.id.podanieImienia);
        tv2 = findViewById(R.id.podanieNazwiska);
        tv3 = findViewById(R.id.podanieLiczbyOcen);
        button = findViewById(R.id.przyciskOceny);
        przyciskKoniec = findViewById(R.id.przyciskKoniec);
        tvSrednia = findViewById(R.id.srednia);
        if(savedInstanceState != null) {
            tv.setText(savedInstanceState.getString(getString(R.string.imie)));
            tv2.setText(savedInstanceState.getString(getString(R.string.nazwisko)));
            tv3.setText(savedInstanceState.getString(getString(R.string.liczba_ocen)));
            tvSrednia.setText(savedInstanceState.getString("srednia"));
            przyciskKoniec.setText(savedInstanceState.getString("przycisk"));
            buttonVisibility = savedInstanceState.getBoolean("przyciskOceny_visibility");
            button.setVisibility(buttonVisibility ? View.VISIBLE : View.INVISIBLE);
            buttonVisibility = savedInstanceState.getBoolean("przyciskKoniec_visibility");
            przyciskKoniec.setVisibility(buttonVisibility ? View.VISIBLE : View.INVISIBLE);
            buttonVisibility = savedInstanceState.getBoolean("srednia_visibility");
            tvSrednia.setVisibility(buttonVisibility ? View.VISIBLE : View.INVISIBLE);
        }
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intencja=new Intent(MainActivity.this, SecondActivity.class);
                intencja.putExtra("liczbaOcen", Integer.parseInt(tv3.getText().toString().trim()));
                startActivityForResult(intencja, 1);
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(getString(R.string.imie), tv.getText().toString());
        outState.putString(getString(R.string.nazwisko), tv2.getText().toString());
        outState.putString(getString(R.string.liczba_ocen), tv3.getText().toString());
        outState.putString("srednia", tvSrednia.getText().toString());
        outState.putString("przycisk", przyciskKoniec.getText().toString());
        outState.putBoolean("przyciskOceny_visibility", button.getVisibility() == View.VISIBLE);
        outState.putBoolean("przyciskKoniec_visibility", przyciskKoniec.getVisibility() == View.VISIBLE);
        outState.putBoolean("srednia_visibility", tvSrednia.getVisibility() == View.VISIBLE);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tv.setText(savedInstanceState.getString(getString(R.string.imie)));
        tv2.setText(savedInstanceState.getString(getString(R.string.nazwisko)));
        tv3.setText(savedInstanceState.getString(getString(R.string.liczba_ocen)));
        tvSrednia.setText(savedInstanceState.getString("srednia"));
        przyciskKoniec.setText(savedInstanceState.getString("przycisk"));
        buttonVisibility = savedInstanceState.getBoolean("przyciskOceny_visibility");
        button.setVisibility(buttonVisibility ? View.VISIBLE : View.INVISIBLE);
        buttonVisibility = savedInstanceState.getBoolean("przyciskKoniec_visibility");
        przyciskKoniec.setVisibility(buttonVisibility ? View.VISIBLE : View.INVISIBLE);
        buttonVisibility = savedInstanceState.getBoolean("srednia_visibility");
        tvSrednia.setVisibility(buttonVisibility ? View.VISIBLE : View.INVISIBLE);
    }
    private void updateTextAndButtonVisibility(float srednia) {
        tvSrednia = findViewById(R.id.srednia);
        przyciskKoniec = findViewById(R.id.przyciskKoniec);
        if (srednia>=2 && srednia<=5){
            tvSrednia.setVisibility(View.VISIBLE);
            tvSrednia.setText(getString(R.string.srednia, srednia));
            if(srednia>3) przyciskKoniec.setText(getString(R.string.button_win));
            else przyciskKoniec.setText(getString(R.string.button_lose));
            przyciskKoniec.setVisibility(View.VISIBLE);
        }
        else {
            tvSrednia.setVisibility(View.GONE);
            przyciskKoniec.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onActivityResult(int kodZadania, int kodZakonczenia, Intent intent) {
        super.onActivityResult(kodZadania,kodZakonczenia,intent);
        float srednia = 0;
        if(kodZakonczenia == RESULT_OK && kodZadania==1) {
            Bundle pakunek = intent.getExtras();
            srednia = pakunek.getFloat("srednia");
        }
        updateTextAndButtonVisibility(srednia);
        przyciskKoniec = findViewById(R.id.przyciskKoniec);
        przyciskKoniec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buttonText = (String) przyciskKoniec.getText();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Komunikat");
                if (buttonText.equals(getString(R.string.button_win))) {
                    builder.setMessage(getString(R.string.info_win));
                } else {
                    builder.setMessage(getString(R.string.info_lose));
                }
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                });
                builder.show();
            }
        });
    }
}
