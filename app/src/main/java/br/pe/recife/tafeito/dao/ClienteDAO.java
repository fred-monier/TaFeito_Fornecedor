package br.pe.recife.tafeito.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.pe.recife.tafeito.negocio.Cliente;
import br.pe.recife.tafeito.util.SQLHelperTaFeito;

public class ClienteDAO implements IDAO<Cliente> {

    private static ClienteDAO instancia;
    private SQLHelperTaFeito bd;

    public static ClienteDAO getInstancia(Context context) {
        ClienteDAO res;
        if (instancia == null) {
            res = new ClienteDAO(context);
        } else {
            res = instancia;
        }
        return instancia;
    }

    private ClienteDAO(Context context) {
        this.bd = SQLHelperTaFeito.getInstancia(context);
    }

    private long inserir(Cliente cliente) {

        SQLiteDatabase db = bd.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(SQLHelperTaFeito.TABELA_CLIENTE_COLUNA_CPF, cliente.getCpf());

        long id = db.insert(SQLHelperTaFeito.TABELA_CLIENTE, null, cv);

        if (id != -1) {
            cliente.setId(id);
        }

        db.close();

        return id;
    }

    private int atualizar(Cliente cliente) {

        SQLiteDatabase db = bd.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(SQLHelperTaFeito.TABELA_CLIENTE_COLUNA_CPF, cliente.getCpf());

        int linhasAlteradas = db.update(SQLHelperTaFeito.TABELA_CLIENTE, cv,
                SQLHelperTaFeito.TABELA_CLIENTE_COLUNA_ID + " = ?",
                new String[]{String.valueOf(cliente.getId())});
        db.close();

        return linhasAlteradas;
    }

    @Override
    public void salvar(Cliente cliente) {
        if (cliente.getId() == 0) {
            this.inserir(cliente);
        } else {
            this.atualizar(cliente);
        }
    }

    @Override
    public Cliente consultar(long id) {

        Cliente res = null;

        SQLiteDatabase db = bd.getReadableDatabase();

        String sql = "SELECT * FROM " + SQLHelperTaFeito.TABELA_CLIENTE;

        sql = sql + " WHERE " + SQLHelperTaFeito.TABELA_CLIENTE_COLUNA_ID + " = ?";
        String args[] = new String[]{"" + id + ""};

        Cursor cursor = db.rawQuery(sql, args);
        if (cursor.moveToNext()) {

            long idCol = cursor.getLong(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_CLIENTE_COLUNA_ID));
            String cpfCol = cursor.getString(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_CLIENTE_COLUNA_CPF));

            Cliente cliente = new Cliente();
            cliente.setId(idCol);
            cliente.setCpf(cpfCol);

            res = cliente;
        }

        cursor.close();

        return res;

    }

    @Override
    public int excluir(Cliente cliente) {

        SQLiteDatabase db = bd.getWritableDatabase();

        int linhasExcluidas = db.delete(SQLHelperTaFeito.TABELA_CLIENTE,
                SQLHelperTaFeito.TABELA_CLIENTE_COLUNA_ID + " = ?",
                new String[]{String.valueOf(cliente.getId())});
        db.close();

        return linhasExcluidas;
    }

    @Override
    public List<Cliente> listar() {

        List<Cliente> res = new ArrayList<Cliente>();

        SQLiteDatabase db = bd.getReadableDatabase();

        String sql = "SELECT * FROM " + SQLHelperTaFeito.TABELA_CLIENTE;

        //sql = sql + " WHERE " + SQLHelperTaFeito.TABELA_CLIENTE_COLUNA_XXX + " = ?";
        //String args[] = new String[]{"" + "XXX" + ""};

        sql = sql + " ORDER BY " + SQLHelperTaFeito.TABELA_CLIENTE_COLUNA_CPF;

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {

            long idCol = cursor.getLong(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_CLIENTE_COLUNA_ID));
            String cpfCol = cursor.getString(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_CLIENTE_COLUNA_CPF));

            Cliente cliente = new Cliente();
            cliente.setId(idCol);
            cliente.setCpf(cpfCol);

            res.add(cliente);
        }

        cursor.close();

        return res;
    }

}
