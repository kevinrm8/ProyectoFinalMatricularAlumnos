package com.example.matriculacion.asignaturaactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.matriculacion.database.entidades.Asignatura;
import com.example.matriculacion.database.repositorio.AsignaturaImp;

import java.util.List;

public class AsignaturaViewModel extends AndroidViewModel {

    private AsignaturaImp asignaturaImp;

    public AsignaturaViewModel(@NonNull Application application) {
        super(application);
        asignaturaImp = new AsignaturaImp(application);
    }

    public LiveData<List<Asignatura>> getAll() {
        return asignaturaImp.getAsignaturas();
    }

    public void insert(Asignatura asignatura) {
        asignaturaImp.addAsignatura(asignatura);
    }

    public void update(Asignatura asignatura){
        asignaturaImp.updateAsignatura(asignatura);
    }

    public void delete(Asignatura asignatura){
        asignaturaImp.deleteAsignatura(asignatura);
    }

}