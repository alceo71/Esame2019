package com.example.esame2019.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.esame2019.MainActivity;
import com.example.esame2019.common.model.Utente;
import com.example.esame2019.persistence.AppDatabase;
import com.example.esame2019.persistence.dao.UtenteDao;

import java.util.List;

public class UtenteService {

    private Context context;

    public UtenteService(){}

    public UtenteService(Context context){
            this.context = context;
    }

    /**
     * Trova un utente
     *
     * @return
     */
    public Utente get(int id){
        // Crea il db
        AppDatabase db = AppDatabase.getDatabase(context);
        // Recupera il DAO
        UtenteDao dao = db.utenteDao();
        return dao.get(id);
    }

    /**
     * Trova tutti gli utenti
     *
     * @return
     */
    public LiveData<List<Utente>> findAll(){
        // Crea il db
        AppDatabase db = AppDatabase.getDatabase(context);
        // Recupera il DAO
        UtenteDao dao = db.utenteDao();
        // Recupera tutti gli utenti
        return dao.findAll();
    }


    public void insert(Utente utente){
        Log.d(MainActivity.LOG_TAG, "Prima di inserire");
        // Crea il db
        AppDatabase db = AppDatabase.getDatabase(context);
        // Recupera il DAO
        UtenteDao dao = db.utenteDao();
        long valore = dao.insert(utente);
        Log.d(MainActivity.LOG_TAG, "Dopo inserimento con id " + valore);
    }


    public void update(Utente utente){
        // Crea il db
        AppDatabase db = AppDatabase.getDatabase(context);
        // Recupera il DAO
        UtenteDao dao = db.utenteDao();
        dao.update(utente);
    }

    public void delete(Utente utente){
        // Crea il db
        AppDatabase db = AppDatabase.getDatabase(context);
        // Recupera il DAO
        UtenteDao dao = db.utenteDao();
        dao.delete(utente);
    }

}
