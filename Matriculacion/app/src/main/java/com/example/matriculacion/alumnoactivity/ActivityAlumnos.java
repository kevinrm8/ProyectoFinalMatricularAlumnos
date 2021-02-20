package com.example.matriculacion.alumnoactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matriculacion.Adapter.AdapterAlumnoPrincipal;
import com.example.matriculacion.R;
import com.example.matriculacion.database.entidades.Alumno;
import com.example.matriculacion.util.DialogListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityAlumnos extends AppCompatActivity implements DialogListener {

    private RecyclerView recyclerView;
    private AdapterAlumnoPrincipal adapterAlumnoPrincipal;
    private FloatingActionButton fabAltaAlumno;
    private AlumnoViewModel alumnoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        alumnoViewModel = new ViewModelProvider(this, factory).get(AlumnoViewModel.class);
        cargarAdaptador();

        //configurar el FAB
        fabAltaAlumno = (FloatingActionButton) findViewById(R.id.fabAltaAlumno);
        fabAltaAlumno.setOnClickListener(b -> {
            insertaralumno();
        });
    }

    //Fragmento para insertar un alumno
    public void insertaralumno() {
        FragmentDialogAlumno fragmentDialogAlumno = new FragmentDialogAlumno(this);
        fragmentDialogAlumno.show(getSupportFragmentManager(),"Datos del nuevo Alumno");
    }

    //Mostrar los datos del view
    public void cargarAdaptador(){
        recyclerView = (RecyclerView) findViewById(R.id.rvAlumnos);
        adapterAlumnoPrincipal = new AdapterAlumnoPrincipal(this);
        recyclerView.setAdapter(adapterAlumnoPrincipal);
        adapterAlumnoPrincipal.setListener(this);
        alumnoViewModel.getAll().observe(this, adapterAlumnoPrincipal::setItems);
    }


    @Override
    public void onClickNueva(Object object) {
        Alumno alumno = (Alumno) object;
        alumnoViewModel.insert(alumno);
    }

    @Override
    public void onClickEditar(Object object) {
        Alumno alumno = (Alumno) object;
        alumnoViewModel.update(alumno);
    }

    @Override
    public void onClickBorrar(Object object) {
        Alumno alumno = (Alumno) object;
        alumnoViewModel.delete(alumno);
    }

}