package br.pe.recife.tafeito.dao;

import java.util.List;

import br.pe.recife.tafeito.negocio.Fornecedor;

public class FornecedorDAO implements IDAO<Fornecedor> {

    @Override
    public void salvar(Fornecedor fornecedor) {

    }

    @Override
    public int excluir(Fornecedor fornecedor) {
        return 0;
    }

    @Override
    public List<Fornecedor> listar() {
        return null;
    }
}
