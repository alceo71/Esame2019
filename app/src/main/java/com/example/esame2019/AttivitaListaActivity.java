package com.example.esame2019;

import android.content.Intent;
import android.os.Bundle;

import com.example.esame2019.common.model.Attivita;
import com.example.esame2019.common.model.AttivitaEUtente;
import com.example.esame2019.service.AttivitaService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import java.util.List;

public class AttivitaListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attivita_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Aggiungi il click sul bottone
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(MainActivity.LOG_TAG, "Formulario nuova attività");
                Intent intent = new Intent(getApplicationContext(), AttivitaFormActivity.class);
                startActivity(intent);

            }
        });

        AttivitaService service = new AttivitaService(this);

        List<Attivita> elenco = service.findAll();

        for(Attivita attivita:elenco){
            Log.d(MainActivity.LOG_TAG, "Sono qua " + attivita.getTitolo() + " " + attivita.getIdUtente());
        }


        List<AttivitaEUtente> elencoConUtenti = service.findAllConUtenti();

        for(AttivitaEUtente attivita:elencoConUtenti){
            Log.d(MainActivity.LOG_TAG, "Sono qua ---- " + attivita.attivita.getTitolo() + " " + attivita.utente.getCongnone());
        }


    }

}
