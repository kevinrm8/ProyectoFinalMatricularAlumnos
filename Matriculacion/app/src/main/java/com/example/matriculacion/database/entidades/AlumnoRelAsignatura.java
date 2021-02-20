package com.example.matriculacion.database.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "alumno_rel_asignatura",
        primaryKeys = {"dni", "codigo"},
        foreignKeys = {
                @ForeignKey(
                        entity = Alumno.class,
                        parentColumns = "dni",
                        childColumns = "dni",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(
                        entity = Asignatura.class,
                        parentColumns = "codigo",
                        childColumns = "codigo")
        }
)
public class AlumnoRelAsignatura {
    @NonNull
    @ColumnInfo(name = "dni")
    private String dni;

    @NonNull
    @ColumnInfo(name = "codigo", index = true)
    private String codigo;
    //Genero constructor, setter and Getter y toString

    public AlumnoRelAsignatura(@NonNull String dni, @NonNull String codigo) {
        this.codigo = codigo;
        this.dni = dni;
    }

    @NonNull
    public String getDni() {
        return dni;
    }

    public void setDni(@NonNull String dni) {
        this.dni = dni;
    }

    @NonNull
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(@NonNull String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "AlumnoRelAsignatura{" +
                "dni='" + dni + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}