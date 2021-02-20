package com.example.matriculacion.database.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.matriculacion.database.dao.AlumnoDao;
import com.example.matriculacion.database.dao.AlumnoRelAsignaturaDao;
import com.example.matriculacion.database.dao.AsignaturaDao;
import com.example.matriculacion.database.entidades.Alumno;
import com.example.matriculacion.database.entidades.AlumnoRelAsignatura;
import com.example.matriculacion.database.entidades.Asignatura;

@androidx.room.Database(entities = {Asignatura.class, Alumno.class, AlumnoRelAsignatura.class}, version = 3, exportSchema = false)
public abstract class Database extends RoomDatabase  {
    public abstract AsignaturaDao getAsignaturaDao();
    public abstract AlumnoDao getAlumnoDao();
    public abstract AlumnoRelAsignaturaDao getAlumnoRelAsignaturasDao();

    private static final String databaseName = "db_asignaturas";
    private static Database INSTANCE;

    //instanciar la base de datos
    public static Database getInstance(final Context context){
        INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, databaseName)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
        return INSTANCE;
    }
}