package com.example.esame2019;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.esame2019.common.model.Attivita;
import com.example.esame2019.common.model.Utente;
import com.example.esame2019.service.AttivitaService;
import com.example.esame2019.service.UtenteService;

import java.nio.file.attribute.AttributeView;
import java.util.List;

public class AttivitaFormActivity extends AppCompatActivity {

    EditText titolo;
    EditText descrizione;
    EditText data;
    Spinner spinnerUtente;

    private int idAttivita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attivita_form);

        setTitle(R.string.nuova_attivita);

        // Controlla se è un utente da modificare
        if(getIntent().getExtras() != null) {
            int value = getIntent().getExtras().getInt(MainActivity.ID_ATTIVITA, -1);
            if (value != -1) {
                idAttivita = value;
            }
        }

        spinnerUtente = findViewById(R.id.spinnerUtente);

        titolo = findViewById(R.id.editTextTitolo);
        descrizione = findViewById(R.id.editTextDescrizione);
        //data = findViewById(R.id.editTextData);

        // Recupera gli utenti
        // Recupera i dati dei pianeti
        UtenteService service = new UtenteService(this);
        service.findAll().observe(this, new Observer<List<Utente>>() {
            @Override
            public void onChanged(List<Utente> utenti) {
                Log.d(MainActivity.LOG_TAG,"Elenco utenti cambiata " + utenti.size());
                // Assegna i valori allo spinner
                UtenteAdapter adapter = new  UtenteAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, utenti);
                spinnerUtente.setAdapter(adapter);

                if(idAttivita != 0){
                    AttivitaService attivitaService = new AttivitaService(getApplicationContext());
                    Attivita attivita = attivitaService.get(idAttivita);
                    assegnaValori(attivita);
                }
            }
        });




    }

    public void salvaAttivita(View view) {

        Log.d(MainActivity.LOG_TAG,"Salva attività ");

        Utente utente = (Utente) spinnerUtente.getSelectedItem();
        Log.d(MainActivity.LOG_TAG,"Salva utente " + utente.getNome());

        Attivita attivita = new Attivita();
        attivita.setTitolo(titolo.getText().toString());
        attivita.setDescrizione(descrizione.getText().toString());

        attivita.setIdUtente(utente.getId());

        // attivita.setTitolo(titolo.getText().toString());

        // Salva nel db
        AttivitaService service = new AttivitaService(this);

        if(idAttivita != 0){
            attivita.setId(idAttivita);
            service.update(attivita);
        } else {
            service.insert(attivita);
        }



        finish();

    }

    public void assegnaValori(Attivita attivita){
        titolo.setText(attivita.getTitolo());
        descrizione.setText(attivita.getDescrizione());
        if(attivita.getData() != null){
            data.setText(MainActivity.formatData.format(attivita.getData()));
        }

        int position = ((UtenteAdapter) spinnerUtente.getAdapter()).getPosition(attivita.getId());

        if(position != -1){
            Log.d(MainActivity.LOG_TAG,"Valore trovato " + position);
            spinnerUtente.setSelection(position);
        }

        //Spinner spinnerUtente;
    }


    /**
     * Adapter da usare per lo spinner
     *
     */
    public class UtenteAdapter extends ArrayAdapter<Utente>{
        private List<Utente> utenti;

        public UtenteAdapter(@NonNull Context context, int resource, @NonNull List<Utente> utenti) {
            super(context, resource, utenti);
            this.utenti = utenti;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            TextView label = (TextView) super.getView(position, convertView, parent);

            Utente utente = utenti.get(position);
            label.setText(utente.getCongnone()+ " " + utente.getNome());
            label.setTextColor(Color.BLACK);

            return label;
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            TextView label = (TextView) super.getDropDownView(position, convertView, parent);

            Utente utente = utenti.get(position);
            label.setText(utente.getCongnone()+ " " + utente.getNome());
            label.setTextColor(Color.BLACK);
            return label;
        }

        public int getPosition(Integer idUtente){
            int result = -1;

            int position = 1;
            for(Utente utente:utenti){
                if(idUtente.equals(utente.getId())){
                    result = position;
                    break;
                }
                position++;
            }

            return result;
        }

    }

}
