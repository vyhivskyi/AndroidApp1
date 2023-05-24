package com.example.laboratorium1;

import static android.app.Activity.RESULT_OK;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {

    private TextView mTextViewOcena;
    Button button;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Bundle pakunek = intent.getExtras();
        int liczbaOcen = pakunek.getInt("liczbaOcen");
        String[] nazwyPrzedmiotow = getResources().getStringArray(R.array.przedmioty);

        InteraktywnyAdapterTablicy adapter = new InteraktywnyAdapterTablicy(this, liczbaOcen, nazwyPrzedmiotow);
        RecyclerView recyclerView = findViewById(R.id.lista_ocen_rv);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button = findViewById(R.id.przyciskSrednia);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                float srednia = adapter.getSrednia(liczbaOcen);
                Intent intent = new Intent();
                intent.putExtra("srednia", srednia);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
