package com.example.matriculacion.asignaturaactivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.matriculacion.R;
import com.example.matriculacion.database.entidades.Asignatura;
import com.example.matriculacion.util.DialogListener;

public class FragmentDialogAsignatura extends DialogFragment implements DialogInterface.OnClickListener {

    private EditText txtNombre;
    private EditText txtCodigo;
    private Asignatura a;
    private DialogListener listener;

    //constructor para editar. le paso la asignatura a editar
    public FragmentDialogAsignatura(Asignatura a, DialogListener listener){
        this.a = a;
        this.listener = listener;
    }

    //constructor para insertar. la asignatura vale null
    public FragmentDialogAsignatura(DialogListener listener){
        this.a = null;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.fragment_dialog_asignatura, null);

        builder.setView(v);
        builder.setTitle(getTag());
        builder.setNegativeButton("Cancelar",this);
        builder.setPositiveButton("Aceptar", this);

        txtNombre = (EditText) v.findViewById(R.id.txtNombre);
        txtCodigo = (EditText) v.findViewById(R.id.txtCodigo);

        if(a!=null){
            txtNombre.setText(a.getNombre());
            txtCodigo.setText(a.getCodigo());
            txtCodigo.setEnabled(false);
        }
        return builder.create();
    }


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i){
            case -2:
                dismiss();
                break;
            case -1:
                if(a==null){
                    a = new Asignatura(txtCodigo.getText().toString(),txtNombre.getText().toString());
                    listener.onClickNueva(a);
                }else{
                    a.setNombre(txtNombre.getText().toString());
                    listener.onClickEditar(a);
                }
                break;
            default:
                break;
        }
    }

}