package com.example.esame2019.common.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class AttivitaEUtente {

    @Embedded
    public Attivita attivita;

    @Relation(
        parentColumn = "id_utente",
        entityColumn = "id"
    )
    public Utente utente;
}
