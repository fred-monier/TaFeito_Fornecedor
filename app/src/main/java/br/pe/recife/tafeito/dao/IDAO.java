package br.pe.recife.tafeito.dao;

import java.util.List;

public interface IDAO<T> {

    void salvar(T entidade);
    int excluir(T entidade);
    List<T> listar();

}
