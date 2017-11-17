package br.pe.recife.tafeito.dao;

import java.util.List;

import br.pe.recife.tafeito.negocio.ServicoCategoria;

/**
 * Created by Frederico on 16/11/2017.
 */

public class ServicoCategoriaDAO implements IDAO<ServicoCategoria> {

    @Override
    public void salvar(ServicoCategoria entidade) {

    }

    @Override
    public int excluir(ServicoCategoria entidade) {
        return 0;
    }

    @Override
    public ServicoCategoria consultar(long id) {
        return null;
    }

    @Override
    public List<ServicoCategoria> listar() {
        return null;
    }
}
