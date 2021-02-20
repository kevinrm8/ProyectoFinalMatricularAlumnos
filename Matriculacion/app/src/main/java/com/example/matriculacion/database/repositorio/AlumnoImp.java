package com.example.matriculacion.database.repositorio;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.matriculacion.database.dao.AlumnoDao;
import com.example.matriculacion.database.data.Database;
import com.example.matriculacion.database.entidades.Alumno;

import java.util.List;

public class AlumnoImp {

    private AlumnoDao alumnoDao;
    private Context appContext;

    public AlumnoImp(Context context) {
        appContext = context.getApplicationContext();
        Database INSTANCE = Database.getInstance(appContext);
        alumnoDao = INSTANCE.getAlumnoDao();
    }

    public LiveData<List<Alumno>> getAlumnos() {
        return alumnoDao.getAlumnos();
    }

    public void addAlumno(Alumno alumno) {
        try {
            alumnoDao.addAlumno(alumno);
        }catch (Exception e){
            Toast.makeText(appContext,"No se ha podido insertar",Toast.LENGTH_LONG).show();
        }
    }

    public void updateAlumno(Alumno alumno) {
        try {
            alumnoDao.updateAlumno(alumno);
        }catch (Exception e){
            Toast.makeText(appContext,"No se ha podido actualizar",Toast.LENGTH_LONG).show();
        }
    }

    public void deleteAlumno(Alumno alumno) {
        try{
            alumnoDao.updateAsignaturas(alumno.getDni());
            alumnoDao.deleteAlumno(alumno);
        }catch (Exception e){
            Toast.makeText(appContext,"No se ha podido borrar",Toast.LENGTH_LONG).show();
        }
    }
}