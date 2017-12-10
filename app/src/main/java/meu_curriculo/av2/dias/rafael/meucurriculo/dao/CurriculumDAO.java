package meu_curriculo.av2.dias.rafael.meucurriculo.dao;

import android.content.ContentValues;
import android.content.Context;

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
}