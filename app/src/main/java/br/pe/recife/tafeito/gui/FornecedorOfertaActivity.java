package br.pe.recife.tafeito.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.pe.recife.tafeito.R;
import br.pe.recife.tafeito.fachada.FachadaTaFeitoSQLite;
import br.pe.recife.tafeito.fachada.IFachadaTaFeito;
import br.pe.recife.tafeito.negocio.Autenticacao;
import br.pe.recife.tafeito.negocio.ServicoCategoria;

public class FornecedorOfertaActivity extends AppCompatActivity
{
    Spinner  _spinner;
    EditText _horaInicio;
    EditText _horaFim;
    EditText _dataInicio;
    EditText _dataFim;
    Button   _button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor_oferta);

        _dataFim    = (EditText) findViewById(R.id.dataFim);
        _dataInicio = (EditText) findViewById(R.id.dataInicio);
        _horaFim    = (EditText) findViewById(R.id.horaFim);
        _horaInicio = (EditText) findViewById(R.id.horaInicio);
        _button     = (Button)   findViewById(R.id.btn_cad_oferta);
        _spinner    = (Spinner)  findViewById(R.id.spinnerServico);

        IFachadaTaFeito fachada = FachadaTaFeitoSQLite.getInstancia(this.getApplicationContext());

        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setToken("123");

        try {
            List<ServicoCategoria> listaServicoCategorias = fachada.listarServicoCategoria(autenticacao);
            Iterator it = listaServicoCategorias.iterator();

            List<String> list = new ArrayList<String>();

            List<ServicoCategoria> categoria = null;
            while (it.hasNext()) {
                ServicoCategoria obj = (ServicoCategoria) it.next();
                //carregar lista
                list.add(obj.toString());
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            _spinner.setAdapter(dataAdapter);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

/*
        _spinner.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //listagem de todos

              //List<ServicoCategoria> listaServicoCategorias = fachada.listarServicoCategoria(autenticacao);
            }
        });
*/

        //Botão
        _button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int i = (int) _spinner.getSelectedItem();
                System.out.println("SELECIONADO O ITEM: "+i);
            }
        });

    }
}
