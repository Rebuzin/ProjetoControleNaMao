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
        sqLiteDatabase.execSQL("CREATE TABLE CREDITO (VALOR DOUBLE, DATA DATE, STATUS BOOLEAN)");
        sqLiteDatabase.execSQL("CREATE TABLE DEBITO (VALOR DOUBLE, DATA DATE, STATUS BOOLEAN)");
        sqLiteDatabase.execSQL("CREATE TABLE CAMINHAO (RENAMED VARCHAR(10), STATUS BOOLEAN)");
        sqLiteDatabase.execSQL("CREATE TABLE GASTO (NAME VARCHAR(20), STATUS BOOLEAN)");
        sqLiteDatabase.execSQL("CREATE TABLE FRETE (NAME VARCHAR(20), STATUS BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        sqLiteDatabase.execSQL("CREATE TABLE CREDITO (VALOR DOUBLE, DATA DATE, STATUS BOOLEAN)");
        sqLiteDatabase.execSQL("CREATE TABLE DEBITO (VALOR DOUBLE, DATA DATE, STATUS BOOLEAN)");
        sqLiteDatabase.execSQL("CREATE TABLE CAMINHAO (RENAMED VARCHAR(10), STATUS BOOLEAN)");
        sqLiteDatabase.execSQL("CREATE TABLE GASTO (NAME VARCHAR(20), STATUS BOOLEAN)");
        sqLiteDatabase.execSQL("CREATE TABLE FRETE (NAME VARCHAR(20), STATUS BOOLEAN)");
    }
}
