package meu_curriculo.av2.dias.rafael.meucurriculo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import meu_curriculo.av2.dias.rafael.meucurriculo.model.Curriculum;
import meu_curriculo.av2.dias.rafael.meucurriculo.model.DbGateway;

/**
 * Created by rafadias on 09/12/17.
 */
public class CurriculumDAO {

    private final String TABLE_CLIENTES = "Perfis";
    private DbGateway gw;

    public CurriculumDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public boolean salvar(String nome, String email, String phone, String cep, String rua, String cidade, String uf){
        ContentValues cv = new ContentValues();
        cv.put("name", nome);
        cv.put("email", email);
        cv.put("phone", phone);
        cv.put("cep", cep);
        cv.put("street", rua);
        cv.put("city", cidade);
        cv.put("uf", uf);

        return gw.getDatabase().insert(TABLE_CLIENTES, null, cv) > 0;
    }

    public Curriculum retornarUltimo(){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Perfis ORDER BY ID DESC",
                null);
        if(cursor.moveToFirst()){
            String nome = cursor.getString(cursor.getColumnIndex("name"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String cep = cursor.getString(cursor.getColumnIndex("cep"));
            String street = cursor.getString(cursor.getColumnIndex("street"));
            String city = cursor.getString(cursor.getColumnIndex("city"));
            String uf = cursor.getString(cursor.getColumnIndex("uf"));
            cursor.close();
            return new Curriculum(nome, email, phone, cep, street, city, uf);
        }
        return null;
    }
}