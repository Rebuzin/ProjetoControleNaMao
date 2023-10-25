package com.example.controlenamao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.controlenamao.helper.SQLiteDataHelper;
import com.example.controlenamao.model.Veiculo;

import java.util.ArrayList;

public class VeiculoDao implements GenericDao<Veiculo> {

    //abrir a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados(BD)
    private SQLiteDatabase bd;

    //Nome das colunas da tabela
    private String[]colunas = {"RENAMED"};

    //Nome da tabela
    private String tableName = "VEICULO";

    //Contexto, quem executa
    private Context context;

    private static VeiculoDao instancia;

    //Metodo que cria a instancia uma unica vez, toda vez que for
    //Necessário utilizar essa classe, retorna sempre a mesma instancia
    public static VeiculoDao getInstancia(Context context) {

        if(instancia == null)
            return instancia = new VeiculoDao(context);
        else
            return instancia;

    }

    private VeiculoDao(Context context) {
        this.context = context;

        //Carregando base de dados
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);

        //Atribuindo a base de dados a variavel e dando permissão para escrever nas tabelas
        bd = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Veiculo obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put("RENAMED", obj.getRenamed());

            //metodo para inserir na tabela (<nome da tabela>, <coluna especifica que queira inserir>, <dados>)
            //retorna a linha que foi inserida na tabela
            return bd.insert(tableName,null, valores);
        }catch (SQLException ex){
            Log.e("ERRO","VeiculoDAO.insert(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Veiculo obj) {
        return 0;
    }

    @Override
    public long delete(Veiculo obj) {
        return 0;
    }

    @Override
    public ArrayList<Veiculo> getAll() {
        return null;
    }

    @Override
    public Veiculo getById(int id) {
        return null;
    }
}
