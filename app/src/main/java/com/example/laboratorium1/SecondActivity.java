package com.example.laboratorium1;

import static android.app.Activity.RESULT_OK;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.example.laboratorium1.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class SecondActivity extends AppCompatActivity {

    Button button;
    ArrayList<ModelOceny> mListaOcen;
    String[] nazwyPrzedmiotow;
    int liczbaOcen;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Bundle pakunek = intent.getExtras();
        liczbaOcen = pakunek.getInt("liczbaOcen");
        nazwyPrzedmiotow = getResources().getStringArray(R.array.przedmioty);
        mListaOcen = new ArrayList<>();
        for (int i = 0; i < liczbaOcen; i++) {
            mListaOcen.add(new ModelOceny(nazwyPrzedmiotow[i], 2));
        }
        InteraktywnyAdapterTablicy adapter = new InteraktywnyAdapterTablicy(this, mListaOcen);
        recyclerView = findViewById(R.id.lista_ocen_rv);
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

        if(savedInstanceState != null) {
            for (int i=0; i<savedInstanceState.getInt("liczbaOcen"); i++) {
                mListaOcen.add(new ModelOceny(nazwyPrzedmiotow[i], savedInstanceState.getInt("ocena"+i)));
            }
            recyclerView.setAdapter(new InteraktywnyAdapterTablicy(this, mListaOcen));
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("liczbaOcen", liczbaOcen);
        for (int i=0; i<liczbaOcen; i++) {
            outState.putInt("ocena"+i, mListaOcen.get(i).getOcena());
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (int i=0; i<savedInstanceState.getInt("liczbaOcen"); i++) {
            mListaOcen.add(new ModelOceny(nazwyPrzedmiotow[i], savedInstanceState.getInt("ocena"+i)));
        }
        recyclerView.setAdapter(new InteraktywnyAdapterTablicy(this, mListaOcen));
    }

}
