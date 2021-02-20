package com.example.matriculacion.database.entidades;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Asignatura {
    @PrimaryKey
    @NonNull
    private String codigo;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "num_alumnos")
    private int numAlumnos;

    //Genero constructor, setter and Getter y toString
    public Asignatura(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.numAlumnos = 0;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumAlumnos() {
        return numAlumnos;
    }

    public void setNumAlumnos(int numAlumnos) {
        this.numAlumnos = numAlumnos;
    }

    @Override
    public String toString() {
        return codigo + ", " + nombre;
    }

}