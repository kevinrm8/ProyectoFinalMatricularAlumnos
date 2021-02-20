package com.example.matriculacion.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.matriculacion.R;

import com.example.matriculacion.alumnoactivity.ActivityAlumnos;
import com.example.matriculacion.asignaturaactivity.ActivityAsignaturas;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnAsignaturas;
    private Button btnAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlumnos = findViewById(R.id.btnAlumnos);
        btnAsignaturas = findViewById(R.id.btnAsignaturas);

        btnAlumnos.setOnClickListener(this);
        btnAsignaturas.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAlumnos:
                startActivity(new Intent(this, ActivityAlumnos.class));
                break;
            case R.id.btnAsignaturas:
                startActivity(new Intent(this, ActivityAsignaturas.class));
                break;
            default:
                break;
        }
    }
}