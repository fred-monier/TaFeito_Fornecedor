package br.pe.recife.tafeito.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.pe.recife.tafeito.negocio.Fornecedor;
import br.pe.recife.tafeito.util.SQLHelperTaFeito;

public class FornecedorDAO implements IDAO<Fornecedor> {

    private static FornecedorDAO instancia;
    private SQLHelperTaFeito bd;

    public static FornecedorDAO getInstancia(Context context) {

        if (instancia == null) {
            instancia = new FornecedorDAO(context);
        }

        return instancia;
    }

    private FornecedorDAO(Context context) {
        this.bd = SQLHelperTaFeito.getInstancia(context);
    }

    private long inserir(Fornecedor fornecedor) {

        SQLiteDatabase db = bd.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(SQLHelperTaFeito.TABELA_FORNECEDOR_COLUNA_CNPJ, fornecedor.getCnpj());

        long id = db.insert(SQLHelperTaFeito.TABELA_FORNECEDOR, null, cv);

        if (id != -1) {
            fornecedor.setId(id);
        }

        db.close();

        return id;
    }

    private int atualizar(Fornecedor fornecedor) {

        SQLiteDatabase db = bd.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(SQLHelperTaFeito.TABELA_FORNECEDOR_COLUNA_CNPJ, fornecedor.getCnpj());

        int linhasAlteradas = db.update(SQLHelperTaFeito.TABELA_FORNECEDOR, cv,
                SQLHelperTaFeito.TABELA_FORNECEDOR_COLUNA_ID + " = ?",
                new String[]{String.valueOf(fornecedor.getId())});
        db.close();

        return linhasAlteradas;
    }

    @Override
    public void salvar(Fornecedor fornecedor) {
        if (fornecedor.getId() == 0) {
            this.inserir(fornecedor);
        } else {
            this.atualizar(fornecedor);
        }
    }

    @Override
    public Fornecedor consultar(long id) {

        Fornecedor res = null;

        SQLiteDatabase db = bd.getReadableDatabase();

        String sql = "SELECT * FROM " + SQLHelperTaFeito.TABELA_FORNECEDOR;

        sql = sql + " WHERE " + SQLHelperTaFeito.TABELA_FORNECEDOR_COLUNA_ID + " = ?";
        String args[] = new String[]{"" + id + ""};

        Cursor cursor = db.rawQuery(sql, args);
        if (cursor.moveToNext()) {

            long idCol = cursor.getLong(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_FORNECEDOR_COLUNA_ID));
            String cnpjCol = cursor.getString(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_FORNECEDOR_COLUNA_CNPJ));

            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(idCol);
            fornecedor.setCnpj(cnpjCol);

            res = fornecedor;
        }

        cursor.close();

        return res;

    }

    @Override
    public int excluir(Fornecedor fornecedor) {

        SQLiteDatabase db = bd.getWritableDatabase();

        int linhasExcluidas = db.delete(SQLHelperTaFeito.TABELA_FORNECEDOR,
                SQLHelperTaFeito.TABELA_FORNECEDOR_COLUNA_ID + " = ?",
                new String[]{String.valueOf(fornecedor.getId())});
        db.close();

        return linhasExcluidas;
    }

    @Override
    public List<Fornecedor> listar() {

        List<Fornecedor> res = new ArrayList<Fornecedor>();

        SQLiteDatabase db = bd.getReadableDatabase();

        String sql = "SELECT * FROM " + SQLHelperTaFeito.TABELA_FORNECEDOR;

        //sql = sql + " WHERE " + SQLHelperTaFeito.TABELA_FORNECEDOR_COLUNA_XXX + " = ?";
        //String args[] = new String[]{"" + "XXX" + ""};

        sql = sql + " ORDER BY " + SQLHelperTaFeito.TABELA_FORNECEDOR_COLUNA_CNPJ;

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {

            long idCol = cursor.getLong(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_FORNECEDOR_COLUNA_ID));
            String cnpjCol = cursor.getString(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_FORNECEDOR_COLUNA_CNPJ));

            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(idCol);
            fornecedor.setCnpj(cnpjCol);

            res.add(fornecedor);
        }

        cursor.close();

        return res;
    }
}
