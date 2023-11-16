package com.example.controlenamao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.controlenamao.helper.SQLiteDataHelper;
import com.example.controlenamao.model.Debito;

import java.util.ArrayList;

public class DebitoDao implements GenericDao<Debito> {

    //abrir a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados(BD)
    private SQLiteDatabase bd;

    //Nome das colunas da tabela
    private String[]colunas = {"VALOR, DATA"};

    //Nome da tabela
    private String tableName = "DEBITO";

    //Contexto, quem executa
    private Context context;

    private static DebitoDao instancia;

    //Metodo que cria a instancia uma unica vez, toda vez que for
    //Necessário utilizar essa classe, retorna sempre a mesma instancia
    public static DebitoDao getInstancia(Context context) {

        if(instancia == null)
            return instancia = new DebitoDao(context);
        else
            return instancia;

    }

    private DebitoDao(Context context) {
        this.context = context;

        //Carregando base de dados
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);

        //Atribuindo a base de dados a variavel e dando permissão para escrever nas tabelas
        bd = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Debito obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put("VALOR", obj.getValor());
            valores.put("DATA", String.valueOf(obj.getData()));

            //metodo para inserir na tabela (<nome da tabela>, <coluna especifica que queira inserir>, <dados>)
            //retorna a linha que foi inserida na tabela
            return bd.insert(tableName,null, valores);
        }catch (SQLException ex){
            Log.e("ERRO","DebitoDAO.insert(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Debito obj) {
        return 0;
    }

    @Override
    public long delete(Debito obj) {
        return 0;
    }

    @Override
    public ArrayList<Debito> getAll() {
        return null;
    }

    @Override
    public Debito getById(int id) {
        return null;
    }
}