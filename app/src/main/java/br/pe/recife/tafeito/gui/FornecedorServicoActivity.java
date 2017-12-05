package br.pe.recife.tafeito.gui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.pe.recife.tafeito.R;
import br.pe.recife.tafeito.fachada.FachadaTaFeitoSQLite;
import br.pe.recife.tafeito.fachada.IFachadaTaFeito;
import br.pe.recife.tafeito.negocio.Autenticacao;
import br.pe.recife.tafeito.negocio.ServicoCategoria;

public class FornecedorServicoActivity extends AppCompatActivity
{
   private IFachadaTaFeito fachada;

    Spinner  _spinner;
    EditText _nomeServico ;
    EditText _descricaoServico ;
    Button   _button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor_servico);

        String nomeServico      = _nomeServico.getText().toString();
        String descricaoServico = _descricaoServico.getText().toString();

         _spinner          = (Spinner) findViewById(R.id.spinner_categoria);
         _nomeServico      = (EditText)findViewById(R.id.edit_nomeServico) ;
         _descricaoServico = (EditText)findViewById(R.id.edit_nomeServico) ;
         _button           = (Button)  findViewById(R.id.btn_cad_servico);

        IFachadaTaFeito fachada = FachadaTaFeitoSQLite.getInstancia( this.getApplicationContext() );

        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setToken("123");

        try
        {
            List<ServicoCategoria> listaServicoCategorias = fachada.listarServicoCategoria(autenticacao);
            Iterator it = listaServicoCategorias.iterator();

            List<String> list = new ArrayList<String>();

            while (it.hasNext())
            {
                ServicoCategoria obj = (ServicoCategoria) it.next();
                list.add( obj.toString() );
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            _spinner.setAdapter(dataAdapter);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        _button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int i = (int) _spinner.getSelectedItem();
                signup();

            }
        });

    }

    private void signup()
    {
        _button.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(FornecedorServicoActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getApplicationContext().getResources().getText(R.string.registro_criando_conta).toString());
        progressDialog.show();

        String name      = _nomeServico.getText().toString();
        String descricao = _descricaoServico.getText().toString();

        Autenticacao autenticacao = new Autenticacao();

        if (autenticacao != null)
        {
            onSignupSuccess(autenticacao);
        } else
        {
            onSignupFailed(null);
        }
        progressDialog.dismiss();
    }

    private void onSignupSuccess(Autenticacao autenticacao)
    {
        _button.setEnabled(true);

        Intent devolve = getIntent();
        devolve.putExtra(FornecedorPrincipalActivity.AUTENTICACAO, autenticacao);
        setResult(RESULT_OK, devolve);
        finish();
    }

    private void onSignupFailed(String message)
    {
        if (message == null)
        {
            message = getApplicationContext().getResources().getText(R.string.registro_falhou).toString();
        }
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();

        _button.setEnabled(true);
    }
}
