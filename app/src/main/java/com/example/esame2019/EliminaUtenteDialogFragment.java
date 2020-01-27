package com.example.esame2019;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.esame2019.common.model.Utente;

public class EliminaUtenteDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private EliminaUtenteListener listener;

    private Utente utente;

    public EliminaUtenteDialogFragment(EliminaUtenteListener listener, Utente utente){
        this.listener = listener;
        this.utente = utente;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Costruiamo il builder per un AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Titolo e corpo
        builder.setTitle(R.string.elimina_utente);
        //String messaggio = getString(R.string.messaggio_elimina_utente, utente.getCongnone()+ " " + utente.getNome());
        builder.setMessage(R.string.messaggio_elimina_utente);

        builder.setPositiveButton(R.string.ok, this);
        builder.setNegativeButton(R.string.annulla, this);

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == DialogInterface.BUTTON_POSITIVE){
            // Avertiamo l'activity
            listener.elimina(utente);
        } else if(i == DialogInterface.BUTTON_NEGATIVE){
            // Avertiamo l'activity
            listener.cancel();
        }
    }

    public interface EliminaUtenteListener {
        void cancel();
        void elimina(Utente utente);
    }
}
