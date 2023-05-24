package com.example.laboratorium1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InteraktywnyAdapterTablicy extends
        RecyclerView.Adapter<InteraktywnyAdapterTablicy.OcenyViewHolder> {
    private List<ModelOceny> mListaOcen;
    private LayoutInflater mPompka;
    public InteraktywnyAdapterTablicy(Activity kontekst, List<ModelOceny> listaOcen)
    {
        mPompka = kontekst.getLayoutInflater();
        this.mListaOcen = listaOcen;
    }
    public InteraktywnyAdapterTablicy(Activity kontekst, int iloscPrzedmiotow, String[] nazwyPrzedmiotow) {
        mPompka = kontekst.getLayoutInflater();
        mListaOcen = new ArrayList<>();
        for (int i = 0; i < iloscPrzedmiotow; i++) {
            mListaOcen.add(new ModelOceny(nazwyPrzedmiotow[i], 2));
        }
    }

    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        View wiersz = mPompka.inflate(R.layout.wiersz_ocena, viewGroup, false);
        return new OcenyViewHolder(wiersz);
    }

    public static int getCheckedRadioButtonId(List<ModelOceny> listaOcen, int numerWiersza) {
        ModelOceny ocena = listaOcen.get(numerWiersza);
        return getRadioButtonIdFromOcena(ocena.getOcena());
    }

    @Override
    public void onBindViewHolder(@NonNull OcenyViewHolder ocenyViewHolder, int numerWiersza)
    {
        ModelOceny ocena = mListaOcen.get(numerWiersza);
        ocenyViewHolder.nazwaPrzedmiotuTextView.setText(ocena.getNazwa());
        ocenyViewHolder.radioGroupOcena.setOnCheckedChangeListener(null); // usuń poprzednie nasłuchiwacz
        ocenyViewHolder.radioGroupOcena.check(getCheckedRadioButtonId(mListaOcen, numerWiersza)); // zaznacz odpowiedni przycisk
        ocenyViewHolder.radioGroupOcena.setOnCheckedChangeListener(ocenyViewHolder);
        ocenyViewHolder.radioGroupOcena.setTag(numerWiersza);
    }

    @Override
    public int getItemCount() {
        return mListaOcen.size();
    }

    public float getSrednia(int liczbaOcen) {
        int suma=0;
        for(int i=0; i<liczbaOcen; i++) {
            suma += mListaOcen.get(i).getOcena();
        }
        return (float)suma/(float)liczbaOcen;
    }
    public static int getRadioButtonIdFromOcena(int ocena) {
        switch (ocena) {
            case 2:
                return R.id.radio_ocena_2;
            case 3:
                return R.id.radio_ocena_3;
            case 4:
                return R.id.radio_ocena_4;
            case 5:
                return R.id.radio_ocena_5;
            default:
                return -1;
        }
    }

    public static int getOcenaFromRadioButtonId(int radioButtonId) {
        switch (radioButtonId) {
            case R.id.radio_ocena_2:
                return 2;
            case R.id.radio_ocena_3:
                return 3;
            case R.id.radio_ocena_4:
                return 4;
            case R.id.radio_ocena_5:
                return 5;
            default:
                return -1;
        }
    }

    public class OcenyViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener  {
        public TextView nazwaPrzedmiotuTextView;
        public RadioGroup radioGroupOcena;
        public OcenyViewHolder (@NonNull View glownyElementWiersza) {

            super(glownyElementWiersza);
            nazwaPrzedmiotuTextView = itemView.findViewById(R.id.nazwa_przedmiotu);
            radioGroupOcena = itemView.findViewById(R.id.radiogroup_oceny);
        }

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int position = (int) radioGroup.getTag();
            ModelOceny ocena = mListaOcen.get(position);
            ocena.setOcena(getOcenaFromRadioButtonId(i));
            mListaOcen.set(position, ocena);
        }
    }
}
