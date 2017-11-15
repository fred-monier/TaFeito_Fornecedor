package br.pe.recife.tafeito.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.pe.recife.tafeito.negocio.Usuario;
import br.pe.recife.tafeito.util.SQLHelperTaFeito;
import br.pe.recife.tafeito.util.Util;

public class UsuarioDAO implements IDAO<Usuario> {

    private static UsuarioDAO instancia;
    private SQLHelperTaFeito bd;

    public static UsuarioDAO getInstancia(Context context) {
        UsuarioDAO res;
        if (instancia == null) {
            res = new UsuarioDAO(context);
        } else {
            res = instancia;
        }
        return instancia;
    }

    private UsuarioDAO(Context context) {
        this.bd = SQLHelperTaFeito.getInstancia(context);
    }

    private long inserir(Usuario usuario) {

        SQLiteDatabase db = bd.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(SQLHelperTaFeito.TABELA_USUARIO_COLUNA_HABILITADO, Util.valorInteiro(usuario.isHabilitado()));
        cv.put(SQLHelperTaFeito.TABELA_USUARIO_COLUNA_NOME, usuario.getNome());
        cv.put(SQLHelperTaFeito.TABELA_USUARIO_COLUNA_ENDERECO, usuario.getEndereco());

        long id = db.insert(SQLHelperTaFeito.TABELA_USUARIO, null, cv);

        if (id != -1) {
            usuario.setId(id);
        }

        db.close();

        return id;
    }

    private int atualizar(Usuario usuario) {

        SQLiteDatabase db = bd.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(SQLHelperTaFeito.TABELA_USUARIO_COLUNA_HABILITADO, Util.valorInteiro(usuario.isHabilitado()));
        cv.put(SQLHelperTaFeito.TABELA_USUARIO_COLUNA_NOME, usuario.getNome());
        cv.put(SQLHelperTaFeito.TABELA_USUARIO_COLUNA_ENDERECO, usuario.getEndereco());

        int linhasAlteradas = db.update(SQLHelperTaFeito.TABELA_USUARIO, cv,
                SQLHelperTaFeito.TABELA_USUARIO_COLUNA_ID + " = ?",
                new String[]{String.valueOf(usuario.getId())});
        db.close();

        return linhasAlteradas;
    }

    @Override
    public void salvar(Usuario usuario) {
        if (usuario.getId() == 0) {
            this.inserir(usuario);
        } else {
            this.atualizar(usuario);
        }
    }

    @Override
    public int excluir(Usuario usuario) {

        SQLiteDatabase db = bd.getWritableDatabase();

        int linhasExcluidas = db.delete(SQLHelperTaFeito.TABELA_USUARIO,
                SQLHelperTaFeito.TABELA_USUARIO_COLUNA_ID + " = ?",
                new String[]{String.valueOf(usuario.getId())});
        db.close();

        return linhasExcluidas;
    }

    @Override
    public List<Usuario> listar() {

        List<Usuario> res = new ArrayList<Usuario>();

        SQLiteDatabase db = bd.getReadableDatabase();

        String sql = "SELECT * FROM " + SQLHelperTaFeito.TABELA_USUARIO;

        //sql = sql + " WHERE " + SQLHelperTaFeito.TABELA_USUARIO_COLUNA_XXX + " = ?";
        //String args[] = new String[]{"" + "XXX" + ""};

        sql = sql + " ORDER BY " + SQLHelperTaFeito.TABELA_USUARIO_COLUNA_NOME;

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {

            long idCol = cursor.getLong(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_USUARIO_COLUNA_ID));
            int habCol = cursor.getInt(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_USUARIO_COLUNA_HABILITADO));
            String nomeCol = cursor.getString(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_USUARIO_COLUNA_NOME));
            String endCol = cursor.getString(cursor.getColumnIndex(SQLHelperTaFeito.TABELA_USUARIO_COLUNA_ENDERECO));

            Usuario usuario = new Usuario();
            usuario.setId(idCol);
            usuario.setHabilitado(Util.valorBooleano(habCol));
            usuario.setNome(nomeCol);
            usuario.setEndereco(endCol);

            res.add(usuario);
        }

        cursor.close();

        return res;
    }
}
