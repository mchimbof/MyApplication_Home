package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;
import android.util.Patterns;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.security.AccessController;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
 Spinner spinnerGenero;
 TextView item_select;
 Button enviar;
 EditText txtcedula, txtnombre, txtfecha, txtciudad, txtcorreo, txttelefono;
 DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtcedula=(EditText)findViewById(R.id.cedula_txt);
        txtnombre=(EditText)findViewById(R.id.nombre_txt);
        txtciudad=(EditText)findViewById(R.id.ciudad_txt);
        txtfecha=(EditText)findViewById(R.id.fecha_txt);
        item_select=(TextView)findViewById(R.id.genero_label);
        txtcorreo=(EditText)findViewById(R.id.correo_txt);
        txttelefono=(EditText)findViewById(R.id.telefono_txt);

        //Lista Desplegable
        spinnerGenero = (Spinner)findViewById(R.id.spinner_genero);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.genero, android.R.layout.simple_spinner_item);
        spinnerGenero.setAdapter(adapter);
        spinnerGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item_select.setText(parent.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Fecha
        txtfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener, year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String fecha_label= month+"/"+dayOfMonth+"/"+year;
                txtfecha.setText(fecha_label);
            }
        };

        //Desplazar a sig. UI
        enviar = (Button)findViewById(R.id.btnEnviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("","Boton Enviar");
                String generoS =item_select.getText().toString();
                String email = txtcorreo.getText().toString().trim();

                if(txtcedula.getText().toString().isEmpty() || txtnombre.getText().toString().isEmpty() || txtfecha.getText().toString().isEmpty() || txtciudad.getText().toString().isEmpty() ||
                        generoS.equalsIgnoreCase("Seleccionar") || !Patterns.EMAIL_ADDRESS.matcher(email).matches()  || txttelefono.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"No se admite campos vacios o Seleccion...",Toast.LENGTH_SHORT).show();
                }else{
                    Bundle E_idData = new Bundle();
                    E_idData.putString("keyCedula", txtcedula.getText().toString());

                    Bundle E_contentData = new Bundle();
                    String[] contentData = new String[]{txtnombre.getText().toString(), txtfecha.getText().toString(), txtciudad.getText().toString(), item_select.getText().toString(), txtcorreo.getText().toString(), txttelefono.getText().toString()};
                    E_contentData.putStringArray("keyData", contentData);

                    Intent x = new Intent(MainActivity.this, Proceso.class);
                    x.putExtras(E_idData);
                    x.putExtras(E_contentData);
                    startActivity(x);
                }
            }
        });
    }
    public void btnCancelar(View v){
        Log.d("Funcion...","Cancelar");
        txtcedula.setText("");
        txtnombre.setText("");
        txtciudad.setText("");
        txtfecha.setText("");
        item_select.setText("");
        txtcorreo.setText("");
        txttelefono.setText("");
        spinnerGenero.setSelection(0);
    }

}
