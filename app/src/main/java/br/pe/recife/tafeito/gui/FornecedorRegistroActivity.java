package br.pe.recife.tafeito.gui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.pe.recife.tafeito.R;
import br.pe.recife.tafeito.util.MaskaraCpfCnpj;
import br.pe.recife.tafeito.util.MaskaraType;
import br.pe.recife.tafeito.util.Util;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class FornecedorRegistroActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    @InjectView(R.id.input_name) EditText _nameText;
    @InjectView(R.id.input_cnpj) EditText _cnpjText;
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fornecedor_registro);

        ButterKnife.inject(this);
        _cnpjText.addTextChangedListener(MaskaraCpfCnpj.insert(_cnpjText, MaskaraType.CNPJ));

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Voltar para a tela de Login
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Registro");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(FornecedorRegistroActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getApplicationContext().getResources().
                getText(R.string.registro_criando_conta).toString());
        progressDialog.show();

        String name = _nameText.getText().toString();
        String cnpj = _cnpjText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: ********* CADASTRAR O FORNECEDOR AQUI

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), getApplicationContext().getResources().
                getText(R.string.registro_falhou).toString(), Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String cnpj = _cnpjText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3 || name.length() > 50) {
            _nameText.setError(getApplicationContext().getResources().
                    getText(R.string.registro_nome_invalido).toString());
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (cnpj.isEmpty() || cnpj.length() != 14 || !Util.isCNPJ(cnpj)) {
            _cnpjText.setError(getApplicationContext().getResources().
                    getText(R.string.registro_cnpj_invalido).toString());
            valid = false;
        } else {
            _cnpjText.setError(null);
        }

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
}
