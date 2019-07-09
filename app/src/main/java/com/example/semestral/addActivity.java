package com.example.semestral;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import com.example.semestral.APPSQLiteOpenHelper;


public class addActivity extends AppCompatActivity {

    private EditText titulo, ubicacion,inicio,fin,asistente,codigo;
    int id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        titulo= (EditText)findViewById(R.id.txtTitulo);
        ubicacion= (EditText)findViewById(R.id.txtUbicacion);
        inicio= (EditText)findViewById(R.id.txtInicio);
        fin= (EditText)findViewById(R.id.txtFin);
        asistente= (EditText)findViewById(R.id.txtAsist);
        codigo= (EditText)findViewById(R.id.txtcodigo);

    }



    public void guardar(View view){
        APPSQLiteOpenHelper con= new APPSQLiteOpenHelper(this);
        SQLiteDatabase sql = con.getWritableDatabase();

        String tit= titulo.getText().toString();
        String ubic= ubicacion.getText().toString();
        String init= inicio.getText().toString();
        String end= fin.getText().toString();
        String assis= asistente.getText().toString();


        if(!tit.isEmpty() && !ubic.isEmpty() && !init.isEmpty() && !end.isEmpty() && !assis.isEmpty()){
            ContentValues registro= new ContentValues();

            registro.put("titulo",tit);
            registro.put("ubicacion",ubic);
            registro.put("fecha_inicio",init);
            registro.put("fecha_final",end);
            registro.put("asistentes",assis);

            sql.insert("notas",null,registro);

            sql.close();
            titulo.setText("");
            ubicacion.setText("");
            inicio.setText("");
            fin.setText("");
            asistente.setText("");

            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void eliminar(View view){
        APPSQLiteOpenHelper con= new APPSQLiteOpenHelper(this);
        SQLiteDatabase sql = con.getWritableDatabase();

        String cod= codigo.getText().toString();


        if(!cod.isEmpty()){
            int cantidad = sql.delete("notas","_id=" + cod,null);
            sql.close();
            titulo.setText("");
            ubicacion.setText("");
            inicio.setText("");
            fin.setText("");
            asistente.setText("");

            if(cantidad >0){
                Toast.makeText(this, "Registro eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El registro no existe", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Debes llenar el campo requerido", Toast.LENGTH_SHORT).show();

        }

    }


}
