package br.pe.recife.tafeito.service;

import android.content.Context;

import br.pe.recife.tafeito.R;
import br.pe.recife.tafeito.dao.AcessoDAO;
import br.pe.recife.tafeito.excecao.InfraException;
import br.pe.recife.tafeito.excecao.NegocioException;
import br.pe.recife.tafeito.negocio.Acesso;
import br.pe.recife.tafeito.negocio.Autenticacao;

public class AcessoService {

    private static AcessoService instancia;
    private AcessoDAO acessoDao;

    public static AcessoService getInstancia(Context context) {

        if (instancia == null) {
            instancia = new AcessoService(context);
        }

        return instancia;
    }

    private AcessoService(Context context) {
        this.acessoDao = AcessoDAO.getInstancia(context);
    }

    public void salvar(Acesso acesso, Context contexto) throws InfraException, NegocioException {

        if(acesso == null) {
            throw new NegocioException(contexto.getResources().getText(R.string.excecao_objeto_nulo).toString());
        }

        try {
            acessoDao.salvar(acesso);
        } catch (Exception e) {
            throw new InfraException(e.getMessage(), e);
        }
    }

    public Autenticacao buscarPorLoginPorSenha(String login, String senha, Context contexto) throws InfraException, NegocioException {

        Autenticacao res = null;

        try {

            long id = acessoDao.buscarPorLoginPorSenha(login, senha);

            if (id <= 0) {
                throw new NegocioException(contexto.getResources().getText(R.string.excecao_objeto_nao_encontrado).toString());
            }

            res = new Autenticacao();
            res.setIdAcesso(id);
            res.setToken("");

        } catch (NegocioException e) {
            throw  e;
        } catch (Exception e) {
            throw new InfraException(e.getMessage(), e);
        }

        return res;

    }

    public boolean existePorLogin(String login) throws InfraException {

        boolean res = false;

        try {

            res = acessoDao.existePorLogin(login);

        } catch (Exception e) {
            throw new InfraException(e.getMessage(), e);
        }

        return res;
    }
}
