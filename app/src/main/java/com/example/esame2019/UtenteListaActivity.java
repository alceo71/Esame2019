package com.example.esame2019;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.esame2019.common.model.Utente;
import com.example.esame2019.service.UtenteService;
import com.example.esame2019.utenti.UtenteClickListener;
import com.example.esame2019.utenti.UtenteLongClickListener;
import com.example.esame2019.utenti.UtentiAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UtenteListaActivity extends AppCompatActivity implements UtenteClickListener, UtenteLongClickListener, EliminaUtenteDialogFragment.EliminaUtenteListener {

    // L'adapter con per la recycler view
    private UtentiAdapter adapter;

    public final static String TAG_DIALOG_ELIMINA_CANZONE = "dialog_elimina_canzone";

/*
    public List<Utente> utenti(){
        UtenteService utenteService = new UtenteService(this);
        return utenteService.findAll();
        List<Utente> result = new ArrayList<>();

        result.add(new Utente("nome", "cognome", 21, "nome.cogmoe@gmail.com"));
        result.add(new Utente("nome1", "cognome", 21, "nome.cogmoe@gmail.com"));
        result.add(new Utente("nome2", "cognome", 21, "nome.cogmoe@gmail.com"));
        result.add(new Utente("nome3", "cognome", 21, "nome.cogmoe@gmail.com"));
        result.add(new Utente("nome4", "cognome", 21, "nome.cogmoe@gmail.com"));


        return result;
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utente_lista);


        setTitle(getString(R.string.elenco_utenti));

        RecyclerView recyclerView = findViewById(R.id.elenco_utenti);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        final UtentiAdapter adapter = new UtentiAdapter( this, this, this);
        recyclerView.setAdapter(adapter);

        // Recupera i dati dei pianeti
        UtenteService service = new UtenteService(this);
        service.findAll().observe(this, new Observer<List<Utente>>() {
            @Override
            public void onChanged(List<Utente> utenti) {
                Log.d(MainActivity.LOG_TAG,"Elenco utenti cambiata");
                // Assegna i valori all'adapter
                adapter.setUtenti(utenti);
            }
        });

        // Aggiungi il click sul bottone
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(MainActivity.LOG_TAG, "Formulario nuovo utente");
                Intent intent = new Intent(getApplicationContext(), UtenteFormActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onClick(Utente utente) {
        Log.d(MainActivity.LOG_TAG, "click su utente " + utente.getId());

        // Chiama il form per una modifica
        Intent intent = new Intent(getApplicationContext(), UtenteFormActivity.class);
        intent.putExtra(MainActivity.ID_UTENTE,utente.getId());
        startActivity(intent);

    }

    @Override
    public void onLongClick(Utente utente) {
        Log.d(MainActivity.LOG_TAG, "Looong click su utente " + utente.getNome());

        // Gestisci il delete dal database
        //UtenteService utenteService = new UtenteService(this);
        //utenteService.delete(utente);

        // Ricarica la lista dell'adapter necessario solo se non sono solo
        //adapter.utenti = utenti();
        //adapter.notifyDataSetChanged();

        FragmentManager fragmentManager = getSupportFragmentManager();
        EliminaUtenteDialogFragment eliminaUtenteDialogFragment = new EliminaUtenteDialogFragment(this, utente);
        eliminaUtenteDialogFragment.show(fragmentManager,TAG_DIALOG_ELIMINA_CANZONE);



    }

    public void utenteEliminato(){
        Toast.makeText(this, R.string.utente_eliminato, Toast.LENGTH_SHORT).show();
        // Non occorre modificare l'adapter perché usa LiveData
    }

    @Override
    public void cancel() {
        // non fare nulla
    }

    @Override
    public void elimina(Utente utente) {
        Log.d(MainActivity.LOG_TAG, "Elimina utente ");
        EliminaUtenteTask eliminaUtenteTask = new EliminaUtenteTask(this);
        eliminaUtenteTask.execute(utente);
    }

    /**
     * Task che elimina l'utente dal database
     *
     */
    private class EliminaUtenteTask extends AsyncTask<Utente,Void,Void> {
        private Context context;

        public EliminaUtenteTask(Context context){
            this.context = context;
        }

        @Override
        protected Void doInBackground(Utente... utenti) {
            Utente utente = utenti[0];
            UtenteService utenteService = new UtenteService(context);
            utenteService.delete(utente);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            utenteEliminato();
        }
    }

}
