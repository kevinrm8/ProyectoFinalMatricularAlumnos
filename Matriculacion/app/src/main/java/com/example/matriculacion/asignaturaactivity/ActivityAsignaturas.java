package com.example.matriculacion.asignaturaactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matriculacion.Adapter.AdapterAsignaturaPrincipal;
import com.example.matriculacion.R;
import com.example.matriculacion.database.entidades.Asignatura;
import com.example.matriculacion.util.DialogListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityAsignaturas extends AppCompatActivity implements DialogListener {

    private RecyclerView recyclerView;
    private AdapterAsignaturaPrincipal adapter;
    private AsignaturaViewModel mViewModel;
    private FloatingActionButton fabAltaAsignatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);

        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        mViewModel = new ViewModelProvider(this, factory).get(AsignaturaViewModel.class);
        loadAdapter();

        fabAltaAsignatura = (FloatingActionButton) findViewById(R.id.fabAltaAsignatura);
        fabAltaAsignatura.setOnClickListener(b -> {
            nuevaAsignatura();
        });
    }

    public void loadAdapter(){
        recyclerView = (RecyclerView) findViewById(R.id.rvAsignaturas);
        adapter = new AdapterAsignaturaPrincipal(this);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        mViewModel.getAll().observe(this, adapter::setItems);
    }

    public void nuevaAsignatura(){
        FragmentDialogAsignatura fragmentDialogAsignatura = new FragmentDialogAsignatura(this);
        fragmentDialogAsignatura.show(getSupportFragmentManager(),"Asignatura insertada");
    }

    @Override
    public void onClickNueva(Object o) {
        mViewModel.insert((Asignatura) o);
    }

    @Override
    public void onClickEditar(Object o) {
        mViewModel.update((Asignatura) o);
    }

    @Override
    public void onClickBorrar(Object o) {
        mViewModel.delete((Asignatura) o);
    }
}