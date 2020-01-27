package com.example.esame2019.service;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.esame2019.MainActivity;
import com.example.esame2019.common.model.Attivita;
import com.example.esame2019.common.model.AttivitaEUtente;
import com.example.esame2019.persistence.AppDatabase;
import com.example.esame2019.persistence.dao.AttivitaDao;
import com.example.esame2019.persistence.dao.AttivitaEUtenteDao;

import java.util.List;

public class AttivitaService {

    private Context context;

    public AttivitaService(){}

    public AttivitaService(Context context){
            this.context = context;
    }

    /**
     * Trova un utente
     *
     * @return
     */
    public Attivita get(int id){
        // Crea il db
        AppDatabase db = AppDatabase.getDatabase(context);
        // Recupera il DAO
        AttivitaDao dao = db.attivitaDao();
        return dao.get(id);
    }

    /**
     * Trova tutti gli utenti
     *
     * @return
     */
    public List<Attivita> findAll(){
        // Crea il db
        AppDatabase db = AppDatabase.getDatabaseNoThread(context);
        // Recupera il DAO
        AttivitaDao dao = db.attivitaDao();
        // Recupera tutti gli utenti
        return dao.findAll();
    }


    public List<AttivitaEUtente> findAllConUtenti(){
        // Crea il db
        AppDatabase db = AppDatabase.getDatabaseNoThread(context);
        // Recupera il DAO
        AttivitaEUtenteDao dao = db.attivitaEUtenteDao();
        // Recupera tutti gli utenti
        return dao.findAll();
    }

    public void insert(Attivita attivita){
        Log.d(MainActivity.LOG_TAG, "Prima di inserire");
        // Crea il db
        AppDatabase db = AppDatabase.getDatabaseNoThread(context);
        // Recupera il DAO
        AttivitaDao dao = db.attivitaDao();
        long valore = dao.insert(attivita);
        Log.d(MainActivity.LOG_TAG, "Dopo inserimento con id " + valore);
    }


    public void update(Attivita attivita){
        // Crea il db
        AppDatabase db = AppDatabase.getDatabase(context);
        // Recupera il DAO
        AttivitaDao dao = db.attivitaDao();
        dao.update(attivita);
    }

    public void delete(Attivita attivita){
        // Crea il db
        AppDatabase db = AppDatabase.getDatabase(context);
        // Recupera il DAO
        AttivitaDao dao = db.attivitaDao();
        dao.delete(attivita);
    }

}
