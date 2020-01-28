package com.example.esame2019.attivita;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esame2019.common.model.AttivitaEUtente;
import com.example.esame2019.common.model.Utente;
import com.example.esame2019.utenti.UtenteClickListener;
import com.example.esame2019.utenti.UtenteLongClickListener;

public class AttivitaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    // L'oggetto view esterno
    View itemView;

    // Elemeni UI da modificare
    public TextView titolo;
    public TextView descrizione;
    public TextView data;
    public TextView nomeUtente;

    // Il pianeta da visualizzare
    public AttivitaEUtente attivita;

    // I listener per il click e longclick
    public AttivitaClickListener attivitaClickListener;
    public AttivitaLongClickListener attivitaLongClickListener;


    public AttivitaViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.itemView = itemView;
    }


    @Override
    public void onClick(View view) {
        attivitaClickListener.onClick(attivita);
    }

    @Override
    public boolean onLongClick(View view) {
        attivitaLongClickListener.onLongClick(attivita);
        return true;
    }
}
