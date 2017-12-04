package br.pe.recife.tafeito.gui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.pe.recife.tafeito.R;
import br.pe.recife.tafeito.fachada.FachadaTaFeitoSQLite;
import br.pe.recife.tafeito.fachada.IFachadaTaFeito;
import br.pe.recife.tafeito.negocio.Autenticacao;

public class FornecedorServicoActivity extends AppCompatActivity
{
    public static final String AUTENTICACAO = "AUTENTICACAO";
    private Autenticacao autenticacao;
    private static final int REQUEST_SIGNUP = 0;

    private IFachadaTaFeito fachada;
    private List<String> nomes = new ArrayList<String>();
    private String categoria;

    Spinner  _spinner  ;
    EditText _nomeServico ;
    EditText _descricaoServico ;
    Button   _button ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor_servico);

         _spinner          = (Spinner) findViewById(R.id.spinner);
         _nomeServico      = (EditText)findViewById(R.id.editText2) ;
        _descricaoServico  = (EditText)findViewById(R.id.editText3) ;
        _button           = (Button)findViewById(R.id.button_add);

        //Adicionando Nomes de Categoria de Serviços
        nomes.add("Barbeiro");
        nomes.add("Medico");
        nomes.add("Farmaceutico");

        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        _spinner.setAdapter(spinnerArrayAdapter);

        //Método do Spinner para capturar o item selecionado
        _spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                categoria = parent.getItemAtPosition(posicao).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {            }
        });

        fachada = FachadaTaFeitoSQLite.getInstancia(getApplicationContext());


        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        String nomeServico      = _nomeServico.getText().toString();
        String descricaoServico = _descricaoServico.getText().toString();

        autenticacao.setToken(AUTENTICACAO);
        try {
            //autenticacao = fachada.listarServicoCategoria(autenticacao);
        } catch (Exception e) {
            autenticacao = null;
        }


    }

    private void signup()
    {
        _button.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(FornecedorServicoActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getApplicationContext().getResources().getText(R.string.registro_criando_conta).toString());
        progressDialog.show();

        String name = _nomeServico.getText().toString();
        String descricao = _descricaoServico.getText().toString();



        Autenticacao autenticacao = new Autenticacao();
/*
        try
        {
           fachada.salvarServico(servico, getApplicationContext(), autenticacao);
        } catch (InfraException e) {
            e.printStackTrace();
        } catch (NegocioException e) {
            e.printStackTrace();
        }
*/
        if (autenticacao != null) {
            onSignupSuccess(autenticacao);
        } else {
            onSignupFailed(null);
        }

        progressDialog.dismiss();
    }
/*
*
*/
    private void onSignupSuccess(Autenticacao autenticacao)
    {
        _button.setEnabled(true);

        Intent devolve = getIntent();
        devolve.putExtra(FornecedorPrincipalActivity.AUTENTICACAO, autenticacao);
        setResult(RESULT_OK, devolve);
        finish();
    }
    /*
    *
    */
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
