package com.example.matriculacion.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matriculacion.R;
import com.example.matriculacion.asignaturaactivity.FragmentDialogAsignatura;
import com.example.matriculacion.database.entidades.Asignatura;
import com.example.matriculacion.util.DialogListener;

import java.util.List;

public class AdapterAsignaturaPrincipal extends RecyclerView.Adapter {

    private Context context;
    private List<Asignatura> asignaturas;
    private DialogListener listener;

    public AdapterAsignaturaPrincipal(Context context) {
        this.context = context;
    }

    //setear el listener
    public void setListener(DialogListener listener) {
        this.listener = listener;
    }

    //setear las asignaturas y notificarlo al recyclerview
    public void setItems(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
        notifyDataSetChanged();
    }

    //inflar el holder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.item_layout_asignatura,null);
        return new Holder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Asignatura asignatura = asignaturas.get(position);
        Holder holderAsignatura = (Holder) holder;
        holderAsignatura.tvCodNombre.setText(asignatura.getCodigo() + " " + asignatura.getNombre());
        holderAsignatura.tvNumAlumnos.setText(asignatura.getNumAlumnos() + " alumnos matriculados.");
    }

    @Override
    public int getItemCount() {
        return asignaturas == null ? 0 : asignaturas.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvCodNombre;
        TextView tvNumAlumnos;
        ImageButton imbBorrar;
        ImageButton imbEditar;

        public Holder(@NonNull View vi) {
            super(vi);

            tvCodNombre = (TextView) vi.findViewById(R.id.tvCodNombre);
            tvNumAlumnos = (TextView) vi.findViewById(R.id.tvNumAlumnos);
            imbBorrar = (ImageButton) vi.findViewById(R.id.imbBorrar);
            imbEditar = (ImageButton) vi.findViewById(R.id.imbEditar);

            imbBorrar.setOnClickListener(this);
            imbEditar.setOnClickListener(this);
        }

        //le damos comportamiento al los botones
        @Override
        public void onClick(View view) {
            Asignatura a = asignaturas.get(getAdapterPosition());
            switch(view.getId()){
                case R.id.imbBorrar: //botón borrar
                    listener.onClickBorrar(a);
                    break;
                case R.id.imbEditar: //boton editar
                    FragmentDialogAsignatura fragmentDialogAsignatura = new FragmentDialogAsignatura(a,listener);
                    fragmentDialogAsignatura.show(((AppCompatActivity) context).getSupportFragmentManager(),"Modificar asignatura");
                    break;
            }
        }
    }
}