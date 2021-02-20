package com.example.matriculacion.alumnodetalleactivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.matriculacion.database.entidades.Asignatura;
import com.example.matriculacion.util.DialogListener;

import java.util.List;

public class FragmentDialogAlumnoSeleccionar extends DialogFragment implements DialogInterface.OnClickListener{
    private DialogListener listener;
    private String codigoSeleccionado;
    private List<Asignatura> asignaturas;
    private String[] lista;

    public FragmentDialogAlumnoSeleccionar(DialogListener listener,List<Asignatura> asignaturas) {
        this.listener = listener;
        this.asignaturas = asignaturas;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Datos de la asignatura y la mostramos
        lista = new String[asignaturas.size()];
        for(int i=0;i<asignaturas.size();i++){
            lista[i] = asignaturas.get(i).toString();
        }
        //Al iniciar esta seleccionado el primer dato
        codigoSeleccionado = lista[0].split(",")[0];

        builder.setTitle(getTag());
        builder.setSingleChoiceItems(lista,0,this);
        builder.setPositiveButton("Aceptar",this);
        builder.setNegativeButton("Cancelar",this);

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i){
            case -2:
                dismiss();
                break;
            case -1:
                listener.onClickNueva(codigoSeleccionado);
                break;
            default:
                codigoSeleccionado = lista[i].split("-")[0];
                break;
        }
    }

}