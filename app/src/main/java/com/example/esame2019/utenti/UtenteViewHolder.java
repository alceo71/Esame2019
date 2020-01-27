package com.example.esame2019.utenti;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esame2019.common.model.Utente;

public class UtenteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    // L'oggetto view esterno
    View itemView;

    // Elemeni UI da modificare
    public TextView nome;
    public TextView eta;

    // Il pianeta da visualizzare
    public Utente utente;

    // I listener per il click e longclick
    public UtenteClickListener utenteClickListener;
    public UtenteLongClickListener utenteLongClickListener;


    public UtenteViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.itemView = itemView;
    }


    @Override
    public void onClick(View view) {
        utenteClickListener.onClick(utente);
    }

    @Override
    public boolean onLongClick(View view) {
        utenteLongClickListener.onLongClick(utente);
        return true;
    }
}
