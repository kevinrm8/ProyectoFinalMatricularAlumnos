package com.example.matriculacion.alumnoactivity;

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
import com.example.matriculacion.database.entidades.Alumno;
import com.example.matriculacion.util.DialogListener;

public class FragmentDialogAlumno extends DialogFragment implements DialogInterface.OnClickListener {

    private EditText Nombre;
    private EditText Apellidos;
    private EditText Dni;
    private Alumno alumno;
    private DialogListener listener;

    //Para editar el alumno, le pasamos uno
    public FragmentDialogAlumno(Alumno alumno, DialogListener listener){
        this.alumno = alumno;
        this.listener = listener;
    }

    //Si creamos alumno nuevo, no le paso ningunoi
    public FragmentDialogAlumno(DialogListener listener){
        this.alumno = null;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog_alumno, null);

        //Preparamos el builder
        builder.setView(view);
        builder.setTitle(getTag());
        builder.setNegativeButton("Cancelar",this);
        builder.setPositiveButton("Aceptar", this);

        Nombre = (EditText) view.findViewById(R.id.txtNombre);
        Dni = (EditText) view.findViewById(R.id.txtDni);
        Apellidos = (EditText) view.findViewById(R.id.txtApellidos);

        if(alumno!=null){
            Nombre.setText(alumno.getNombre());
            Apellidos.setText(alumno.getApellidos());
            Dni.setText(alumno.getDni());
            Dni.setEnabled(false);
        }
        return builder.create();
    }

    //Botones
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        //Los botones devuelven -2 Cancelar, -1 Aceptar
        switch (i){
            case -2:
                dismiss();
                break;
            case -1:
                if(alumno==null){
                    alumno = new Alumno(Dni.getText().toString(),Nombre.getText().toString(),Apellidos.getText().toString());
                    listener.onClickNueva(alumno);
                }else{
                    alumno.setNombre(Nombre.getText().toString());
                    alumno.setApellidos(Apellidos.getText().toString());
                    listener.onClickEditar(alumno);
                }
                break;
            default:
                break;
        }
    }
}