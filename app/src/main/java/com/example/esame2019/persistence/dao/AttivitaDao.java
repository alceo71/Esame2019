package com.example.esame2019.persistence.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.esame2019.common.model.Attivita;
import com.example.esame2019.common.model.Utente;

import java.util.List;

@Dao
public interface AttivitaDao {

    @Query("SELECT * FROM attivita WHERE id = :id")
    Attivita get(int id);

    @Query("SELECT * FROM attivita")
    List<Attivita> findAll();

    @Insert
    Long insert(Attivita attivita);

    @Update
    int update(Attivita attivita);

    @Delete
    void delete(Attivita attivita);



}

