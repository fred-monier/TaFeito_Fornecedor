package br.pe.recife.tafeito.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.pe.recife.tafeito.negocio.ServicoCategoria;
import br.pe.recife.tafeito.util.SQLHelperTaFeito;

public class ServicoCategoriaDAO implements IDAO<ServicoCategoria> {

    private static ServicoCategoriaDAO instancia;
    private SQLHelperTaFeito bd;

    public static ServicoCategoriaDAO getInstancia(Context context) {

        if (instancia == null) {
            instancia = new ServicoCategoriaDAO(context);
        }

        return instancia;
    }

    private ServicoCategoriaDAO(Context context) {
        this.bd = SQLHelperTaFeito.getInstancia(context);
    }

    private long inserir(ServicoCategoria servicoCategoria) {

        SQLiteDatabase db = bd.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_NOME, servicoCategoria.getNome());
        cv.put(SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_DESCRICAO, servicoCategoria.getDescricao());

        long id = db.insert(SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA, null, cv);

        if (id != -1) {
            servicoCategoria.setId(id);
        }

        db.close();

        return id;
    }

    private int atualizar(ServicoCategoria servicoCategoria) {

        SQLiteDatabase db = bd.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_NOME, servicoCategoria.getNome());
        cv.put(SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_DESCRICAO, servicoCategoria.getDescricao());

        int linhasAlteradas = db.update(SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA, cv,
                SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_ID + " = ?",
                new String[]{String.valueOf(servicoCategoria.getId())});
        db.close();

        return linhasAlteradas;
    }

    @Override
    public void salvar(ServicoCategoria servicoCategoria) {
        if (servicoCategoria.getId() == 0) {
            this.inserir(servicoCategoria);
        } else {
            this.atualizar(servicoCategoria);
        }
    }

    @Override
    public ServicoCategoria consultar(long id) {

        ServicoCategoria res = null;

        SQLiteDatabase db = bd.getReadableDatabase();

        String sql = "SELECT * FROM " + SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA;

        sql = sql + " WHERE " + SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_ID + " = ?";
        String args[] = new String[]{"" + id + ""};

        Cursor cursor = db.rawQuery(sql, args);
        if (cursor.moveToNext()) {

            long idCol = cursor.getLong(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_ID));
            String nomeCol = cursor.getString(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_NOME));
            String descCol = cursor.getString(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_DESCRICAO));

            ServicoCategoria servicoCategoria = new ServicoCategoria();
            servicoCategoria.setId(idCol);
            servicoCategoria.setNome(nomeCol);
            servicoCategoria.setDescricao(descCol);

            res = servicoCategoria;
        }

        cursor.close();

        return res;

    }

    @Override
    public int excluir(ServicoCategoria servicoCategoria) {

        SQLiteDatabase db = bd.getWritableDatabase();

        int linhasExcluidas = db.delete(SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA,
                SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_ID + " = ?",
                new String[]{String.valueOf(servicoCategoria.getId())});
        db.close();

        return linhasExcluidas;
    }

    @Override
    public List<ServicoCategoria> listar() {

        List<ServicoCategoria> res = new ArrayList<ServicoCategoria>();

        SQLiteDatabase db = bd.getReadableDatabase();

        String sql = "SELECT * FROM " + SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA;

        //sql = sql + " WHERE " + SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_XXX + " = ?";
        //String args[] = new String[]{"" + "XXX" + ""};

        sql = sql + " ORDER BY " + SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_NOME;

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {

            long idCol = cursor.getLong(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_ID));
            String nomeCol = cursor.getString(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_NOME));
            String descCol = cursor.getString(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_SERVICO_CATEGORIA_COLUNA_DESCRICAO));

            ServicoCategoria servicoCategoria = new ServicoCategoria();
            servicoCategoria.setId(idCol);
            servicoCategoria.setNome(nomeCol);
            servicoCategoria.setDescricao(descCol);

            res.add(servicoCategoria);
        }

        cursor.close();

        return res;
    }
}
