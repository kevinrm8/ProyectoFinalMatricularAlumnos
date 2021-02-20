package com.example.matriculacion.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matriculacion.R;
import com.example.matriculacion.database.entidades.Asignatura;
import com.example.matriculacion.util.DialogListener;

import java.util.List;

public class AdapterAlumnoDetalle extends RecyclerView.Adapter {

    private Context context;
    private List<Asignatura> asignaturas;
    private DialogListener listener;

    //constructor
    public AdapterAlumnoDetalle(Context context) {
        this.context = context;
    }

    //setear el listener
    public void setListener(DialogListener listener) {
        this.listener = listener;
    }

    //setear las asignaturas que tiene el alumno, y notificarlo al recyclerview
    public void setItems(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return asignaturas == null ? 0 :  asignaturas.size();
    }

    //inflar el holder con los datos de la asignatura
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.item_layout_asignatura_detalle,null);
        return new Holder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Asignatura asignatura = asignaturas.get(position);
        Holder holderAsignatura = (Holder) holder;
        holderAsignatura.tvCodNombre.setText(asignatura.getCodigo() + " " + asignatura.getNombre());
    }

    //clase que describe el formato del holder
    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvCodNombre;
        ImageButton imbBorrar;

        public Holder(@NonNull View vi) {
            super(vi);

            tvCodNombre = (TextView) vi.findViewById(R.id.tvCodNombre);
            imbBorrar = (ImageButton) vi.findViewById(R.id.imbBorrar);

            imbBorrar.setOnClickListener(this);
        }

        //le damos comportamiento al botón de borrar
        @Override
        public void onClick(View view) {
            listener.onClickBorrar(asignaturas.get(getAdapterPosition()));
        }
    }
}