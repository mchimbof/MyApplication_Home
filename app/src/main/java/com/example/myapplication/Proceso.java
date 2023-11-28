package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Proceso extends AppCompatActivity {
Button atras;
TextView rcedula_txt,rDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceso);

        rcedula_txt = (TextView) findViewById(R.id.rcedula_txt);
        rDatos = (TextView) findViewById(R.id.rDatos_txt);

        atras=(Button)findViewById(R.id.btnAtras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("","Boton Atras...");
                Intent z = new Intent(Proceso.this, MainActivity.class);
                startActivity(z);
            }
        });

        //

        Bundle R_idData = getIntent().getExtras();
        String cedula = R_idData.getString("keyCedula");
        rcedula_txt.setText("Cedula: "+cedula);

        Bundle R_contentData = getIntent().getExtras();
        String[] rdata = R_contentData.getStringArray("keyData");
        rDatos.setText("Nombre: "+rdata[0]+"\nFecha: "+rdata[1]+"\nCiudad: "+rdata[2]+"\nGenero: "+rdata[3]+"\nCorreo: "+rdata[4]+"\nTelefono: "+rdata[5]);
    }
}