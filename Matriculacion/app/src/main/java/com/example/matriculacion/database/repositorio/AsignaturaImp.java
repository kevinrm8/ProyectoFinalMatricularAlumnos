package com.example.matriculacion.database.repositorio;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.matriculacion.database.dao.AsignaturaDao;
import com.example.matriculacion.database.data.Database;
import com.example.matriculacion.database.entidades.Asignatura;

import java.util.List;

public class AsignaturaImp {

    private AsignaturaDao asignaturaDao;
    private Context appContext;

    public AsignaturaImp(Context context) {
        appContext = context.getApplicationContext();
        Database INSTANCE = Database.getInstance(appContext);
        asignaturaDao = INSTANCE.getAsignaturaDao();
    }

    public LiveData<List<Asignatura>> getAsignaturas() {
        return asignaturaDao.getAsignaturas();
    }

    public Asignatura getAsignatura(String id) {
        return asignaturaDao.getAsignatura(id);
    }

    public void addAsignatura(Asignatura asignatura) {
        try{
            asignaturaDao.addAsignatura(asignatura);
        }catch (Exception e){
            Toast.makeText(appContext,"No se ha podido insertar",Toast.LENGTH_LONG).show();
        }
    }

    public void updateAsignatura(Asignatura asignatura) {
        try{
            asignaturaDao.updateAsignatura(asignatura);
        }catch (Exception e){
            Toast.makeText(appContext,"No se ha podido actualizar",Toast.LENGTH_LONG).show();
        }
    }

    public void deleteAsignatura(Asignatura asignatura) {
        try{
            asignaturaDao.deleteAsignatura(asignatura);
        }catch (Exception e){
            Toast.makeText(appContext,"No se ha podido borrar",Toast.LENGTH_LONG).show();
        }
    }
}