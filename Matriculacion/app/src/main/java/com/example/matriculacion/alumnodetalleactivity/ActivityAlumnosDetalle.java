package com.example.matriculacion.alumnodetalleactivity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matriculacion.Adapter.AdapterAlumnoDetalle;
import com.example.matriculacion.R;
import com.example.matriculacion.database.entidades.Alumno;
import com.example.matriculacion.database.entidades.AlumnoRelAsignatura;
import com.example.matriculacion.database.entidades.Asignatura;
import com.example.matriculacion.util.DialogListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActivityAlumnosDetalle extends AppCompatActivity implements DialogListener {

    private RecyclerView recyclerView;
    private AdapterAlumnoDetalle adapter;
    private DetalleViewModel mViewModel;

    private FloatingActionButton fabNuevaAsignatura;
    private List<Asignatura> asignaturasDisponibles;
    private Alumno alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos_detalle);
        alumno = (Alumno) getIntent().getSerializableExtra("alumno");

        //Cambiar el titulo
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetalle);
        toolbar.setTitle("Asignaturas de " + alumno.getNombre());

        ViewModelProvider.AndroidViewModelFactory factory =
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        mViewModel = new ViewModelProvider(this, factory).get(DetalleViewModel.class);

        loadAdapter();

        fabNuevaAsignatura = (FloatingActionButton) findViewById(R.id.fabNuevaAsignatura);
        fabNuevaAsignatura.setOnClickListener(b -> {
            asignarAsignatura();
        });
    }

    private void asignarAsignatura() {
        asignaturasDisponibles = mViewModel.getAsignaturasDisponiblesDeAlumno(alumno.getDni());
        if(asignaturasDisponibles == null || asignaturasDisponibles.isEmpty()){
            Toast.makeText(this,"No existen asignaturas", Toast.LENGTH_SHORT).show();
        }else{
            FragmentDialogAlumnoSeleccionar fragment = new FragmentDialogAlumnoSeleccionar(this,asignaturasDisponibles);
            fragment.show(getSupportFragmentManager(), "Añadir asignatura a " + alumno.getNombre());
        }
    }

    public void loadAdapter(){
        recyclerView = (RecyclerView) findViewById(R.id.rvAsignaturasAlumno);
        adapter = new AdapterAlumnoDetalle(this);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        mViewModel.getAsignaturasDeAlumno(alumno.getDni()).observe(this, adapter::setItems);
    }

    @Override
    public void onClickNueva(Object object) {
        String codigo = (String) object;
        AlumnoRelAsignatura relacion = new AlumnoRelAsignatura(alumno.getDni(),codigo);
        mViewModel.insert(relacion);
    }

    @Override
    public void onClickBorrar(Object object) {
        Asignatura asignatura = (Asignatura) object;
        AlumnoRelAsignatura relacion = new AlumnoRelAsignatura(alumno.getDni(),asignatura.getCodigo());
        mViewModel.delete(relacion);
    }

    @Override
    public void onClickEditar(Object object) {

    }
}