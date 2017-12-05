package br.pe.recife.tafeito.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import br.pe.recife.tafeito.R;
import br.pe.recife.tafeito.fachada.FachadaTaFeitoSQLite;
import br.pe.recife.tafeito.fachada.IFachadaTaFeito;

public class FornecedorPrincipalActivity extends AppCompatActivity
{
    public static final String AUTENTICACAO = "AUTENTICACAO";
    private static final int REQUEST_SIGNUP = 0;
    private IFachadaTaFeito fachada;

    CardView cardView_oferta;
    CardView cardView_agenda;
    CardView cardView_servico;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor_principal);

        cardView_oferta  = (CardView)findViewById(R.id.card_view1) ;
        cardView_agenda  = (CardView)findViewById(R.id.card_view2) ;
        cardView_servico = (CardView)findViewById(R.id.card_view3) ;

        fachada = FachadaTaFeitoSQLite.getInstancia(getApplicationContext());

        //Oferta
        cardView_oferta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /* Chama á tela de cadastro de Oferta do fornecedor */
                Intent intent = new Intent(getApplicationContext(), FornecedorOfertaActivity.class);
               startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        //Agenda
        cardView_agenda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /* Chama á tela de agendamento de serviços do fornecedor com o Cliente*/
                 Intent intent = new Intent(getApplicationContext(), FornecedorAgendamentoActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        //Serviço
        cardView_servico.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Chama á tela cadastro de Serviço do fornecedor
                 Intent intent = new Intent(getApplicationContext(), FornecedorServicoActivity.class);
                  startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }
}
