package com.example.controlenamao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.controlenamao.helper.SQLiteDataHelper;
import com.example.controlenamao.model.FiltroVo.HomeFiltroVo;
import com.example.controlenamao.model.Frete;
import com.example.controlenamao.model.Gasto;
import com.example.controlenamao.model.Movimentacao;
import com.example.controlenamao.model.Veiculo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovimentacaoDao implements GenericDao<Movimentacao> {

    //abrir a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados(BD)
    private SQLiteDatabase bd;

    //Nome das colunas da tabela
    private String[] colunas = {"VALOR, DATA, TIPO, VEICULO_ID"};

    //Nome da tabela
    private String tableName = "MOVIMENTACAO";

    //Contexto, quem executa
    private Context context;

    private static MovimentacaoDao instancia;

    //Metodo que cria a instancia uma unica vez, toda vez que for
    //Necessário utilizar essa classe, retorna sempre a mesma instancia
    public static MovimentacaoDao getInstancia(Context context) {

        if (instancia == null)
            return instancia = new MovimentacaoDao(context);
        else
            return instancia;

    }

    private MovimentacaoDao(Context context) {
        this.context = context;

        //Carregando base de dados
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 1);

        //Atribuindo a base de dados a variavel e dando permissão para escrever nas tabelas
        bd = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Movimentacao obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("VALOR", obj.getValor());
            valores.put("DATA", String.valueOf(obj.getData()));
            valores.put("TIPO", obj.getTipo());

            if (obj.getVeiculo() != null) {
                valores.put("VEICULO_ID", obj.getVeiculo().getId());
            }

            if (obj.getFrete() != null) {
                valores.put("FRETE_ID", obj.getFrete().getId());
            }

            if (obj.getGasto() != null) {
                valores.put("GASTO_ID", obj.getGasto().getId());
            }

            //metodo para inserir na tabela (<nome da tabela>, <coluna especifica que queira inserir>, <dados>)
            //retorna a linha que foi inserida na tabela
            return bd.insert(tableName, null, valores);
        } catch (SQLException ex) {
            Log.e("ERRO", "MovimentacaoDao.insert(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Movimentacao obj) {
        return 0;
    }
    @Override
    public long delete(Movimentacao obj) {
        String[]identificador = {String.valueOf(obj.getId())};
        return bd.delete(tableName,"ID = ?",identificador);
    }

    @Override
    public ArrayList<Movimentacao> getAll() {
        return null;
    }

    @Override
    public Movimentacao getById(Long id) {
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
//            Log.e("ERRO", "MovimentacaoDao.getAll(): " + ex.getMessage());
//        }
        return null;
    }


    //////////////////////////////
    //  Busca saldos
    //////////////////////////////
    public Double buscarDebitoByGasto(Gasto gasto, HomeFiltroVo filtro) {
        Double valor = 0d;
        try {

            String sql = "select sum(VALOR) " +
                    "from MOVIMENTACAO " +
                    "where TIPO = 'D' AND GASTO_ID = " + gasto.getId() + "" +
                    " ;"; //GASTO ID 3 = COMBUSTIVEL DE FORMA FIXA

            if(filtro != null && filtro.getIdVeiculo() != null){
                sql = "select sum(VALOR) " +
                        "from MOVIMENTACAO " +
                        "where TIPO = 'D' AND GASTO_ID = " + gasto.getId() + " AND VEICULO_ID = " + filtro.getIdVeiculo() +
                        " ;"; //GASTO ID 3 = COMBUSTIVEL DE FORMA FIXA
            }

            Cursor c = bd.rawQuery(sql, null);
            if (c.moveToFirst()) {
                valor = c.getDouble(0);
            } else {
                valor = 0d;
            }

            c.close();

        } catch (SQLException ex) {
            Log.e("ERRO", "MovimentacaoDao.buscaSaldoCombustivel(): " + ex.getMessage());
        }
        return valor;
    }

    public Double buscarCreditosFrete(HomeFiltroVo filtro) {
        Double valor = 0d;
        try {

            String sql = "select sum(VALOR) " +
                    "from MOVIMENTACAO " +
                    "where TIPO = 'C' AND FRETE_ID NOT NULL;"; //Busca todos os creditos de frete
            if(filtro != null && filtro.getIdVeiculo() != null){
                sql = "select sum(VALOR) " +
                        "from MOVIMENTACAO " +
                        "where TIPO = 'C' AND FRETE_ID NOT NULL AND VEICULO_ID = " + filtro.getIdVeiculo() +
                        " ;"; //GASTO ID 3 = COMBUSTIVEL DE FORMA FIXA
            }
            Cursor c = bd.rawQuery(sql, null);
            if (c.moveToFirst()) {
                valor = c.getDouble(0);
            } else {
                valor = 0d;
            }

            c.close();

        } catch (SQLException ex) {
            Log.e("ERRO", "MovimentacaoDao.buscaSaldoCombustivel(): " + ex.getMessage());
        }
        return valor;
    }

    public ArrayList<Movimentacao> getAllCredito() {
        ArrayList<Movimentacao> lista = new ArrayList<>();
        try {

            String sql = "select ID, VALOR, DATA, TIPO, VEICULO_ID " +
                    "from MOVIMENTACAO " +
                    "where TIPO = 'C' AND FRETE_ID NOT NULL " +
                    "ORDER BY DATA ;";

            Cursor cursor = bd.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {

                    SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-mm-dd");

                    Movimentacao movimentacao = new Movimentacao();
                    movimentacao.setId(cursor.getInt(0));
                    movimentacao.setValor(cursor.getDouble(1));
                    movimentacao.setData(new java.util.Date(Date.parse(cursor.getString(2).replaceAll("-03:00", ""))));
                    movimentacao.setTipo(cursor.getString(3));
//                    movimentacao.setVeiculo(Long.getId(String.valueOf(3)));

                    lista.add(movimentacao);
                } while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "MovimentacaoDao.getAll(): " + ex.getMessage());
        }
        return lista;
    }

    public ArrayList<Movimentacao> getAllDebito() {
        ArrayList<Movimentacao> lista = new ArrayList<>();
        try {

            String sql = "select ID, VALOR, DATA, TIPO, VEICULO_ID " +
                    "from MOVIMENTACAO " +
                    "where TIPO = 'D' AND GASTO_ID NOT NULL " +
                    "ORDER BY DATA ;";

            Cursor cursor = bd.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    Movimentacao movimentacao = new Movimentacao();
                    movimentacao.setId(cursor.getInt(0));
                    movimentacao.setValor(cursor.getDouble(1));
                    movimentacao.setData(new java.util.Date(Date.parse(cursor.getString(2).replaceAll("-03:00", ""))));
                    movimentacao.setTipo(cursor.getString(3));
//                    movimentacao.setVeiculo(Long.getId(String.valueOf(3)));

                    lista.add(movimentacao);
                } while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "MovimentacaoDao.getAll(): " + ex.getMessage());
        }
        return lista;
    }
}