package com.example.esame2019.persistence.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.esame2019.common.model.AttivitaEUtente;

import java.util.List;

@Dao
public interface AttivitaEUtenteDao {

    @Transaction
    @Query("SELECT * FROM Attivita")
    public List<AttivitaEUtente> findAll();


}
