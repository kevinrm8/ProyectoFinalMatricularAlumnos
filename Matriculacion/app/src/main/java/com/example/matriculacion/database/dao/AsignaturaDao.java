package com.example.matriculacion.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.matriculacion.database.entidades.Asignatura;

import java.util.List;

@Dao
public interface AsignaturaDao {
    @Transaction
    @Query("SELECT * FROM asignatura ORDER BY codigo")
    LiveData<List<Asignatura>> getAsignaturas();

    @Transaction
    @Query("SELECT * FROM asignatura WHERE codigo LIKE :uuid")
    Asignatura getAsignatura(String uuid);

    @Transaction
    @Insert
    void addAsignatura(Asignatura asignatura);

    @Transaction
    @Delete
    void deleteAsignatura(Asignatura asignatura);

    @Transaction
    @Update
    void updateAsignatura(Asignatura asignatura);
}