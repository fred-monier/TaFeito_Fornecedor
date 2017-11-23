package br.pe.recife.tafeito.fachada;

import android.content.Context;

import java.util.List;

import br.pe.recife.tafeito.excecao.InfraException;
import br.pe.recife.tafeito.excecao.NegocioException;
import br.pe.recife.tafeito.negocio.Agendamento;
import br.pe.recife.tafeito.negocio.Cliente;
import br.pe.recife.tafeito.negocio.Fornecedor;
import br.pe.recife.tafeito.negocio.Oferta;
import br.pe.recife.tafeito.negocio.Servico;
import br.pe.recife.tafeito.negocio.ServicoCategoria;
import br.pe.recife.tafeito.service.AgendamentoService;
import br.pe.recife.tafeito.service.ClienteService;
import br.pe.recife.tafeito.service.FornecedorService;
import br.pe.recife.tafeito.service.OfertaService;
import br.pe.recife.tafeito.service.ServicoCategoriaService;
import br.pe.recife.tafeito.service.ServicoService;

/**
 * Created by HP on 22/11/2017.
 */

public class FachadaTaFeitoSQLite implements IFachadaTaFeito {

    private static FachadaTaFeitoSQLite instancia;
    private AgendamentoService agendamentoService;
    private ClienteService clienteService;
    private FornecedorService fornecedorService;
    private OfertaService ofertaService;
    private ServicoCategoriaService servicoCategoriaService;
    private ServicoService servicoService;

    public static FachadaTaFeitoSQLite getInstancia(Context context){

        if(instancia != null) {
            instancia = new FachadaTaFeitoSQLite(context);
        }

        return instancia;
    }

    private FachadaTaFeitoSQLite(Context context){

        this.agendamentoService = AgendamentoService.getInstancia(context);
        this.clienteService = ClienteService.getInstancia(context);
        this.fornecedorService = FornecedorService.getInstancia(context);
        this.ofertaService = OfertaService.getInstancia(context);
        this.servicoCategoriaService = ServicoCategoriaService.getInstancia(context);
        this.servicoService = ServicoService.getInstancia(context);
    }

    @Override
    public void salvarAgendamento(Agendamento agendamento) throws InfraException, NegocioException {

        this.agendamentoService.salvar(agendamento);
    }

    @Override
    public Agendamento consultarAgendamento(long id) throws InfraException, NegocioException {

        return this.agendamentoService.consultar(id);

    }

    @Override
    public int excluirAgendamento(Agendamento agendamento) throws InfraException, NegocioException {

        return this.agendamentoService.excluir(agendamento);
    }

    @Override
    public List<Agendamento> listarAgendamento() throws InfraException, NegocioException {

        return this.agendamentoService.listar();
    }

    @Override
    public void salvarCliente(Cliente cliente) throws InfraException, NegocioException {

        this.clienteService.salvar(cliente);
    }

    @Override
    public Cliente consultarCliente(long id) throws InfraException, NegocioException {

        return this.clienteService.consultar(id);
    }

    @Override
    public int excluirCliente(Cliente cliente) throws InfraException, NegocioException {

        return this.clienteService.excluir(cliente);
    }

    @Override
    public List<Cliente> listarCliente() throws InfraException, NegocioException {

        return this.clienteService.listar();
    }

    @Override
    public void salvarFornecedor(Fornecedor fornecedor) throws InfraException, NegocioException {

        this.fornecedorService.salvar(fornecedor);
    }

    @Override
    public Fornecedor consultarFornecedor(long id) throws InfraException, NegocioException {

        return this.fornecedorService.consultar(id);
    }

    @Override
    public int excluirFornecedor(Fornecedor fornecedor) throws InfraException, NegocioException {

        return this.fornecedorService.excluir(fornecedor);
    }

    @Override
    public List<Fornecedor> listarFornecedor() throws InfraException, NegocioException {

        return this.fornecedorService.listar();
    }

    @Override
    public void salvarOferta(Oferta oferta) throws InfraException, NegocioException {

        this.ofertaService.salvar(oferta);
    }

    @Override
    public Oferta consultarOferta(long id) throws InfraException, NegocioException {

        return this.ofertaService.consultar(id);
    }

    @Override
    public int excluirOferta(Oferta oferta) throws InfraException, NegocioException {

        return this.ofertaService.excluir(oferta);
    }

    @Override
    public List<Oferta> listarOferta() throws InfraException, NegocioException {

        return this.ofertaService.listar();
    }

    @Override
    public void salvarServicoCategoria(ServicoCategoria servicoCategoria) throws InfraException, NegocioException {

        this.servicoCategoriaService.salvar(servicoCategoria);
    }

    @Override
    public ServicoCategoria consultarServicoCategoria(long id) throws InfraException, NegocioException {

        return this.servicoCategoriaService.consultar(id);
    }

    @Override
    public int excluirServicoCategoria(ServicoCategoria servicoCategoria) throws InfraException, NegocioException {

        return this.servicoCategoriaService.excluir(servicoCategoria);
    }

    @Override
    public List<ServicoCategoria> listarServicoCategoria() throws InfraException, NegocioException {

        return this.servicoCategoriaService.listar();
    }

    @Override
    public void salvarServico(Servico servico) throws InfraException, NegocioException {

        this.servicoService.salvar(servico);
    }

    @Override
    public Servico consultarServico(long id) throws InfraException, NegocioException {

        return this.servicoService.consultar(id);
    }

    @Override
    public int excluirServico(Servico servico) throws InfraException, NegocioException {

        return this.servicoService.excluir(servico);
    }

    @Override
    public List<Servico> listarServico() throws InfraException, NegocioException {

        return this.servicoService.listar();
    }
}
