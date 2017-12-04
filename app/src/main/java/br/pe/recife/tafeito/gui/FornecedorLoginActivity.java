package br.pe.recife.tafeito.gui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.pe.recife.tafeito.R;
import br.pe.recife.tafeito.fachada.FachadaTaFeitoSQLite;
import br.pe.recife.tafeito.fachada.IFachadaTaFeito;
import br.pe.recife.tafeito.negocio.Autenticacao;

public class FornecedorLoginActivity extends AppCompatActivity {

    public static final String AUTENTICACAO = "AUTENTICACAO";

    private static final int REQUEST_SIGNUP = 0;

    private IFachadaTaFeito fachada;

    EditText _emailText;
    EditText _passwordText;
    Button   _loginButton;
    TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fornecedor_login);

        _emailText    = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _loginButton  = (Button) findViewById(R.id.btn_login);
        _signupLink   = (TextView) findViewById(R.id.link_signup);

        fachada = FachadaTaFeitoSQLite.getInstancia(getApplicationContext());

        _loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Chama a tela de registro de fornecedor
                Intent intent = new Intent(getApplicationContext(), FornecedorRegistroActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                Autenticacao autenticacao = (Autenticacao) data.getSerializableExtra(AUTENTICACAO);
                goOn(autenticacao);
            }
        }
    }

    @Override
    public void onBackPressed() {

        //Impede o comando de voltar para uma activity anterior
        moveTaskToBack(true);
    }

    private void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(FornecedorLoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getApplicationContext().getResources().
                getText(R.string.login_autenticando).toString());
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        //Autenticar Fornecedor
        Autenticacao autenticacao;

        try {
            autenticacao = fachada.buscarPorLoginPorSenhaFornecedorAcesso(email, password, getApplicationContext());
        } catch (Exception e) {
            autenticacao = null;
        }

        if (autenticacao != null) {
            onLoginSuccess(autenticacao);
        } else {
            onLoginFailed();
        }
        progressDialog.dismiss();
    }

    private boolean validate() {

        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError(getApplicationContext().getResources().
                    getText(R.string.login_email_invalido).toString());
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError(getApplicationContext().getResources().
                    getText(R.string.login_senha_tamanho_invalido).toString());
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private void onLoginSuccess(Autenticacao autenticacao) {
        _loginButton.setEnabled(true);
        goOn(autenticacao);
    }

    private void onLoginFailed() {
        Toast.makeText(getBaseContext(), getApplicationContext().getResources().
                getText(R.string.login_falhou).toString(), Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    private void goOn(Autenticacao autenticacao) {

        //Chama a tela principal do fornecedor
        Intent intent = new Intent(getApplicationContext(), FornecedorPrincipalActivity.class);
        intent.putExtra(AUTENTICACAO, autenticacao);
        startActivity(intent);
    }

}


