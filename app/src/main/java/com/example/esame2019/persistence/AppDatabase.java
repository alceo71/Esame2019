package com.example.esame2019.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.esame2019.common.model.Attivita;
import com.example.esame2019.common.model.AttivitaEUtente;
import com.example.esame2019.common.model.Utente;
import com.example.esame2019.persistence.dao.AttivitaDao;
import com.example.esame2019.persistence.dao.AttivitaEUtenteDao;
import com.example.esame2019.persistence.dao.UtenteDao;


@Database(entities = {Utente.class, Attivita.class}, version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    // DAO per la tabella utenti
    public abstract UtenteDao utenteDao();

    // DAO per la tabella attivita
    public abstract AttivitaDao attivitaDao();

    // DAO per attività e utenti
    public abstract AttivitaEUtenteDao attivitaEUtenteDao();

    private static final String DB_NAME = "esame2019";

    // Singletone
    private static AppDatabase instance;

    public static AppDatabase getDatabase(final Context context){
        if(instance == null){
            synchronized (AppDatabase.class){
                if(instance == null){
                    // .allowMainThreadQueries() da non usare in produzione
                    instance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, DB_NAME).build();
                }
            }
        }

        return instance;
    }
    // Singletone
    private static AppDatabase instanceNoThread;

    public static AppDatabase getDatabaseNoThread(final Context context){
        if(instanceNoThread == null){
            synchronized (AppDatabase.class){
                if(instanceNoThread == null){
                    // .allowMainThreadQueries() da non usare in produzione
                    instanceNoThread = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, DB_NAME).allowMainThreadQueries().build();
                }
            }
        }

        return instanceNoThread;
    }
}

