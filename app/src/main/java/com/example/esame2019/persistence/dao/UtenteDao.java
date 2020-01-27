package com.example.esame2019.persistence.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.esame2019.common.model.Utente;

import java.util.List;

@Dao
public interface UtenteDao {

    @Query("SELECT * FROM utente WHERE id = :id")
    Utente get(int id);

    @Query("SELECT * FROM utente")
    LiveData<List<Utente>> findAll();

    @Insert
    Long insert(Utente utente);

    @Update
    int update(Utente utente);

    @Delete
    void delete(Utente pianeta);



}

