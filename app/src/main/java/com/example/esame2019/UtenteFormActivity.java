package com.example.esame2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esame2019.common.model.Utente;
import com.example.esame2019.service.UtenteService;

public class UtenteFormActivity extends AppCompatActivity {

    // Gli elementi per la UI
    private EditText nome;
    private EditText cognome;
    private EditText eta;
    private EditText email;

    // L'id dell'utente da modificare se esistente
    private Integer idUtente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_utente);

        nome = findViewById(R.id.editTextNome);
        cognome = findViewById(R.id.editTextCognome);
        eta = findViewById(R.id.editTextEta);
        email = findViewById(R.id.editTextEmail);

        // Assegna il titolo
        setTitle(R.string.nuovo_utente);

        // Controlla se è un utente da modificare
        if(getIntent().getExtras() != null){
            int value = getIntent().getExtras().getInt(MainActivity.ID_UTENTE, -1);
            if(value != -1){
                idUtente = value;

                // Recupera i dati dell'utente
                // UtenteService utenteService = new UtenteService(this);
                // Utente utente = utenteService.get(idUtente);
                // inizializza(utente)

                // Richiedi il caricamento in un secondo thread
                CaricaUtenteTask caricaUtenteTask = new CaricaUtenteTask(this);
                caricaUtenteTask.execute(idUtente);

                // Assegna il titolo
                setTitle(R.string.modifica_utente);

                Button bottone_salva = findViewById(R.id.bottone_salva);
                bottone_salva.setText(R.string.modifica);
                // cambia etichetta bottone
            }
        }

    }


    /**
     * Salva l'utente se non ci sono errori e torna alla lista utenti
     *
     * @param view
     */
    public void salvaUtente(View view) {

        StringBuilder messaggioErrori = new StringBuilder();

        // Controlla per errori
        boolean errori = false;

        String nomeStr = nome.getText().toString();
        String cognomeStr = cognome.getText().toString();

        String etaStr = eta.getText().toString();
        int etaNum = 0;

        String emailStr = email.getText().toString();

        if(nomeStr.equals("")){
            errori = true;
            messaggioErrori.append("Il campo nome non può essere vuoto.\n");
        }

        if(cognomeStr.equals("")){
            errori = true;
            messaggioErrori.append("Il campo cognome non può essere vuoto.\n");
        }

        if(etaStr.equals("")){
            errori = true;
            messaggioErrori.append("Il campo età non può essere vuoto.\n");
        } else{
            etaNum = Integer.parseInt(etaStr);
        }

        if(emailStr.equals("") || !emailValida(emailStr)){
            errori = true;
            messaggioErrori.append("Email non valida.\n");
        }





        if(!errori){
            // Salva il valore nel database
            Utente utente= new Utente(nomeStr, cognomeStr,etaNum, emailStr);;
            if(idUtente != null){
                utente.setId(idUtente);
            }
            SalvaUtenteTask salvaUtenteTask = new SalvaUtenteTask(this);
            salvaUtenteTask.execute(utente);


        } else{
            // Mostra un Toast con gli errori
            Toast.makeText(this, "Controlla il formulario\n" + messaggioErrori.toString(), Toast.LENGTH_LONG).show();
        }

    }


    public void inizializza(Utente utente){
        nome.setText(utente.getNome());
        cognome.setText(utente.getCongnone());
        eta.setText(utente.getEta()+"");
        email.setText(utente.getEmail());
    }

    /**
     * Verifica se la sringa data è un'email valida
     *
     * @param email
     * @return
     */
    public boolean emailValida(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Chiude l'activity tornano all'elenco degli utenti
     *
     */
    public void chiudiActivity(){
        //Intent intent = new Intent(this, UtenteListaActivity.class);
        //startActivity(intent);
        // Togli l'activity dallo stack
        finish();
    }

    /**
     * Task che salva un utente nel database.
     * Alla fine del salvataggio chiama il metodo  <i>chiudiActivity</i>
     *
     */
    private class SalvaUtenteTask extends AsyncTask<Utente,Void,Void>{

        private Context context;

        public SalvaUtenteTask(Context context){
            this.context = context;
        }

        @Override
        protected Void doInBackground(Utente... utenti) {
            UtenteService utenteService = new UtenteService(context);
            Utente utente = utenti[0];
            if(utente.getId() == 0){

                utenteService.insert(utente);
            } else {
                utenteService.update(utente);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            chiudiActivity();
        }
    }


    private class CaricaUtenteTask extends AsyncTask<Integer,Void,Utente>{
        private Context context;

        public CaricaUtenteTask(Context context){
            this.context = context;
        }

        @Override
        protected Utente doInBackground(Integer... ids) {
            Integer id = ids[0];
            UtenteService utenteService = new UtenteService(context);
            return utenteService.get(id);
        }

        @Override
        protected void onPostExecute(Utente utente) {
            inizializza(utente);
        }
    }

}
