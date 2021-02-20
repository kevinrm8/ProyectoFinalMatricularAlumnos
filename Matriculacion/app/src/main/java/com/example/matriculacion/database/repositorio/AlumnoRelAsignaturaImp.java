package com.example.matriculacion.database.repositorio;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.matriculacion.database.dao.AlumnoRelAsignaturaDao;
import com.example.matriculacion.database.data.Database;
import com.example.matriculacion.database.entidades.AlumnoRelAsignatura;
import com.example.matriculacion.database.entidades.Asignatura;

import java.util.List;

public class AlumnoRelAsignaturaImp {

    private AlumnoRelAsignaturaDao asignaturaRelDao;
    private Context appContext;

    public AlumnoRelAsignaturaImp(Context context) {
        appContext = context.getApplicationContext();
        Database INSTANCE = Database.getInstance(appContext);
        asignaturaRelDao = INSTANCE.getAlumnoRelAsignaturasDao();
    }

    public void addAlumnoConAsignaturas(AlumnoRelAsignatura asignatura) {
        try{
            asignaturaRelDao.addAlumnoConAsignaturas(asignatura);
            asignaturaRelDao.updateAsignaturasSumar(asignatura.getCodigo());
        }catch (Exception e){
            Toast.makeText(appContext,"No se ha podido insertar",Toast.LENGTH_LONG).show();
        }
    }

    public void deleteAlumnoConAsignaturas(AlumnoRelAsignatura asignatura) {
        try{
            asignaturaRelDao.deleteAlumnoConAsignaturas(asignatura);
            asignaturaRelDao.updateAsignaturasRestar(asignatura.getCodigo());
        }catch (Exception e){
            Toast.makeText(appContext,"No se ha podido borrar",Toast.LENGTH_LONG).show();
        }
    }

    public LiveData<List<Asignatura>> getAsignaturasDeAlumno(String id){
        return asignaturaRelDao.getAsignaturasDeAlumno(id);
    }
    public List<Asignatura> getAsignaturasDisponiblesDeAlumno(String id){
        return asignaturaRelDao.getAsignaturasDisponiblesDeAlumno(id);
    }

}