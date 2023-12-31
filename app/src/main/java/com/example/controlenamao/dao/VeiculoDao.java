package com.example.controlenamao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    private String[]colunas = {"ID, RENAMED"};

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
//            valores.put("ID", obj.getId());
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
        String[]identificador = {String.valueOf(obj.getId())};
        return bd.delete(tableName,"ID = ?",identificador);
    }

    @Override
    public ArrayList<Veiculo> getAll() {
        ArrayList<Veiculo> lista = new ArrayList<>();
        try {
            Cursor cursor = bd.query(tableName, colunas,
                    null, null,
                    null, null, "RENAMED");
            if (cursor.moveToFirst()) {
                do {
                    Veiculo veiculo = new Veiculo();
                    veiculo.setId(cursor.getLong(0));
                    veiculo.setRenamed(cursor.getString(1));

                    lista.add(veiculo);
                } while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "VeiculoDao.getAll(): " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Veiculo getById(Long id) {
        return null;
    }

    public int buscaId(String renamed){
        int idVeiculo = 0;
        try {
            String sql = "select ID " +
                    "from VEICULO " +
                    "where RENAMED = " + renamed;
            Cursor c = bd.rawQuery(sql, null);
            if (c.moveToFirst()) {
                idVeiculo = c.getInt(0);
            } else {
                idVeiculo = 0;
            }

            c.close();
        }catch (SQLException ex) {
            Log.e("ERRO", "VeiculoDao.buscaId(): " + ex.getMessage());
        }
        return idVeiculo;
    }
}
