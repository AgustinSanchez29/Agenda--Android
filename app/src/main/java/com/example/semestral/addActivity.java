package com.example.semestral;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.example.semestral.APPSQLiteOpenHelper;


public class addActivity extends AppCompatActivity {

    private EditText titulo, nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        titulo= (EditText)findViewById(R.id.txtTitulo);
        nota= (EditText)findViewById(R.id.txtNota);
    }



    public void guardar(View view){
        APPSQLiteOpenHelper con= new APPSQLiteOpenHelper(this);
        SQLiteDatabase sql = con.getWritableDatabase();

        String tit= titulo.getText().toString();
        String not= nota.getText().toString();

        if(!tit.isEmpty() && !not.isEmpty()){
            ContentValues registro= new ContentValues();

            registro.put("titulo",tit);
            registro.put("nota",not);

            sql.insert("notas",null,registro);

            sql.close();
            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void Eliminar(View view){
        APPSQLiteOpenHelper con= new APPSQLiteOpenHelper(this);
        SQLiteDatabase sql = con.getWritableDatabase();

        String tit= titulo.getText().toString();

        if(!tit.isEmpty()){
            int cantidad = sql.delete("notas","titulo=" + tit,null);
            sql.close();
            titulo.setText("");
            nota.setText("");

            if(cantidad == 1){
                Toast.makeText(this, "Artículo eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }


        }
        else{
            Toast.makeText(this, "Debes de introducir el código del artículo", Toast.LENGTH_SHORT).show();

        }

    }


}
