package com.example.matriculacion.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.matriculacion.database.entidades.AlumnoRelAsignatura;
import com.example.matriculacion.database.entidades.Asignatura;

import java.util.List;

@Dao
public interface AlumnoRelAsignaturaDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addAlumnoConAsignaturas(AlumnoRelAsignatura asignatura);

    @Transaction
    @Delete
    void deleteAlumnoConAsignaturas(AlumnoRelAsignatura asignatura);

    //Asignaturas del alumno
    @Transaction
    @Query("SELECT a.* FROM asignatura a INNER JOIN alumno_rel_asignatura rel ON a.codigo = rel.codigo WHERE rel.dni LIKE :dni")
    LiveData<List<Asignatura>> getAsignaturasDeAlumno(String dni);

    @Transaction
    @Query("SELECT a.* FROM asignatura a WHERE a.codigo NOT IN " +
            "(SELECT a2.codigo FROM asignatura a2 INNER JOIN alumno_rel_asignatura rel ON a2.codigo = rel.codigo WHERE rel.dni like :dni)")
    List<Asignatura> getAsignaturasDisponiblesDeAlumno(String dni);


    //Aumentar o disminuir la cantidad de alumnos matriculados
    @Transaction
    @Query("UPDATE asignatura SET num_alumnos=num_alumnos+1 WHERE codigo LIKE :codigo")
    void updateAsignaturasSumar(String codigo);

    @Transaction
    @Query("UPDATE asignatura SET num_alumnos=num_alumnos-1 WHERE codigo LIKE :codigo")
    void updateAsignaturasRestar(String codigo);
}