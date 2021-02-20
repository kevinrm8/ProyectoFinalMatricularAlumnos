package com.example.matriculacion.alumnodetalleactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.matriculacion.database.entidades.AlumnoRelAsignatura;
import com.example.matriculacion.database.entidades.Asignatura;
import com.example.matriculacion.database.repositorio.AlumnoRelAsignaturaImp;

import java.util.List;

public class DetalleViewModel extends AndroidViewModel {
    private AlumnoRelAsignaturaImp alumnoRelAsignaturaImp;

    public DetalleViewModel(@NonNull Application application) {
        super(application);
        alumnoRelAsignaturaImp = new AlumnoRelAsignaturaImp(application);
    }

    public LiveData<List<Asignatura>> getAsignaturasDeAlumno(String id) {
        return alumnoRelAsignaturaImp.getAsignaturasDeAlumno(id);
    }

    public List<Asignatura> getAsignaturasDisponiblesDeAlumno(String id) {
        return alumnoRelAsignaturaImp.getAsignaturasDisponiblesDeAlumno(id);
    }
    //Insertar asignatura en un alumno
    public void insert(AlumnoRelAsignatura alumnoRelAsignatura) {
        alumnoRelAsignaturaImp.addAlumnoConAsignaturas(alumnoRelAsignatura);
    }
    //Quitar la asignatura de un alumno
    public void delete(AlumnoRelAsignatura alumnoRelAsignatura){
        alumnoRelAsignaturaImp.deleteAlumnoConAsignaturas(alumnoRelAsignatura);
    }
}