package com.example.esame2019;

import android.content.Intent;
import android.os.Bundle;

import com.example.esame2019.attivita.AttivitaAdapter;
import com.example.esame2019.attivita.AttivitaClickListener;
import com.example.esame2019.attivita.AttivitaLongClickListener;
import com.example.esame2019.common.model.Attivita;
import com.example.esame2019.common.model.AttivitaEUtente;
import com.example.esame2019.service.AttivitaService;
import com.example.esame2019.utenti.UtentiAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.util.List;

public class AttivitaListaActivity extends AppCompatActivity implements AttivitaClickListener, AttivitaLongClickListener {

    // L'adapter con per la recycler view
    private AttivitaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attivita_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(getString(R.string.titolo_elenco_attivita));

        RecyclerView recyclerView = findViewById(R.id.elenco_attivita);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        AttivitaService service = new AttivitaService(this);


        List<AttivitaEUtente> elencoConUtenti = service.findAllConUtenti();

        for(AttivitaEUtente attivita:elencoConUtenti){
            Log.d(MainActivity.LOG_TAG, "Sono qua ---- " + attivita.attivita.getTitolo() + " " + attivita.utente.getCongnone());
        }

        final AttivitaAdapter adapter = new AttivitaAdapter(elencoConUtenti, this, this, this);
        recyclerView.setAdapter(adapter);

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(AttivitaEUtente attivita) {
        // Modifica
        Log.d(MainActivity.LOG_TAG, "Formulario modifica attività");
        Intent intent = new Intent(getApplicationContext(), AttivitaFormActivity.class);
        intent.putExtra(MainActivity.ID_ATTIVITA,attivita.attivita.getId());
        startActivity(intent);
    }

    @Override
    public void onLongClick(AttivitaEUtente attivita) {
        // Delete
    }
}
