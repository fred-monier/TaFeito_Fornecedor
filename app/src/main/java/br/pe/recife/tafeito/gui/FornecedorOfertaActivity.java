package br.pe.recife.tafeito.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import br.pe.recife.tafeito.R;

public class FornecedorOfertaActivity extends AppCompatActivity
{
    Spinner _spinner;
    EditText _horaInicio;
    EditText _horaFim;
    EditText _dataInicio;
    EditText _dataFim;
    Button _button;

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
        _spinner    = (Spinner)  findViewById(R.id.spinner);

        //Lista de Serviços
        _spinner.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

            }
        });

        //Botão
        _button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

    }
}
