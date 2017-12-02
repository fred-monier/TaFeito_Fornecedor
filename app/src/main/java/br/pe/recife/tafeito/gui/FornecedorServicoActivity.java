package br.pe.recife.tafeito.gui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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
import br.pe.recife.tafeito.dao.ServicoCategoriaDAO;
import br.pe.recife.tafeito.excecao.InfraException;
import br.pe.recife.tafeito.excecao.NegocioException;
import br.pe.recife.tafeito.fachada.FachadaTaFeitoSQLite;
import br.pe.recife.tafeito.fachada.IFachadaTaFeito;
import br.pe.recife.tafeito.negocio.Autenticacao;
import br.pe.recife.tafeito.negocio.Servico;
import br.pe.recife.tafeito.negocio.ServicoCategoria;
import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.R.attr.data;

public class FornecedorServicoActivity extends AppCompatActivity
{
    public static final String AUTENTICACAO = "AUTENTICACAO";
    private Autenticacao autenticacao;
    private static final int REQUEST_SIGNUP = 0;

    private IFachadaTaFeito fachada;
    private List<String> nomes = new ArrayList<String>();
    private String categoria;

    @InjectView(R.id.spinner)    Spinner  _spinner;
    @InjectView(R.id.editText2)  EditText _nomeServico;
    @InjectView(R.id.editText3)  EditText _descricaoServico;
    @InjectView(R.id.button_add) Button   _button;
    /*
    *
    */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor_servico);

        ButterKnife.inject(this);

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
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fachada = FachadaTaFeitoSQLite.getInstancia(getApplicationContext());


        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        String nomeServico = _nomeServico.getText().toString();
        String descricaoServico = _descricaoServico.getText().toString();


        //Autenticar Fornecedor
        Autenticacao autenticacao = (Autenticacao) data.getSerializableExtra(AUTENTICACAO);

        try {
            autenticacao = fachada.listarServicoCategoria(autenticacao);
        } catch (Exception e) {
            autenticacao = null;
        }


    }
    /*
    *
    */
    private void signup()
    {
        _button.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(FornecedorServicoActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getApplicationContext().getResources().getText(R.string.registro_criando_conta).toString());
        progressDialog.show();

        String name = _nomeServico.getText().toString();
        String descricao = _descricaoServico.getText().toString();

        Servico servico = new Servico();
        servico.setNome(name);
        servico.getDescricao(descricao);
        servico.setFornecedor(autenticacao);
        servico.setServicoCategoria(categoria);

        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setIdAcesso();
        autenticacao.setToken();

        try {
            fachada.salvarServico(servico, getApplicationContext(), autenticacao);
        } catch (InfraException e) {
            e.printStackTrace();
        } catch (NegocioException e) {
            e.printStackTrace();
        }

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
