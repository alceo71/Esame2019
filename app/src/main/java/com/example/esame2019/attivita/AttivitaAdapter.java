package com.example.esame2019.attivita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esame2019.MainActivity;
import com.example.esame2019.R;
import com.example.esame2019.common.model.AttivitaEUtente;
import com.example.esame2019.common.model.Utente;
import com.example.esame2019.utenti.UtenteClickListener;
import com.example.esame2019.utenti.UtenteLongClickListener;
import com.example.esame2019.utenti.UtenteViewHolder;

import java.util.List;

public class AttivitaAdapter extends RecyclerView.Adapter<AttivitaViewHolder>{

    public List<AttivitaEUtente> attivita;
    public Context context;
    public AttivitaClickListener attivitaClickListener;
    public AttivitaLongClickListener attivitaLongClickListener;


    public AttivitaAdapter(Context context, AttivitaClickListener attivitaClickListener, AttivitaLongClickListener attivitaLongClickListener) {
        this.context = context;
        this.attivitaClickListener = attivitaClickListener;
        this.attivitaLongClickListener = attivitaLongClickListener;
    }

    public AttivitaAdapter(List<AttivitaEUtente> attivita, Context context, AttivitaClickListener attivitaClickListener, AttivitaLongClickListener attivitaLongClickListener) {
        this.attivita = attivita;
        this.context = context;
        this.attivitaClickListener = attivitaClickListener;
        this.attivitaLongClickListener = attivitaLongClickListener;
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
    public AttivitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Crea il layout
        View convertView = LayoutInflater.from(context).inflate(R.layout.attivita_item, parent, false);

        // Crea il viewHolder
        AttivitaViewHolder result = new AttivitaViewHolder(convertView);

        // Associa i listener
        result.attivitaClickListener = attivitaClickListener;
        result.attivitaLongClickListener = attivitaLongClickListener;

        result.titolo = convertView.findViewById(R.id.titolo);
        result.descrizione = convertView.findViewById(R.id.descrizione);
        result.data = convertView.findViewById(R.id.data);
        result.nomeUtente = convertView.findViewById(R.id.nome_utente);

        return result;
    }

    @Override
    public void onBindViewHolder(@NonNull AttivitaViewHolder holder, int position) {
        // Trova l'utente
        AttivitaEUtente support = attivita.get(position);

        // Associa l'utente
        holder.attivita = support;

        // Testo nome e cognome
        String nomeCognome = context.getString(R.string.nome_cognome, support.utente.getNome(), support.utente.getCongnone());
        holder.nomeUtente.setText(nomeCognome);

        // Titolo
        holder.titolo.setText(support.attivita.getTitolo());

        // descrizione
        holder.descrizione.setText(support.attivita.getDescrizione());

        // descrizione
        if(support.attivita.getData() != null){
            holder.data.setVisibility(View.VISIBLE);
            holder.data.setText(MainActivity.formatData.format(support.attivita.getData()));
        } else {
            holder.data.setVisibility(View.GONE);
        }

    }

    /**
     * Assegna le attivita
     *
     * @param attivita
     */
    public void setAttivita(List<AttivitaEUtente> attivita){
        this.attivita = attivita;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(attivita == null)
            return 0;
        else
            return attivita.size();
    }
}
