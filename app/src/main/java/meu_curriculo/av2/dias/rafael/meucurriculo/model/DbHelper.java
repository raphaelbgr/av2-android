package meu_curriculo.av2.dias.rafael.meucurriculo.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Crud.db";
    private static final int DATABASE_VERSION = 1;
    private final String CREATE_TABLE = "" +
            "CREATE TABLE Perfis (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, " +
            "email TEXT, phone TEXT, cep TEXT, street TEXT, city TEXT, uf TEXT);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}