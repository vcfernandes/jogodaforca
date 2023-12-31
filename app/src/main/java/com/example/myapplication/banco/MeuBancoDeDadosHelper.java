package com.example.myapplication.banco;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MeuBancoDeDadosHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO_DE_DADOS = "meu_banco_de_dados.db";
    private static final int VERSAO_BANCO_DE_DADOS = 1;

    public MeuBancoDeDadosHelper(Context context) {
        super(context, NOME_BANCO_DE_DADOS, null, VERSAO_BANCO_DE_DADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crie a tabela para armazenar as palavras
        String queryCriacaoTabela = "CREATE TABLE IF NOT EXISTS tabela_palavras (_id INTEGER PRIMARY KEY AUTOINCREMENT, palavra TEXT)";
        db.execSQL(queryCriacaoTabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualize o banco de dados, se necessário
    }

    public List<String> getPalavras() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> palavras = new ArrayList<>();
        String query = "SELECT palavra FROM tabela_palavras";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String palavra = cursor.getString(0);
            palavras.add(palavra);
        }
        if (palavras.size() == 0) {
            palavras.add("teste");
        }
        return palavras;
    }

    public void inserirPalavra(String palavra) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO tabela_palavras (palavra) VALUES ('" + palavra + "')";
        db.execSQL(query);
    }

}