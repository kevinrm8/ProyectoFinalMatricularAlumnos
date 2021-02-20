package com.example.matriculacion.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matriculacion.R;
import com.example.matriculacion.alumnoactivity.FragmentDialogAlumno;
import com.example.matriculacion.alumnodetalleactivity.ActivityAlumnosDetalle;
import com.example.matriculacion.database.entidades.Alumno;
import com.example.matriculacion.util.DialogListener;

import java.util.List;

public class AdapterAlumnoPrincipal extends RecyclerView.Adapter {

    private Context context;
    private List<Alumno> alumnos;
    private DialogListener listener;

    public AdapterAlumnoPrincipal(Context context){
        this.context = context;
    }

    //setear los alumnos y notificarlo al recyclerview
    public void setItems(List<Alumno> alumnos){
        this.alumnos = alumnos;
        notifyDataSetChanged();
    }

    //setear el listener
    public void setListener(DialogListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return alumnos == null ? 0 : alumnos.size();
    }

    //inflar el holder con los datos del alumno
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.item_layout_alumno,null);
        return new Holder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Alumno alumno = alumnos.get(position);
        Holder holderAlumno = (Holder) holder;
        holderAlumno.tvNombre.setText(alumno.getNombre());
        holderAlumno.tvApellidos.setText(alumno.getApellidos());
        holderAlumno.tvDni.setText(alumno.getDni());
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvNombre;
        TextView tvApellidos;
        TextView tvDni;
        ImageButton imbBorrar;
        ImageButton imbEditar;
        ImageButton imbDetalle;

        public Holder(@NonNull View vi) {
            super(vi);

            tvNombre = (TextView) vi.findViewById(R.id.tvNombre);
            tvApellidos = (TextView) vi.findViewById(R.id.tvApellidos);
            tvDni = (TextView) vi.findViewById(R.id.tvDni);

            imbBorrar = (ImageButton) vi.findViewById(R.id.imbBorrar);
            imbEditar = (ImageButton) vi.findViewById(R.id.imbEditar);
            imbDetalle = (ImageButton) vi.findViewById(R.id.imbDetalle);

            imbBorrar.setOnClickListener(this);
            imbEditar.setOnClickListener(this);
            imbDetalle.setOnClickListener(this);

        }

        //le damos comportamiento al los botones
        @Override
        public void onClick(View view) {
            Alumno a = alumnos.get(getAdapterPosition());
            switch(view.getId()){
                case R.id.imbBorrar: //botón borrar
                    listener.onClickBorrar(a);
                    break;
                case R.id.imbEditar: //botón editar
                    FragmentDialogAlumno fragmentDialogAlumno = new FragmentDialogAlumno(a,listener);
                    fragmentDialogAlumno.show(((AppCompatActivity) context).getSupportFragmentManager(),"Modificar alumno");
                    break;
                case R.id.imbDetalle: //boton detalle
                    Intent intent = new Intent(context, ActivityAlumnosDetalle.class);
                    intent.putExtra("alumno",alumnos.get(getAdapterPosition()));
                    context.startActivity(intent);
                    break;
            }
        }
    }
}