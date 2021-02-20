package com.example.matriculacion.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.matriculacion.database.entidades.Alumno;

import java.util.List;

@Dao
public interface AlumnoDao {
    @Transaction
    @Query("SELECT * FROM alumno ORDER BY apellidos")
    LiveData<List<Alumno>> getAlumnos();

    @Transaction
    @Insert
    void addAlumno(Alumno alumno);

    @Transaction
    @Delete
    void deleteAlumno(Alumno alumno);

    //Resto 1 al borrar el alumno
    @Transaction
    @Query("UPDATE asignatura SET num_alumnos=num_alumnos-1 WHERE codigo IN " +
            "(SELECT a.codigo FROM asignatura a INNER JOIN alumno_rel_asignatura rel ON a.codigo = rel.codigo WHERE rel.dni LIKE :dni)")
    void updateAsignaturas(String dni);

    @Transaction
    @Update
    void updateAlumno(Alumno alumno);
}