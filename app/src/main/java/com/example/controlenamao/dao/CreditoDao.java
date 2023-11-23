package com.example.controlenamao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.controlenamao.helper.SQLiteDataHelper;
import com.example.controlenamao.model.Credito;
import com.example.controlenamao.model.Veiculo;

import java.util.ArrayList;

public class CreditoDao implements GenericDao<Credito> {

    //abrir a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados(BD)
    private SQLiteDatabase bd;

    //Nome das colunas da tabela
    private String[]colunas = {"VALOR, DATA"};

    //Nome da tabela
    private String tableName = "CREDITO";

    //Contexto, quem executa
    private Context context;

    private static CreditoDao instancia;

    //Metodo que cria a instancia uma unica vez, toda vez que for
    //Necessário utilizar essa classe, retorna sempre a mesma instancia
    public static CreditoDao getInstancia(Context context) {

        if(instancia == null)
            return instancia = new CreditoDao(context);
        else
            return instancia;

    }

    private CreditoDao(Context context) {
        this.context = context;

        //Carregando base de dados
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);

        //Atribuindo a base de dados a variavel e dando permissão para escrever nas tabelas
        bd = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Credito obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put("VALOR", obj.getValor());
            valores.put("DATA", String.valueOf(obj.getData()));

            //metodo para inserir na tabela (<nome da tabela>, <coluna especifica que queira inserir>, <dados>)
            //retorna a linha que foi inserida na tabela
            return bd.insert(tableName,null, valores);
        }catch (SQLException ex){
            Log.e("ERRO","CreditoDAO.insert(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Credito obj) {
        return 0;
    }

    @Override
    public long delete(Credito obj) {
        return 0;
    }

    @Override
    public ArrayList<Credito> getAll() {
        return null;
    }

    @Override
    public Credito getById(int id) {
        //TENTATIVA DE RETORNAR CREDITO POR ID / PRECISA SER VERIFICADO COMO PUXAR DADOS DE OUTROS DAO'S TAMBÉM
//        ArrayList<Credito> Credito = new ArrayList<>();
//        try {
//            Cursor cursor = bd.query(tableName, colunas,
//                    null, null,
//                    null, null, "VALOR");
//            if (cursor.moveToFirst()) {
//                do {
//                    Credito Credito = new Credito();
//                    Credito.setValor(cursor.getString(0));
//
//                    lista.add(credito);
//                } while (cursor.moveToNext());
//            }
//        } catch (SQLException ex) {
//            Log.e("ERRO", "CreditoDao.getAll(): " + ex.getMessage());
//        }
        return null;
    }
}