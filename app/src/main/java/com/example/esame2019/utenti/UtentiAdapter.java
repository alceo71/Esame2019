package com.example.esame2019.utenti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esame2019.R;
import com.example.esame2019.common.model.Utente;

import java.util.List;

public class UtentiAdapter extends RecyclerView.Adapter<UtenteViewHolder>{

    public List<Utente> utenti;
    public Context context;
    public UtenteClickListener utenteClickListener;
    public UtenteLongClickListener utenteLongClickListener;


    public UtentiAdapter(Context context, UtenteClickListener utenteClickListener, UtenteLongClickListener utenteLongClickListener) {
        this.utenti = utenti;
        this.context = context;
        this.utenteClickListener = utenteClickListener;
        this.utenteLongClickListener = utenteLongClickListener;
    }

    public UtentiAdapter(List<Utente> utenti, Context context, UtenteClickListener utenteClickListener, UtenteLongClickListener utenteLongClickListener) {
        this.utenti = utenti;
        this.context = context;
        this.utenteClickListener = utenteClickListener;
        this.utenteLongClickListener = utenteLongClickListener;
    }

    /**
     * Crea un pinaetaViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public UtenteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Crea il layout
        View convertView = LayoutInflater.from(context).inflate(R.layout.utente_item, parent, false);

        // Crea il viewHolder
        UtenteViewHolder result = new UtenteViewHolder(convertView);

        // Associa i listener
        result.utenteClickListener = utenteClickListener;
        result.utenteLongClickListener = utenteLongClickListener;

        result.nome = convertView.findViewById(R.id.utente_nome);
        result.eta = convertView.findViewById(R.id.utente_eta);

        return result;
    }

    @Override
    public void onBindViewHolder(@NonNull UtenteViewHolder holder, int position) {
        // Trova l'utente
        Utente utente = utenti.get(position);

        // Associa l'utente
        holder.utente = utente;

        // Testo nome e cognome
        String nomeCognome = context.getString(R.string.nome_cognome, utente.getNome(), utente.getCongnone());
        holder.nome.setText(nomeCognome);

        // Testo età
        String eta = context.getString(R.string.eta, utente.getEta());
        holder.eta.setText(eta);
    }

    /**
     * Assegna gli utenti
     *
     * @param utenti
     */
    public void setUtenti(List<Utente> utenti){
        this.utenti = utenti;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(utenti == null)
            return 0;
        else
            return utenti.size();
    }
}
