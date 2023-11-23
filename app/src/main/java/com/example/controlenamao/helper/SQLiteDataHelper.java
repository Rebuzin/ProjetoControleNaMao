package com.example.controlenamao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
//        sqLiteDatabase.execSQL("CREATE TABLE DEBITO (VALOR DOUBLE, DATA DATE, STATUS BOOLEAN)");
        sqLiteDatabase.execSQL("CREATE TABLE VEICULO (ID INTEGER PRIMARY KEY AUTOINCREMENT, RENAMED VARCHAR(10), STATUS BOOLEAN)");
        sqLiteDatabase.execSQL("CREATE TABLE GASTO (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(20), STATUS BOOLEAN)");
        sqLiteDatabase.execSQL("CREATE TABLE FRETE (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(20), STATUS BOOLEAN)");

        String sqlMovimentacao = "CREATE TABLE MOVIMENTACAO " +
                                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "VALOR DOUBLE, DATA DATE, " +
                "TIPO VARCHAR(2), " +
                "VEICULO_ID INTEGER, FRETE_ID INTEGER, GASTO_ID INTEGER, " +
                " FOREIGN KEY (VEICULO_ID) REFERENCES VEICULO (ID)," +
                " FOREIGN KEY (FRETE_ID) REFERENCES FRETE (ID)," +
                " FOREIGN KEY (GASTO_ID) REFERENCES GASTO (ID)" +
                ")";
        sqLiteDatabase.execSQL(sqlMovimentacao);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
    }
}
