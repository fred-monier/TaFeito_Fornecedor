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
import br.pe.recife.tafeito.negocio.Servico;

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

        try
        {
            List<Servico> listaServicos = fachada.listarServico(autenticacao);
            Iterator it = listaServicos.iterator();

            List<String> list = new ArrayList<String>();
            while (it.hasNext())
            {
                Servico obj = (Servico) it.next();
                list.add( obj.toString() );
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            _spinner.setAdapter(dataAdapter);

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        //Botão
        _button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int i = (int) _spinner.getSelectedItem();
            }
        });

    }
}
