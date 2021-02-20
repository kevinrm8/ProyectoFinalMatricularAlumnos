package com.example.matriculacion.alumnoactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.matriculacion.database.entidades.Alumno;
import com.example.matriculacion.database.repositorio.AlumnoImp;

import java.util.List;

public class AlumnoViewModel extends AndroidViewModel {
    private AlumnoImp alumnoImp;

    public AlumnoViewModel(@NonNull Application application) {
        super(application);
        alumnoImp = new AlumnoImp(application);
    }

    //Alumnos que hay en la base de datos
    public LiveData<List<Alumno>> getAll() {
        return alumnoImp.getAlumnos();
    }

    //insertar alumno en la base de datos
    public void insert(Alumno alumno) {
        alumnoImp.addAlumno(alumno);
    }

    public void update(Alumno alumno){
        alumnoImp.updateAlumno(alumno);
    }

    public void delete(Alumno alumno){
        alumnoImp.deleteAlumno(alumno);
    }
}