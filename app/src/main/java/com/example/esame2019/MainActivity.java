package com.example.esame2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public final static String LOG_TAG = "esame2019_tag";

    public final static String ID_UTENTE = "it_utente";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Click che mostra l'actvity lista
     *
     * @param view
     */
    public void mostraListaAttivita(View view) {
        Intent intent = new Intent(this, AttivitaListaActivity.class);
        startActivity(intent);
    }

    /**
     * Click che mostra l'activity utente
     *
     * @param view
     */
    public void mostraListaUtenti(View view) {
        Intent intent = new Intent(this, UtenteListaActivity.class);
        startActivity(intent);
    }




}
