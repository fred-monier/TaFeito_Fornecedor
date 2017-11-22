package br.pe.recife.tafeito.fachada;

import android.support.annotation.BinderThread;
import android.support.v4.media.session.PlaybackStateCompat;

import java.util.List;

import br.pe.recife.tafeito.excecao.InfraException;
import br.pe.recife.tafeito.excecao.NegocioException;
import br.pe.recife.tafeito.negocio.Agendamento;
import br.pe.recife.tafeito.negocio.Cliente;
import br.pe.recife.tafeito.negocio.Fornecedor;
import br.pe.recife.tafeito.negocio.Oferta;
import br.pe.recife.tafeito.negocio.Servico;
import br.pe.recife.tafeito.negocio.ServicoCategoria;

public interface IFachadaTaFeito {

    //AgendamentoService
    void salvarAgendamento(Agendamento agendamento) throws InfraException,NegocioException;
    Agendamento consultarAgendamento (long id) throws InfraException,NegocioException;
    int excluirAgendamento(Agendamento agendamento) throws InfraException,NegocioException;
    List<Agendamento> listarAgendamento() throws InfraException,NegocioException;


    //ClienteService
    void salvarCliente(Cliente cliente) throws InfraException,NegocioException;
    Cliente consultarCliente  (long id) throws InfraException,NegocioException;
    int excluirCliente(Cliente cliente) throws InfraException,NegocioException;
    List<Cliente> listarCliente() throws InfraException,NegocioException;

    //FornecedorService
    void salvarFornecedor(Fornecedor fornecedor) throws InfraException,NegocioException;
    Fornecedor consultarFornecedor  (long id) throws InfraException,NegocioException;
    int excluirFornecedor(Fornecedor fornecedor) throws InfraException,NegocioException;
    List<Fornecedor> listarFornecedor() throws InfraException,NegocioException;


    //OfertaService
    void salvarOferta(Oferta oferta ) throws InfraException,NegocioException;
    Oferta consultarOferta  (long id) throws InfraException,NegocioException;
    int excluirOferta(Oferta oferta) throws InfraException,NegocioException;
    List<Oferta> listarOferta() throws InfraException,NegocioException;


    //ServicoCategoriaService
    void salvarServicoCategoria(ServicoCategoria servicoCategoria)throws InfraException, NegocioException;
    ServicoCategoria consultarServicoCategoria  (long id) throws InfraException,NegocioException;
    int excluirServicoCategoria(ServicoCategoria servicoCategoria) throws InfraException,NegocioException;
    List<ServicoCategoria> listarServicoCategoria() throws InfraException,NegocioException;


    //ServicoService
    void salvarServico(Servico servico)throws InfraException, NegocioException;
    Servico consultarServico  (long id) throws InfraException,NegocioException;
    int excluirServico(Servico servico) throws InfraException,NegocioException;
    List<Servico> listarServico() throws InfraException,NegocioException;



}
