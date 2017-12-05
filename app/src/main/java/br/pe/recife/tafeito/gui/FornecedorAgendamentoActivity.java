package br.pe.recife.tafeito.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

import br.pe.recife.tafeito.R;
import br.pe.recife.tafeito.excecao.InfraException;
import br.pe.recife.tafeito.fachada.FachadaTaFeitoSQLite;
import br.pe.recife.tafeito.fachada.IFachadaTaFeito;
import br.pe.recife.tafeito.negocio.Agendamento;
import br.pe.recife.tafeito.negocio.Autenticacao;

public class FornecedorAgendamentoActivity extends AppCompatActivity
{
    ListView _listViewAgenda;
    TextView _edit_nomeServico;
    TextView _edit_data;
    TextView _edit_hora;
    TextView _edit_nomeCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor_agendamento);

        _listViewAgenda    = (ListView) findViewById(R.id.listViewAgenda);
        _edit_nomeServico  = (EditText) findViewById(R.id.edit_nomeServico);
        _edit_data         = (EditText) findViewById(R.id.edit_data);
        _edit_hora         = (EditText) findViewById(R.id.edit_hora);
        _edit_nomeCliente  = (EditText) findViewById(R.id.edit_nomeCliente);

        IFachadaTaFeito fachada = FachadaTaFeitoSQLite.getInstancia(this.getApplicationContext());

        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setToken("123");

        try
        {
            List<Agendamento> listaAgenda = fachada.listarAgendamento(autenticacao);
            Iterator it = listaAgenda.iterator();

            while ( it.hasNext() )
            {
                Agendamento obj = (Agendamento) it.next();
            }
        } catch (InfraException e)
        {
            e.printStackTrace();
        }


    }

}
