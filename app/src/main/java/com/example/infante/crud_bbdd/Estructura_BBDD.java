package com.example.infante.crud_bbdd;

/**
 * Created by infante on 2/2/2018.
 */

public class Estructura_BBDD {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Estructura_BBDD() {}

    /* Inner class that defines the table contents */
    //public static class FeedEntry implements BaseColumns
    public static final String TABLE_NAME = "datosPersonales";
    public static final String NOMBRE_COLUMNA1 = "Id";
    public static final String NOMBRE_COLUMNA2 = "Nombre";
    public static final String NOMBRE_COLUMNA3 ="Apellido";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Estructura_BBDD.TABLE_NAME + " (" +
                    Estructura_BBDD.NOMBRE_COLUMNA1 + " INTEGER PRIMARY KEY," +
                    Estructura_BBDD.NOMBRE_COLUMNA2 + " TEXT," +
                    Estructura_BBDD.NOMBRE_COLUMNA3 + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Estructura_BBDD.TABLE_NAME;
}
