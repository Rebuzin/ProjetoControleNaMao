package com.example.controlenamao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.controlenamao.helper.SQLiteDataHelper;
import com.example.controlenamao.model.Frete;
import com.example.controlenamao.model.Gasto;

import java.util.ArrayList;

public class GastoDao implements GenericDao<Gasto> {

    //abrir a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados(BD)
    private SQLiteDatabase bd;

    //Nome das colunas da tabela
    private String[]colunas = {"ID, NAME"};

    //Nome da tabela
    private String tableName = "GASTO";

    //Contexto, quem executa
    private Context context;

    private static GastoDao instancia;

    //Metodo que cria a instancia uma unica vez, toda vez que for
    //Necessário utilizar essa classe, retorna sempre a mesma instancia
    public static GastoDao getInstancia(Context context) {

        if(instancia == null)
            return instancia = new GastoDao(context);
        else
            return instancia;

    }

    private GastoDao(Context context) {
        this.context = context;

        //Carregando base de dados
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);

        //Atribuindo a base de dados a variavel e dando permissão para escrever nas tabelas
        bd = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Gasto obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put("NAME", obj.getName());

            //metodo para inserir na tabela (<nome da tabela>, <coluna especifica que queira inserir>, <dados>)
            //retorna a linha que foi inserida na tabela
            return bd.insert(tableName,null, valores);
        }catch (SQLException ex){
            Log.e("ERRO","GastoDAO.insert(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Gasto obj) {
        return 0;
    }

    @Override
    public long delete(Gasto obj) {
        return 0;
    }

    @Override
    public ArrayList<Gasto> getAll() {
        ArrayList<Gasto> lista = new ArrayList<>();
        try {
            Cursor cursor = bd.query(tableName, colunas,
                    null, null,
                    null, null, "NAME");
            if (cursor.moveToFirst()) {
                do {
                    Gasto gasto = new Gasto();
                    gasto.setId(cursor.getInt(0));
                    gasto.setName(cursor.getString(1));

                    lista.add(gasto);
                } while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "GastoDao.getAll(): " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Gasto getById(int id) {
        return null;
    }
}
