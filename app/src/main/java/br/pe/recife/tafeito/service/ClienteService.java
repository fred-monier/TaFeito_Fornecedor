package br.pe.recife.tafeito.service;

import android.content.Context;

import java.util.List;

import br.pe.recife.tafeito.R;
import br.pe.recife.tafeito.dao.ClienteDAO;

import br.pe.recife.tafeito.excecao.InfraException;
import br.pe.recife.tafeito.excecao.NegocioException;
import br.pe.recife.tafeito.negocio.Cliente;


/**
 * Created by HP on 21/11/2017.
 */

public class ClienteService {

    private static ClienteService instancia;
    private ClienteDAO clienteDao;
    //private UsuarioService usuarioService;
    private Context contexto;

    public static ClienteService getInstancia(Context context) {

        if (instancia == null) {
            instancia = new ClienteService(context);
        }

        return instancia;
    }

    private ClienteService(Context context) {
        this.clienteDao = ClienteDAO.getInstancia(context);
        //this.usuarioService = UsuarioService.getInstancia(context);
    }

    public void salvar(Cliente cliente) throws InfraException, NegocioException {

        if(cliente == null) {
            throw new NegocioException(contexto.getResources().getText(R.string.excecao_objeto_nulo).toString());
        }

        try {
            clienteDao.salvar(cliente);

        } catch (Exception e) {
            throw new InfraException(e.getMessage(), e);
        }
    }

    public Cliente consultar(long id) throws InfraException, NegocioException {

        Cliente res = null;

        try {

            res = clienteDao.consultar(id);

            if (res == null) {
                throw new NegocioException(contexto.getResources().getText(R.string.excecao_objeto_nao_encontrado).toString());
            }
        } catch (NegocioException e) {
            throw  e;
        } catch (Exception e) {
            throw new InfraException(e.getMessage(), e);
        }

        return res;

    }

    public int excluir(Cliente cliente) throws InfraException, NegocioException{

        int res = 0;

        try {

            res = clienteDao.excluir(cliente);

            if (res <= 0) {
                throw new NegocioException(contexto.getResources().getText(R.string.excecao_objeto_nao_excluido).toString());
            }
        } catch (NegocioException e) {
            throw  e;
        } catch (Exception e) {
            throw new InfraException(e.getMessage(), e);
        }

        return res;

    }

    public List<Cliente> listar() throws InfraException{

        try {
            return clienteDao.listar();
        }catch (Exception e){
            throw new InfraException(e.getMessage(),e);
        }
    }
}
