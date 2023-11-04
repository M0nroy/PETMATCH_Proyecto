package com.example.petmatch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ControladorDB extends SQLiteOpenHelper {
    public ControladorDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuarios (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, correo TEXT, password TEXT)");
        db.execSQL("CREATE TABLE mascota (id_mascota INTEGER PRIMARY KEY AUTOINCREMENT, tipo_mascota TEXT, nombre TEXT, raza TEXT, sexo TEXT, peso REAL, edad INTEGER)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2) {

    }
}
