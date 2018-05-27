package com.example.infante.crud_bbdd;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button botonInsertar, botonActualizar, botonBorrar, botonBuscar, botonLimpiar, botonNuevaSangria;
    EditText textoId, texttoNombre, textoApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonInsertar =  findViewById(R.id.insertar);
        botonActualizar =  findViewById(R.id.actualizar);
        botonBorrar =  findViewById(R.id.borrar);
        botonBuscar =  findViewById(R.id.buscar);
        botonLimpiar =  findViewById(R.id.limpiar);
        botonNuevaSangria = findViewById(R.id.nueva_sangria);

        textoId =  findViewById(R.id.id);
        texttoNombre =  findViewById(R.id.nombre);;
        textoApellido =  findViewById(R.id.apellido);

        final BBDD_Helper helper = new BBDD_Helper(this);

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textoId.setText("");
                texttoNombre.setText("");
                textoApellido.setText("");
            }
        });
        botonNuevaSangria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botonNuevaSangria = new Intent(MainActivity.this, NuevaSangria.class);
                startActivity(botonNuevaSangria);
            }
        });

        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gets the data repository in write mode
                SQLiteDatabase db = helper.getWritableDatabase();


// Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(Estructura_BBDD.NOMBRE_COLUMNA1, textoId.getText().toString());
                values.put(Estructura_BBDD.NOMBRE_COLUMNA2, texttoNombre.getText().toString());
                values.put(Estructura_BBDD.NOMBRE_COLUMNA3, textoApellido.getText().toString());


// Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(Estructura_BBDD.TABLE_NAME, null, values);

                Toast.makeText(getApplicationContext(), "Se guardo el registro con clave: " + newRowId, Toast.LENGTH_LONG).show();
                textoId.setText("");
                texttoNombre.setText("");
                textoApellido.setText("");


            }
        });
        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getWritableDatabase();

                // New value for one column
                ContentValues values = new ContentValues();
                values.put(Estructura_BBDD.NOMBRE_COLUMNA2, texttoNombre.getText().toString());
                values.put(Estructura_BBDD.NOMBRE_COLUMNA3, textoApellido.getText().toString());

                // Which row to update, based on the title
                String selection = Estructura_BBDD.NOMBRE_COLUMNA1 + " LIKE ?";
                String[] selectionArgs = { textoId.getText().toString() };

                int count = db.update(
                       // FeedReaderDbHelper.FeedEntry.TABLE_NAME,
                        Estructura_BBDD.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);

            }
        });
        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getWritableDatabase();
                // Define 'where' part of query.
                String selection = Estructura_BBDD.NOMBRE_COLUMNA1 + " LIKE ?";
                // Specify arguments in placeholder order.
                String[] selectionArgs = { textoId.getText().toString() };
                // Issue SQL statement.
                db.delete(Estructura_BBDD.TABLE_NAME, selection, selectionArgs);
                Toast.makeText(getApplicationContext(), "Se elimino el registro: " + textoId.getText(), Toast.LENGTH_LONG).show();
                textoId.setText("");
                texttoNombre.setText("");
                textoApellido.setText("");
            }
        });
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = helper.getReadableDatabase();

                // Define a projection that specifies which columns from the database
                // you will actually use after this query.
                String[] projection = {
                        //FeedEntry._ID,
                        Estructura_BBDD.NOMBRE_COLUMNA2,
                        Estructura_BBDD.NOMBRE_COLUMNA3,
                };

                // Filter results WHERE "title" = 'My Title'
                String selection = Estructura_BBDD.NOMBRE_COLUMNA1 + " = ?";
                String[] selectionArgs = {textoId.getText().toString()};

                // How you want the results sorted in the resulting Cursor
                /*String sortOrder =
                        FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";*/

                try {
                    Cursor cursor = db.query(
                            Estructura_BBDD.TABLE_NAME,     // The table to query
                            projection,                     // The columns to return
                            selection,                      // The columns for the WHERE clause
                            selectionArgs,                  // The values for the WHERE clause
                            null,                   // don't group the rows
                            null,                       // don't filter by row groups
                            null                        // The sort order
                    );

                    cursor.moveToFirst();
                    texttoNombre.setText(cursor.getString(0));
                    textoApellido.setText(cursor.getString(1));
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "No se encontro registro.", Toast.LENGTH_LONG).show();
                    textoId.setText("");
                    texttoNombre.setText("");
                    textoApellido.setText("");
                }

            }
        });


    }

}//final de la clse
